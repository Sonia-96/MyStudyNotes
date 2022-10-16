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

