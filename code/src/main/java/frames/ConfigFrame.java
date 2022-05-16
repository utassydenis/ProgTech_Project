package frames;

import classes.Spaceship.SpaceshipAbstract;
import classes.SpaceshipType.Fighter;
import classes.SpaceshipType.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ConfigFrame extends JFrame {
    private JButton FighterButton;
    private JButton TransportButton;
    private JButton IndustrialButton;
    private JPanel ConfPanel;
    private JPanel TypePanel;

    public ConfigFrame() {
        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
        setContentPane(ConfPanel);
        setSize(800, 600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        FighterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Fighter button clicked");
                TypePanel.setVisible(false);
                SpaceshipAbstract fighter = new Fighter();
                try {
                    FighterConfigFrame fighterConfigFrame = new FighterConfigFrame((Fighter) fighter);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        TransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Transport button clicked");
                TypePanel.setVisible(false);
                SpaceshipAbstract transport = new Transport();

                try {
                    TransportConfigFrame transportConfigFrame = new TransportConfigFrame((Transport) transport);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        IndustrialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Industrial button clicked");
                TypePanel.setVisible(false);
                //TODO: create spaceship
            }
        });
    }
}
