package Frames;

import javax.swing.*;

public class ConfigFrame extends JFrame{
    private JButton FighterButton;
    private JButton TransportButton;
    private JButton IndustrialButton;
    private JPanel TypesPanel;

    public ConfigFrame(){
        setSize(800,600);
        setTitle("Spaceship Configurator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
