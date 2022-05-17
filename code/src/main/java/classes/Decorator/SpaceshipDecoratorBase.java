package classes.Decorator;

import classes.Spaceship.Spaceship;

public abstract class SpaceshipDecoratorBase extends Spaceship {
    private Spaceship spaceship;

    public SpaceshipDecoratorBase(Spaceship s) {
        spaceship = s;
    }

    @Override
    public String getFuel() {
        return spaceship.getFuel();
    }

    @Override
    public int getConsumption() {
        return spaceship.getConsumption();
    }

    @Override
    public int getWeapon() {
        return spaceship.getWeapon();
    }

    @Override
    public int getQuantum_drive() {
        return spaceship.getQuantum_drive();
    }

    @Override
    public int getPower_plant() {
        return spaceship.getPower_plant();
    }

    @Override
    public int getPrice() {
        return spaceship.getPrice();
    }

    @Override
    public String getType() {
        return spaceship.getType();
    }


}
