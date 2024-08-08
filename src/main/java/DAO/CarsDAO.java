/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Carsmodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsDAO {
    private final Connection conn;

    public CarsDAO(Connection conn) {
        this.conn = conn;
    }
    public Connection getConnection(){
        return conn;
    }

    public void addCar(Carsmodel u) throws SQLException {
        String sql = "INSERT INTO cardetials (RegistrationNo, Model, Brand, Year, Price, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.getRegister());
            pstmt.setString(2, u.getModel());
            pstmt.setString(3, u.getBrand());
            pstmt.setString(4, u.getYear());
            pstmt.setString(5, u.getPrice());
            pstmt.setString(6, u.getStatus());
            pstmt.executeUpdate();
        }
    }

 public void updateCars(Carsmodel u) throws SQLException {
    String sqlSelect = "SELECT Model, Brand,  Year,Price, Status FROM cardetials WHERE RegistrationNo=?";
    String sqlUpdate = "UPDATE cardetials SET Model=?, Brand=?,  Year=?,Price=?, Status=? WHERE RegistrationNo=?";
    String register= u.getRegister();
    String model = u.getModel();
    String brand = u.getBrand();
    String year = u.getYear();
    String price = u.getPrice();
    String status = u.getStatus();
    try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
        pstmtSelect.setString(1, u.getRegister());
        try (ResultSet rs = pstmtSelect.executeQuery()) {
            if (rs.next()) {
                // Get existing values from the database
                String existingmodel = rs.getString("Model");
                String existingbrand = rs.getString("Brand");
                String existingyear = rs.getString("Year");
                String existingprice = rs.getString("Price");
                String existingstatus = rs.getString("Status");

                if (model.isEmpty()) {
                    model = existingmodel;
                }
                if (brand.isEmpty()) {
                    brand = existingbrand;
                }
                if (year.isEmpty()) {
                    year = existingyear;
                }
                 if (price.isEmpty()) {
                    price= existingprice;
                }
                if (status.isEmpty()) {
                    status = existingstatus;
                }
            }
        }
    }

    // Perform the update with the modified parameters
    try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
        pstmtUpdate.setString(1, model);
        pstmtUpdate.setString(2, brand);
        pstmtUpdate.setString(3, year);
        pstmtUpdate.setString(4, price);
        pstmtUpdate.setString(5, status);
        pstmtUpdate.setString(6, register); 
        pstmtUpdate.executeUpdate();
    }
}

    public void deleteUser(String register) throws SQLException {
        String sql = "DELETE FROM cardetials WHERE RegistrationNo=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, register);
            pstmt.executeUpdate();
        }
    }

    // Other methods like getUser, etc.

   public boolean userExists(String registration) throws SQLException {
    // Query the database to check if the user with the given ID exists
    try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cardetials WHERE RegistrationNo = ?")) {
        pstmt.setString(1, registration);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a user with the given ID exists, false otherwise
        }
    }
}


}

