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

# Data types

primitives are stored on stack

objects are created on the heap, and any variable referring to an object is a pointer

difference?

| Primitive data type | Size/bits | Wrapper class (Object data type) | Size/bits |
| ------------------- | --------- | -------------------------------- | --------- |
| int                 | 32        | Integer                          |           |
| byte                | 8         | Byte                             |           |
| short               | 16        | Short                            |           |
| long                | 64        | Long                             |           |
| float               | 32        | Float                            |           |
| double              | 64        | Double                           |           |
| char                | 16        | Character                        |           |
| boolean             | 1         | Boolean                          |           |

# Array

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

# File I/O

## Adapters

Adapters are higher level objects that use streams to provide higher level operations.

1. Input:
   - Scanner: read bytes and returns numeric values, strings, etc.
2. Output:
   - PrintWriter: takes in variables and sends their values to associated output streams

We use adapters to read and write bytes (raw), then we can deal with normal data types, e.g. String, int, etc.

## Scanner

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


## File Output

### FileWriter

```java
FileWriter myWriter = new FileWriter("<filename>");
myWriter.write("Put this text into a file.\n");
myWriter.close();
```

### [PrintWriter](https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html)

```java
PrintWriter pw = new PrintWriter("<filename>");
pw.print(...);
pw.printf(...);
pw.println(...);
pw.close();
```

[PrintWriter vs. FileWriter](https://medium.com/geekculture/using-printwriter-vs-filewriter-in-java-2958df85f105)

# Modifiers

## Access modifiers

|                       | class | package | subclass (same pkg) | subclass (diff pkg) | World |
| --------------------- | ----- | ------- | ------------------- | ------------------- | ----- |
| public                | Y     | Y       | Y                   | Y                   | Y     |
| protected             | Y     | Y       | Y                   | Y                   |       |
| default (no modifier) | Y     | Y       | Y                   |                     |       |
| private               | Y     |         |                     |                     |       |

https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

## Final

Use `final` to mark constants: `public final String resourcePath = "../resources/"`

## Static

#### Classes

#### Methods

- **static methods**: we should use className to call a static method
  - `main()` should always be static 
- **non-static methods (instance methods)**: we should use methodName to call a non-static method
  - A static method does not have an object attached to it. So we don't need an object to access it.


differences:

1. instance methods can access both static & non-static variables and methods inside the class
2. static methods can only access static variables and methods
3. static methods can't use `this` keyword 

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

#### Variable

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

Note: only throw an exception when you can handle it!

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

1. `printStackTrace()`: display the class stack down to where the exception occurred

   ```java
   try {
     Fraction f9 = new Fraction(30, 0);
   } catch (ArithmeticException e) {
     e.printStackTrace();
   }
   ```

2. `getMessage()`

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

When we use `new` to create a `String`,  JVM will create a new String object in the normal heap space intead  of the string pool. 

<img src="./assets/image-20220930231107775.png" alt="image-20220930231107775" style="zoom:50%;" />

# Inheritance

## Interface

Interface is a blueprint of a class. An Interface doesn't implement its methods, only specifies their signatures. These methods are called **abstract methods**.

Note, a subclass and its parent class should be "is-a" relationship. For example:

- Cat "is-a" Animal:white_check_mark: 
- Cat "has-a" Claw:x:

If you want to implement a method in a Interface, the method should be `default`

### Implements

When you initialize a subclass, the type can be either superclass or subclass. If it is superclass, the object can only use the methods of superclass. If it's subclass, the object can only use the methods of subclass.

```java
Interface Drawable {
  void draw();
}

class Bird implements Drawable {
  private String name;
  private String color;
  
  public void draw() {System.out.println("Drawing a bird")};
  public void fly() {System.out.println("A bird can fly")};
  
  public static void main(String[] args) {
    Drawable b1 = Bird();
    b1.draw();
    // b1.fly(); // b1 cannot fly, because it's not Bird!!
    
    Bird b2 = Bird();
    b2.fly(); // b2 cannot 
  }
}
```

## Override

Overriding vs. Overloading:

- Overriding（方法重写）: two functions have same name and same signatures. Overriding is usually happened when a subclass inherits a baseclass, and we should add `@Override` annotation in front of the method in the subclass.
- Overloading（方法重载）: two functions have same name but different signatures

### Dynamic method selection

A variable has two types:

- **compile-time type (static type)** **编译类型**：the type in the variable declaration
- **run-time type (dynamic type)** **运行时的类型**：the type in the variable initializaiton

For example, 

```java
Person employee = new Employee();
```

for the object `employee`, its static type is `Employee`, and dynamic type is `Person`. 

When java runs a overriden method, it will first choose the method in the dynamic type. This principle is called ”dynamic method selection“.

注意：这个原则**不**适用于重载的方法。对于重载的方法，java只匹配变量的static type。

https://cs61b.bencuan.me/oop/dynamic-method-selection

## Extends

If a subclass `extends` a base class, the subclass can inherite the following things from the base class:

- all static variables and instance variables
- all non-private methods
- all non-private inner classes

### super

A subclass can use `super` to use the variables and methods from its base class.

- use constructor: `super(parameters required in base class)`
- use methods: `super.methodName(parameters)`
- use variables: `super.variableName`



### extends vs. implements



## Abstraction

## Polymorphism

