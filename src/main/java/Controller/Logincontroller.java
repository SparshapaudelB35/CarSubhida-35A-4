package Controller;
import View.Login;

import DAO.logindao;
import Model.loginmodel;
import View.Functionportal;
import View.UserProf;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Logincontroller {

    private final Login view;
    private final logindao logindao;

    public Logincontroller(Login view) {
        this.view = view;
        this.logindao = new logindao();

        // Add action listener to the login button
        view.getLoginButton().addActionListener(e -> loginButtonClicked());
    }

    private void loginButtonClicked() {
        String email = view.getEmail().getText().trim();
        String password = new String(view.getpassword().getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
            loginmodel log= new loginmodel(email,password);
            try {
            if (logindao.isValidLogin(log)) {
                // Login successful
                JOptionPane.showMessageDialog(view, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Handle navigation to next view
                Functionportal up = new Functionportal();
                up.setLocationRelativeTo(null);
                up.setVisible(true);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "An error occurred while logging in: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}