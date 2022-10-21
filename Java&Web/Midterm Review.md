# Q2

Choose a random number from 1 to 100.  Ask the user to guess that number.  After each guess, tell the user if their guess was too low or too high (or that they win).  Additional challenges: a) only allow the user to make 10 guesses before they lose; b) Let the user know if they have already guessed a number. 

## Approach #1 : ArrayList

```java
Random random = new Random();
int answer = random.nextInt(100) + 1;
int i = 0;
ArrayList<Integer> guesses = new ArrayList<>();
while (i < 10) {
  System.out.println("Enter a number from 1 - 100:");
  Scanner sc = new Scanner(System.in);
  int guess = sc.nextInt();
  boolean guessExists = false;
  for (int num : guesses) {
    if (num == guess) {
      guessExists = true;
      System.out.println("You already guessed the number!");
      break;
    }
  }
  if (!guessExists) {
    if (guess == answer) {
      System.out.println("Your guess is correct!");
      break;
    } else if (guess < answer) {
      System.out.println("Your guess is too low!");
    } else {
      System.out.println("Your guess is too high!");
    }
    guesses.add(guess);
  }
  i++;
}
if (i == 10) {
  System.out.println("You lose!");
} else {
  System.out.println("You win!");
}
```

i = 9, the user guesses the right number. Q: what's the value of i when the program ends? 9

i = 9, the user guesses the wrong number. Q: what's the value of i when the program ends? 10

## Approach #2: HashMap (recommended)

Acutally HashSet is better. 

```java
Random random = new Random();
int answer = random.nextInt(100) + 1;
int i = 0;
HashMap<Integer, Boolean> guesses = new HashMap<>();
while (i < 10) {
  System.out.println("Enter a number from 1 - 100:");
  Scanner sc = new Scanner(System.in);
  int guess = sc.nextInt();
  if (guesses.containsKey(guess)) {
    System.out.println("You already guessed the number!");
  } else {
      if (guess == answer) {
        System.out.println("Your guess is correct!");
        break;
      } else if (guess < answer) {
        System.out.println("Your guess is too low!");
      } else {
        System.out.println("Your guess is too high!");
      }
      guesses.add(guess);
  }
  i++;
}
if (i == 10) {
  System.out.println("You lose!");
} else {
  System.out.println("You win!");
}
```

## Approach #3: boolean array (recommended)

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



# Q3

Assume January 1 is a Monday. Ask the user to enter a day of the year (1-365) and display what day of the week it is.  Hint: Use the mod (%) function: If the user enters 1, you would display Monday. If the user enters 2, Tuesday ... enters 7, display Sunday - what about 8?  Monday again.

```java
Scanner scanner = new Scanner(System.in); 
System.out.println("Enter a day of the year (1 - 365):");
int dayOfYear = scanner.nextInt();
int dayOfWeek = dayOfYear % 7;
String[] dayStrings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sundy"};
System.out.println(dayStrings[(dayOfWeek + 6) % 7]);
```

|         | Remainder | (dayOfWeek + 6) % 7 |
| ------- | --------- | ------------------- |
| Monday  | 1         | 0                   |
| Tuesday | 2         | 8 % 7 = 1           |
|         |           |                     |
|         |           |                     |
|         |           |                     |
|         |           |                     |
| Sunday  | 0         | 6                   |

## Scanners

1. read from user:

   ```java
   Scanner scanner = new Scanner(System.int)
   ```

2. read from file:

   ```java
   File file = new File(<fileName>);
   if (!file.exists()) {
     // output a message and end the program
   }
   Scanner scanner = new Scanner(file);
   ```

# Q4

Write a function that takes in an array of numbers and returns the 3 largest numbers in that array. (Consider how you would do it using just basic programming techniques, and then how you could do it (much more easily) using more advanced data structures (and associated methods).

1. basic programming techniques:

   ```java
   int[] get3BiggestNumbers(int[] nums) {
     SelectionSort(nums); // sort nums in descending order
     int[] result = new int[3];
     for (int i = 0; i < result.length; i++) {
       result[i] = nums[i];
     }
     return result;
   }
   ```

2. more advanced data structures

   ```java
   int[] get3BiggestNumbers(int[] nums) {
   	Arrays.sort(nums);
     int[] result = new int[3];
     for (int i = 0; i < result.length; i++) {
       result[i] = nums[nums.length - 1 - i]
     }
     return result;
   }
   ```

   i = 0, last element of nums - nums.length - 1;

   i = 1, last bust 1 element - nums.length - 2;

   i = 2, nums.length - 3;

## Selection Sort

### In ascending order

1.Iterate through unsorted part, and find the min value

2.Swap the min value with the first element in the unsorted part

```java
// Iterate through unsorted part, and find the min value
int findMinValueIndex(int[] nums, int start) { // i - start index of unsorted part
  int minValue = nums[start];
  int minValueIndex = start;
  for (int i = start; i < nums.length; i++) {
    if (nums[i] < minValue) {
      minValue = nums[i];
      minValueIndex = i;
    }
  }
  return minValueIndex;
}

void swap(int[] nums, int a, int b) {
  int temp = nums[a];
  nums[a] = nums[b];
  nums[b] = temp;
}

void SelectionSort(int[] nums) {
  for (int i = 0; i < nums.length; i++) {
    // 1. find the min value index
    int minValueIndex = findMinValueIndex(nums, i);
    // 2. swap minValue with the first element of the unsorted part
    swap(nums, i, minValueIndex);
  }
}
```

### In descending order

```java
// Iterate through unsorted part, and find the max value
int findMaxValueIndex(int[] nums, int start) { // i - start index of unsorted part
  int maxValueIndex = start;
  for (int i = start; i < nums.length; i++) {
    if (nums[i] > nums[maxValueIndex]) {
      maxValueIndex = i;
    }
  }
  return maxValueIndex;
}

void swap(int[] nums, int a, int b) {
  int temp = nums[a];
  nums[a] = nums[b];
  nums[b] = temp;
}

void SelectionSort(int[] nums) {
  for (int i = 0; i < nums.length; i++) {
    // 1. find the max value index
    int minValueIndex = findMaxValueIndex(nums, i);
    // 2. swap maxValue with the first element of the unsorted part
    swap(nums, i, minValueIndex);
  }
}
```



## Bubble Sort

