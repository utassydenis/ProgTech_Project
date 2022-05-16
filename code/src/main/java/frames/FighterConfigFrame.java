package frames;

import classes.Spaceship.SpaceshipAbstract;
import classes.SpaceshipModule.Power_plant;
import classes.SpaceshipModule.Quantum_drive;
import classes.SpaceshipModule.Weapon;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private SpaceshipAbstract fighter;
    private ResultSet power_plants;
    private ResultSet quantum_drives;
    private ResultSet weapons;
    private Power_plant selectedPower_plant;
    private Quantum_drive selectedQuantum_drive;
    private Weapon selectedWeapon;
    Logger logger = LoggerFactory.getLogger(FighterConfigFrame.class);

    public FighterConfigFrame(SpaceshipAbstract fighter) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
        setContentPane(FighterConfigPanel);
        setSize(800, 600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.fighter = fighter;
        loadDataToUI();
        PowerComboBox.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            setSelectedPower_plant();
                            logger.info("trigger");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        WeaponComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedWeapon();
                } catch (SQLException ex) {
                    try {
                        setSelectedQuantum_drive();
                    } catch (SQLException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        });
        QuantumComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
    }

    private void addItemsToPowerComboBox() throws SQLException {

        power_plants = MySQLConnect.executeQuery("SELECT * from power_plants;");

        while (power_plants.next()) {
            PowerComboBox.addItem(power_plants.getString("name"));
        }
    }

    private void addItemsToQuantumComboBox() throws SQLException {
        quantum_drives = MySQLConnect.executeQuery("SELECT * from quantum_drives;");

        while (quantum_drives.next()) {
            QuantumComboBox.addItem(quantum_drives.getString("name"));
        }
    }

    private void addItemsToWeaponComboBox() throws SQLException {
        weapons = MySQLConnect.executeQuery("SELECT * from weapons;");

        while (weapons.next()) {
            WeaponComboBox.addItem(weapons.getString("name"));
        }
    }

    private void setSelectedPower_plant() throws SQLException {
        while (power_plants.next()) {
            if (power_plants.getString("name") == PowerComboBox.getSelectedItem()) {
                selectedPower_plant.setName(power_plants.getString("name"));
                selectedPower_plant.setPower(power_plants.getInt("power"));
                selectedPower_plant.setPrice(power_plants.getInt("price"));
                PowerLabel.setText(Integer.toString(selectedPower_plant.getPower()));
                PowerPriceLabel.setText(Integer.toString(selectedPower_plant.getPrice()));
            }
            else
            {
                logger.info("not good");
            }
        }
    }
    private void setSelectedQuantum_drive() throws SQLException {
        while (quantum_drives.next()) {
            if (quantum_drives.getString("name") == QuantumComboBox.getSelectedItem()) {
                selectedQuantum_drive.setName(quantum_drives.getString("name"));
                selectedQuantum_drive.setSpeed(quantum_drives.getInt("speed"));
                selectedQuantum_drive.setPrice(quantum_drives.getInt("price"));
                SpeedLabel.setText(Integer.toString(selectedQuantum_drive.getSpeed()));
                QuantumPriceLabel.setText(Integer.toString(selectedQuantum_drive.getPrice()));
            }
        }
    }
    private void setSelectedWeapon() throws SQLException {
        while (weapons.next()) {
            if (weapons.getString("name") == PowerComboBox.getSelectedItem()) {
                selectedWeapon.setName(weapons.getString("name"));
                selectedWeapon.setType(weapons.getString("type"));
                selectedWeapon.setPrice(weapons.getInt("price"));
                selectedWeapon.setDps(weapons.getInt("dps"));
                WeaponTypeLabel.setText(selectedWeapon.getType());
                WeaponDPSLabel.setText(Integer.toString(selectedWeapon.getDps()));
                WeaponPriceLabel.setText(Integer.toString(selectedWeapon.getPrice()));
            }
        }
    }

    private void setPriceLabel()
    {
        PriceLabel.setText(Integer.toString(
                fighter.getPrice()
                +selectedPower_plant.getPrice()
                +selectedQuantum_drive.getPrice()
                +selectedWeapon.getPrice()));
    }
}
