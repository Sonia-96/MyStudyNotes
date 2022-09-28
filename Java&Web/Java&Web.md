# 1 Web Pages

## Unix

### Unix Permissions

d<span style="color:red">rwx</span><span style="color:blue">r-x</span><span style="color:green">---</span>

- d - directory
- <span style="color:red">owner's permissions</span>
- <span style="color:blue">group's permissions</span> (try running groups in cmd)
- <span style="color:green">everyone else</span>
- `% chmod go+rX images # change permissions for group and other to read and execute`
  - capital X???
- `ls -ld`

### Home Directory

commands: 

- `echo $HOME`
- `cd $HOME / cd ~ / cd`: enter home directory
- `echo $PATH`: what is $PATH? list of dire the computer will look through to find commands you run
- `echo $SHELL`
- `env`: environment variables in your system 

## Web Client & Server

- web client: web browser - display info
- web server: a program that allows a web browser to talk to it and sends it the requested files
  - popular web servers:
    - Apach
    - Apach Tomcat
    - Nginx
    - LiteSpeed

## HTML

1. HTML is a static language, not programming language

2. Tags & Attributes: 

   - `<tag attr1="value1" attr2="value2"> stuff </tag>`
     - Attributes can be from CSS
   - <tag/>: tags don't contain "stuff"

3. HTML web page pieces //TODO

   ```html
   <html>
     	head
   		body
   </html>
   ```

### Common Tags

- `<a href="https://google.com"> This is a link for google </a>`: a hyperlink
  - <a href="https://google.com"> This is a link for google </a>

- `<img src="name.png"/>`: no closing tag

- `<ul>`: unordered list

- `<ol>`: ordered list
  - `<li>`

- Grouping:

  - `<div>`

  - `<span>`

- `<script>`: for Javascript

### Colors

stored as RGB, 1 byte for each value. e.g., 0xFF0033 is red

the smallest trunk the computer deals with - 1 byte

normal size  - 8 bytes

We use int to store color, extra 1 byte is used for opacity

## CSS

1. Link CSS file to your HTML file: (rel is for relation)

   ```html
   <head>
     <link rel="stylesheet" type="text/css" href="styles.css">  //  TODO
   </head>
   ```

2. Selector:

3. Class

4. id

## Emacs

open emacs editor: `emacs -nw`

1. create a new file: C-x C-f
2. save a file: C-x C-s
3. split the screen: C-x 2
4. switch buffers: C-x b
5. move between split windows: C-x o
6. suspend Emacs: C-z
7. fg: return Emacs
8. cancel a command: C-g
9. save file: C-x C-s
10. mark set: C-space （必须首先调成英文模式）
11. copy: M-w
12. cut: C-w
13. paste: C-y
14. move the cursor:
    - By line: C-a to start, C-e to end

TODO: create a html file and css file in Emacs

what's the address for html file in a remote server?

## Javascript

link script to html

# 2 Java

## Intro

1. Why java?

   - Android apps use java

   - java is safer (not crash) than C++

     - array bound checking

     - hides pointers

   - java programs are portable

2. Java platform components

   - JRE (Java Runtime Environment): a software that Java programs require to run correctly. For Java web & mobile applications, JRE communicates between the Java program and the operating system. With JRE, you can run your program in any operating system.

     > A software program needs a runtime environment that provides access to memory and other system resources such as program files and dependencies. In the past, most software used the operating system directly as its runtime environment. However, this meant that developers had to write different code for each operating system that they wanted their applications to run on. The Java Runtime Environment (JRE) technology was created as a solution to this problem. [What is JRE](https://aws.amazon.com/what-is/java-runtime-environment/)

   - JDK (Java Development Kit): a collection of software tools that are used for developing java applications. (When you are writing java code, you are using JDK.)

   - JVM (Java Virtual Machine): a program to run java program anywhere

3. Java & Pointers

   - java don't use pointers, only use reference
     - difference between pointers & reference? https://www.tutorialspoint.com/what-is-difference-between-a-pointer-and-reference-parameter-in-cplusplus
   - Garbage collection (?)

4. compilation

   - C++: .cpp -> .o -> executable

   - Java: .java -> .class -> .jar -> JVM

     ```bash
     javac HelloWorld.java # compile the program
     java HelloWorld # run the program
     ```

5. Java vs. C++

## Syntax

### Array

1. fixed array

   ```java
   int[] intArray = new int[10];
   int len = intArray.length;
   ```

2. dynamic array (resizable)

   - ArrayLists: faster due to multi threads support
   - Vectors: single thread ?

   ```java
   Vector<Integer> v = new Vector<>(5);
   for (int i = 0; i < 5;i++ ){
     v.add(i);
   }
   v.remove(v.size() - 1);
   ```


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
   int i = sc.nextInt();
   String s = sc.nextLine();
   ```

   // TODO: try scanner using file input

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

### Testing

