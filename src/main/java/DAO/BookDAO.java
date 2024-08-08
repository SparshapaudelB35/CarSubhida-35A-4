/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Acer
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Model.BookModel;
import static com.mysql.cj.util.TimeUtil.DATE_FORMATTER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BookDAO {
    private final Connection conn;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public BookDAO(Connection conn) {
        this.conn = conn;
    }
    public Connection getConnection(){
        return conn;
    }

    public void addbook(BookModel u) throws SQLException {
        // Check if the registration number or customer ID is already taken
        if (isRegistrationTaken(u.getRegBox(), null)) {
            throw new SQLException("Registration number is already taken");
        }
        if (isCustomerIDTaken(u.getIDbox(), null)) {
            throw new SQLException("Customer ID is already taken");
        }

        String sql = "INSERT INTO bookprof (bookid, Registration, Customerid,Name, PickUp, DropOff, Price) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.getBookID());
            pstmt.setString(2, u.getRegBox());
            pstmt.setString(3, u.getIDbox());
            String name= getNameByID(u.getIDbox());
            pstmt.setString(4,name);
            pstmt.setString(5, u.getPickDate());
            pstmt.setString(6, u.getDropDate());
             double price = calculateTotalPrice(u.getRegBox(), u.getPickDate(), u.getDropDate());
            pstmt.setDouble(7, price);

            pstmt.executeUpdate();
        }
    }
 public void updatebook(BookModel u) throws SQLException {
        if (u == null) {
            throw new IllegalArgumentException("BookModel cannot be null");
        }

        String registration = u.getRegBox();
        if (isRegistrationTaken(registration, u.getBookID())) {
            throw new SQLException("Registration number is already taken");
        }
        if (isCustomerIDTaken(u.getIDbox(), u.getBookID())) {
            throw new SQLException("Customer ID is already taken");
        }

        String sqlUpdate = "UPDATE bookprof SET ";
        boolean updateCustomerid = false;
        boolean updateRegistration = false;
        boolean updatePickUp = false;
        boolean updateDropOff = false;

        if (u.getIDbox() != null && !u.getIDbox().isEmpty()) {
            sqlUpdate += "Customerid=?, Name=?, ";
            updateCustomerid = true;
        }
        if (u.getRegBox() != null && !u.getRegBox().isEmpty()) {
            sqlUpdate += "Registration=?, Price=?, ";
            updateRegistration = true;
        }
        if (u.getPickDate() != null && !u.getPickDate().isEmpty()) {
            sqlUpdate += "PickUp=?, ";
            updatePickUp = true;
        }
        if (u.getDropDate() != null && !u.getDropDate().isEmpty()) {
            sqlUpdate += "DropOff=?, ";
            updateDropOff = true;
        }

        if (!updateCustomerid && !updateRegistration && !updatePickUp && !updateDropOff) {
            throw new IllegalArgumentException("Nothing to update");
        }

        sqlUpdate = sqlUpdate.substring(0, sqlUpdate.length() - 2); // Remove the extra ", "
        sqlUpdate += " WHERE bookid=?";

        try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {
            int index = 1;
            if (updateCustomerid) {
                pstmtUpdate.setString(index++, u.getIDbox());
                String name = getNameByID(u.getIDbox());
                pstmtUpdate.setString(index++, name);
            }
            if (updateRegistration) {
                pstmtUpdate.setString(index++, u.getRegBox());
                double price = calculateTotalPrice(u.getRegBox(), u.getPickDate(), u.getDropDate());
                pstmtUpdate.setDouble(index++, price);
            }
            if (updatePickUp) {
                pstmtUpdate.setString(index++, u.getPickDate());
            }
            if (updateDropOff) {
                pstmtUpdate.setString(index++, u.getDropDate());
            }
            pstmtUpdate.setString(index, u.getBookID());
            pstmtUpdate.executeUpdate();
        }
    }



    public void deletebook(String register) throws SQLException {
        String sql = "DELETE FROM bookprof WHERE bookid=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, register);
            pstmt.executeUpdate();
        }
    }
public String getRegistrationByBookID(String bookID) throws SQLException {
    String sql = "SELECT Registration FROM bookprof WHERE bookid = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, bookID);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Registration");
            } else {
                // Handle case where book ID doesn't exist
                return null; // Or throw an exception
            }
        }
    }
}
public String getCustomerIDByBookID(String bookID) throws SQLException {
    String sql = "SELECT Customerid FROM bookprof WHERE bookid =?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, bookID);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Customerid");
            } else {
                // Handle case where book ID doesn't exist
                return null; // Or throw an exception
            }
        }
    }
}
    // Other methods like getUser, etc.

   public boolean BookExists(String book) throws SQLException {
    // Query the database to check if the user with the given ID exists
    try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bookprof WHERE bookid = ?")) {
        pstmt.setString(1, book);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a user with the given ID exists, false otherwise
        }
    }
   }

    public boolean CarExists(String register) throws SQLException {
          try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bookprof WHERE bookid = ?")) {
        pstmt.setString(1, register);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a user with the given ID exists, false otherwise
        }
    }
    }

    public boolean CustomerExists(String idcust) throws SQLException {
               try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bookprof WHERE bookid = ?")) {
        pstmt.setString(1, idcust);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a user with the given ID exists, false otherwise
        }
    }
    }

public double getPriceByRegistration(String register) throws SQLException {
    
        String sql = "SELECT Price FROM cardetials WHERE RegistrationNo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, register);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Price");
                } else {
                    // Handle case where registration number doesn't exist
                    return -1; // Or throw an exception
                }
            }
        }
    }
public String getNameByID(String Namee) throws SQLException {
    
        String sql = "SELECT Name FROM userprof WHERE CustomerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Namee);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Name");
                } else {
                    // Handle case where registration number doesn't exist
                    return ""; // Or throw an exception
                }
            }
        }
    }
   private boolean isRegistrationTaken(String registration, String excludeBookID) throws SQLException {
        String sql = "SELECT * FROM bookprof WHERE Registration = ?";
        if (excludeBookID != null) {
            sql += " AND bookid != ?";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, registration);
            if (excludeBookID != null) {
                pstmt.setString(2, excludeBookID);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Return true if registration number is taken, false otherwise
            }
        }
    }

    private boolean isCustomerIDTaken(String customerID, String excludeBookID) throws SQLException {
        String sql = "SELECT * FROM bookprof WHERE Customerid = ?";
        if (excludeBookID != null) {
            sql += " AND bookid != ?";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customerID);
            if (excludeBookID != null) {
                pstmt.setString(2, excludeBookID);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Return true if customer ID is taken, false otherwise
            }
        }
    }
    
public boolean isBookingExists(String registration, String customerID) throws SQLException {
    String sql = "SELECT * FROM bookprof WHERE Registration = ? OR Customerid = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, registration);
        pstmt.setString(2, customerID);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Return true if a booking with the given registration or customer ID exists, false otherwise
        }
    }
}
   private double calculateTotalPrice(String registration, String pickDate, String dropDate) throws SQLException {
        double dailyPrice = getPriceByRegistration(registration);
        LocalDate pickUpDate = LocalDate.parse(pickDate, DATE_FORMATTER);
        LocalDate dropOffDate = LocalDate.parse(dropDate, DATE_FORMATTER);
        long numberOfDays = ChronoUnit.DAYS.between(pickUpDate, dropOffDate);
        return dailyPrice * numberOfDays;
    }
}


