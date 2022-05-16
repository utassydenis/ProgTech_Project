import frames.LoginFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import database.MySQLConnect;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("project started");
        MySQLConnect.connectDatabase();
        LoginFrame loginFrame = new LoginFrame(true);
        logger.info("window initialized");

    }
}
