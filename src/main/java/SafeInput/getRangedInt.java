package SafeInput;
import java.util.Scanner;
public class getRangedInt {
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int userInput = 0;
        boolean validInput = false;

        do {
            System.out.print(prompt);

            if (pipe.hasNextInt()) {
                userInput = pipe.nextInt();
                if (userInput >= low && userInput <= high) {
                    validInput = true;
                } else {
                    System.out.println("Input is out of the specified range [" + low + " - " + high + "]. Please try again.");
                }
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input. Please enter a valid integer within the range [" + low + " - " + high + "].");
            }

        } while (!validInput);

        pipe.nextLine();

        return userInput;
    }
}
