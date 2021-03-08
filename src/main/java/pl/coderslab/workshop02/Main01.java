package pl.coderslab.workshop02;

import java.util.Scanner;

public class Main01 {

    private static Scanner userInput = new Scanner(System.in);
    private static User loggedUser = null;
    private static final String WRONG_COMMAND = "Command not recognized, please enter number of chosen option";
    private static final String CHANGE_SUCCESS = "Changes saved successfully";
    private static final String CHANGE_FAILED = "Error while saving new information, changes have been reverted";


    public static void main(String[] args) {

        if (authenticationMenu()) {
            userOptionsMenu();
        }
    }

    public static boolean authenticationMenu() {

        String command = new String();
        while (true) {
            System.out.println("Choose option:");
            System.out.println("1 - log in into existing account");
            System.out.println("2 - register new account");
            System.out.println("3 - exit");
            command = userInput.nextLine();
            if (command.length() == 1) {
                switch (command.toLowerCase().charAt(0)) {
                    case '1':
                        if (logIn()) {
                            return true;
                        }
                        break;
                    case '2':
                        if (register()) {
                            return true;
                        }
                        break;
                    case '3':
                        return false;
                    default:
                        System.out.println(WRONG_COMMAND);
                        break;
                }
            } else {
                System.out.println(WRONG_COMMAND);
            }
        }
    }

    public static void userOptionsMenu() {

        String command = new String(),
                temp = new String();
        boolean isExit = false;

        while (!isExit) {
            System.out.println("Choose option:");
            System.out.println("1 - view account information");
            System.out.println("2 - change your e-mail");
            System.out.println("3 - change your username");
            System.out.println("4 - change your password");
            System.out.println("5 - delete account");
            System.out.println("6 - exit");
            command = userInput.nextLine();
            if (command.length() == 1) {
                switch (command.toLowerCase().charAt(0)) {
                    case '1':
                        System.out.println("\n\nYour account data:\n" + loggedUser.toString() + "\n\n");
                        break;
                    case '2':
                        System.out.println("Enter new e-mail:");
                        temp = SharedMethods.getEmailFromUser();
                        System.out.println("Are you sure you want to change your e-mail?");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(loggedUser.setEmail(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '3':
                        System.out.println("Enter new username:");
                        temp = SharedMethods.getUsernameFromUser();
                        System.out.println("Are you sure you want to change your username?");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(loggedUser.setUsername(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '4':
                        System.out.println("Enter new password:");
                        temp = SharedMethods.getPasswordFromUser();
                        System.out.println("Are you sure you want to change your password");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(loggedUser.setPassword(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '5':
                        System.out.println("Are you certain you want to delete your account?");
                        if (SharedMethods.getYesNoChoice()) {
                            isExit = loggedUser.deleteUser();
                            System.out.println(isExit ? "Account deleted successfully" : "Error while deleting account");
                        }
                        break;
                    case '6':
                        isExit = true;
                        break;
                    default:
                        System.out.println(WRONG_COMMAND);
                        break;
                }
            } else {
                System.out.println(WRONG_COMMAND);
            }
        }
    }

    public static boolean logIn() {

        System.out.println("Enter e-mail address used during registration:");
        String email = userInput.nextLine();
        System.out.println("Enter password:");
        String password = userInput.nextLine();
        loggedUser = User.getUserByEmail(email);
        if (loggedUser != null) {
            if (loggedUser.checkPassword(password)) {
                return true;
            }
        }
        System.out.println("Logging attempt failed - invalid e-mail or password");
        loggedUser = null;
        return false;
    }

    public static boolean register() {

        System.out.println("Enter e-mail:");
        String email = SharedMethods.getEmailFromUser();
        System.out.println("Enter your username\nUsername must be between 4 and 255 characters long");
        String username = SharedMethods.getUsernameFromUser();
        System.out.println("Enter password\nPassword must be at least 8 characters long");
        String password = SharedMethods.getPasswordFromUser();
        System.out.println("Do you want to register new account using following information:\ne-mail: " + email + "\nusername: " + username + "\npassword: " + password);
        if (!SharedMethods.getYesNoChoice()) {
            return false;
        }
        loggedUser = new User(email, username, password);
        if (loggedUser.getId() < 1) {
            System.out.println("Error while creating account, new account was not saved");
            loggedUser = null;
            return false;
        }
        return true;
    }

}
