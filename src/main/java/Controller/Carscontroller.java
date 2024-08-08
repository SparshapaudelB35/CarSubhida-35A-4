/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Acer
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAO.CarsDAO;
import Model.Carsmodel;
import Model.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import View.Cars1;

/**
 *
 * @author Acer
 */
public class Carscontroller {
    private final Cars1 c;
    private final CarsDAO carsdao;
    
     public Carscontroller( Cars1 c) {
        this.c = c;
        Connection conn = null;
        try {
            conn = Database.getConnection();
            this.carsdao= new CarsDAO(conn);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(c, "Error establishing database connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Unable to establish database connection.", e);
        }
        
        c.getAddbtn().addActionListener(new AddButtonListener());
        c.getUpbtn().addActionListener(new UpbtnListener());
        c.getDltbtn().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DltbtnMouseClicked(evt);
            }
        });
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) c.getCarsTable().getModel();
    
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
        try (PreparedStatement ps = carsdao.getConnection().prepareStatement("SELECT * FROM cardetials")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[] {
                        rs.getString("RegistrationNO"),
                        rs.getString("Model"),
                        rs.getString("Brand"),
                        rs.getString("Year"),
                        rs.getString("Price"),
                        rs.getString("Status")
                    };
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(c, "Error fetching data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
    String model = c.getModel().getText().trim();
    String brand = c.getBrand().getText().trim();
    String year = c.getYear().getText().trim();
    String price = c.getPrice().getText().trim();
    String status = (String) c.getStatus().getSelectedItem(); // Assuming GenderC is a JComboBox with gender options
    String registration= c.getRegister().getText().trim();

    
    if (model.isEmpty() || brand.isEmpty() || year.isEmpty() || price.isEmpty() || status == null || registration.isEmpty()) {
        JOptionPane.showMessageDialog(c, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    Carsmodel cm=new Carsmodel(registration,model,brand,year, price,status);
 try {
        if (!carsdao.userExists(registration)) {
        } else {
            JOptionPane.showMessageDialog(c, "User with ID " + registration+ " already exists.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
            return;
        }
        carsdao.addCar(cm);
        JOptionPane.showMessageDialog(c,"User added successfully");
         loadTableData();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(c, "Error checking existing user data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
        
        c.getModel().setText("");
     c.getYear().setText("");
     c.getBrand().setText("");
     c.getPrice().setText("");
     c.getRegister().setText("");
    }                      
    
  private void UpbtnActionPerformed(java.awt.event.ActionEvent evt) {                                      
        String model = c.getModel().getText().trim();
    String brand = c.getBrand().getText().trim();
    String year = c.getYear().getText().trim();
    String price = c.getPrice().getText().trim();
    String status = (String) c.getStatus().getSelectedItem(); // Assuming GenderC is a JComboBox with gender options
    String registration= c.getRegister().getText().trim();

        if (price.isEmpty()) {
            JOptionPane.showMessageDialog(c, "Price can't be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Carsmodel cm=new Carsmodel(registration,model,brand,year, price,status);
        String selectedId = c.getSelectedRowId();
         try {
             if (!carsdao.userExists(registration)) {
                JOptionPane.showMessageDialog(c, "User with ID " + registration + " does not exist.", "User Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            }
        carsdao.updateCars(cm);
        JOptionPane.showMessageDialog(c,"Cars updated successfully");
         loadTableData();
         }catch (SQLException e) {
        JOptionPane.showMessageDialog(c, "Error checking existing car data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return;}
      
    c.getModel().setText("");
     c.getYear().setText("");
     c.getBrand().setText("");
     c.getPrice().setText("");
     c.getRegister().setText("");
    }     
       
       
       
     private void DltbtnMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
     String register = c.getRegister().getText().trim(); 

    if (register.isEmpty()) {
        JOptionPane.showMessageDialog(c, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(c, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            if (carsdao.userExists(register)) {
                carsdao.deleteUser(register);
                JOptionPane.showMessageDialog(c, "Record deleted successfully!");
                 loadTableData();
               
            } else {
                JOptionPane.showMessageDialog(c, "No record found with the provided Registration No.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(c, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     
       c.getModel().setText("");
     c.getYear().setText("");
     c.getBrand().setText("");
     c.getPrice().setText("");
     c.getRegister().setText("");
       
}
}

