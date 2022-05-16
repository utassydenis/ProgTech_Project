package classes.SpaceshipType;

import classes.Spaceship.SpaceshipAbstract;

public class Transport extends SpaceshipAbstract {
    public Transport() {
        this.setPrice(550000);
        this.setConsumption(175);
    }

    @Override
    protected void setConsumption(int consumption) {
        super.consumption = consumption;
    }

    @Override
    protected void setQuantum_drive(int quantum_drive) {
        super.consumption = consumption;
    }

    @Override
    protected void setPower_plant(int power_plant) {
        super.consumption = consumption;
    }

    @Override
    protected void setPrice(int price) {
        super.consumption = consumption;
    }
}
