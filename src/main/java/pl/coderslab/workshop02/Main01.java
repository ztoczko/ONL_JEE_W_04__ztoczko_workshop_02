package pl.coderslab.workshop02;


//CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;
//
//        USE workshop2;
//
//        CREATE TABLE users (
//        id INT UNSIGNED AUTO_INCREMENT,
//        email VARCHAR (255) UNIQUE NOT NULL,
//        username VARCHAR(255) NOT NULL,
//        password VARCHAR(60) NOT NULL,
//        PRIMARY KEY (id)
//        );
//
//        DESCRIBE users;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.util.Scanner;

public class Main01 {

    public static void main(String[] args) {

        String a = "aaa";
        String b = a;
        a = "bbb";
        System.out.println(b);

        User user = new User("adadsda", "affsf", "affgs");
        System.out.println(user.toString());

//        Connection conn = DBUtil.connect();
//        Scanner scan = new Scanner(System.in);
//        String password = scan.nextLine();
//        String passwordhashed = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(passwordhashed);
//        System.out.println(passwordhashed.length());
//        password = scan.nextLine();
//        System.out.println(BCrypt.checkpw(password, passwordhashed));
//
//        password = scan.nextLine();
//        passwordhashed = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println(passwordhashed);
//        System.out.println(passwordhashed.length());
//        password = scan.nextLine();
//        System.out.println(BCrypt.checkpw(password, passwordhashed));

    }

}
