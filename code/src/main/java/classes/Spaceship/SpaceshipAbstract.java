package classes.Spaceship;

public abstract class SpaceshipAbstract {

    protected String type;
    protected String fuel = "hydrogen";
    protected int consumption;
    protected int weapon;
    protected int quantum_drive;
    protected int power_plant;
    protected int price;

    public String getFuel() {
        return fuel;
    }

    private void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getConsumption() {
        return consumption;
    }

    protected abstract void setConsumption(int consumption);

    public int getWeapon() {
        return weapon;
    }

    protected void setWeapon(int weapon) {
        this.weapon = 0;
    }

    public int getQuantum_drive() {
        return quantum_drive;
    }

    protected abstract void setQuantum_drive(int quantum_drive);

    public int getPower_plant() {
        return power_plant;
    }

    protected abstract void setPower_plant(int power_plant);

    public int getPrice() {
        return price;
    }

    protected abstract void setPrice(int price);


    public String getType() {
        return type;
    }

    protected abstract void setType(String type);
}
