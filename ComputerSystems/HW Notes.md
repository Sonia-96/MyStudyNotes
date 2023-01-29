# Disassembly Lab

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

# Assembly Programming

for `write(SYS_write, 1, "hello", 5)`,

the parameters stored in registers are: 

- `edi` = 1
- `rsi` = "hello"
- `edx` = 5



in main, call `add(1, 2)`, the parameters are stored in :

- `edi` = 1
- `esi` = 2



Bakup:

```assembly
mov edx, esi
mov rsi, rdi
mov rdi, 1
```

