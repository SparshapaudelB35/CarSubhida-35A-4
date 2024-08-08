package CARrentalSERVICE;

import javax.swing.UIManager;
import View.Load;
import java.awt.Toolkit;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.FontFormatException;

public class run {
    public static void main(String[] args) throws FontFormatException {
       
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        float scaleFactor = dpi / 96.0f; 
        System.setProperty("sun.java2d.uiScale", String.valueOf(scaleFactor));
        
       
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the Load form
        Load first = new Load();
        first.setLocationRelativeTo(null);
        first.setVisible(true);
    }
}
