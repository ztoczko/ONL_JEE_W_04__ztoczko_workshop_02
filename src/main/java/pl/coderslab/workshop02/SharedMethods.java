package pl.coderslab.workshop02;

import java.util.Scanner;

public class SharedMethods {

    private static Scanner userInput = new Scanner(System.in);

    public static String getEmailFromUser() {

        String email = new String();

        while (true) {
            email = userInput.nextLine();
            if (isStringValidEmail(email) && email.length() <= 255) {

                if (User.isEmailUnique(email)) {
                    return email;
                } else {
                    System.out.println("E-mail is already in use, please enter other e-mail address");
                }

            } else {
                System.out.println("Entered text is not an e-mail address or is too long, please enter valid e-mail");
            }
        }
    }

    public static String getUsernameFromUser() {

        String username = new String();

        while (true) {
            username = userInput.nextLine();
            if (username.length() >= 4 && username.length() <= 255) {
                return username;
            } else {
                System.out.println("Entered username is not between 4 and 255 characters long, please try again");
            }
        }
    }

    public static String getPasswordFromUser() {

        String password = new String();

        while (true) {
            password = userInput.nextLine();
            if (password.length() >= 8) {
                return password;
            } else {
                System.out.println("Entered password is less than 8 characters long, please try again");
            }
        }
    }

    public static boolean getYesNoChoice() {

        String command = new String();
        System.out.println("(Y)es/(N)o");
        while (true) {
            command = userInput.nextLine();
            if (command.length() == 1) {
                switch (command.toLowerCase().charAt(0)) {
                    case 'y':
                        return true;
                    case 'n':
                        return false;
                    default:
                        break;
                }
            }
            System.out.println("Command not recognized, please type Y for Yes or N for No");
        }
    }

    public static boolean isStringValidEmail(String email) {

        String[] tester = email.split("@");
        if (tester.length != 2) {
            return false;
        }
        if (tester[0].length() == 0 || tester[1].length() == 0) {
            return false;
        }
        tester = tester[1].split(".");

        if (tester.length == 1) {
            return false;
        }
        for (String str : tester) {
            if (str.length() == 0) {
                return false;
            }
        }
        return true;
    }
}
