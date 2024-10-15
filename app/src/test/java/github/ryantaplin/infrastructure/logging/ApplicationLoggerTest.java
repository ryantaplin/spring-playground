package github.ryantaplin.infrastructure.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.MDC;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationLoggerTest {

    @Nested
    class Infrastructure {
        @Test
        void loggerIsInstanceOfDelegatingLogger() {
            assertThat(logger).isInstanceOf(AbstractDelegatingLogger.class);
        }

        @Test
        void loggerNameIsApplication() {
            assertThat(logger.getName()).isEqualTo("APPLICATION");
        }
    }

    @Nested
    @ExtendWith(OutputCaptureExtension.class)
    class Format {
        //TODO: sadly logs the DelegateLogger class instead of the calling class (is there a way to defer this?)
        // can use LocationAwareLogger? but seems a bit yucky.

        @Test
        void generalLogFormat(CapturedOutput output) {
            MDC.put("trace.uuid", "random-uuid");
            logger.info("some message");
            assertThat(output.getAll()).matches(pattern(escape("""
                    [START] [CONTEXT=APPLICATION] [LEVEL=INFO] [AT=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}] [Test worker] (github.ryantaplin.infrastructure.logging.AbstractDelegatingLogger: 146) [TraceId=random-uuid]
                    some message
                    [END]
                    """)));
        }

        private Pattern pattern(String value) {
            return Pattern.compile(".*" + value + ".*", Pattern.DOTALL);
        }

        private String escape(String unsantisiedValue) {
            return unsantisiedValue
                    .replaceAll("\\[", "\\\\[")
                    .replaceAll("\\]", "\\\\]")
                    .replaceAll("\\(", "\\\\(")
                    .replaceAll("\\)", "\\\\)");
        }

        @AfterEach
        void tearDown() {
            MDC.clear();
        }
    }

    private final ApplicationLogger logger = new ApplicationLogger();
}