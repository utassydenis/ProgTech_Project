package frames;

import classes.Spaceship.SpaceshipAbstract;
import classes.SpaceshipType.Fighter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigFrame extends JFrame {
    private JButton FighterButton;
    private JButton TransportButton;
    private JButton IndustrialButton;
    private JPanel ConfPanel;
    private JComboBox WeaponComboBox;
    private JPanel ConfigPanel;
    private JPanel TypePanel;

    public ConfigFrame() {
        Logger logger = LoggerFactory.getLogger(ConfigFrame.class);
        setContentPane(ConfPanel);
        setSize(800, 600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ConfigPanel.setVisible(false);

        FighterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Fighter button clicked");
                TypePanel.setVisible(false);
                ConfigPanel.setVisible(true);
                SpaceshipAbstract fighter = new Fighter();
                //TODO: create spaceship
            }
        });
        TransportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Transport button clicked");
                TypePanel.setVisible(false);
                ConfigPanel.setVisible(true);
                //TODO: create spaceship
            }
        });
        IndustrialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Industrial button clicked");
                TypePanel.setVisible(false);
                ConfigPanel.setVisible(true);
                //TODO: create spaceship
            }
        });
    }
}
