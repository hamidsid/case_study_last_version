package matchit.base.server.database;

import matchit.base.server.Config;
import matchit.base.server.user.Credentials;
import matchit.base.server.user.Role;
import matchit.base.server.user.User;
import org.junit.After;
import org.junit.Before;

import java.sql.SQLException;

/**
 * Base class for H2 database tests. The connection url configures an in-memory database.
 *
 * @author Rasmus Ros, rasmus.ros@cs.lth.se
 */
public abstract class BaseDataAccessTest {

    private static final String IN_MEM_DRIVER_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    protected static final User ADMIN = new User(1, Role.ADMIN, "Admin");
    protected static final Credentials ADMIN_CREDENTIALS = new Credentials("Admin", "password", Role.ADMIN);
    protected static final User TEST = new User(2, Role.USER, "Test");
    protected static final Credentials TEST_CREDENTIALS = new Credentials("Test", "password", Role.USER);

    static {
        Config.instance().setDatabaseDriver(IN_MEM_DRIVER_URL);
    }

    @Before
    public void createDatabase() throws SQLException {
        new CreateSchema(IN_MEM_DRIVER_URL).createSchema();
    }

    @After
    public void deleteDatabase() throws SQLException {
        new CreateSchema(IN_MEM_DRIVER_URL).dropAll();
    }
}
