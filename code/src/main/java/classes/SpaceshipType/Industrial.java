package classes.SpaceshipType;

import classes.Spaceship.Spaceship;

public class Industrial extends Spaceship {
    public Industrial(int price, int consumption) {
        this.setPrice(price);
        this.setConsumption(consumption);
        this.setType();
    }

    @Override
    public String getFuel() {
        return super.fuel;
    }

    @Override
    public int getConsumption() {
        return super.consumption;
    }

    @Override
    public void setConsumption(int consumption) {
        super.consumption = (int)(consumption*1.5);
    }

    @Override
    public int getWeapon() {
        return super.weapon;
    }

    @Override
    public int getQuantum_drive() {
        return super.quantum_drive;
    }

    @Override
    public void setQuantum_drive(int quantum_drive) {
        super.quantum_drive = quantum_drive;
    }

    @Override
    public int getPower_plant() {
        return super.power_plant;
    }

    @Override
    public void setPower_plant(int power_plant) {
        super.power_plant = power_plant;
    }

    @Override
    public int getPrice() {
        return super.price;
    }

    @Override
    public void setPrice(int price) {
        super.price = (int)(price*1.6);
    }

    @Override
    public String getType() {
        return super.type;
    }

    @Override
    public void setType() {
        super.type = "industrial";
    }
}
