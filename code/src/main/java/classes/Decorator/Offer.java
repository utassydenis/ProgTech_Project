package classes.Decorator;

import classes.Spaceship.SpaceshipAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Offer extends SpaceshipDecoratorBase {
    Logger logger = LoggerFactory.getLogger(Offer.class);

    public Offer(SpaceshipAbstract s) {
        super(s);
    }

    @Override
    protected void setConsumption(int consumption) {

    }

    @Override
    protected void setQuantum_drive(int quantum_drive) {

    }

    @Override
    protected void setPower_plant(int power_plant) {

    }

    @Override
    protected void setPrice(int price) {

    }

    @Override
    protected void setType(String type) {

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

        int baseprice = super.getPrice();
        int percent = 100 - getDiscount();
        logger.info("Decorator setting discount.");
        return Math.round((baseprice * percent) / 100);

    }
    private int efficiency;

    public int getEfficiency(){
        return efficiency;
    }
    public void setEfficiency(int e){
        efficiency = e;
    }

    @Override
    public int getConsumption(){
        int baseconsumption = super.getConsumption();
        int percent = 100 - getEfficiency();
        logger.info("Decorator setting consumption");
        return Math.round((baseconsumption * percent)/100);
    }
}
