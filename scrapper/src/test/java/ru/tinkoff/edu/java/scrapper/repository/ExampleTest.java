package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest extends IntegrationEnvironment {

    @Test
    void containerIsRunningTest() {
        Assertions.assertTrue(IntegrationEnvironment.POSTGRE_SQL_CONTAINER.isRunning());
    }
}
