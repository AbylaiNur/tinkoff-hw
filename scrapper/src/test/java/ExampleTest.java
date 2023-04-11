import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest extends IntegrationEnvironment {

    @Test
    void containerIsRunningTest() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }
}
