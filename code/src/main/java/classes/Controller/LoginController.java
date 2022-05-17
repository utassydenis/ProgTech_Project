package classes.Controller;

import classes.Users;
import database.MySQLConnect;
import frames.ConfigFrame;
import frames.LoginFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoginController {

    private LoginFrame frame;

    public LoginController(LoginFrame f) {
        frame = f;
    }

    public boolean isUsernamePasswordEmpty(String usernameText, String passwordText) {
        if (!usernameText.isEmpty() && !passwordText.isEmpty()) {
            String username = frame.usernameField.getText();
            String password = frame.passwordField.getText();
            loginButtonClicked(username, password);
            return true;

        } else {
            JOptionPane.showMessageDialog(frame,
                    "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void loginButtonClicked(String usr, String pswd) {
        frame.logger.info("Login button clicked.");
        user = getAuthenticatedUser(usr, pswd);
        if (user.username != null && user.password != null) {
            MySQLConnect.connectedUSer = user;
            frame.logger.info("User logged in.");
            frame.loginPanel.setVisible(false);
            frame.choicePanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Wrong username or password.", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Users user;

    public Users getAuthenticatedUser(String programUsername, String programPassword) {
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
            frame.logger.warn(e.getMessage());
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

    private int getTableSize() {
        frame.logger.info("Requesting table size from database.");
        int size = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM spaceships where uid ='"
                    + MySQLConnect.connectedUSer.id + "';";
            ResultSet res = MySQLConnect.executeQuery(query);
            res.next();
            size = res.getInt(1);

            frame.logger.info("Request successful.");
        } catch (Exception x) {

            x.printStackTrace();
        }
        return size;
    }

    private void prepareDataForTable(int size) {
        try {
            frame.logger.info("Preparing data for table.");
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
            frame.logger.info("Preparation successful.");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void createShipTable(String data[][], String columns[]) {
        frame.logger.info("Creating table.");
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
        frame.setVisible(false);
        LoginFrame temp = frame;
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                f.dispose();
                temp.setVisible(true);
            }
        });
    }

    public void registrationButtonClicked() {
        frame.logger.info("Registration button clicked.");
        frame.loginPanel.setVisible(false);
        frame.registrationPanel.setVisible(true);
    }

    public void registrationRegistrationButtonClicked() {
        frame.logger.info("Registration button clicked on registration panel.");
        if (!frame.registrationUsernameField.getText().isEmpty() && !frame.registrationUsernameField.getText().isEmpty()) {
            String username = frame.registrationUsernameField.getText();
            String password = frame.registrationPasswordField.getText();
            registerUser(username, password);
            frame.logger.info("User created and stored in database successfully.");
            frame.registrationPanel.setVisible(false);
            frame.loginPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Please type in a username and password", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createNewShipButtonClicked() {
        frame.logger.info("Create ship button pressed.");
        frame.dispose();
        ConfigFrame conf = new ConfigFrame();
    }

    public void checkOrdersButtonClicked() {
        frame.logger.info("Check orders button pressed");
        prepareDataForTable(getTableSize());
        frame.logger.info("Table successfully created.");
    }
}
