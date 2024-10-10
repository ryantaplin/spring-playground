package github.ryantaplin.properties;

import github.ryantaplin.Main;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;


class PropertiesTest {

    @Nested
    @SpringBootTest(
            classes = Main.class,
            webEnvironment = NONE,
            properties = {
                    "app.info.name=app",
                    "app.info.version=1.0.0",
                    "app.infrastructure.http-client.connect-timeout=10s"
            }
    )
    class MockProperties {
        @Test
        void mockedAppInfoNameReturnsExpected() {
            assertThat(applicationProperties.name()).isEqualTo("app");
        }

        @Test
        void mockedAppInfoVersionReturnsExpected() {
            assertThat(applicationProperties.version()).isEqualTo("1.0.0");
        }

        @Test
        void mockedAppInfrastructureHttpClientConnectTimeoutReturnsExpected() {
            AssertionsForClassTypes.assertThat(httpClientProperties.connectTimeout()).isEqualTo(Duration.ofSeconds(10));
        }

        @Autowired
        private ApplicationProperties applicationProperties;

        @Autowired
        private HttpClientProperties httpClientProperties;
    }

    @Nested
    @SpringBootTest
    class BaseClassPathProperties {
        @Test
        void baseAppInfoNameReturnsExpected() {
            assertThat(applicationProperties.name()).isEqualTo("spring-playground");
        }

        @Test
        void baseAppInfoVersionReturnsExpected() {
            assertThat(applicationProperties.version()).isEqualTo("${VERSION}");
        }

        @Autowired
        private ApplicationProperties applicationProperties;
    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("local")
    class LocalClassPathProperties {
        @Test
        void localAppInfrastructureHttpClientConnectTimeoutReturnsExpected() {
            assertThat(httpClientProperties.connectTimeout()).isEqualTo(Duration.ofSeconds(5));
        }

        @Autowired
        private HttpClientProperties httpClientProperties;
    }

    @Nested
    @SpringBootTest
    class NotFoundProperties {
        @Test
        void baseAppInfoNameReturnsExpected() {
            assertThat(httpClientProperties.connectTimeout()).isNull();
        }

        @Autowired
        private HttpClientProperties httpClientProperties;
    }
}