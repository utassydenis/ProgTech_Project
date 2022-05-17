import classes.SpaceshipType.Fighter;
import classes.SpaceshipType.Industrial;
import classes.SpaceshipType.Transport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import classes.Decorator.Offer;


public class OfferTest {

    Offer testOffer;
    Fighter testFighter;
    Transport testTransport;
    Industrial testIndustrial;

    @Test
    void testDiscountWithFighter() {
        testFighter = new Fighter(100, 100);
        testOffer = new Offer(testFighter);
        testOffer.setDiscount(10);
        int expectedPrice = (int) Math.round(testFighter.getPrice() * 0.9);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }

    @Test
    void testDiscountWithTransport() {
        testTransport = new Transport(100, 100);
        testOffer = new Offer(testTransport);
        testOffer.setDiscount(10);
        int expectedPrice = (int) Math.round(testTransport.getPrice() * 0.9);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }

    @Test
    void testDiscountWithIndustrial() {
        testIndustrial = new Industrial(100, 100);
        testOffer = new Offer(testIndustrial);
        testOffer.setDiscount(10);
        int expectedPrice = (int) Math.round(testIndustrial.getPrice() * 0.9);
        Assertions.assertEquals(expectedPrice, testOffer.getPrice());
    }

    @Test
    void testEfficiencyWithFighter() {
        testFighter = new Fighter(100, 100);
        testOffer = new Offer(testFighter);
        testOffer.setEfficiency(10);
        int expectedEfficiency = (int) Math.round(testFighter.getConsumption() * 0.9);
        Assertions.assertEquals(expectedEfficiency, testOffer.getConsumption());
    }

    @Test
    void testEfficiencyWithTransport() {
        testTransport = new Transport(100, 100);
        testOffer = new Offer(testTransport);
        testOffer.setEfficiency(10);
        int expectedEfficiency = (int) Math.round(testTransport.getConsumption() * 0.9);
        Assertions.assertEquals(expectedEfficiency, testOffer.getConsumption());
    }

    @Test
    void testEfficiencyWithIndustrial() {
        testIndustrial = new Industrial(100, 100);
        testOffer = new Offer(testIndustrial);
        testOffer.setEfficiency(10);
        int expectedEfficiency = (int) Math.round(testIndustrial.getConsumption() * 0.9);
        Assertions.assertEquals(expectedEfficiency, testOffer.getConsumption());
    }

}
