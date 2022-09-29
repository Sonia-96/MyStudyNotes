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

4. Garbage collection: Java scans memory on a regular basis to find non-accessible heap memory, and then automatically frees it

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

### Data types

primitives are stored on stack

objects are created on the heap, and any variable referring to an object is a pointer

difference?

| Primitive data type | Wrapper class (Object data type) |
| ------------------- | -------------------------------- |
| int                 | Integer                          |
| byte                |                                  |
| short               |                                  |
| long                |                                  |
| float               |                                  |
| double              |                                  |
| char                |                                  |
| Boolean             |                                  |

### Array

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

#### ArrayList\<T>

1. walking through 

  - for-loop

  - for-each loop

  - **Iterator**

    ```java
    Iterator iter = names.iterator();
    while (iter.hasNext()) {
      out.println(iter.next());
    }
    ```

2. copy // TODO!!!

   ```java
   ArrayList<String> moreNamew = new ArrayList<>(names); // this is a shllow copy!!! TODO
   ```

### File I/O

#### Scanner

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

   // TODO: try scanner using file input

#### File Output

##### FileWriter

```java
FileWriter myWrite= mew FileWriter("<filename>");
myWriter.write("Put this text into a file.\n");
myWriter.close();
```

##### PrintWriter

printerwriter不用outputstream也行

为什么scanner必须用inputstream？？

### Modifiers

|                       |      |      |      |      |
| --------------------- | ---- | ---- | ---- | ---- |
| public                |      |      |      |      |
| private               |      |      |      |      |
| protected             |      |      |      |      |
| default (no modifier) |      |      |      |      |

