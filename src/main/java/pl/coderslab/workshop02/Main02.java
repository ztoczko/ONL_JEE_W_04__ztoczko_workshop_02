package pl.coderslab.workshop02;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Main02 {

    private static User[] userList = User.getAllUsers();
    private static Scanner userInput = new Scanner(System.in);
    private static final String WRONG_COMMAND = "Command not recognized, please enter number of chosen option";
    private static final String CHANGE_SUCCESS = "Changes saved successfully";
    private static final String CHANGE_FAILED = "Error while saving new information, changes have been reverted";


    public static void main(String[] args) {

        if (userList != null) {
            mainMenu();
        } else {
            System.out.println("Error while loading users' data");
        }

    }

    public static void mainMenu() {

        String command = new String();
        boolean isExit = false;
        int id = 0;

        while (!isExit) {
            System.out.println("Select an option:");
            System.out.println("1 - list users");
            System.out.println("2 - add user");
            System.out.println("3 - change user's data");
            System.out.println("4 - delete user");
            System.out.println("5 - exit");
            command = userInput.nextLine();
            if (command.length() == 1) {
                switch (command.toLowerCase().charAt(0)) {
                    case '1':
                        listUsers();
                        break;
                    case '2':
                        addUser();
                        break;
                    case '3':
                        id = getIdFromUser();
                        if (id != 0) {
                            updateUser(id);
                        }
                        break;
                    case '4':
                        id = getIdFromUser();
                        if (id != 0) {
                            deleteUser(id);
                        }
                        break;
                    case '5':
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

    public static void listUsers() {
        System.out.println();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        System.out.println();
    }

    public static void addUser() {

        System.out.println("Enter e-mail:");
        String email = SharedMethods.getEmailFromUser();
        System.out.println("Enter username\nUsername must be between 4 and 255 characters long");
        String username = SharedMethods.getUsernameFromUser();
        System.out.println("Enter password\nPassword must be at least 8 characters long");
        String password = SharedMethods.getPasswordFromUser();
        System.out.println("Do you want to register new account using following information:\ne-mail: " + email + "\nusername: " + username + "\npassword: " + password);

        if (SharedMethods.getYesNoChoice()) {
            User user = new User(email, username, password);

            if (user.getId() < 1) {
                System.out.println("Error while creating account, new account was not saved");
            } else {
                System.out.println("New account created successfully");
                userList = Arrays.copyOf(userList, userList.length + 1);
                userList[userList.length - 1] = user;
            }
        }
    }

    public static void updateUser(int id) {

        int index = convertIdToIndex(id);
        boolean isExit = false;
        String command = new String(),
                temp = new String();

        while (!isExit) {
            System.out.println(userList[index].toString());
            System.out.println("Select field to change:");
            System.out.println("1 - e-mail");
            System.out.println("2 - username");
            System.out.println("3 - password");
            System.out.println("4 - exit");
            command = userInput.nextLine();
            if (command.length() == 1) {
                switch (command.toLowerCase().charAt(0)) {
                    case '1':
                        System.out.println("Enter new email");
                        temp = SharedMethods.getEmailFromUser();
                        System.out.println("Are you sure you want to change user's e-mail?");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(userList[index].setEmail(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '2':
                        System.out.println("Enter new username");
                        temp = SharedMethods.getUsernameFromUser();
                        System.out.println("Are you sure you want to change user's username?");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(userList[index].setUsername(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '3':
                        System.out.println("Enter new password");
                        temp = SharedMethods.getPasswordFromUser();
                        System.out.println("Are you sure you want to change user's password?");
                        if (SharedMethods.getYesNoChoice()) {
                            System.out.println(userList[index].setPassword(temp) ? CHANGE_SUCCESS : CHANGE_FAILED);
                        }
                        break;
                    case '4':
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

    public static void deleteUser(int id) {

        int index = convertIdToIndex(id);
        System.out.println(userList[index].toString());
        System.out.println("Are you sure you want to delete this user?");
        if (SharedMethods.getYesNoChoice()) {
            if (userList[index].deleteUser()) {
                userList = ArrayUtils.remove(userList, index);
                System.out.println(CHANGE_SUCCESS);
            } else {
                System.out.println(CHANGE_FAILED);
            }
        }
    }


    public static int getIdFromUser() {

        String id = new String();
        System.out.println("Enter account's ID, or enter 0 to exit");

        while (true) {
            id = userInput.nextLine();
            if (NumberUtils.isDigits(id)) {
                if (User.getUserById(Integer.parseInt(id)) != null || Integer.parseInt(id) == 0) {
                    return Integer.parseInt(id);
                }
            }
            System.out.println("Invalid ID number, please try again");
        }
    }

    public static int convertIdToIndex(int id) {
        for (int i = 0; i < userList.length; i++) {
            if (userList[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
