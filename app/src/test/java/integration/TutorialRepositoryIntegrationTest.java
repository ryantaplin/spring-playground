package integration;

import github.ryantaplin.Main;
import github.ryantaplin.infrastructure.database.tutorial.Tutorial;
import github.ryantaplin.infrastructure.database.tutorial.TutorialRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.oracle.OracleContainer;

import java.time.Duration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@Disabled
@SpringBootTest(
        classes = Main.class,
        webEnvironment = NONE,
        properties = {
                "spring.datasource.url=${DATABASE_URL}",
                "spring.datasource.username=${DATABASE_USERNAME}",
                "spring.datasource.password=${DATABASE_PASSWORD}",
                "spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver"
        })
public class TutorialRepositoryIntegrationTest {

    @Test
    void oracleTutorialRepositorySaveIntegrationTest() {
        repository.save(Tutorial.tutorial()
                .withTitle("Title")
                .withDescription("Description")
                .withPublished(true)
                .build());
    }

    @BeforeAll
    public static void beforeAll() {
        oracleContainer.start();

    }

    @Container
    static OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-free:23.5-slim-faststart")
            .withStartupTimeout(Duration.ofMinutes(3))
            .withUsername("test_user")
            .withPassword(("test_password"));


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("DATABASE_URL", oracleContainer::getJdbcUrl);
        registry.add("DATABASE_USERNAME", oracleContainer::getUsername);
        registry.add("DATABASE_PASSWORD", oracleContainer::getPassword);
    }

    @Autowired
    private TutorialRepository repository;
}
