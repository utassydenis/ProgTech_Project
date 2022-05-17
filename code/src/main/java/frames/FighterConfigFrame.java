package frames;

import classes.Decorator.Offer;
import classes.Spaceship.Spaceship;
import classes.SpaceshipModule.Power_plant;
import classes.SpaceshipModule.Quantum_drive;
import classes.SpaceshipModule.Weapon;
import classes.SpaceshipType.Fighter;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FighterConfigFrame extends JFrame {
    private JLabel ConsumptionLabel;
    private JComboBox QuantumComboBox;
    private JComboBox PowerComboBox;
    private JComboBox WeaponComboBox;
    private JLabel PriceLabel;
    private JPanel FighterConfigPanel;
    private JLabel FuelLabel;
    private JLabel SpeedLabel;
    private JLabel QuantumPriceLabel;
    private JLabel PowerLabel;
    private JLabel PowerPriceLabel;
    private JLabel WeaponTypeLabel;
    private JLabel WeaponDPSLabel;
    private JLabel WeaponPriceLabel;
    private JButton OrderButton;
    private JTextField couponTextField;
    private JCheckBox efficientEngineCheckBox;
    private Spaceship fighter;
    private static Power_plant selectedPower_plant;
    private static Quantum_drive selectedQuantum_drive;
    private static Weapon selectedWeapon;
    Logger logger = LoggerFactory.getLogger(FighterConfigFrame.class);
    private List<Power_plant> power_plants = new ArrayList<>();
    private List<Quantum_drive> quantum_drives = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();

    public FighterConfigFrame(Fighter fighter) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
        logger.info("fighter frame initialized");
        setContentPane(FighterConfigPanel);
        setSize(800, 600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.fighter = fighter;
        loadDataToUI();
        setDefaultComponents();
        setPriceLabel();
        QuantumComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedQuantum_drive();
                    QuantumPriceLabel.setText(Integer.toString(selectedQuantum_drive.getPrice()));
                    SpeedLabel.setText(Integer.toString(selectedQuantum_drive.getSpeed()));
                    setPriceLabel();
                    logger.info("new quantum_drive selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        PowerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedPower_plant();
                    PowerPriceLabel.setText(Integer.toString(selectedPower_plant.getPrice()));
                    PowerLabel.setText(Integer.toString(selectedPower_plant.getPower()));
                    setPriceLabel();
                    logger.info("new power_plant selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        WeaponComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedWeapon();
                    WeaponDPSLabel.setText(Integer.toString(selectedWeapon.getDps()));
                    WeaponPriceLabel.setText(Integer.toString(selectedWeapon.getPrice()));
                    WeaponTypeLabel.setText(selectedWeapon.getType());
                    setPriceLabel();
                    logger.info("new weapon selected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        OrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fighter.setPrice(fighter.getPrice()
                        + selectedPower_plant.getPrice()
                        + selectedQuantum_drive.getPrice()
                        + selectedWeapon.getPrice());
                fighter.setPower_plant(selectedPower_plant.getId());
                fighter.setQuantum_drive(selectedQuantum_drive.getId());
                fighter.setWeapon(selectedWeapon.getId());


                if (couponTextField.getText().equals("BARNA")) {
                    Offer disc = new Offer(fighter);
                    disc.setDiscount(25);
                    fighter.setPrice(disc.getPrice());
                } else if (couponTextField.getText().equals("DENES")) {
                    Offer disc = new Offer(fighter);
                    disc.setDiscount(10);
                    fighter.setPrice(disc.getPrice());
                } else if (couponTextField.getText().equals("EDE")) {
                    Offer disc = new Offer(fighter);
                    disc.setDiscount(100);
                    fighter.setPrice(disc.getPrice());
                }
                if(efficientEngineCheckBox.isSelected()){
                    Offer efficient = new Offer(fighter);
                    efficient.setEfficiency(10);
                    fighter.setConsumption(efficient.getConsumption());
                }

                String spaceship = "INSERT INTO spaceships (" +
                        " type," +
                        " fuel," +
                        " consumption," +
                        " price," +
                        " weapon," +
                        " power_plant," +
                        "quantum_drive," +
                        "uid) " +
                        "VALUES ('" + fighter.getType() + "','"
                        + fighter.getFuel() + "',"
                        + fighter.getConsumption() + ","
                        + fighter.getPrice() + ","
                        + fighter.getWeapon() + ","
                        + fighter.getPower_plant() + ","
                        + fighter.getQuantum_drive() + "," +
                        MySQLConnect.connectedUSer.id + ");";
                logger.info(spaceship);
                try {
                    MySQLConnect.modifyDatabase(spaceship);

                    logger.info("new fighter added to database");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
                LoginFrame log = new LoginFrame(false);

            }
        });
    }

    private void loadDataToUI() throws SQLException {
        PriceLabel.setText(Integer.toString(fighter.getPrice()));
        ConsumptionLabel.setText(Integer.toString(fighter.getConsumption()));
        FuelLabel.setText(fighter.getFuel());
        addItemsToPowerComboBox();
        addItemsToQuantumComboBox();
        addItemsToWeaponComboBox();
        QuantumPriceLabel.setText(Integer.toString(quantum_drives.get(0).getPrice()));
        SpeedLabel.setText(Integer.toString(quantum_drives.get(0).getSpeed()));
        PowerPriceLabel.setText(Integer.toString(power_plants.get(0).getPrice()));
        PowerLabel.setText(Integer.toString(power_plants.get(0).getPower()));
        WeaponDPSLabel.setText(Integer.toString(weapons.get(0).getDps()));
        WeaponPriceLabel.setText(Integer.toString(weapons.get(0).getPrice()));
        WeaponTypeLabel.setText(weapons.get(0).getType());
        logger.info("data loaded to UI");
    }

    private void addItemsToPowerComboBox() throws SQLException {

        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from power_plants;");
        while (resultSet.next()) {
            Power_plant power_plant = new Power_plant();
            PowerComboBox.addItem(resultSet.getString("name"));
            power_plant.setPower(resultSet.getInt("power"));
            power_plant.setPrice(resultSet.getInt("price"));
            power_plant.setName(resultSet.getString("name"));
            power_plant.setId(resultSet.getInt("id"));
            power_plants.add(power_plant);
        }
    }

    private void addItemsToQuantumComboBox() throws SQLException {
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from quantum_drives;");
        while (resultSet.next()) {
            Quantum_drive quantum_drive = new Quantum_drive();
            QuantumComboBox.addItem(resultSet.getString("name"));
            quantum_drive.setName(resultSet.getString("name"));
            quantum_drive.setSpeed(resultSet.getInt("speed"));
            quantum_drive.setPrice(resultSet.getInt("price"));
            quantum_drive.setId(resultSet.getInt("id"));
            quantum_drives.add(quantum_drive);
        }
    }

    private void addItemsToWeaponComboBox() throws SQLException {
        ResultSet resultSet = MySQLConnect.executeQuery("SELECT * from weapons;");
        while (resultSet.next()) {
            Weapon weapon = new Weapon();
            WeaponComboBox.addItem(resultSet.getString("name"));
            weapon.setName(resultSet.getString("name"));
            weapon.setType(resultSet.getString("type"));
            weapon.setPrice(resultSet.getInt("price"));
            weapon.setDps(resultSet.getInt("dps"));
            weapon.setId(resultSet.getInt("id"));
            weapons.add(weapon);
        }
    }

    private void setSelectedPower_plant() throws SQLException {
        for (int i = 0; i < power_plants.size(); i++) {
            if (power_plants.get(i).getName().equals(PowerComboBox.getSelectedItem())) {
                selectedPower_plant = power_plants.get(i);
            }
        }

    }

    private void setSelectedQuantum_drive() throws SQLException {
        for (int i = 0; i < quantum_drives.size(); i++) {

            if (quantum_drives.get(i).getName().equals(QuantumComboBox.getSelectedItem().toString())) {
                selectedQuantum_drive = quantum_drives.get(i);
            }
        }
    }

    private void setSelectedWeapon() throws SQLException {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getName().equals(WeaponComboBox.getSelectedItem())) {
                selectedWeapon = weapons.get(i);
            }
        }
    }

    private void setPriceLabel() {
        PriceLabel.setText(Integer.toString(
                fighter.getPrice()
                        + selectedPower_plant.getPrice()
                        + selectedQuantum_drive.getPrice()
                        + selectedWeapon.getPrice()));
        logger.info("new price set");
    }

    private void setDefaultComponents() {
        selectedQuantum_drive = quantum_drives.get(0);
        selectedPower_plant = power_plants.get(0);
        selectedWeapon = weapons.get(0);
        logger.info("default components added");
    }

}
