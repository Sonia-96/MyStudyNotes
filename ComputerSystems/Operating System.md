# 1 Intro

1. Operating System: an interface between users and hardware. "The operating system is just another program."
2. What we will learn in this course:
   - Concurrency & concurrent proramming
   - Resource management
   - Performance engineering
   - Security policy design & enforcement
   - Enduring ideas
   - Interface design

# 2 CPU Architecture

## Microprocessor

1. Microprocessor: microprocessor is the heart of every CPU. It is a circuit of transistors and other electrical components on a chip that can process programs, remember information, of calculate things

2. Design Objectives:

   - maximize performance
     - minimize latency: complete individual instructions in small number of **clock cycle**
       - clock cycle: the amount of time between two pulses of an oscillator
       - clock speed: the number of pulses per second. The clock speed is measured in Hz, typically either MHz or GHz. Generally speacking, the higher the clock speed, the computer processor will be able to process information.
     - maximize **throughput**: increase instructions per second (IPS) 
   - maximize productivity: interface provided might be easy to program for
   - Optimize power usage

3. Design Constraints

   - power consumed
   - Cost
   - area occupied

   - **Backward compatibility**
   - Fast design & production time
   - security, scalability, reliability

4. Important factors for different microprocessor markets

   - Desktop: cost, backward compatibility
   - Server: throuput
   - Embedded: cost, power consumption

## Von Neumann Architecture

<img src="./assets/Von-Neumann-Architecture-Diagram.jpg" alt="Von Neumann Architecture Diagram - Computer Science GCSE" style="zoom:80%;" />

### Components

#### CPU

1. **Arithmetic/Logic Unit (ALU)**: allows arithmetic (add, subtract, etc. ) and logic (AND, OR, NOT, etc.) operations to be carried out

2. :star:**Control Unit**: controls the operations like ALU, memory, and input/output devices

   - How does Control Unit work?

     a. fetch: ask the RAM for the instruction whose address is stored in IP

     b. execute: execute the instruction

     c. Repeat: add 1 to the address stored in IP, then go back to step a

   <img src="./assets/image-20230115113015708.png" alt="image-20230115113015708" style="zoom: 67%;" />

   - example: c <- a + b. instructions in this program:
     - IR: load `a` into register `r1` (load r1, c). IP:2005
     - IR: load `b` into register `r3` (load r3, b). IP: 2006
     - IR: r2 <- r1 + r3 (add r2, r1, r3). IP: 2007
     - IR: store `r2` into `c` (store c, r2). IP: 2008

3. **Registers**: high-speed **storage** areas in the CPU. The data processed by the CPU are fetched from the registers.

   - instruction register: hold the current instruction during processing
   - instruction pointer (IP): hold the address of the current instrution in RAM

#### Buses

A bus is like a highway of information, which is a simplified way for many devices to communicate to each other.

> [Buses](https://www.computerscience.gcse.guru/glossary/bus) are the means by which data is transmitted from one part of a computer to another, connecting all major internal components to the CPU and memory.

#### Memory Unit

Memory units are used to store data, instructions, and information. There are two major types of memoty units:

- **RAM** (Random Access Memory): RAM is used for temporaty storage of program data. Its data is lost when power is turned off.
- **ROM** (Read-Only Memory): ROM is read-only. It is used for permanent storage of data.

## Exercise

1. What is a microprocessor?
2. Draw the diagram of the von Neumann architecture, and explain the usage of each component.
3. Explain how does the CPU execute this simple program: `c <- a + b`

# 3 ISA & Assembly

specifying a program and its instructions
- highest level - High-level programming language, e.g., Python, Java, C++. Each statement can be translated into many instructions, e.g., c <- a + b
- middle level - Assembly language: specify each machine instruction in mnemonic form, e.g., load r1, A
- lowest level - machine language: specify each machine instruction in bits, e.g., 1101101000001110011

## ISA

Instruction Set Architecture (ISA) is a part of the processor that defined how the CPU is controled by the software. ISA acts as an interface between software and hardware. ISA provides the only way through which a user is able to interact with the hardware.

A device that executes the instructions defined by ISA, like a CPU, is called an implementation

1. There are two types of ISA in complexity, both of which are in vogue today: 
   - **Complex Instruction Set Computer** (CISC)
     - variable instruction sizes in RAM: instructions can have variable sizes, e.g., add can use 2 or 3 registers
     - semantically more useful
     - modern x86 architectures (such as Intel and AMD) use CISC ISA
   - **Reduced Instruction Set Computer** (RISC)
     - semantically more basic
     - simpler, faster, easier to design CPU
     - Intel CPU are RISC implementations

1. decoder: bridge the gap between CISC ISA and RISC implementation
   - decoder takes CISC instructions and reduce them down to RISC instructions

1. priviledge rings / CPU protection rings

   Computer operating systems provide different layers of access to resources. Each of these layers have different priviledges. We use "**protection rings**" term while mentioning this system. The rings are arranged in a hierachy from the most privileged (most trusted) to the least privileged (least trusted) layer. Protection rings are one of the key solutions for sharing resources and hardware.
   Most OS only use two of these: Kernel Mode (ring 0), Usere Mode (ring 3).

   <img src="./assets/1_rings3-1024x678.png" alt="1 rings3" style="zoom:50%;" />


## Assembly

### Instruction format

- Intel: `operation dest_operand, src_operand` (we use this)
- AMD: `operation src, dest`

### Operation

- load: load a value from RAM into a register
- load direct: put a fixed value directly into a specific register
- store: copy value from a specific register to RAM
  - `mov eax, ebx`
- Compare: if the value in the first register is larger than the value in the second register, put 0 in a special register `r0`
  - `cmp eax, 9`
- Jump: if the value in `r0` is 0, change instruction pointer to the value in a specific register
  - `jmp labelName`
- Branch: if the value in a specific regsiter is larger than that in another register, change instruction pointer to a specific value
- arithmetic: 
  - `add`: add the contents of two registers and put the result in a third register
    - add is a variable-sized instructions
  -  `sub`, `imul`, `inc`, `dec`, ...
- Logic:`and`, `or`,  `xor`, `not`, `shl`, `shr` (???)
  - `xor eax, eax`: zero out `eax`, much faster than moving 0 to `eax`

### Operands

1. Registers
   - general registers (in 16 bits); ax, bx, cx, dx
     - add a prefix if we want to look at more than 16 bits:
       - 64 bits: `r`. `rax`, `rbx`, ...
       - 32 bits: `e`
       - lower 8 bits: l
       - higher 8 bits: h
   
   - xmm0, xmm1, ... : floating point parameters
     
   - bp (base pointer / frame pointer): the start of the stack frame
   
   - sp (stack pointer): the top of the stack frame
   
   - si (source index), di (destination index)
   
2. memory access - `[]`

   - `mov eax, DWORD PTR [rcx+8]`: read 32 bits from the address in the square bracket, and store its value in the register eax
     - `DWORD PTR` is called "size directive" which defines how much to read. "DWORD" stands for "double word" and a WORD is 16 bits.

3. immediate values

   - an immediate value is a constant number embedded in an instruction, e.g., `mov cx, 16` in which 16 is an immediate value. (immediate value is not a memory location or register)

## Exercise

Write the following C++ program in ASM instructions:

```c++
int sum = 0;
for(int i = 0; i < 10; ++i){
	sum += i; 
}
```

instructions:

```assembly
xor eax, eax; // sum = 0
xor ebx, ebx; // i = 0
J1:
cmp ebx, 9;
jg J2;
add eax, ebx;
add ebx, 1;
jmp J1;
J2:
```

# 4 ASM Function Calls

x86 Assembly guide: https://www.cs.virginia.edu/~evans/cs216/guides/x86.html

## Application Binary Interface (ABI)

registers hold addresses in RAM

- first 6 int or pointer parameters must be placed in: `rdi`, `rsi`, `rdx`, `rcx`, `r8`, `r9`
- floating point parameters: xmm0, ..., xmm7
- int return value: `rax`
- callee-saved registers (preserved): register value remains the same after the function call,  `rbx`, `rbp`, r12-r15 (Q: not change after return?)
- caller-saved registers (temproraries): `r10`, `rsi`

## Managing the Stack

one prpogram has only one stack. Different programs have different memory space in computer.

1. Prologue - set up the stack
   - save `rbp` to the stack: `rbp` stores the address of the bottom of the stack, its value never changes
     - Backup `rbp` values: push the value into some register?
   - `rsp`: if we push something, `rsp` will decrease; pop, `rsp` increase
2. Epilogue - clean up the stack
3. push & pop
   - "push" stores a constant or 64-bit register out onto the stack. So "push \<stuff>" is equivalent to a "sub rsp, 8" and then "mov QWORD[rsp], \<stuff>".
   - "pop" retrieves the last value pushed from the stack. Everything you push, you MUST pop again afterwards, or your code will crash almost immediately!

## Disassembly Lab

1. login CADE:

   - from terminal: `ssh u1428723@lab1-17.eng.utah.edu`
   - `logout` to logout
2. copy file between local computer and remote server (`sftp` & `scp` command)

   - Run commands in local computer:

     - Copy a file from remote server to local computer: `scp u1428723@lab1-17.eng.utah.edu:<remote_path> <local_path>`
       - or you can first `sftp u1428723@lab1-17.eng.utah.edu`, then `get <remote_path> .`

     - Copy a file from local computer to the remote server: `scp <local_path> u1428723@lab1-17.eng.utah.edu:<remote_path>`
     - this requires password for the remote server

   - Run commands in remote server:

     - Copy a file from remote server to local computer: `scp <remote_path> sonia@127.0.01:<local_path>`
     - Copy a file from local computer to the remote server: `scp sonia@127.0.01:<local_path> <remote_path>`
     - this requires ssh password for the local computer
3. [Optimization options](https://developer.arm.com/documentation/100748/0618/Using-Common-Compiler-Options/Selecting-optimization-options) for assembly
   - `-O0`: no optimization
   - `O2`, `-O3`: faster performance
4. example:
   - `sub rsp, 16`: reserve 16 bits for the next function call 
   - `add rsp, 16`: the function call is ended, release the stack

# 5 ASM System Calls

1. system calls
   - In computing, a **system call** is the programatic way in whch a computer program requests a service (like I/O, reating new process, checking the time, etc.) from the operating system. (use API)
   - When a system call is made, the CPU switch to kernal mode to perform the task, then back to the user mode when it returns. Therefore, a system call can slow down your program.
2. Wake up system calls
   - traps
   - exceptions
   - Interrupts: usually relate to I/O 
     - OS uses the interrupt vector to store pointers to each interrupt handler. When an interrupt occurs, the CPU is switched into kernel mode and jumps to the appropriate interrupt handler code.
3. Anatomy of a system call
   - put syscall parameters in registers
   - put syscall numbers in registers
   - `syscall` ASM instruction
4. Example:
   - the assembly of `printf("%d", 1)`, the last line is `mov eax, 0`. because the number of this syscall is 0.
