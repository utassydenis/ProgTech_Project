import classes.Controller.LoginController;
import classes.Users;
import database.MySQLConnect;
import frames.LoginFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

public class LoginControllerTest {

    @Mock
    LoginFrame testFrame;

    @Mock
    Users testUser;


    LoginController testController = new LoginController(testFrame);
    @Test
    public void getAuthenticatedUserTestWrongData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("alma", "alma");
        Assertions.assertEquals(null,testUser.username);
    }
    @Test
    public void getAuthenticatedUserTestGoodData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("admin", "admin");
        Assertions.assertEquals("admin",testUser.username);
    }


}
