import classes.SpaceshipType.Industrial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class IndustrialAttributesTest {
    @Mock
    Industrial industrial = new Industrial(500000, 100);

    @Test
    public void industrialPriceTest() {
        int expectedPrice = (int)(500000*1.6);
        int actualPrice = industrial.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void industrialConsumptionTest() {
        int expectedConsumption = 150;
        int actualConsumption = industrial.getConsumption();
        Assertions.assertEquals(expectedConsumption, actualConsumption);
    }

    @Test
    public void industrialTypeTest() {
        String expectedType = "industrial";
        String actualType = industrial.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}
