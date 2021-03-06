package frames;

import classes.Controller.IndustrialController;
import classes.Controller.TransportController;
import classes.Decorator.Offer;
import classes.Spaceship.Spaceship;
import classes.SpaceshipModule.Power_plant;
import classes.SpaceshipModule.Quantum_drive;
import classes.SpaceshipType.Industrial;
import classes.SpaceshipType.Transport;
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

public class TransportConfigFrame extends JFrame {
    private JPanel TransportConfigPanel;
    private JLabel ConsumptionLabel;
    private JComboBox QuantumComboBox;
    private JComboBox PowerComboBox;
    private JLabel PriceLabel;
    private JLabel FuelLabel;
    private JLabel SpeedLabel;
    private JLabel QuantumPriceLabel;
    private JLabel PowerLabel;
    private JLabel PowerPriceLabel;
    private JButton OrderButton;
    private JTextField couponTextField;
    private JCheckBox efficientEngineCheckBox;

    private Spaceship transport;
    private static Power_plant selectedPower_plant;
    private static Quantum_drive selectedQuantum_drive;
    Logger logger = LoggerFactory.getLogger(FighterConfigFrame.class);
    private List<Power_plant> power_plants = new ArrayList<>();
    private List<Quantum_drive> quantum_drives = new ArrayList<>();

    public TransportConfigFrame(Transport transport) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
        logger.info("transport frame initialized");
        setContentPane(TransportConfigPanel);
        setSize(800, 600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.transport = transport;
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
        OrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                handleOrderClick();

            }
        });
    }

    private void loadDataToUI() throws SQLException {
        PriceLabel.setText(Integer.toString(transport.getPrice()));
        ConsumptionLabel.setText(Integer.toString(transport.getConsumption()));
        FuelLabel.setText(transport.getFuel());
        addItemsToPowerComboBox();
        addItemsToQuantumComboBox();
        QuantumPriceLabel.setText(Integer.toString(quantum_drives.get(0).getPrice()));
        SpeedLabel.setText(Integer.toString(quantum_drives.get(0).getSpeed()));
        PowerPriceLabel.setText(Integer.toString(power_plants.get(0).getPrice()));
        PowerLabel.setText(Integer.toString(power_plants.get(0).getPower()));
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

    private void setPriceLabel() {
        PriceLabel.setText(Integer.toString(
                transport.getPrice()
                        + selectedPower_plant.getPrice()
                        + selectedQuantum_drive.getPrice()));
        logger.info("new price set");
    }

    private void setDefaultComponents() {
        selectedQuantum_drive = quantum_drives.get(0);
        selectedPower_plant = power_plants.get(0);
        logger.info("default components added");
    }
    private void handleOrderClick() {
        Transport transport = (Transport) this.transport;
        transport.setPrice(getOrderTotalPrice());
        transport.setPower_plant(selectedPower_plant.getId());
        transport.setQuantum_drive(selectedQuantum_drive.getId());

        Offer offer = TransportController.getOffer(
                couponTextField.getText(),
                efficientEngineCheckBox.isSelected(),
                transport);

        transport.setPrice(offer.getPrice());
        transport.setConsumption(offer.getConsumption());

        if (TransportController.storeSpaceship(transport)) {
            dispose();
            LoginFrame log = new LoginFrame(false);
        }

    }

    private int getOrderTotalPrice() {
        return transport.getPrice()
                + selectedPower_plant.getPrice()
                + selectedQuantum_drive.getPrice();
    }
}
