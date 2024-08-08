/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DAO.BookDAO;

import Model.BookModel;
import Model.Database;

import View.BookProf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class BookController {
    private final BookProf b;
    private final BookDAO bookdao;
    
     public BookController(BookProf b) {
        this.b = b;
        Connection conn = null;
        try {
            conn = Database.getConnection();
            this.bookdao= new BookDAO(conn);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(b, "Error establishing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Unable to establish database connection.", e);
        }
        
        b.getAddbtn().addActionListener(new AddButtonListener());
        b.getUpbtn().addActionListener(new UpbtnListener());
        b.getDltbtn().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DltbtnMouseClicked(evt);
            }
        });
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) b.getBookTable().getModel();
    
    // Clear the existing data from the table
    model.setRowCount(0);
    
    // Retrieve new data from your database or wherever it's stored
    List<Object[]> newData = fetchDataFromDatabase();
    
    // Populate the table with the new data
    for (Object[] rowData : newData) {
        model.addRow(rowData);
    }
}

// Method to fetch data from the database, replace it with your actual implementation
    private List<Object[]> fetchDataFromDatabase() {
        List<Object[]> data = new ArrayList<>();
        try (PreparedStatement ps = bookdao.getConnection().prepareStatement("SELECT * FROM bookprof")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[] {
                        rs.getString("bookid"),
                        rs.getString("Registration"),
                        rs.getString("Customerid"),
                        rs.getString("Name"),
                        rs.getString("PickUp"),
                        rs.getString("DropOff"),
                        rs.getString("Price")
                    };
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(b, "Error fetching data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
     private class AddButtonListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            AddbtnActionPerformed(evt);
        }
    }

  private class UpbtnListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            UpbtnActionPerformed(evt);
        }
    }
  

  
      private void AddbtnActionPerformed(java.awt.event.ActionEvent evt) {  
          String book= b.getBookID().getText().trim();
    String register = (String) b.getRegBox().getSelectedItem();
    String idcust = (String) b.getIDbox().getSelectedItem();
    String datedrop = b.getDropDate().getText().trim();
    String datepick = b.getPickDate().getText().trim();
    
    if (book.isEmpty()){
        JOptionPane.showMessageDialog(b, "BookID can't be left empty", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (register.isEmpty() || idcust.isEmpty() || datedrop.isEmpty() || datepick.isEmpty()) {
        JOptionPane.showMessageDialog(b, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    BookModel u=new BookModel(book, register,idcust,datepick,datedrop);
 try {
        if (bookdao.BookExists(book)) {
            JOptionPane.showMessageDialog(b, "BookID " + book + " already Exists", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (bookdao.isBookingExists(register, idcust)) {
            JOptionPane.showMessageDialog(b, "Car " + register + " or Customer " + idcust + " is already booked", "Duplicate Booking", JOptionPane.WARNING_MESSAGE);
            return;
        }
        bookdao.addbook(u);
        JOptionPane.showMessageDialog(b,"User added successfully");
        loadTableData();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(b, "Error checking existing data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        b.getBookID().setText("");
      
     b.getDropDate().setText("");
     b.getPickDate().setText("");
    }                      
    
 private void UpbtnActionPerformed(java.awt.event.ActionEvent evt) {
    String book = b.getBookID().getText().trim();
    String register = (String) b.getRegBox().getSelectedItem();
    String idcust = (String) b.getIDbox().getSelectedItem();
    String datedrop = b.getDropDate().getText().trim();
    String datepick = b.getPickDate().getText().trim();

    if (book.isEmpty() || register == null || idcust == null || datedrop.isEmpty() || datepick.isEmpty()) {
        JOptionPane.showMessageDialog(b, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    BookModel u = new BookModel(book, register, idcust, datepick, datedrop);

    try {
        if (bookdao.isBookingExists(register, idcust) && !register.equals(bookdao.getRegistrationByBookID(book)) && !idcust.equals(bookdao.getCustomerIDByBookID(book))) {
            JOptionPane.showMessageDialog(b, "Car " + register + " or Customer " + idcust + " is already booked", "Duplicate Booking", JOptionPane.WARNING_MESSAGE);
            return;
        }
        bookdao.updatebook(u);
        JOptionPane.showMessageDialog(b, "Booking updated successfully");
        loadTableData();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(b, "Error updating booking: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }


    // Clear the input fields after successful update
    b.getBookID().setText("");
    b.getDropDate().setText("");
    b.getPickDate().setText("");
}

       
       
       
     private void DltbtnMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
     String book = b.getBookID().getText().trim();

    int confirm = JOptionPane.showConfirmDialog(b, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            if (bookdao.BookExists(book)) {
                bookdao.deletebook(book);
                JOptionPane.showMessageDialog(b, "Record deleted successfully!");
                 loadTableData();
               
            } else {
                JOptionPane.showMessageDialog(b, "No record found with the provided ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(b, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     
     b.getBookID().setText("");
     b.getDropDate().setText("");
     b.getPickDate().setText("");
       
}
}

