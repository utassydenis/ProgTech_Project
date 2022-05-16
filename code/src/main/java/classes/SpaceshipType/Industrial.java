package classes.SpaceshipType;

import classes.Spaceship.SpaceshipAbstract;

public class Industrial extends SpaceshipAbstract {
    public Industrial() {
        this.setPrice(650000);
        this.setConsumption(230);
        this.setType("industrial");
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
    public void setType(String type) {
        super.type = type;
    }
}
