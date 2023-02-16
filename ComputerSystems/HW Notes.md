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

for `write(SYS_write, 1, "hello", 5)`, the parameters stored in registers are: 

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

# Unix Shell

1. `const_cast()`: add / remove `cast` qualification of a parameter
   - more examples: https://www.geeksforgeeks.org/const_cast-in-c-type-casting-operators/
2. `int execvp(const char *file, char *const argv[]);` the second parameter should be `const char**`
3. `std::vector<T>::data()`: returns the pointer to the first element of the array, the return type is `T*`

## [I/O system calls](https://www.geeksforgeeks.org/input-output-system-calls-c-create-open-close-read-write/)

1. file descriptor: a nonnegative integer that uniquely identifies an open file or process. Some common file descriptors:

   - 0: stdin
   - 1: stdout
   - 2: stderr
2. `int dup2(int oldfd, int newfd);`: change the filedescriptor

   - for example, for the command `echo hello world > test.txt`, `oldfd` is the file descriptor of the file "test.txt", `newfd` is 1 (stdout). This will lead all the stdout statements be written to test.txt.

   - more examples: https://www.geeksforgeeks.org/dup-dup2-linux-system-call/
3. [open(2)](https://man7.org/linux/man-pages/man2/open.2.html#RETURN_VALUE): opens the file specified by the pathname. If the file does not exist, we can use flag `O_CREAT` to create the file, or -1 will be returned.
   - `int open(const char *pathname, int flags);`: on success, return the new file descriptor (a nonnegative integer). On error, return -1.

   - flags: The argument flags must include one of the following access modes: `O_RDONLY`, `O_WRONLY`, or `O_RDWR`.
     - read man page to know more flags
4. `int pipe(int fd[2])`: fd[0] - read, fd[1] -write
5. `int chdir(const char *path);`: changes the current working directory to the specified`path`
   - On success, zero is returned.  On error, -1 is returned

## Unix Commands

1. Linux I/O redirect operator:

      ```
      >   - redirect output stream to a file, eg >somefile (for stdout) or 2>somefile
      >|  - as above but overwrite the file even if the noclobber shell option is set
      >>  - append output stream to file
      <   - redirect input stream from file, n defaults to 0 for stdin
      <>  - open file for reading and writing on stdin
      >&  - redirect output stream to another stream (eg >&1) or close with - (eg 2>&-)
      <<  - here document - see http://en.wikipedia.org/wiki/Here_document
      <<- - here document with leading tabs removed.
      ```

2. I/O redirection & pipes: https://homepages.uc.edu/~thomam/Intro_Unix_Text/IO_Redir_Pipes.html
3. `head -n <N> <filename>`: display top N lines of the file. If `-n <N>` is missed, it will print top 10 lines by default.
