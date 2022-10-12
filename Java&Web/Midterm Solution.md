# General Programming

## Q1

Ask the user for a file name.  (Continue asking until a valid (existing) file name is entered.)  Read all text from that file and calculate, store, and print out the number of times each letter occurs.  As an extra challenge, print out the letters (and corresponding counts) in order of the most occurring to the least.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CharacterCounts {
    private static class Pair {
        char letter;
        int count;

        Pair(char c, int i) {
            letter = c;
            count = i;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean stop = false;
        File file = null;
        while (!stop) {
            Scanner sinReader = new Scanner(System.in);
            System.out.print("Enter a file name: ");
            String filename = sinReader.nextLine();
            file = new File(filename);
            if (file.exists()) {
                stop = true;
            }
        }
        Scanner fileReader = new Scanner(file);
        int[] letterCounts = new int[26];
        while (fileReader.hasNext()) {
            String word = fileReader.next();
            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));
                if (c >= 'a' && c <= 'z' ) {
                    letterCounts[c - 'a'] += 1;
                }
            }
        }

        Pair[] pairs = new Pair[26];
        for (int i = 0; i < 26; i++) {
            pairs[i] = new Pair((char) ('a' + i), letterCounts[i]);
        }
        Arrays.sort(pairs, (c1, c2) -> (c2.count - c1.count));
        for (Pair p : pairs) {
            System.out.println(p.letter + " " + p.count);
        }
    }
}
```

another way to sort the characters: use maxHeap (too advanced)

```java
PriorityQueue<int[]> maxHeap = new PriorityQueue<>((c1, c2) -> (c2[1] - c1[1]));
for (int i = 0; i < letterOccurs.length; i++) {
  maxHeap.add(new int[] {'a' + i, letterOccurs[i]});
}
while (!maxHeap.isEmpty()) {
  int[] temp = maxHeap.poll();
  System.out.println((char) temp[0] + " " + temp[1]);
}
```

## Q2

```java
Random rand = new Random();
int answer = rand.nextInt(100) + 1;
int i = 0;
boolean stop = false;
while (i < 10 && !stop) {
  Scanner sc = new Scanner(System.in);
  System.out.print("Guess a number: ");
  int guess = sc.nextInt();
  if (guess < answer) {
    System.out.println("Your guess is too low");
  } else if (guess > answer) {
    System.out.println("Your guess is too high");
  } else {
    System.out.println("Your guess is correct!");
    stop = true;
  }
  i++;
}
if (!stop) {
  System.out.print("You lose.");
}
```

## Q3

```java
Scanner sc = new Scanner(System.in);
boolean stop = false;
int day = -1;
while (!stop) {
  System.out.println("Enter a day of the year (1 - 365)");
  day = sc.nextInt();
  if (day >= 1 && day <= 365) {
    stop = true;
  }
}
day %= 7;
String dayOfWeek = ""; // remember to initialize the String
switch(day) {
  case 0:
    dayOfWeek = "Sunday";
    break;
  case 1:
    dayOfWeek = "Monday";
    break;
  case 2:
    dayOfWeek = "Tuesday";
    break;
  case 3:
    dayOfWeek = "Wednesday";
    break;
  case 4:
    dayOfWeek = "Thursday";
    break;
  case 5:
    dayOfWeek = "Friday";
    break;
  case 6:
    dayOfWeek = "Saturday";
    break;
}
System.out.println("This is " + dayOfWeek);
```

## Q4

Solution 1: Arrays.sort

```java
public int[] get3BiggestNumbers(int[] arr) {
  Arrays.sort(arr);
  int[] res = new int[3];
  for (int i = 0; i < 3; i++) {
    res[i] = arr[arr.length - 1 - i];
  }
  return res;
}
```

Solution 2: max heap

```java
public int[] get3BiggestNumbers(int[] arr) {
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((c1, c2) -> (c2 - c1));
  for (int i : arr) {
    maxHeap.add(i);
  }
  int[] res = new int[3];
  for (int i = 0; i < 3; i++) {
    res[i] = maxHeap.poll();
  }
  return res;
}
```

## Q5

```java
int  x = 130; // 130 = 0b 0 1000 0010
byte b = (byte)x; // b = 1000 0010
System.out.println( b ); // java treat b as a signed int, b = -1 * 2^7 + 1 * 2^1 = -126
```

What is invalid about this code (and why):

```
class HelloWorld {
   int x = 10;
   public static void main(String[] args) {
       System.out.println( x );
   }
}
```

x is not a static variable. main is a static method. so `main` cannot use variable `x`.

# Networking

## Q1

One-shot server: Write an http server that accepts a single message from a client in the form of a string, prints that string to the console, then shuts itself down. The string will be passed using a get request from the client, right after "/".

```java
public static void main(String[] args) throws IOException {
  ServerSocket server = new ServerSocket(8080); // remember to set a port number + throw an exception
  Socket client = server.accept(); // remember to throw an exception
  Scanner sc = new Scanner(client.getInputStream());
  String firstLine = sc.nextLine();
  String message = firstLine.split(" ")[1];
  message = message.substring(1);
  System.out.println(message);
  client.close();
  server.close();
}
```

