package Controller;

import View.Signup;
import View.Login;  // Import the Login class
import Model.Admin;
import DAO.AdminDAO;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SignupController {

    private final Signup view;
    private final AdminDAO adminDao;

    public SignupController(Signup view) {
        this.view = view;
        this.adminDao = new AdminDAO();
        
    }

// public void signupBTNActionPerformed(MouseEvent evt) {
//    String username = view.getUsername().getText().trim();
//    String email = view.getEmail().getText().trim();
//    String password = new String(view.getPassword().getPassword()).trim();
//    String contact = view.getContact().getText().trim();
//    String gender = view.getGenderBox().getSelectedItem().toString();
//    String maritalStatus = view.getMaritalBox().getSelectedItem().toString();
//    String citizenshipId = view.getIdBox().getText().trim();
//
//    // Validate input
//    if (username.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty() || citizenshipId.isEmpty()) {
//        JOptionPane.showMessageDialog(view, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    if (!email.endsWith("@gmail.com")) {
//        JOptionPane.showMessageDialog(view, "Please enter a valid Gmail address \"@gmail.com\"", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    if (password.length() < 8) {
//        JOptionPane.showMessageDialog(view, "Password must be at least 8 characters long", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    if (contact.length() != 10 || !contact.matches("\\d+")) {
//        JOptionPane.showMessageDialog(view, "Please enter a valid 10-digit contact number", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Check if username is taken
//    try {
//        if (adminDao.isUsernameTaken(username)) {
//            JOptionPane.showMessageDialog(view, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(view, "An error occurred while checking username availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Check if email is taken
//    try {
//        if (adminDao.isEmailTaken(email)) {
//            JOptionPane.showMessageDialog(view, "Email already taken", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(view, "An error occurred while checking email availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Check if contact is taken
//    try {
//        if (adminDao.isContactTaken(contact)) {
//            JOptionPane.showMessageDialog(view, "Contact already taken", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(view, "An error occurred while checking contact availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Check if citizenship ID is taken
//    try {
//        if (adminDao.isCitizenshipIdTaken(citizenshipId)) {
//            JOptionPane.showMessageDialog(view, "Citizenship ID already taken", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(view, "An error occurred while checking citizenship ID availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//     //If all checks pass, proceed with user registration
//    Admin admin = new Admin(username, password, contact, email, gender, maritalStatus, citizenshipId);
//    try {
//        boolean isSignedUp = adminDao.registerAdmin(admin);
//
//        if (isSignedUp) {
//            
//            Login loginView = new Login();
//            loginView.setVisible(true);
//            JOptionPane.showMessageDialog(null, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//            // Redirect to login page
//            
//            view.dispose();
//        } else {
//            JOptionPane.showMessageDialog(view, "Sign up failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    } catch (SQLException e) {
//        JOptionPane.showMessageDialog(view, "An error occurred while signing up: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}
 
    public void jLabel12MouseClicked(MouseEvent evt) {
        // Dispose the signup view
        view.dispose();

        // Show the login view
        Login loginView = new Login();
        loginView.setVisible(true);
    }

    public boolean isUsernameTaken(String userName) {
        try {
            // Perform a database query to check if the username exists
            return adminDao.isUsernameTaken(userName);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(view, "An error occurred while checking username availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true; // Assuming the username is taken to prevent further issues
        }
    }
 public boolean isEmailTaken(String email) {
        try {
            // Perform a database query to check if the username exists
            return adminDao.isEmailTaken(email);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(view, "An error occurred while checking username availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true; // Assuming the username is taken to prevent further issues
        }
    }
  public boolean isContactTaken(String contact) {
        try {
            // Perform a database query to check if the username exists
            return adminDao.isContactTaken(contact);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(view, "An error occurred while checking username availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true; // Assuming the username is taken to prevent further issues
        }
    }
   public boolean isCitizenshipIdTaken(String citizenshipId) {
        try {
            // Perform a database query to check if the username exists
            return adminDao.isCitizenshipIdTaken(citizenshipId);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(view, "An error occurred while checking username availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true; // Assuming the username is taken to prevent further issues
        }
    }
    public boolean registerAdmin(String userName, String password, String contact, String email, String gender, String maritalStatus, String citizenshipID) {
        try {
            // Create an Admin object with the provided details
            Admin admin = new Admin(userName, password, contact, email, gender, maritalStatus, citizenshipID);
            // Register the admin using the AdminDAO
            return adminDao.registerAdmin(admin);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(view, "An error occurred while registering user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
