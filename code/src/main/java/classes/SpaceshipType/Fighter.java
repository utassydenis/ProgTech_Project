package classes.SpaceshipType;

import classes.Spaceship.SpaceshipAbstract;


public class Fighter extends SpaceshipAbstract {

    public Fighter() {
        this.setPrice(400000);
        this.setConsumption(70);
        this.setType("fighter");
    }

    @Override
    public void setConsumption(int consumption) {
        super.consumption = consumption;
    }

    @Override
    public void setQuantum_drive(int quantum_drive) {
        super.quantum_drive = quantum_drive;
    }

    @Override
    public void setPower_plant(int power_plant) {
        super.power_plant = power_plant;
    }

    @Override
    public void setPrice(int price) {
        super.price = price;
    }

    @Override
    protected void setType(String type) {
        super.type = type;
    }

    @Override
    public void setWeapon(int weapon) {
        super.weapon = weapon;
    }
}
