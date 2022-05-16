package frames;

import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import classes.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LoginFrame extends JDialog {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton registrationButton;
    private JPanel loginPanel;
    private JPanel registrationPanel;
    private JTextField registrationUsernameField;
    private JTextField registrationPasswordField;
    private JButton registrationRegistrationButton;
    private JPanel overall;
    static Logger logger = LoggerFactory.getLogger(LoginFrame.class);



    public LoginFrame() {
        setTitle("Login");
        setContentPane(overall);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registrationPanel.setVisible(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Login button clicked.");
                if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    user = getAuthenticatedUser(username, password);
                    if (user.username != null && user.password != null) {
                        MySQLConnect.connectedUSer = user;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this,
                                "Wrong username or password.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Registration button clicked.");
                loginPanel.setVisible(false);
                registrationPanel.setVisible(true);
            }
        });

        registrationRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Registration button clicked on registration.");
                if (!registrationUsernameField.getText().isEmpty() && !registrationUsernameField.getText().isEmpty()) {
                    String username = registrationUsernameField.getText();
                    String password = registrationPasswordField.getText();
                    registerUser(username, password);
                    registrationPanel.setVisible(false);
                    loginPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

    public Users user;

    private Users getAuthenticatedUser(String programUsername, String programPassword) {
        Users user = new Users();

        try {
            String sql = "SELECT * FROM users WHERE username='"
                    + programUsername + "' AND password='" + programPassword + "';";
            ResultSet resultSet = MySQLConnect.executeQuery(sql);
            if (resultSet.next()) {
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
                user.id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private void registerUser(String programUsername, String programPassword){
        try {
            logger.info(programPassword);
            logger.info(programUsername);
            String sql = "INSERT INTO users (username, password) VALUES('"+programUsername+"','"+programPassword+"');";
            logger.info(sql);
            MySQLConnect.modifyDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
