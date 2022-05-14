package frames;

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
    private JButton guestButton;
    private JPanel loginPanel;

    public LoginFrame(){
        setTitle("Login");
        setContentPane(loginPanel);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = passwordField.getText();

                    user = getAuthenticatedUser(username,password);

                    if (user != null){
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Wrong username or password.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
        //loginPanel.setVisible(false);
    }
    public Users user;
    private Users getAuthenticatedUser(String programUsername, String programPassword){
        Users user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/progtech";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,programUsername);
            preparedStatement.setString(2,programPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
            }
            statement.close();
            connection.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
