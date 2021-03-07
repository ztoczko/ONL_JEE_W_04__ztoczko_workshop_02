package pl.coderslab.workshop02;

import java.sql.*;

public class UserDAO {
    private static final String CREATE = "INSERT INTO users VALUES (null, ?, ?, ?);";
    private static final String READ = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static DBUtil dBUtil= new DBUtil();


    public static void create(User user) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){
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


    public boolean update (User user){
        return true;
    }


    public static boolean isEmailUnique(String email) {
        return true;
    }


}
