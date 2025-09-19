import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        int guessCount = 1;
        int guess = -1;
        int randomNum = (int)(Math.random() * 101); // 0 to 100

        System.out.println("Generating number...");

        while (guess != randomNum) {
            System.out.print("Enter guess: ");

            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();

                if (guess < randomNum) {
                    System.out.println("Too low!");
                } else if (guess > randomNum) {
                    System.out.println("Too high!");
                } else {
                    System.out.printf("Congrats! You guessed the number on the %s. attempt!", guessCount);
                }

                guessCount++;
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
