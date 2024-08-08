package DAO;

import Model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    public Connection getConnection(){
        return conn;
    }

   public void addUser(UserModel u) throws SQLException {
        if (contactExists(u.getContact(), null)) {
            throw new SQLException("Contact number is already taken");
        }
        if (licenseExists(u.getLicense(), null)) {
            throw new SQLException("License number is already taken");
        }

        String sql = "INSERT INTO userprof (CustomerID, Name, Contact, Address, LicenseNo, Gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.getID());
            pstmt.setString(2, u.getName());
            pstmt.setString(3, u.getContact());
            pstmt.setString(4, u.getAddress());
            pstmt.setString(5, u.getLicense());
            pstmt.setString(6, u.getGender());
            pstmt.executeUpdate();
        }
    }


     public void updateUser(UserModel u) throws SQLException {
        if (contactExists(u.getContact(), u.getID())) {
            throw new SQLException("Contact number is already taken");
        }
        if (licenseExists(u.getLicense(), u.getID())) {
            throw new SQLException("License number is already taken");
        }

        String sqlSelect = "SELECT Name, Contact, Address, LicenseNo, Gender FROM userprof WHERE CustomerID=?";
        String sqlUpdate = "UPDATE userprof SET Name=?, Contact=?, Address=?, LicenseNo=?, Gender=? WHERE CustomerID=?";
        String id = u.getID();
        String name = u.getName();
        String contact = u.getContact();
        String address = u.getAddress();
        String license = u.getLicense();
        String gender = u.getGender();

        try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
            pstmtSelect.setString(1, u.getID());
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    // Get existing values from the database
                    String existingName = rs.getString("Name");
                    String existingContact = rs.getString("Contact");
                    String existingAddress = rs.getString("Address");
                    String existingLicense = rs.getString("LicenseNo");
                    String existingGender = rs.getString("Gender");

                    if (name.isEmpty()) {
                        name = existingName;
                    }
                    if (address.isEmpty()) {
                        address = existingAddress;
                    }
                    if (contact.isEmpty()) {
                        contact = existingContact;
                    }
                    if (license.isEmpty()) {
                        license = existingLicense;
                    }
                    if (gender.isEmpty()) {
                        gender = existingGender;
                    }
                }
            }
        }

        // Perform the update with the modified parameters
        try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
            pstmtUpdate.setString(1, name);
            pstmtUpdate.setString(2, contact);
            pstmtUpdate.setString(3, address);
            pstmtUpdate.setString(4, license);
            pstmtUpdate.setString(5, gender);
            pstmtUpdate.setString(6, id); // Set ID for WHERE clause
            pstmtUpdate.executeUpdate();
        }
    }

    public void deleteUser(String id) throws SQLException {
        String sql = "DELETE FROM userprof WHERE CustomerID=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }



  public boolean userExists(String id) throws SQLException {
    // Query the database to check if the user with the given ID exists
    try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM userprof WHERE CustomerID = ?")) {
        pstmt.setString(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a user with the given ID exists, false otherwise
        }
    }
}

 public boolean contactExists(String contact, String excludeCustomerID) throws SQLException {
        String sql = "SELECT * FROM userprof WHERE Contact = ?";
        if (excludeCustomerID != null) {
            sql += " AND CustomerID != ?";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contact);
            if (excludeCustomerID != null) {
                pstmt.setString(2, excludeCustomerID);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Return true if a user with the given contact number exists, false otherwise
            }
        }
    }

 public boolean licenseExists(String license, String excludeCustomerID) throws SQLException {
        String sql = "SELECT * FROM userprof WHERE LicenseNo = ?";
        if (excludeCustomerID != null) {
            sql += " AND CustomerID != ?";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, license);
            if (excludeCustomerID != null) {
                pstmt.setString(2, excludeCustomerID);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Return true if a user with the given license number exists, false otherwise
            }
        }
    }
}
