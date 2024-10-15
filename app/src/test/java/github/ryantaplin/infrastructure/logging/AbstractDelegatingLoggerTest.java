package github.ryantaplin.infrastructure.logging;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

class AbstractDelegatingLoggerTest {

    @Test
    void loggerIsInstanceOfSl4fjLogger() {
        assertThat(testLogger).isInstanceOf(Logger.class);
    }

    @ParameterizedTest
    @ArgumentsSource(LoggerFunctionProvider.class)
    void delegateLoggerSupplyingMethodsAreInvokedAndResultIsReturned(Function<Logger, Object> loggerFunction, Object value) {
        givenPriming(delegateLogger, loggerFunction, value);
        thenAssert(testLogger, loggerFunction, value);
        thenVerify(delegateLogger, loggerFunction);
    }

    @ParameterizedTest
    @ArgumentsSource(LoggerConsumerProvider.class)
    void delegateLoggerConsumingMethodsAreInvoked(Consumer<Logger> loggerConsumer) {
        loggerConsumer.accept(testLogger);
        thenVerify(delegateLogger, loggerConsumer);
    }

    private static class LoggerFunctionProvider implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(func("getName()", Logger::getName), "value"),
                    arguments(func("isTraceEnabled()", Logger::isTraceEnabled), true),
                    arguments(func("isTraceEnabled(Marker)", logger -> logger.isTraceEnabled(anyMarker)), true),
                    arguments(func("isDebugEnabled()", Logger::isDebugEnabled), true),
                    arguments(func("isDebugEnabled(Marker)", logger -> logger.isDebugEnabled(anyMarker)), true),
                    arguments(func("isInfoEnabled()", Logger::isInfoEnabled), true),
                    arguments(func("isInfoEnabled(Marker)", logger -> logger.isInfoEnabled(anyMarker)), true),
                    arguments(func("isWarnEnabled()", Logger::isWarnEnabled), true),
                    arguments(func("isWarnEnabled(Marker)", logger -> logger.isWarnEnabled(anyMarker)), true),
                    arguments(func("isErrorEnabled()", Logger::isErrorEnabled), true),
                    arguments(func("isErrorEnabled(Marker)", logger -> logger.isErrorEnabled(anyMarker)), true)
            );
        }

        private final Marker anyMarker = Mockito.mock(Marker.class);
        private Named<Function<Logger, Object>> func(String functionName, Function<Logger, Object> function) {
            return named(functionName, function);
        }

    }
    private static class LoggerConsumerProvider implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(consumer("trace(String)", logger -> logger.trace("msg"))),
                    arguments(consumer("trace(String, Object)", logger -> logger.trace("msg{}", 1))),
                    arguments(consumer("trace(String, Object, Object)", logger -> logger.trace("msg{}{}", 1, 2))),
                    arguments(consumer("trace(String, Object...)", logger -> logger.trace("msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("trace(String, Throwable)", logger -> logger.trace("msg", anyException))),
                    arguments(consumer("trace(Marker, String)", logger -> logger.trace(anyMarker, "msg"))),
                    arguments(consumer("trace(Marker, String, Object)", logger -> logger.trace(anyMarker, "msg{}", 1))),
                    arguments(consumer("trace(Marker, String, Object, Object)", logger -> logger.trace(anyMarker, "msg{}{}", 1, 2))),
                    arguments(consumer("trace(Marker, String, Object...)", logger -> logger.trace(anyMarker, "msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("trace(Marker, String, Throwable)", logger -> logger.trace(anyMarker, "msg", anyException))),

                    arguments(consumer("debug(String)", logger -> logger.debug("msg"))),
                    arguments(consumer("debug(String, Object)", logger -> logger.debug("msg{}", 1))),
                    arguments(consumer("debug(String, Object, Object)", logger -> logger.debug("msg{}{}", 1, 2))),
                    arguments(consumer("debug(String, Object...)", logger -> logger.debug("msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("debug(String, Throwable)", logger -> logger.debug("msg", anyException))),
                    arguments(consumer("debug(Marker, String)", logger -> logger.debug(anyMarker, "msg"))),
                    arguments(consumer("debug(Marker, String, Object)", logger -> logger.debug(anyMarker, "msg{}", 1))),
                    arguments(consumer("debug(Marker, String, Object, Object)", logger -> logger.debug(anyMarker, "msg{}{}", 1, 2))),
                    arguments(consumer("debug(Marker, String, Object...)", logger -> logger.debug(anyMarker, "msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("debug(Marker, String, Throwable)", logger -> logger.debug(anyMarker, "msg", anyException))),

                    arguments(consumer("info(String)", logger -> logger.info("msg"))),
                    arguments(consumer("info(String, Object)", logger -> logger.info("msg{}", 1))),
                    arguments(consumer("info(String, Object, Object)", logger -> logger.info("msg{}{}", 1, 2))),
                    arguments(consumer("info(String, Object...)", logger -> logger.info("msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("info(String, Throwable)", logger -> logger.info("msg", anyException))),
                    arguments(consumer("info(Marker, String)", logger -> logger.info(anyMarker, "msg"))),
                    arguments(consumer("info(Marker, String, Object)", logger -> logger.info(anyMarker, "msg{}", 1))),
                    arguments(consumer("info(Marker, String, Object, Object)", logger -> logger.info(anyMarker, "msg{}{}", 1, 2))),
                    arguments(consumer("info(Marker, String, Object...)", logger -> logger.info(anyMarker, "msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("info(Marker, String, Throwable)", logger -> logger.info(anyMarker, "msg", anyException))),

                    arguments(consumer("warn(String)", logger -> logger.warn("msg"))),
                    arguments(consumer("warn(String, Object)", logger -> logger.warn("msg{}", 1))),
                    arguments(consumer("warn(String, Object, Object)", logger -> logger.warn("msg{}{}", 1, 2))),
                    arguments(consumer("warn(String, Object...)", logger -> logger.warn("msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("warn(String, Throwable)", logger -> logger.warn("msg", anyException))),
                    arguments(consumer("warn(Marker, String)", logger -> logger.warn(anyMarker, "msg"))),
                    arguments(consumer("warn(Marker, String, Object)", logger -> logger.warn(anyMarker, "msg{}", 1))),
                    arguments(consumer("warn(Marker, String, Object, Object)", logger -> logger.warn(anyMarker, "msg{}{}", 1, 2))),
                    arguments(consumer("warn(Marker, String, Object...)", logger -> logger.warn(anyMarker, "msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("warn(Marker, String, Throwable)", logger -> logger.warn(anyMarker, "msg", anyException))),

                    arguments(consumer("error(String)", logger -> logger.error("msg"))),
                    arguments(consumer("error(String, Object)", logger -> logger.error("msg{}", 1))),
                    arguments(consumer("error(String, Object, Object)", logger -> logger.error("msg{}{}", 1, 2))),
                    arguments(consumer("error(String, Object...)", logger -> logger.error("msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("error(String, Throwable)", logger -> logger.error("msg", anyException))),
                    arguments(consumer("error(Marker, String)", logger -> logger.error(anyMarker, "msg"))),
                    arguments(consumer("error(Marker, String, Object)", logger -> logger.error(anyMarker, "msg{}", 1))),
                    arguments(consumer("error(Marker, String, Object, Object)", logger -> logger.error(anyMarker, "msg{}{}", 1, 2))),
                    arguments(consumer("error(Marker, String, Object...)", logger -> logger.error(anyMarker, "msg{}{}{}", 1, 2, 3))),
                    arguments(consumer("error(Marker, String, Throwable)", logger -> logger.error(anyMarker, "msg", anyException)))
            );
        }

        private final Exception anyException = new Exception();
        private final Marker anyMarker = Mockito.mock(Marker.class);
        private Named<Consumer<Logger>> consumer(String functionName, Consumer<Logger> function) {
            return named(functionName, function);
        }
    }

    private <T> void givenPriming(Logger logger, Function<Logger, T> supplier, T value) {
        when(supplier.apply(logger)).thenReturn(value);
    }

    private void thenVerify(Logger logger, Function<Logger, ?> interaction) {
        interaction.apply(Mockito.verify(logger));
    }

    private void thenVerify(Logger logger, Consumer<Logger> interaction) {
        interaction.accept(Mockito.verify(logger));
    }

    private <T> void thenAssert(Logger logger, Function<Logger, T> supplier, T value) {
        assertThat(supplier.apply(logger)).isEqualTo(value);
    }

    private final Logger delegateLogger = Mockito.mock(Logger.class);
    private final TestLogger testLogger = new TestLogger(delegateLogger);
    private static class TestLogger extends AbstractDelegatingLogger {
        public TestLogger(Logger delegateLogger) {
            super(delegateLogger);
        }
    }
}