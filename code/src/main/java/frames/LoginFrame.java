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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Login button clicked.");
                login();

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
                logger.info("Registration button clicked on registration panel.");
                if (!registrationUsernameField.getText().isEmpty() && !registrationUsernameField.getText().isEmpty()) {
                    String username = registrationUsernameField.getText();
                    String password = registrationPasswordField.getText();
                    registerUser(username, password);
                    logger.info("User created and stored in database successfully.");
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
                getTableSize();
                logger.info("Table successfully created.");
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
            String sql = "INSERT INTO users (username, password) VALUES('"
                    + programUsername + "','" + programPassword + "');";
            MySQLConnect.modifyDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTableSize() {
        logger.info("Requesting table size from database.");
        try {
            String query = "SELECT COUNT(*) AS total FROM spaceships where uid ='"
                    + MySQLConnect.connectedUSer.id + "';";
            ResultSet res = MySQLConnect.executeQuery(query);
            res.next();
            int size = res.getInt(1);
            prepareDataForTable(size);
            logger.info("Request successful.");
        } catch (Exception x) {

            x.printStackTrace();
        }
    }

    private void prepareDataForTable(int size) {
        try {
            logger.info("Preparing data for table.");
            String query = "SELECT spaceships.type , spaceships.fuel, spaceships.consumption, spaceships.price,\n" +
                    "weapons.name AS 'Weapon', \n" +
                    "power_plants.name AS 'Power Plant',\n" +
                    "quantum_drives.name AS 'Quantum Drive' \n" +
                    "FROM((spaceships INNER JOIN weapons ON spaceships.weapon = weapons.id)\n" +
                    "INNER JOIN power_plants ON spaceships.power_plant = power_plants.id)\n" +
                    "INNER JOIN quantum_drives ON spaceships.quantum_drive = quantum_drives.id\n" +
                    "WHERE spaceships.uid ='" + MySQLConnect.connectedUSer.id + "';";

            ResultSet res = MySQLConnect.executeQuery(query);
            String columns[] = {"Type", "Fuel", "Consumption", "Price",
                    "Weapon", "Power Plant", "Quantum drive"};
            String data[][] = new String[size][7];
            int i = 0;
            while (res.next()) {
                String type = res.getString("type");
                String fuel = res.getString("fuel");
                int consumption = res.getInt("consumption");
                int price = res.getInt("price");
                String weapon = res.getString("Weapon");
                String powerPlant = res.getString("Power Plant");
                String quantumDrive = res.getString("Quantum Drive");

                data[i][0] = type;
                data[i][1] = fuel;
                data[i][2] = consumption + "";
                data[i][3] = price + "";
                data[i][4] = weapon;
                data[i][5] = powerPlant;
                data[i][6] = quantumDrive;

                i++;
            }
            createShipTable(data, columns);
            logger.info("Preparation successful.");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void createShipTable(String data[][], String columns[]) {
        logger.info("Creating table.");
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
        this.setVisible(false);
        LoginFrame temp = this;
        f.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                f.dispose();
                temp.setVisible(true);
            }
        });
    }

    private void login(){
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            user = getAuthenticatedUser(username, password);
            if (user.username != null && user.password != null) {
                MySQLConnect.connectedUSer = user;
                logger.info("User logged in.");
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
}
