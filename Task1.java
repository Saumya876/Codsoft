import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        System.out.println(
                "\n Welcome to the Number Game.\n Let's get started with the game.\nGame is as follow:-\n-The System will generate a random number between 1 to 100.\n-You have to guess that random number.\n-Your score will be the number of attempts you took to guess the number.\n");
        System.out.print("Enter the number of rounds you want to play :");
        int rounds = sc.nextInt();
        for (int i = 1; i <= rounds; i++) {
            System.out.println();
            System.out.println("Round" + i + "begins-->\n");
            System.out.println(
                    "Enter the difficulty level:\n 1->EASY i.e.There is No Limit for number of attempts.\n 2->HARD i.e. There is Limit for number of attempts");
            int limit = sc.nextInt();
            switch (limit) {
                case 1: {
                    System.out.println("Welcome to the game here you will not have any fixed number of attempts.");
                    int random = 1 + rd.nextInt(100);
                    System.out.println("Our work is done,now your turn\n");
                    int count = 1;
                    whileloop: while (true) {
                        System.out.println("Enter your guess number " + count + ":");
                        int guess = sc.nextInt();
                        if (guess > random) {
                            System.out
                                    .println(guess + "  is HIGHER than Generated Number. You have to Guess again :(.");
                        } else if (guess < random) {
                            System.out.println(guess + " is LOWER than Generated Number. You have to Guess again :(.");
                        } else {
                            System.out.println(guess + " is EQUAL to than Generated Number.\nCongrats it took you "
                                    + count + " attempts.");
                            break whileloop;
                        }
                        count++;
                    }
                }
                    break;
                case 2: {
                    System.out.println("Welcome to the game here you will  have  fixed number of attempts.");
                    System.out.println(
                            "The limit is just 10.\nIf you have guessed the number in 10 or less attempts you have won the game.\n Else you will lose the game.\n");
                    int random = 1 + rd.nextInt(100);
                    System.out.println("Our work is done,now your turn");

                    int count = 1;
                    whileloop: while (count <= 10) {
                        System.out.println("\nNo of guesses remaining : " + (11 - count) + "\n");
                        System.out.print("Enter you guess number " + count + ": ");
                        int guess = sc.nextInt();
                        if (guess > random) {
                            System.out.println(guess + " is HIGHER than Generated Number. You have to Guess again :(");
                        } else if (guess < random) {
                            System.out.println(guess + " is LOWER than Generated Number. You have to Guess again :(");
                        } else {
                            System.out.println(guess + " is EQUAL to than Generated Number.\nCongrats it took you "
                                    + count + " attempts.");
                            break whileloop;
                        }
                        count++;
                    }
                    if (count > 10) {
                        System.out.println(
                                "OOPS!!! You were not able to guess the number in 10 attempts.\nThe Generated Number was "
                                        + random + "\nBETTER LUCK NEXT TIME\n");
                    }
                    break;
                }
                default: {
                    System.out.println("--- WRONG INPUT ---\n Sorry!!! But this round has been wasted.");
                    break;
                }
            }
            System.out.println("ROUND " + i + " is OVER.");
        }
        System.out.println("\nAll the Rounds are over.\nThank You for Playing the Game.\nHope you enjoyed it.");
        sc.close();
    }

}
