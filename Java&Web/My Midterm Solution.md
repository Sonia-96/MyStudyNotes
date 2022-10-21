# General Programming

## Q1

Ask the user for a file name.  (Continue asking until a valid (existing) file name is entered.)  Read all text from that file and calculate, store, and print out the number of times each letter occurs.  As an extra challenge, print out the letters (and corresponding counts) in order of the most occurring to the least.

### Solution 1: Comparator

```java
public class LetterCount {
    private static class Pair {
        char letter;
        int count;

        Pair(char c, int i) {
            letter = c;
            count = i;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = null;
        while (file == null || !file.exists()) {
            Scanner sinReader = new Scanner(System.in);
            System.out.print("Enter a file name: ");
            String filename = sinReader.nextLine();
            file = new File(filename);
        }
        Scanner fileReader = new Scanner(file);
        Pair[] pairs = new Pair[26];
        for (int i = 0; i < 26; i++) {
            pairs[i] = new Pair((char) ('a' + i), 0);
        }
        while (fileReader.hasNext()) {
            String word = fileReader.next();
            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));
                if (c >= 'a' && c <= 'z' ) {
                    pairs[c - 'a'].count += 1;
                }
            }
        }
        Arrays.sort(pairs, (c1, c2) -> (c2.count - c1.count)); // comparator
        for (Pair p : pairs) {
            System.out.println(p.letter + " " + p.count);
        }
    }
}
```

### Solution 2: Comparable

another way: Pair implements Comparable Interface, then it can be sorted by Arrays.sort()

```java
public class LetterCount {
    private static class Pair implements Comparable<Pair>{
        char letter;
        int count;

        Pair(char c, int i) {
            letter = c;
            count = i;
        }

        public int compareTo(Pair rhs) {
            return rhs.count - this.count;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = null;
        while (file == null || !file.exists()) {
            Scanner sinReader = new Scanner(System.in);
            System.out.print("Enter a file name: ");
            String filename = sinReader.nextLine();
            file = new File(filename);
        }
        Scanner fileReader = new Scanner(file);
        Pair[] pairs = new Pair[26];
        for (int i = 0; i < 26; i++) {
            pairs[i] = new Pair((char) ('a' + i), 0);
        }
        while (fileReader.hasNext()) {
            String word = fileReader.next();
            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));
                if (c >= 'a' && c <= 'z' ) {
                    pairs[c - 'a'].count += 1;
                }
            }
        }
        Arrays.sort(pairs);
        for (Pair p : pairs) {
            System.out.println(p.letter + " " + p.count);
        }
    }
}
```



## Q2

Choose a random number from 1 to 100.  Ask the user to guess that number.  After each guess, tell the user if their guess was too low or too high (or that they win).  Additional challenges: a) only allow the user to make 10 guesses before they lose; b) Let the user know if they have already guessed a number.

```java
public class GuessGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int answer = rand.nextInt(100) + 1;
        boolean[] numOccur = new boolean[100];
        Arrays.fill(numOccur, false);
        int i = 0;
        while (i < 10) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Guess a number: ");
            int guess = sc.nextInt();
            if (numOccur[guess - 1]) {
                System.out.println("You already guessed the number!");
                continue;
            }
            if (guess < answer) {
                System.out.println("Your guess is too low");
            } else if (guess > answer) {
                System.out.println("Your guess is too high");
            } else {
                System.out.println("Your guess is correct!");
                break;
            }
            numOccur[guess - 1] = true;
            i++;
        }
        if (i == 10) {
            System.out.println("You lose.");
        } else {
          	Syste.out.prinln("You win.")
        }
    }
}
```

## Q3

Assume January 1 is a Monday. Ask the user to enter a day of the year (1-365) and display what day of the week it is.  Hint: Use the mod (%) function: If the user enters 1, you would display Monday. If the user enters 2, Tuesday ... enters 7, display Sunday - what about 8?  Monday again.

```java
Scanner sc = new Scanner(System.in);
int day = -1;
while (day < 1 || day > 365) {
  System.out.print("Enter a day of the year (1 - 365): ");
  day = sc.nextInt();
}
String[] dayStrings = {"Sundy", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
System.out.println("This is " + dayStrings[day % 7]);
```

## Q4

Write a function that takes in an array of numbers and returns the 3 largest numbers in that array. (Consider how you would do it using just basic programming techniques, and then how you could do it (much more easily) using more advanced data structures (and associated methods).

### Solution 1: Selection Sort

```java
public void selectionSort(int[] nums) {
  for (int i = 0; i < nums.length - 1; i++) {
    int maxIndex = i;
    for (int j = i + 1; j < nums.length; j++) {
      if (nums[j] > nums[maxIndex]) {
        maxIndex = j;
      }
    }
    int temp = nums[maxIndex];
    nums[maxIndex] = nums[i];
    nums[i] = temp;
  }
}

public int[] getThreeBiggestNumbers(int[] nums) {
  if (nums.length < 3) {
    throw new RuntimeException("The length of array should be greater than 3!");
  }
  selectionSort(nums);
  int[] result = new int[3];
  for (int i = 0; i < 3; i++) {
    result[i] = nums[i];
  }
  return result;
}
```

### Solution 2: Arrays.sort

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

## Q5

What is the output of the following code (and why)?

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
  client.close(); // remember to close the client and server
  server.close();
}
```

