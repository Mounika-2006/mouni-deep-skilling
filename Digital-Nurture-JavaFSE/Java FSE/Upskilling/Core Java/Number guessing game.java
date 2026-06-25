import java.util.*;

class Numberguessinggame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 70; // The number to guess
        int guess = 0; // Variable to store the user's guess
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100:");

        while (guess != number) {
            guess = scanner.nextInt();

            if (guess < number) {
                System.out.println("Too low! Try again:");
            } else if (guess > number) {
                System.out.println("Too high! Try again:");
            } else {
                System.out.println("Congratulations! You've guessed the correct number: " + number);
            }
        }

        scanner.close();
    }
}
