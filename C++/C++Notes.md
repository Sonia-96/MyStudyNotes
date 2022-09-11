# 2 Variables

## 2.1 Hardware & Software

### Hardware

1. CPU: do math very fast, memory in MBs
2. Memory:
   - units: bits, bytes, …
   - RAM: where the data is stored. temporary, fast, GBs
     - when running programs, you are using RAM
   - SSD/ HDD: permanent storage, TBs

### Software

- Operating system
- programs
  - **compiler**: a program which transfer programs into CPU understandable instructions
  - programming language:
    - high level: C++, Java, Python
    - low level: assembly
    - lowest: machine code (used to control CPU)
  - programs consist:
    - data - variables
    - code - algorithms

## 2.2 Datatypes

### Basic types / built-in types

| type   | size (typical)    | example                                 |
| ------ | ----------------- | --------------------------------------- |
| char   | 1 byte (8 bits)   | 'f'                                     |
| int    | 4 bytes (32 bits) | 42                                      |
| float  | 4 bytes (32 bits) | 3.141592 (~ 7 digits precision)         |
| double | 8 bytes (64 bits) | 3.141592653589793(~16 digits precision) |
| bool   | 1 byte (8 bits)   | true / false                            |

#### int

int has various **qualifier**s, e.g., `signed`, `unsigned`, `short`, `long`.（qualifier：限定词，即int前面的词）

- unsigned int: nonnegative integers in the range [0, 2<sup>32</sup>]
- signed int: integers in the range [-2<sup>32</sup>, +2<sup>31</sup>]. If `int` has no qualifier, then it is signed by default.
- short int (int can be omitted): 16 bits (2 bytes)
- long int (int can be omitted): long int has greater than or equal to as many bits as an int

reference: https://en.cppreference.com/w/cpp/language/types

### Combinations

- arrays: lists of onely one type of data
- Structs/objects: groupings of many data types into a single variable

## 2.3 Type conversion & Casting

What to do if operands in an expression have different types? -- Convert them to the same type.

Note, the types of constants can be modified by adding a letter suffix if needed: U for unsigned, L for long, f for float. 

#### Type conversion

In type conversion, a data type is automatically converted to another type by the **compiler** at the compiler time. In this way, the bit representation of the original type will be changed to that of the new type. It should be noted that the destination data type cannot be smaller than the source data type, that's why it is also called *type promotion*.

  There are four common ways to change the bit representation during the type promotion:

  1. smaller signed int -> larger signed int: **sign extended** (the sign must be copied an appropriate times to fill in the additional bits.)

  2. smaller unsigned int -> larger unsigned int: zero extended

  3. longer int -> shorter int: the most significant bits will be thrown away. See the example below:

     ```c
     short int s;
     int x = 99999;
     s = x;
     print('%d\n', s) // -31073
     ```

     In this code, the resulted `s` is -31073. Why?

     - The binary representation of 99999 is 11000011010011111
     - Since the `short int` has only 16 bits, when the compiler convert `int` to `short`, it will throw away the bits other than the 16 least significant bits, resulting in 1000011010011111.
     - In two's compliment, '1000011010011111' is '0111100101100001', which is 31073 in decimal.
     - The most significant bit is the sign bit, which means this number is negative. Therefore, the resulted `s` is -31073.

  4. fully calculate what the representation of the value is in the new type. eg., int -> float

#### **Type casting**

In type casting, a data type is converted to another type by the **programmer** by using the casting operator during the program design. The destination data type may be smaller than the source type.

The syntax is as following:

```c
destination_datatype variable2 = (target_datatype) variable;
```

Note, the casting has very high **operating precedence**.

# 3 Control Flow

## 3.1 Conditional Statements

1. If statement
1. switch statement (not mentioned in class)

## 3.2 Loops
1. for loop

     use for loop when we know how many times we want the computer to repeat

     ```c++
     // print from 1 to 10
     for (int i = 1; i < 11; i++) {
       std::cout << i << " ";
     }
     
     // print from 10 to 1
     for (int i = 10; i > 0; i--) {
       std::cout << i << " ";
     }
     ```

2. while loop

     use while loop when we don't know how many times something will happen

     ```c++
     // string analyzer
     string s;
     cin >> s;
     while (s != "done") {
       // analyze s;
       std::cin >> s;
     }
     ```

3. do-while loop : a do-while loop is guaranteed to execute the body at lease once because it executes the body before checking the condition. (I recommend while loop than do-while loop. )

     ```c++
     i = 10;
     do {
       std::cout << i;
       i--;
     } while (i > 0)
     ```
# 4 Strings

A string is an array of chars. Its **capacity** is the size of the array. Its **size** is the number of chars in the string.

```c++
#include <string>
using namespace std;

string name = "Sonia";
string myString('a', 10); // create a string of length 10 filled with 'a'
```

Basic operations on a string:

- \<stringName\>.length()
- Compare two strings: <, >, ==, !=
- +: concatenates two strings
- \<stringName\>.find(substr, pos): finds the location of a substra starting from `pos`
- \<stringName\>.substr(pos, length)

# 5 Functions

## Scope

Scope is where the variable can be used.

```c++
// test 1 - statement scope
for (int i = 0; i < 5; i++) {
  
}
cout << i << endl; // what's the value of i?

int i = 0;
for (; i < 5; i++) {
  
}
cout << i << endl;

// test 2 - local scope
char firstLetter( string sentence ) { //sentence is function scope
  char c = sentence.front(); 
	return c; 
}
char theName = firstLetter("Amy");
cout << c << endl; // what's the value of c?
```

Read more: https://docs.microsoft.com/en-us/cpp/cpp/scope-visual-cpp?view=msvc-170

# 6 Vectors

## Compilation & Linking

### Header files - declaration files

- header files only contain function **signatures** (function name, parameter types, return types)

- header files can be included in .cpp files: 

  ```c++
  #include "<filename>.hpp"
  ```

  - `\#include “”` vs `#include <>`: `""` is for local files, `<>` is for system files

- **Include Guards**: to avoid include a header file twice. 

  ```c++
  #ifndef _MYFUNCSH_
  #define _MYFUNCSH_
  // functions declared here
  #ENDIF
  ```

​		Now, the new way to do it is  `#pragma once`. (`#` invokes the preprocessor)

### cpp files - Implementation files

.h and .cpp file come in pairs, excepted for main file and standard library files

### Commands

```bash
# compile all .cpp files to .o files
clang++ -std=c++11 -c *.cpp  
# link all .o files to a program named myProgram
clang++ -o myProgram *.o
```

## Vecotr

1. Create a vector:

   ```c++
   #include <vector>
   using namespace std;
   
   vector<float> grades; // create an empty vector
   vector<string> names(5); // a vector of 5 strings (with default values of "")
   vector<int> nums = {1, 2, 3, 4};
   ```

2. methods to use:

   - `.size()`
   - `.push_back()`
   - `.pop_back()`: remove the last element
   - `.front()`: return the first element
   - `.back()`

3. for-each loop

# 7 Structs

A struct can hold multiple datatypes. A struct instance is an object.

1. declare a struct in .hpp files

2. two ways to initialize a struct

   ```c++
   struct Student {
     string name;
     unsigned short age;
   }
   
   // option 1: dot notation
   Student ben;
   ben.name = "Ben";
   ben.age = 12;
   
   // option 2: curly braces
   Student ben {"Ben", 12};
   ```

# 8 References

- passed by value: the value of the parameter is copied into the function. Changing the parameter inside the function doesn't effect the original variable.
- passed by reference (use `&`): the parameter refers to the variable that was passed into the function. If the parameter's value is changed inside the function, the original variable will be changed too.
  - `const int& para1`: pass the integer `para1` into the function by reference, but the function cannot change its value

# 9 File I/O

Read and write data from files. Library to use: `fstream`

### Input stream

1. open a file: 

   ```c++
   #include <fstream>
   #include <cstdlib> // for exit() function
   
   using namespace std;
   
   string filename = "test.txt";
   ifstream fin(filename);
   if (fin.fail()) {
     cout << "Failed to open file: " << filename << endl;
     exit(1);
   }
   ```

2. read a file word by word

   ```c++
   vector<string> words;
   while (fin >> word) {
     words.push_back(word);
   }
   ```

3. read a file line by line

   ```c++
   string sentence;
   vector<string> sentences;
   while (getline(fin, sentence, '\n')) {
     sentences.push_back(sentence);
   }
   ```

4. reset file pointers:

   ```c++
   fin.clear(); // Clear any fail / EoF bits
   fin.seekg(0, ios::beg); // move file pointer to the beginning of the file
   fin.seekg(6, ios::beg); // move pointer to the 6th position from the beginning of the file
   ```

### Output Stream

1. create an output stream

   ```c++
   // if "test.txt" already exsits, it will be erased and overwritten
   ofstream fout("test.txt");
   // just append to the file
   ofstream fout("test.txt", ios_base::app);
   // write something to the file
   fout << "something";
   // force the buffer to be written into the file
   fout.flush();
   // .close() make sure all your data will be written into the file
   fout.close();
   ```

# 10 Pointers

## Array

Array is fixed-sized, and has no member functinos, e.g., .size(). 

1. create an array: `type arrayName[size]`

2. If your data is fixed size, use `std::array<type, size>` is better. This works just like a fix-sized vector.

   ```c++
   #include <array>
   using namespace std;
   
   array<int, 5> = {1, 2, 3, 4, 5};
   ```

## Pointers

   1. Arrays are **pointers**, which means it will be passed by reference into a function.

      - a pointer is a variable that holds the address of another value
      - pointer is a data type, not an integer
      - *pointer and reference are the same. pointer is an old C concept, reference is a C++ concept

      ```c++
      void myFunction( int array[3] ) { 
        array[0] = 0;
      	array[1] = 1;
      	array[2] = 2; 
      }
      
      int main() {
      	int a[3] = { 5, 5, 5 };
      	myFunction( a );
      	cout << a[0] << a[1] << a[2]; // 012
      }
      ```

2. use `->` to access a pointer of a struct's field:

   ```c++
   struct Point {
     int x, y;
   }
   
   Point point {1, 2};
   Point* pPoint = &point;
   cout << pPoint -> x ; // 1
   ```

3. Syntax:

   ```c++
   int* ptr;
   int x = 5;
   ptr = &x; // use & to get the address of x
   int y = *ptr; // *ptr is dereference. This line assign the value of x to y
   *ptr = 8; // change the value of x to 8
   ```

4. an array can be written as a pointer. For example, `int a[]` can be wrriten like `int*`, and this pointer points to the first elements of the array

   ```c++
   // The following 2 functions are the same
   void getSum(int* nums, int size);
   void getSum(int nums[], int size);
   ```

5. write swap() by using pointers

   ```c++
   void swap(int* a, int* b) {
     int temp = *a;
     *a = *b;
     *b = temp;
   }
   ```

6. print array

   ```c++
   void printArray(int* arr, int size) {
     for (int i = 0; i < size; i++) {
       cout << *(arr + i) << " ";
       // cout << *(arr + i * sizeof(int)) << " "; // only use this when the type is a struct
     }
   }
   ```

## Commandline Arguments

for `int main( int argc, char* argv[] ) `

- argc: argument count, which is the number of arguments
- argv: argument vector, which is a 2D array (each argument is an array of chars)
  - `char** argv`is the same as `char* argv[]` . so `argv` is a pointer to a 2D array
  - `argv[0]` is current path

# 11 Number Systems

## Base 2 (Binary)

1. numbers to know in binary

   - One: 1
   - Three: 11
   - Seven: 111
   - Fifteen: 1111

2. How many numbers can 8 bits store?

   - store 2<sup>8</sup> = 256 numbers
   - for `uint8_t` (unsigned int with 8 bits): 0 ~ 255
   - for `int8_t` (signed int with 8 bits): -128 ~ 127

3. Tell if a binary number is odd or even: if one's digit is 0 - even, else odd

   ```c++
   bool isOdd(int num) {
     if ((num & 1) == 1) {
       return true;
     }
     return false;
   }
   ```

## Base 16 (Hexadecimal)

4 bits for one hex digit

| hex digit | binary |
| --------- | ------ |
| 0         | 0000   |
| 1         | 0001   |
| 2         | 0010   |
| 3         | 0011   |
| 4         | 0100   |
| 5         | 0101   |
| 6         | 0110   |
| 7         | 0111   |
| 8         | 1000   |
| 9         | 1001   |
| A         | 1010   |
| B         | 1011   |
| C         | 1100   |
| D         | 1101   |
| E         | 1110   |
| F         | 1111   |

## Converting Between Bases

1. 10 in different number systems -- represents the base

   - in decimal: 10 = 10

   - in binary: 0b10 = 2

     - Note: 0 in front of the leftmost non-0 digit can be ignored. e.g., 

       - 0b0000000000000**1**0 = 0b10 = 0 *  2<sup>0</sup> + 1 * 2<sup>1</sup> + 0 + 0 +... = 3

       - 0b00100 = 0 *  2<sup>0</sup> + 0 * 2<sup>1</sup> + 1 * 2<sup>2</sup> + 0 + 0 = 4

   - in hexadecimal: 0x10 = 0 * 16<sup>0</sup> + 1 * 16 <sup>1</sup> = 16

2. Hex -> Binary

   Replace each hex digit with 4 binary digits. e.g. 0x1A = 0001 1010

3. Binary -> Hex

   Group the binary into groups of 4, then convert it to hex. e.g. 0001 1110 = 0x1E

4. Decimal -> Binary / Hex

   Divide the number by 2 (or 16) repeatedly, then keep track of remainders in reverse order. e.g., 

   ![210-2](./assets/Dec2Bin.png)

# 12 Number Representations

sign and magnitude representation

## One's complement

1. In one's complement, negative number starts with `1` and flip other bits of corresponding positive number

   For example, how to represent -2:

   - write down 2 in binary using 4 bits: 0010

   - flip all bits: 1101. This is treated as -2
   
   How do we know 1101 is -2?

    - Step 1: the **most significant bit (MSB)** is 1, so this number is negative.
    - Step 2: flip all bits, it's 0010, it's 2.
    - Combine the result of step 1 and step 2, the result is -2.
   
1. 3 bits in one's compliment

   | binary | Decimal |
   | ------ | ------- |
   | 000    | 0       |
   | 001    | 1       |
   | 010    |         |
   |        |         |
   |        |         |
   |        |         |
   |        |         |
   |        |         |

	From above table, we can see that in 1's compliment, negative number + positive number = 1111, which is -0 in decimal. This is weird, since 0 should not have a sign! In addition, we already have 0000 for 0. Using 1111 to represent -0 waste a number!

## Two's Complement

1. Signed: if a number has 3 bits, the first digit is singed digit, 1 - negative, 0 - positive
   - how many numbers can a signed 3 bits represent? 2 ** 3 = 8
   - for a n-digit binary number (no matter signed or **unsigned**), it can represent 2 ** n numbers

1. All modern machines use 2's complement. By using two's complement, we can have an extra negative number. (save 1111)

   For example, the range of signed 3 bits:

   | binary | Decimal |
   | ------ | ------- |
   | 000    | 0       |
   | 001    | 1       |
   | 010    | 2       |
   | 011    | 3       |
   | 100    | -4      |
   | 101    | -3      |
   | 110    | -2      |
   | 111    | -1      |

101= -1 *2<sup>2</sup>+ 0 * 2<sup>1</sup> + 1 * 2<sup>0</sup> =-3		

​	- 3bits represent 8 numbers: non-negative: [0, 3]; negative: [-4, -1]

	- Positive number + negative number = 001 + 111 = 0b000 = 0

2. How to represent -2 in 2's complement

   - 2: 010

   - flip bits: 101

   - add one: 110
3. How do we know 0b111 is -1?
   - Method1: 0b111 = -1 * 2<sup>2</sup> + 1 * 2<sup>1</sup>  + 1 * 2<sup>0</sup>  = -4 + 2 + 1 = -1
   - Method2: 
     - MSB is 1 -> negative number
     - 0b111 - 1 = 0b110
     - Flip all bits: 0b001 = 1
     - Result: -1

4. How to convert negative number to positive number:

   For example, -2 : 0b110 

   - Step 1: flip all bits -> 0b001（相当于-1）

   - Step 2: add 1 -> 0b110 

	Note, the **most negative number** cannot be negated in 2's complement. This can cause all sorts of errors if you're not careful!!!

** Floats are always signed.

## Floating Point Numbers

### Binary Scientific Notation

In computers, floating point numbers are stored in the format of **binary scientific notation**:
$$
(-1)^s\times m\times2^e
$$
, in which s is **sign bit** (When s = 1, the number is negative. When s = 0, the number is positive.), m is the **mantissa** (/mæn'tɪsə/) (i.e. significand), and e is the exponent. 

| type   | size                                                   | precision        |
| ------ | ------------------------------------------------------ | ---------------- |
| float  | 32 bits (1 for sign, 8 for exponent, 23 for mantissa)  | single precision |
| double | 64 bits (1 for sign, 11 for exponent, 52 for mantissa) | double precision |

(In **normalized** binary scientific notation, the firtst position of mantissa is always 1, so we don't need to store it.

The picture below can help you better understand the concept "precision":

<img src="./assets/PAab4ImjEeeMTA56ZcIfig_f64d6390d81ccaf96818493f85e2bca1_3.2.3B-float-double.png" alt="img" style="zoom:150%;" />

Note, the default print setting for floats and doubles is to **print up to six decimal places**.

### Special Floating Point Numbers

- NaN: not a number
- +inf, -inf
- Floating numbers are approximated. Many numbers cannot be represented in binary, e.g., 0.1 (???) // TODO
- use `abs(A-B) < tolerance` to check if two floating numbers are equal

# 13 Bitwise Operations

1. If you are working with bits, it's better to use types like `int8_t`, `uint16_t`, etc., -- preferred an unsigned type.

2. **bitwise operator**:

   - `|`: if either of operands are true, the result is true.
   - `&`: only when both operands are true, the result is true.
   - `^` (exclusive or, Xor): if either of operands are  true, the result is true. But if both operands are the same, the result is false.
     - 0 ^ 0 = 0
     - 0 ^ 1 = 1
     - 1 ^ 0 = 1
     - 1 ^ 1 = 0
   - `~`: bitwise not, which is used to flip the bit

3. **Bit shifting**:

   - right shifting ( `>>`): shift bits to the right and fill in 0s to the left
     - (int8_t) 0110 10<u>10</u> >> 2 = <u>00</u>01 1010
     - right shift by 1  = divide the number by 2 in integer math
   - left shifting (`<<`): shift bits to the left and fill in 0s to the right
     - (int8_t) <u>01</u>10 1010 << 2 = 1010 1000 = -88 (The number becomes **negative** because the MSB is 1)
     - left shift by 1 = multiply the number by 2 but may lost MSB

4. Boolean identifiers

   (x is a single bit)

   - `x | 1 == 1`
   - `x | 0 == x`
   - `x & 1 == x`
   - `x & 0 == 0`

   Q: how to get last 4 digits of  `int16_t x`?

   ```c++
   int low4BitsOfX = x & 0x000F
   ```

5. **Masking**

    think about what bits you want to change, then design a mask. For the bits you want to use, set the values of the mask as 1, otherwise 0. For example: 

   ```c++
   uint32_t x = 0xDEADBEEF;
   // how to remove everything but DE?
   uint32_t mask = 0xFF000000;
   x &= mask; // x = 0xDE000000
   // how to get just DE?
   x >> 24; 
   ```

6. **Sign extension**

   When you assign a smaller signed number to a larger signed number, the new bits will copy the signed bit of the smaller value. For example,

   ```c++
   int8_t b = -1; // in binary, it's 1111 1111
   int16_t x = b; 
   ```

   what is x in binary? x is (<u>1111 1111</u> 1111 1111), because the first digit of b is 1. The underlined digits is the extended signs.

   Sign extension also happens in right shifting:

   ```c++
   int4_t b = 0b1100; // -4 in decimal
   b >= 1; // b = 0b1110 (-2 in decimal)
   ```

   Note, 0xF000 >> 4 = 0x0F00, because <u>hex constants are treated as unsigned</u>. But if you first assign `0x0F00` to a signed int then shift it, you'll get 0x`FF00`.

7. In two's compliment, how to negative a number: flip all bits then add one

   ```c++
   uint32_t x = 0x00FE;
   x = ~x; // x = 0xFF01
   x += 1; // x= 0xFF02
   ```

8. Endianness

   Endianness referes to the order of the bytes as stored in memory.

   - Big-endian (BE): stores the most significant byte at the smallest address and the lease significant byte at the largest address
   - Little-endian (LE): stores the least significant byte at the smallest address
     - MAC is little endianness
