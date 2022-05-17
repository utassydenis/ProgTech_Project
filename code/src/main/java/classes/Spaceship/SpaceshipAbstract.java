package classes.Spaceship;

public abstract class SpaceshipAbstract {

    protected String type;
    protected String fuel;
    protected int consumption;
    protected int weapon;
    protected int quantum_drive;
    protected int power_plant;
    protected int price;

    public SpaceshipAbstract() {
        setFuel();
    }

    public abstract String getFuel();

    private void setFuel() {
        this.fuel = "hydrogen";
    }

    public abstract int getConsumption();

    protected abstract void setConsumption(int consumption);

    public abstract int getWeapon();

    protected void setWeapon(int weapon) {
        this.weapon = 0;
    }

    public abstract int getQuantum_drive();

    protected abstract void setQuantum_drive(int quantum_drive);

    public abstract int getPower_plant();

    protected abstract void setPower_plant(int power_plant);

    public abstract int getPrice();

    protected abstract void setPrice(int price);


    public abstract String getType();

    protected abstract void setType();
}
