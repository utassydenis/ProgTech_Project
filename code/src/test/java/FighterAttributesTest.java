import classes.SpaceshipType.Fighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


public class FighterAttributesTest {

    @Mock
    Fighter fighter = new Fighter(500000, 100);

    @Test
    public void fighterPriceTest() {
        int expectedPrice = 500000;
        int actualPrice = fighter.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void fighterConsumptionTest() {
        int expectedConsumption = 80;
        int actualConsumption = fighter.getConsumption();
        Assertions.assertEquals(expectedConsumption, actualConsumption);
    }

    @Test
    public void fighterTypeTest() {
        String expectedType = "fighter";
        String actualType = fighter.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}

