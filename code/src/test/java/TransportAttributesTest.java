import classes.SpaceshipType.Transport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TransportAttributesTest {


    @Mock
    Transport transport = new Transport(500000, 100);

    @Test
    public void fighterPriceTest() {
        int expectedPrice = (int)(500000*1.1);
        int actualPrice = transport.getPrice();
        Assertions.assertEquals(expectedPrice, actualPrice);

    }

    @Test
    public void fighterConsumptionTest() {
        int expectedConsumption = 100;
        int actualConsumption = transport.getConsumption();
        Assertions.assertEquals(expectedConsumption, actualConsumption);
    }

    @Test
    public void fighterTypeTest() {
        String expectedType = "transport";
        String actualType = transport.getType();
        Assertions.assertEquals(expectedType, actualType);
    }
}
