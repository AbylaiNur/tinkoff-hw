import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest extends IntegrationEnvironment {

    @Test
    void containerIsRunningTest() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }

    @Test
    void containsAllTables() {
        Set<String> tables = Set.of("chat", "link", "chat_link");
        Set<String> containerTables = new HashSet<>();

        try {
            ResultSet rs = connection.getMetaData().getTables(null, null, null, new String[] {"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");

                if (tables.contains(tableName)) {
                    containerTables.add(tableName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(Objects.equals(tables, containerTables));
    }
}
