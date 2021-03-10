package pl.coderslab.workshop02;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {
    private static final String CREATE = "INSERT INTO users VALUES (null, ?, ?, ?);";
    private static final String READ_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String READ_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static DBUtil dBUtil = new DBUtil("mysql://localhost:3306", "root", "ENTER YOUR PASSWORD HERE", "workshop2");


    public static void create(User user) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            } else {
                user.setId(-1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            user.setId(-1);
        }
    }

    public User read(int id) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(READ_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User read(String email) {

        User user = null;

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(READ_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                rs.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(User user) {
        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User[] findAll() {

        User[] userList = new User[0];

        try (Connection conn = dBUtil.connect();
        PreparedStatement ps = conn.prepareStatement(FIND_ALL);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                userList = Arrays.copyOf(userList, userList.length + 1);
                userList[userList.length - 1] = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            return userList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
