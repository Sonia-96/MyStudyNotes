# 1 Makefiles

## Compile & Link

1. compilers: g++, c++, clang, clang++

```bash
c++ *.cpp -o myProgram
```

## Make - Automatic Build

The command `make` is to check if the target file (executable) is newer than its dependencies. If not, it will recompile the changed files and the target file. By using `make`, we need not to recompile the whole program or manually find and recompile the files that have been changed, which are time-consuming for big projects.

Software tools for automatic build:

- gmake: Linux, Mac OS
- nmake: Windows

#### General Structure

Write the following things in the file named `Makefile`:

1. Header: comments start with `#` that describe the makefile

2. Rules: describe the targets and their dependencies, and the commands to execute

   ![image-20230110101221735](./assets/make_rules.png)

   - implicit rules / built-in generic rules: `%.o : %.c` is an implicit rule, we don't need to specify this.

3. Macros: varibales used in Makefile

   ```bash
   CXX=c++
   CFLAGS= -std=c++11
   CCSOURCE=${wildcard *.cpp}
   
   program: # TODO
   ```

   automatic variables: // TODO

# 2 C++ classes

## C++ refresh

1. `cerr`: output error messages

   - output error message to a file: `./myProgram > fileName`

   - cerr is not buffered, cout is buffered // TODO cerr vs. cout
2. use `==` to compare `string`, use `strcmp` to compare `char*`

   - // TODO expalnation
3. `extern`: `extern` keyword extends the function's visibility to the whole program
4. `virtual`: `virtual` keyword specifies the member functions that need to be overriden by subclasses
5. `=0`: add `=0` to the end of the function declaration means this function has no implementation and it should be implemented by its subclasses. For example, `virtual bool equal(Object* other) = 0`
6. `dynamic_cast<Type*> obj`: cast an object to Type // TODO need to learn 
   - https://www.geeksforgeeks.org/dynamic-_cast-in-cpp/
   - Why we add * after Type?
   - Down casting & up cacting ??
