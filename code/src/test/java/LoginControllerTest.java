import classes.Controller.LoginController;
import classes.Users;
import database.MySQLConnect;
import frames.LoginFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    LoginFrame testFrame;

    LoginController testController = new LoginController(testFrame);

    @Test
    public void getAuthenticatedUserTestWrongData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("alma", "alma");
        Assertions.assertEquals(null, testUser.username);
    }

    @Test
    public void getAuthenticatedUserTestGoodData() throws SQLException {
        MySQLConnect.connectDatabase();
        Users testUser = testController.getAuthenticatedUser("admin", "admin");
        Assertions.assertEquals("admin", testUser.username);
    }

    @Mock
    LoginController loginController;

    @Mock
    Users user;


    @Test
    public void authenticateUserHappyPathTest() {
        Mockito.when(loginController.getAuthenticatedUser(user.username, user.password)).thenReturn(user);
        Users testUser = loginController.getAuthenticatedUser(user.username, user.password);

        Assertions.assertEquals(user, testUser);
    }

    @Test
    public void authenticateUserBadPathTest() {
        Mockito.when(loginController.getAuthenticatedUser(user.username, user.password)).thenReturn(null);
        Users testUser = loginController.getAuthenticatedUser(user.username, user.password);

        Assertions.assertEquals(null, testUser);
    }
}
