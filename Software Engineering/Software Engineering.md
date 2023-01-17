# 1 Makefiles

## 1.1 Compile & Link

1. compilers: g++, c++, clang, clang++

2. Commands:

   ```bash
   # compile all .cpp files to .o files
   c++ -std=c++11 -c *.cpp  
   # link all .o files to a program named myProgram
   c++ -o myProgram *.o
   # run the program
   ./myProgram
   ```

## 1.2 Makefile

The command `make` can check if the target file (executable) is newer than its dependencies. If not, it will recompile the changed files and the target file. By using `make`, we need not to recompile the whole program or manually find and recompile the changed files, which are time-consuming for big projects.

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

   ```makefile
   CXX=c++
   CFLAGS= -std=c++11
   CCSOURCE=${wildcard *.cpp}
   OBJS=$(patsubst %.cpp, %.o, $(CCSOURCE))
   ```

4. automatic variables: 

   - `$@`: target filename of the current rule
   - `$^`: the filenames of all the prerequisites, seperated by spaces
   - `$<`: first prerequisite filename in the list
   - `$?`: the names of all the prerequisites that are newer than the target, seperated by spaces

#### Example

```makefile
# Targets:
# clean deletes program and all .o files
# runProg runs the executable program
# printM prints the values of all macros

CXX=c++
CFLAGS= -std=c++11
CXXSOURCE=$(wildcard *.cpp)
HEADERS=$(wildcard *.h)
OBJS=$(patsubst %.cpp, %.o, $(CXXSOURCE))

.PHONY: clean runProg printM # .PHONY(not real) means these targets are not associated with files
program: $(OBJS)
	$(CXX) $(CFLAGS) $(OBJS) -o $@
runProg: program
	./$^
%.o: %.cpp # implicit rule
	$(CXX) $(CFLAGS) -c $<
%.o: %.h
clean:
	rm -rf *.o program
printM:
	@echo $(CXX) $(CFLAGS) $(CXXSOURCE) $(HEADERS) $(OBJS)
```



# 2 C++ classes

## 2.1 C++ refresh

### cerr

`cerr`: output error messages

- `std::cerr` vs. `std::cout`:
  - `cerr` is for error messages, `cout` is for normal output
  - `cerr` is unbuffered, which means it write out the error message immediately and it cannot store the error message to display it later

- redirect output to a file: `./myProgram > filename` [(ref)](https://askubuntu.com/questions/420981/how-do-i-save-terminal-output-to-a-file)
  - `> filename`: save stdout to a file

  - `2> filename`: save stderr to a file

  - `&> filename`: save stdout & stderr to a file

  - `./myProgram > cout.txt 2> cerr.txt`: run prog, save cout messages in `cout.txt` and error messages in `cerr.txt`

### strcmp

use `==` to compare `string`, use `strcmp` to compare `char*`

- `strcmp(a, b)` reutrns 0 if a equals b, a positive value if a > b, else a negative value

```c++
char string_a[] = "foo";
char string_b[] = "foo";
char *string_c = string_a;
std::cout << strcmp(string_a, string_b) << std::endl; // 0 - equal
std::cout << (string_a == string_b) << std::endl; // 0 - not equal (comparing the address of two pointers)
std::cout << (string_c == string_a) << std::endl; // 1 - equal
```

### extern

 `extern` keyword extends the function's visibility to the whole **program**

- `sum.h`: 

  ```c++
  int sum(int n);
  extern int array[];
  ```

- `sum.cpp`:

  ```c++
  #include "sum.h"
  int sum(int n) {
      int res = 0;
      for (int i = 0; i < n; i++) {
          res += array[i];
      }
      return res;
  }
  ```
  
- `main.cpp`: you don't need to include `sum.cpp` in this file

  ```c++
  #include "sum.h"
  int array[] = {1, 2, 3};
  int main(int argc, const char * argv[]) {
      std::cout << sum(3) << std::endl;
      return 0;
  }
  ```

If you don't use a headerfile. You want to include everything in `main.cpp`:

```c++
#include <iostream>

int array[] = {1, 2, 3};
int sum(int n);

int main(int argc, const char * argv[]) {
    std::cout << sum(3) << std::endl;
    return 0;
}

int sum(int n) {
    int res = 0;
    for (int i = 0; i < n; i++) {
        res += array[i];
    }
    return res;
}
```

Note, if you use `using namespace` before, the variable name `array` will be ambiguous, because there's a library named `std::array`.

### pointers & references

A **pointer** in C++ is a variable that holds the memory address of another variable.

A **reference** is an alias for an already existing variable. Once a reference is initialized to a variable, it cannot be changed to refer to another variable.

<img src="./assets/image-20230116213726723.png" alt="image-20230116213726723" style="zoom:67%;" />

## 2.2 Inheritance

1. syntax:`class derived-class: access-specifier base-class`

   - access-specifier: `public`, `protected`, or `private`. This specifies the type of inheritance.

   <img src="./assets/image-20230116203024251.png" alt="image-20230116203024251" style="zoom:80%;" />

   - if acess-specifier is not specified, then its private inheritance by default.

2. Multiple Inheritance: In C++, a class can inherit multiple base classes. But this is not allowed in Java. Syntax: 

   ```
   class derived-class: access-specifier baseA, access-specifier baseB....
   ```

3. A derived class inherits all base class methods with the following exceptions:

   - Constructors, destructors and copy constructors of the base class.
   - Overloaded operators of the base class.
   - The friend functions of the base class.

## 2.3 Abstract Class

An **abstract class** is the class designed to be specifically used as a base class (like an interface in Java). An abstract class contains at least one **pure virtual function** (function with `virtual` and  `=0`). 

- A class derived from an abstract class is still abstract unless you implement every pure virtual function in the derived class.

1. `virtual`: `virtual` keyword specifies the member functions in the base class that need to be overriden by subclasses [[ref]](https://www.geeksforgeeks.org/virtual-function-cpp/)

   - virtual functions can be implemented in the base class

   - Only virtual functions can be overriden by subclasses


   ```c++
   class Base {
   public:
       virtual void print() {
           cout << "print base class.\n";
       }
       
       void show() {
           cout << "show base class.\n";
       }
   };
   
   class Derived : public Base {
   public:
       void print() {
           cout << "print derived class.\n";
       }
       
       void show() {
           cout << "show derived class.\n";
       }
   };
   
   int main(int argc, const char * argv[]) {
       Base *base;
       Derived *derived = new Derived();
     	base = derived;
      	
       base->print(); // print derived class. (virtual function is binded at run time)
       base->show(); // show base class. (non-virtual function is binded at compile time)
       return 0;
   }
   ```

2. `=0`: a virtual function with `=0` in the end of its declaration is called pure virtual function. A pure virtual function has no implementation and should be implemented by its subclasses. For example, `virtual bool equal(Object* other) = 0`

3. It is not allowed to declare an object of an abstract class. But you can declare references or pointers to an abstract class. For example, for the base class `Shape` and subclass `Circle`:

   ```c++
   class Shape {
   public:
       virtual float getArea()=0;
   };
   ```

   ```c++
   Shape shape1; // error: Variable type "Shape" is an abstract class
   
   Shape *shape2; // works
   shape2 = new Circle(5);
   ```

## 2.4 Type Conversion & Casting

### Type Conversion

In type conversion (also called **implicit type conversion**), a data type is automatically converted to another type at the **compiler time**. (See detailed explanation in the part 2.3 of my C++ Notes)

### Type Casting

In type casting (also called **explicit type conversion**): a date type is converted to another type by the **programmer**. In C++, this can be done in two ways:

1. Assignment:  `(type) expression`

1. casting operator:

   - `dynamic_cast<new_type>(expression)`: safe **downcasting** at run time. To work on [dynamic_cast](https://en.cppreference.com/w/cpp/language/dynamic_cast), there must be at aleast one virtual function in the base class.

     - If the cast is successful, `dynamic_cast` returns a value of type *new-type*. If the cast fails and *new-type* is a pointer type, it returns a null pointer of that type. If the cast fails and *new-type* is a reference type, it throws an exception that matches a handler of type [std::bad_cast](https://en.cppreference.com/w/cpp/types/bad_cast).
     - downcasting: casting a base class pointer to a derived class pointer. 

     ```c++
     Shape shape;
     Circle *circle = dynamic_cast<Shape*>(shape);
     ```

     

   - [static_cast](https://www.geeksforgeeks.org/static_cast-in-c-type-casting-operators/): a compile-time cast // TODO

   - const_cast // TODO

   - reinterpret_cast / TODO
