package ru.tinkoff.edu.java.scrapper.repository;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.nio.file.Path;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class IntegrationEnvironment {

    static PostgreSQLContainer POSTGRE_SQL_CONTAINER;
    static Connection connection;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withUsername("postgres")
                .withDatabaseName("hhhhahhaha")
                .withPassword("password")
                .withExposedPorts(5432);
        POSTGRE_SQL_CONTAINER.start();

        try {
            connection = DriverManager.getConnection(
                    POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    POSTGRE_SQL_CONTAINER.getUsername(),
                    POSTGRE_SQL_CONTAINER.getPassword()
            );

            Path path = new File(System.getProperty("user.dir"))
                    .toPath()
                    .resolve("migrations");

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase("master.xml", new DirectoryResourceAccessor(path), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
    }
}
