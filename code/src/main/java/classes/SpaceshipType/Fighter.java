package classes.SpaceshipType;

import classes.Spaceship.SpaceshipAbstract;


public class Fighter extends SpaceshipAbstract {

    public Fighter() {
        this.setPrice(400000);
        this.setConsumption(70);
    }

    @Override
    protected void setConsumption(int consumption) {
        super.consumption = consumption;
    }

    @Override
    protected void setQuantum_drive(int quantum_drive) {
        super.quantum_drive = quantum_drive;
    }

    @Override
    protected void setPower_plant(int power_plant) {
        super.power_plant = power_plant;
    }

    @Override
    protected void setPrice(int price) {
        super.price = price;
    }

    @Override
    protected void setWeapon(int weapon) {
        super.weapon = weapon;
    }
}
