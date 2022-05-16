package frames;

import classes.Spaceship.SpaceshipAbstract;
import classes.SpaceshipModule.Weapon;
import classes.SpaceshipType.Fighter;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedShipsFrame extends JDialog {
    private JPanel savedShipPanel;
    private JTable savedShipsTable;
    private JButton refreshButton;
    static Logger logger = LoggerFactory.getLogger(SavedShipsFrame.class);

    public SavedShipsFrame() {
        setTitle("Saved ships");
        setContentPane(savedShipPanel);
        setSize(800, 600);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Refresh button pressed");
                try {
                    String query = "select count(*) FROM spaceships where uid ='" + MySQLConnect.connectedUSer.id + "';";
                    ResultSet res = MySQLConnect.executeQuery(query);
                    int size = res.getRow();
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
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.setVisible(true);



                } catch (Exception x) {
                    x.printStackTrace();
                }


            }
        });
        setVisible(true);
    }
}
