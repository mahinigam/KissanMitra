package com.example.webapp;

import java.sql.*;

public class UserDAO {

    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO farmers (name, email, password, gender, state, district, pincode, identity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getGender());
            stmt.setString(5, user.getState());
            stmt.setString(6, user.getDistrict());
            stmt.setString(7, user.getPincode());
            stmt.setString(8, user.getIdentity());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public User loginUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM farmers WHERE email = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setState(rs.getString("state"));
                user.setDistrict(rs.getString("district"));
                user.setPincode(rs.getString("pincode"));
                user.setIdentity(rs.getString("identity"));
                return user;
            }
        }
        return null;
    }
}
