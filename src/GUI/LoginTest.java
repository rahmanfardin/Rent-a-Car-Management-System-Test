package GUI;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private Login loginFrame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton, closeButton;

    @Before
    public void setUp() {
        loginFrame = new Login();
        usernameTextField = loginFrame.UN_TextField;
        passwordField = loginFrame.Password_Field;
        loginButton = loginFrame.Login_Button;
        closeButton = loginFrame.Close_Button;
    }

    @Test
    public void testLoginButton() {
        assertTrue(loginButton.isVisible());
    }

    @Test
    public void testCloseButton() {
        assertTrue(closeButton.isVisible());
    }

    @Test
    public void testValidLogin() {
        usernameTextField.setText("admin");
        passwordField.setText("123");
        loginButton.doClick();
        assertEquals("", usernameTextField.getText());
        assertEquals("", String.valueOf(passwordField.getPassword()));
    }

    @Test
    public void testInvalidLogin() {
        usernameTextField.setText("wrongUser");
        passwordField.setText("wrongPass");
        loginButton.doClick();
        
        String expectedMessage = "Invalid UserName/Password";
        String actualMessage = getLatestJOptionPaneMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUsernameFields() {
        usernameTextField.setText("");
        assertEquals("", usernameTextField.getText());
    }
    
    @Test
    public void testPasswordFields() {
        passwordField.setText("");
        assertEquals("", String.valueOf(passwordField.getPassword()));
    }

    private String getLatestJOptionPaneMessage() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                for (Component component : dialog.getContentPane().getComponents()) {
                    if (component instanceof JOptionPane) {
                        JOptionPane pane = (JOptionPane) component;
                        return pane.getMessage().toString();
                    }
                }
            }
        }
        return null;
    }
}
