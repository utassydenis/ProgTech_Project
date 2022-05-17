import classes.SpaceshipType.Fighter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpaceshipTypeTest {

@Test
    void testFighterTypeSet()
{
    Fighter fighter = new Fighter(500000,100);
    String expectedType = "fighter";
    String actualType = fighter.getType();

    Assertions.assertEquals(expectedType,actualType);
}

}
