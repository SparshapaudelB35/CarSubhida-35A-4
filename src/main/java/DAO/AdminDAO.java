package DAO;

import Model.Admin;
import Model.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public boolean registerAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin (username, password, contact, email, gender, maritalStatus, citizenshipId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getContact());
            stmt.setString(4, admin.getEmail());
            stmt.setString(5, admin.getGender());
            stmt.setString(6, admin.getMaritalStatus());
            stmt.setString(7, admin.getCitizenshipId());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            // Log the error or handle it as needed
            e.printStackTrace();
            throw e; // Re-throw the exception to indicate the failure to register
        }
    }
    
    public boolean isUsernameTaken(String userName) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM admin WHERE username = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }

 public boolean isEmailTaken(String email) throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM admin WHERE email = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0; 
            }
        }
    }
    return false;
}

public boolean isContactTaken(String contact) throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM admin WHERE contact = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, contact);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        }
    }
    return false;
}

public boolean isCitizenshipIdTaken(String citizenshipId) throws SQLException {
    String sql = "SELECT COUNT(*) AS count FROM admin WHERE citizenshipid = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, citizenshipId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        }
    }
    return false;
}
}
