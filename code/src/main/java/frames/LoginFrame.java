package frames;

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
    private JPanel choicePanel;
    private JButton createNewShipButton;
    private JButton checkOrdersButton;
    static Logger logger = LoggerFactory.getLogger(LoginFrame.class);


    public LoginFrame() {
        setTitle("Login");
        setContentPane(overall);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registrationPanel.setVisible(false);
        choicePanel.setVisible(false);
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
                        loginPanel.setVisible(false);
                        choicePanel.setVisible(true);
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

        createNewShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Create ship button pressed.");
                dispose();
                ConfigFrame conf = new ConfigFrame();
            }
        });

        checkOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Check orders button pressed");
                try {
                    logger.info(Integer.toString(MySQLConnect.connectedUSer.id));
                    String query = "SELECT COUNT(*) AS total FROM spaceships where uid ='" + MySQLConnect.connectedUSer.id + "';";
                    ResultSet res = MySQLConnect.executeQuery(query);
                    res.next();
                    int size = res.getInt(1);

                    query = "SELECT * FROM spaceships WHERE uid ='" + MySQLConnect.connectedUSer.id + "';";
                    res = MySQLConnect.executeQuery(query);

                    String columns[] = {"Type", "Fuel", "Consumption", "Price", "Weapon", "Power Plant", "Quantum drive"};
                    String data[][] = new String[size][7];
                    int i = 0;
                    while (res.next()) {
                        String type = res.getString("type");
                        String fuel = res.getString("fuel");
                        int consumption = res.getInt("consumption");
                        int price = res.getInt("price");
                        int weapon = res.getInt("weapon");
                        int powerPlant = res.getInt("power_plant");
                        int quantumDrive = res.getInt("quantum_drive");
                        data[i][0] = type;
                        data[i][1] = fuel;
                        data[i][2] = consumption + "";
                        data[i][3] = price + "";
                        data[i][4] = weapon + "";
                        data[i][5] = powerPlant + "";
                        data[i][6] = quantumDrive + "";

                        i++;
                    }

                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    JTable table = new JTable(model);
                    table.setShowGrid(true);
                    table.setShowVerticalLines(true);
                    JScrollPane pane = new JScrollPane(table);
                    JFrame f = new JFrame("Populate JTable from Database");
                    JPanel panel = new JPanel();
                    panel.add(pane);
                    f.add(panel);
                    f.setSize(500, 250);
                    f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    f.setVisible(true);

                } catch (Exception x) {
                    x.printStackTrace();
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

    private void registerUser(String programUsername, String programPassword) {
        try {
            logger.info(programPassword);
            logger.info(programUsername);
            String sql = "INSERT INTO users (username, password) VALUES('" + programUsername + "','" + programPassword + "');";
            logger.info(sql);
            MySQLConnect.modifyDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
