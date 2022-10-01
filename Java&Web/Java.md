# Intro

1. Why java?

   - Android apps use java

   - java is safer (not crash) than C++

     - array bound checking

     - hides pointers

   - java programs are portable

2. Java platform components

   - **JRE** (Java Runtime Environment): a software that Java programs require to run correctly. For Java web & mobile applications, JRE communicates between the Java program and the operating system. With JRE, you can run your program in any operating system.

     > A software program needs a runtime environment that provides access to memory and other system resources such as program files and dependencies. In the past, most software used the operating system directly as its runtime environment. However, this meant that developers had to write different code for each operating system that they wanted their applications to run on. The Java Runtime Environment (JRE) technology was created as a solution to this problem. [What is JRE](https://aws.amazon.com/what-is/java-runtime-environment/)

   - **JDK** (Java Development Kit): a collection of software tools that are used for developing java applications. (When you are writing java code, you are using JDK.)

   - **JVM** (Java Virtual Machine): a program to run java program anywhere

3. Java & Pointers

   - java don't use pointers, only use reference
     - difference between pointers & reference? https://www.tutorialspoint.com/what-is-difference-between-a-pointer-and-reference-parameter-in-cplusplus

4. **Garbage collection**: Java scans memory on a regular basis to find non-accessible heap memory, and then automatically frees it

5. compilation

   - C++: .cpp -> .o -> executable

   - Java: .java -> .class -> .jar -> JVM

     ```bash
     javac HelloWorld.java # compile the program
     java HelloWorld # run the program
     ```

6. Java vs. C++

   - All java code is written in classes

# Syntax

## Data types

primitives are stored on stack

objects are created on the heap, and any variable referring to an object is a pointer

difference?

| Primitive data type | Wrapper class (Object data type) |
| ------------------- | -------------------------------- |
| int                 | Integer                          |
| byte                | Byte                             |
| short               | Short                            |
| long                | Long                             |
| float               | Float                            |
| double              | Double                           |
| char                | Character                        |
| boolean             | Boolean                          |

## Array

1. fixed array

   ```java
   int[] intArray = new int[10];
   int len = intArray.length;
   ```

2. dynamic array (resizable)

   - `ArrayList`(recommended): faster due to multi threads support

     ```java
     ArrayList<Integer> names = new ArrayList<>(); // we must use wrapper class in <>
     ```

   - `Vector`: single thread (synchronized)

     ```java
     Vector<Integer> v = new Vector<>(5); // Integer is generic type
     for (int i = 0; i < 5;i++ ){
       v.add(i);
     }
     v.remove(v.size() - 1);
     ```

### ArrayList\<T>

1. walking through 

     - for-loop
     
     - for-each loop
     
     -  **Iterator**
     
       ```java
       Iterator iter = names.iterator();
       while (iter.hasNext()) {
         out.println(iter.next());
       }
       ```


2. copy

   ```java
   ArrayList<String> moreNamew = new ArrayList<>(names); // this is a shllow copy!!! TODO
   ```

## File I/O

### Scanner

1. user input

   ```java
   Scanner s = new Scanner(System.in); // create a scanner object
   int i = s.nextInt();
   double d = s.nextDouble();
   byte b = s.nextByte();
   String str = s.nextLine();
   ```

2. file input:

   ```java
   File file = new File("test.txt");
   Scanner sc = new Scanner(file);
   String s = sc.nextLine(); // read next String by line
   s = sc.next(); //read next String by word
   int i = sc.nextInt(); // read next int
   ```


### File Output

#### FileWriter

```java
FileWriter myWriter = new FileWriter("<filename>");
myWriter.write("Put this text into a file.\n");
myWriter.close();
```

#### [PrintWriter](https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html)

```java
PrintWriter pw = new PrintWriter("<filename>");
pw.print(...);
pw.printf(...);
pw.println(...);
pw.close();
```

[PrintWriter vs. FileWriter](https://medium.com/geekculture/using-printwriter-vs-filewriter-in-java-2958df85f105)

## Modifiers

### Access modifiers

|                       | class | package | subclass (same pkg) | subclass (diff pkg) | World |
| --------------------- | ----- | ------- | ------------------- | ------------------- | ----- |
| public                | Y     | Y       | Y                   | Y                   | Y     |
| protected             | Y     | Y       | Y                   | Y                   |       |
| default (no modifier) | Y     | Y       | Y                   |                     |       |
| private               | Y     |         |                     |                     |       |

https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

### Final

Use `final` to mark constants: `public final String resourcePath = "../resources/"`

### Static

##### Methods

A static method does not have an object attached to it. So we don't need an object to access it.

```java
public class Main {
    static int x = 0;
    int y = 10;
  
  	void print_ns() {
        System.out.println(x);
        System.out.println(y);
    }

    void static print_s() {
        System.out.println(x); 
        // System.out.println(y); // syntax error!
    }
  
  	void static main(String[] args) {
      	Main test = new Main();
      	test.print_ns(); // must use object to use non-static methods
      	Main.print_s(); // must use class name to use static methods // error
    }
}
```

##### Variable

# Exception

Exceptions are a way of handling unexpected errors.

- thow an exception: when we encounter an exception, the program will stop

  ```java
  public void add(T x) {
    if (x == null) {
      throw new IllegalArgumentException("can't add null");
    }
    ...
  }
  ```

- catch an exception: when we encounter an exception, the program will output the error information but not stop

  ```java
  try {
      // something that may throw an exception
  } catch (Exception e) {
      // Do something about it 
  }
  ```

## Exception types

- `IoException`
- `FileNotFoundException`
- `ArithmeticException`
- `ClassNotFoundException`
- `ArrayIndexOutOfBoundsException`

## Useful methods for exceptions

```java
public Fraction( long n, long d ) throws ArithmeticException {
  if (d == 0) {
    throw new ArithmeticException("denominator can't be 0!");
  }
}
```

1. printStackTrace(): display the class stack down to where the exception occurred

   ```java
   try {
     Fraction f9 = new Fraction(30, 0);
   } catch (ArithmeticException e) {
     e.printStackTrace();
   }
   ```

2. getMessage()

   ```java
   try {
     Fraction f9 = new Fraction(30, 0);
   } catch (ArithmeticException e) {
     System.out.println(e.getMessage()); // what's the difference between  print(e) & print(e.getMessage())?
   }
   ```

   

# String

String is immutable in Java.

## String literals & String Objects

```java
String s1 = "Hello"; // string literal
String s2 = "Hello"; // string literal
String s3 = new String("Hello"); // string object
System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false
```

### String Pool

Java has a String Pool in the heap to store all string literals. When we create a string literal, JVM will first check if the literal exisits in the pool. If the literal already exists, JVM will return a reference to the pooled instance. If not, JVM will create a new String object in the String Pool.

When we use `new` to create a `String`, we are creating a String object. In this case, JVM will create a new String object in the normal heap space (not in the string pool). 

<img src="./assets/image-20220930231107775.png" alt="image-20220930231107775" style="zoom:50%;" />
