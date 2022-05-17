package frames;

import classes.Controller.LoginController;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import classes.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LoginFrame extends JDialog {
    public JTextField usernameField;
    public JTextField passwordField;
    private JButton loginButton;
    private JButton registrationButton;
    public JPanel loginPanel;
    public JPanel registrationPanel;
    public JTextField registrationUsernameField;
    public JTextField registrationPasswordField;
    private JButton registrationRegistrationButton;
    private JPanel overall;
    public JPanel choicePanel;
    private JButton createNewShipButton;
    private JButton checkOrdersButton;
    public static Logger logger = LoggerFactory.getLogger(LoginFrame.class);
    private LoginController controller;

    public LoginFrame(boolean first) {
        setTitle("Login");
        setContentPane(overall);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if (first) {
            registrationPanel.setVisible(false);
            choicePanel.setVisible(false);
        } else {
            loginPanel.setVisible(false);
            registrationPanel.setVisible(false);
        }

        controller = new LoginController(this);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loginButtonClicked();
            }
        });

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrationButtonClicked();
            }
        });

        registrationRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrationRegistrationButtonClicked();
            }
        });

        createNewShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createNewShipButtonClicked();
            }
        });

        checkOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkOrdersButtonClicked();
            }
        });

        setVisible(true);
    }
}
