package classes.Decorator;

import classes.Spaceship.SpaceshipAbstract;

public abstract class SpaceshipDecoratorBase extends SpaceshipAbstract { //SpaceshipAbstract az alaposztály az adott funkciókkal
    //ez a dekorátor osztály
    private SpaceshipAbstract spaceship;

    public SpaceshipDecoratorBase(SpaceshipAbstract s) {
        spaceship = s;
    }

    @Override
    public int getConsumption() {
        return spaceship.getConsumption();
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
    public int getWeapon() {
        return spaceship.getWeapon();
    }

    @Override
    public int getPrice() {
        return super.price;
    }
}
