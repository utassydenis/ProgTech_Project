package classes.Decorator;

import classes.Spaceship.SpaceshipAbstract;

public abstract class Discount extends SpaceshipDecoratorBase {

    public Discount(SpaceshipAbstract s) {
        super(s);
    }

    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int d) {
        discount = d;
    }

    @Override
    public int getPrice() {
        int baseprice = super.price;
        int percent = 100 - getDiscount();
        return Math.round((baseprice * percent) / 100);
    }


}
