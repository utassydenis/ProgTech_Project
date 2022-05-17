package classes.Controller;

import classes.Decorator.Offer;
import classes.SpaceshipModule.Power_plant;
import classes.SpaceshipModule.Quantum_drive;
import classes.SpaceshipModule.Weapon;
import classes.SpaceshipType.Fighter;
import database.MySQLConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class FighterController {
    static Logger logger = LoggerFactory.getLogger(FighterController.class);
    public static Offer getOffer(String coupon, boolean tick, Fighter fighter) {
        Offer offer = new Offer(fighter);
        if (coupon.equals("BARNA")) {
            offer.setDiscount(25);
        } else if (coupon.equals("DENES")) {
            offer.setDiscount(10);
        } else if (coupon.equals("EDE")) {
            offer.setDiscount(100);
        }

        if (tick) {
            offer.setEfficiency(10);
        }

        return offer;
    }

    public static boolean storeSpaceship(Fighter fighter) {
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
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Power_plant setSelectedPower_plant(String selectedItem, List<Power_plant> power_plants) throws SQLException {
        for (int i = 0; i < power_plants.size(); i++) {
            if (power_plants.get(i).getName().equals(selectedItem)) {
                return power_plants.get(i);
            }
        }
        return null;
    }

    public static Quantum_drive setSelectedQuantum_drive(String selectedItem, List<Quantum_drive> quantum_drives) throws SQLException {
        for (int i = 0; i < quantum_drives.size(); i++) {

            if (quantum_drives.get(i).getName().equals(selectedItem)) {
                return quantum_drives.get(i);
            }
        }
        return null;
    }

    public static Weapon setSelectedWeapon(String selectedItem, List<Weapon> weapons) throws SQLException {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getName().equals(selectedItem)) {
                return weapons.get(i);
            }
        }
        return null;
    }
}
