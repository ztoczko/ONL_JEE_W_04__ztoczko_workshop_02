package pl.coderslab.workshop02;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String email;
    private String username;
    private String password;
    private static UserDAO userDAO = new UserDAO();

    public User(String email, String username, String password) {

        this.email = email; //weryfikacja emaila
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        UserDAO.create(this);
    }

    User(int id, String email, String username, String password) {
        this.id = id;
        this.email = email; //weryfikacja emaila
        this.username = username;
        this.password = password;
    }

    public static User getUserById(int id) {
        return userDAO.read(id);
    }

    public static User getUserByEmail(String email) {
        return userDAO.read(email);
    }

    public boolean deleteUser() {
        return userDAO.delete(id);
    }

    public static User[] getAllUsers() {
        return userDAO.findAll();
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {  //weryfikacja emaila

        String backup = this.email;
        this.email = email;
        if (!userDAO.update(this)) {
            this.email = backup;
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {

        String backup = this.username;
        this.username = username;
        if (!userDAO.update(this)) {
            this.username = backup;
            return false;
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        String backup = this.password;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        if (!userDAO.update(this)) {
//            System.out.println("Error while updating user's password in database, last change has been reverted");
            this.password = backup;
            return false;
        }
        return true;
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public static boolean isEmailUnique(String email) {
        return userDAO.read(email) == null ? true : false;
    }

    public boolean equals(User user) {
        return user.id == this.id ? true : false;
    }

    public String toString() {
        return "id: " + id + ", username: " + username + ", e-mail: " + email;
    }


}
