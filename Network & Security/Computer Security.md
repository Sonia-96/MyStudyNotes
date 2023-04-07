# 1 Buffer Overflow

1. code:
   
   - // c code
   
   - Diff between call & jump: call will return an address (then the process can jump to that address)
   - solutions:
     - use safe API 
       - in c++, we use string. 2 pros:
         - string can grow
         - string store its chars on heap. 
           - heap overflow is not as serious as buffer overflow because we don't modify the return address
     - use safe API bounds checking
       - `fgets()`

2. Shellcode (不太懂)
   - for example: `execve(“/bin/sh”, [“/bin/sh”], NULL);`
   - syscall (copy the syscall number of `execve` number into rax)

3. Compiler / OS mitigations
   - stack canaries: stack check guard (___stack_chk_guard)
   - make stacks grow up 
   - address space layout randomization (ASLR)
   - separate code stack and data stack
   - write or execute: the stack is writable is not executable, so jumping to the shell code will get a seg fault
   - BSD "pledge"

## Low Level Tools

1. `c++filt __ZNSt3__14coutE`
2. `otool`
   - `-tV`: disassemble
   - `-p`

3. Lldb

// TODO: read the assembly code, preparing for the lab on Friday. Know how to draw the stack picture. 

# 2 Code Injection - SQL

Solutions:

1. Set DB permissions
2. Restrict sqlQuery function to run only one statement
3. Sanitize user input:
   - remove `;`, `'`, `"`, `--`
   - Escape: `'` -> `\'`
4. modern best practice: 

# 3 Return Oriented Programming

an executable code can't be writable

x86: variable length instructions

ret instruction is only one byte, e.g., ret 0xC3

return instruction is usefuly for attacker because we can know the return address (后面补充). rsp += 8

attacker can change the return address, then change the control flow

"return to libc": overwrite return address to return in the middle of a syscall wrapper like exec (?)

ASLR: address space layout randomization. 

> **Address space layout randomization** (ASLR) is a memory-protection process for operating systems (OSes) that guards against buffer-overflow attacks by randomizing the location where system executables are loaded into memory.



Attacker: looks for C3 bytes while **compiling** so they know all return addresses. Look back from `ret` they can know each stack contents. (? 理解不一定正确)



**mprotect** - syscall to change page permissions

A typical attack:

ROP chain: https://www.ired.team/offensive-security/code-injection-process-injection/binary-exploitation/rop-chaining-return-oriented-programming



solutions:

1. reduce the number of useful gadgets
   - replace all C3 bytes that are not return (//TODO why this helps?)
   - try to add "poison" before normal C3 instructions
2. ASLR

# 4 Side-channel Attack

side-channel attack: attacks that are not caused by bugs in code, but

## Example 1: check password

e.g. checkpassword: 

<img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230331094519625.png" alt="image-20230331094519625" style="zoom:30%;" />

Attackers can use timer to figure out the password. The longer the runtime is, the more correct the provided password is.

Improvement1: return true/false in the end

```java
check_password_v2(String provided, String expected) {
  bool ret = true;
  if (provided.length) // TODO
}
```

improvement2: check hash. the timing won't leak any important information.

## Example 2: fastModPow

k here is probably the private key. Since the key length is fixed, the number of the bit 1 can affect the actual running time.

<img src="./assets/image-20230331095700062.png" alt="image-20230331095700062" style="zoom:50%;" />

Solution 1: add `else acc *= 1` after the if. Serious problem: `acc *= 1` and `acc *=x` has different power usage. But this requires physical access for the attacker, which is difficult to implement.

## Other measurements

1. EM([Electromagnetic Analysis](https://www.sciencedirect.com/topics/engineering/electromagnetic-analysis)) Analysis

2. Acoustices (?)

3. Differential fault analysis

   > **Differential fault analysis** (DFA) is a type of active [side-channel attack](https://en.wikipedia.org/wiki/Side-channel_attack) in the field of [cryptography](https://en.wikipedia.org/wiki/Cryptography), specifically [cryptanalysis](https://en.wikipedia.org/wiki/Cryptanalysis). The principle is to induce *faults*—unexpected environmental conditions—into cryptographic operations to reveal their internal states. (Wikipedia)

## Data Remanence

DoEncryptionStuff

even after the function returns, the private key bytes are still on the stack

Solution: zero out the key bytes. But the compiler sees that you won't use this memory space anymore so they might not do this. 

(dead store elimination)

Solution: zero out the key bytes while the compiler won't ompimize it away (really hard!)

Mmap returns zeroed pages

# Meltdown / Spectre

Knowledge included: page table, virtual memory, caches, kernel, concurrency, CPU Architecture

## Speculative Execution

The CPU guesses which way the branch will go, then starts on the expected branch instruction before the check is finished. If the CPU guesses right, the program will speed up. If it guesses wrong, the changes are reverted and the results are ignored. It behaves as if they were never executed, except they may modify hidden CPU state or read memory into cache. Based on these races, attackers can read sensitive information from your memory.

- **meltdown**: exploits speculative execution around **memory accesses**
- **spectre**: exploits speculative execution around **branch prediction**

## Meltdown

1. Speculative execution will load some data into cache when they read it from RAM. For example, given following registers:

   - rcx: a pointer to the data we can't access (page that is not readable)
   - rbx: a "probe array" which has 256 entries, each entry is one page. We don't care about the data in this array, but care about which page is in cache, which is in RAM. 

   ```assembly
   mov al, byte[rcx] # a = *rcx. move one byte from rcx to the register rax. we'll get seg fault once the page table is checked. This is a slow check, so it will take a while
   shl rax, Oxc # shift left. a << 12 = a * 2 ** 12 = a * 4096 (page size)
   mov rbx, [rbx + rax] # rbx = bx[a]. bx[a] will be copied to cache (why???)
   ```

   Note, `al` is the least significant byte of the `rax` register.

   It's very easy to catch a seg fault and let your program keep going. 

   Before attack, make sure no pages of rbx array are in cache. After attack, there should be exactly one page in cache, and the index should be the byte we read from protected memory.

   How to figure out which page is in cache?  We can use a timing program to measure the time (**timing side channel**) of reading each page. The fastest time was the one in cache. The index is the byte we read before.

   finally, we can read the memory that we don't have permissions. 

2. why this is so bad: we have virtual address space, and it's 2 ** 48 bits per page (very big) // TODO not quite understand

   - Map kernal memory into each process, so syscall doesn't have to switch page tables (marked as not readable, writable, and executable), so they will be a lot faster
   - Kernel keeps all physical memory mapped into its address space

3. Fixes: Kernel page table isolation

   - Main idea: don't map the kernal into user processes. But how can we do this without making everything slower?
   - Solution: CPUs added PID field to TLB entries (stores virtual address to physical address mappings) , when we switch to a new process, all these mappings are invalid, because each process has different mappings. This makes context switch faster. Kernel gets its own set of TLB entries

## Spectre

Spectre also uses the same trick, but instead of reading protected memory, it tres to circumvent bounds checks. For example, for the fowlloing code snippets:

```c++
if (index > array.length) {
  throw an exception
}
```

Spectre will make bounds check as slow as possible, so it can read out-of-bound memory and copy it into the cache before the program crashes.

# 5 Denial of Service

We've talked about: the ways to make a program to do waht they shouldn't:

- code injection: use user input as code
- stack smashing: change control flow
- side-channel attack: leak data

1. DoS: get the program to not do what it should. Usually we just overwhelm the system with requests to block legitimate requests

2. fun DoS: "fork bomb". see the following shell scripts:

   <img src="/Users/sonia/Documents/CSStudy/MyStudyNotes/Network & Security/assets/image-20230405094304268.png" alt="image-20230405094304268" style="zoom:30%;" />

   ```c++
   while (true) {
   	fork(); // PID is 16 bits, may be running out quickly
   }
   ```

   Problems:

   - running out of PID
   - scheduler's process collection get "full" (it's double linked list, but will be really big then get slow)

3. Network DoS Attacks

   - `ping` : generate pure network layer data, won't use transport layer. 
   - If I want to use `ping` to overwhelm the google server, there are 2 issues:
     - google's server is more powerful than my laptop
     - google will respond with the same amount of pongs which will overwhelm my laptop
   - Solution: 
     - use a bunch of attackers. We call this **bot net**.
     - IP sporting: put a fake src address in the IP header

4. TCP SYN Spooting

   - when a client send SYN, the server will allocate a receive buffer
   - if a client only send SYNs but won't respond, they will quickoy run out of the server's resources // TODO review ben's explaination

5. App Layer: HTTP specific attack

   pseduocode to parse the headers:

   ```java
   while (nextLine is not empty) {
     parse this line
   }
   ```

   If there's no empty line, the server will be block.

   Server's solution:

   - add timeout for each request -- a smart client can resend before the timeout
   - Slowloris: a kind of attack. makes up + sends HTTP headers periodically. This may exhausts thread. (server has one thread for each connection)

6. App Layer: DNS + indirection

   - since UDP is not connection based, make DNS request, spoot my IP to the victim's IP in the src field. Then traffic is amplified.
   - Memcached ? // TODO review video
   - example: 2018 GitHub ddos attack -- an advertising for the attacker's bot net https://www.cloudflare.com/learning/ddos/famous-ddos-attacks/#:~:text=The%20February%202018%20GitHub%20DDoS,of%20126.9%20million%20per%20second.
   - controlling botnets: IRC (?)

## Defense

1. buy more computers
2. fight IP spooting
3. traffic classification: legitimate vs. malicious -> super hard!!
4. TCP SYN spoofies:
   - delay allocation of buffers (SYN Cookie???)
5. Rate limiting
