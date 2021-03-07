package pl.coderslab.workshop02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBUtil {
    private static final String DB_NAME = "workshop2";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSH=false&characterSet=utf8";
    private static final String DB_USER = "root";
    private static String DBPassword = new String();


    public /*static*/ Connection connect() {

        if (DBPassword.length() == 0) {
            System.out.println("logging into MySQL server\nlogin: root\nenter password for current session:");
            Scanner userInput = new Scanner(System.in);
            DBPassword = userInput.nextLine();
        }

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DBPassword);
            return conn;
        } catch (SQLException e) {
            if (e.getMessage().equals("Access denied for user 'root'@'localhost' (using password: YES)")) {
                System.out.println("wrong password, login attempt was unsuccessful");
                DBPassword = "";
            } else {
                System.out.println(e.getMessage());
            }
            return null;
        }

    }


}
