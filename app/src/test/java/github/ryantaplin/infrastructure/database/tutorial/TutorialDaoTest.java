package github.ryantaplin.infrastructure.database.tutorial;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TutorialDaoTest {

    @Nested
    class Builder {
        @ParameterizedTest
        @ArgumentsSource(TutorialMethodReferencesProvider.class)
        void builderReturnsExpectedDefaultValues(Function<TutorialDao, Object> tutorialFunc) {
            assertThat(tutorialFunc.apply(TutorialDao.tutorial().build())).isEqualTo(null);
        }

        @ParameterizedTest
        @ArgumentsSource(TutorialMethodReferencesWithExpectedValuesProvider.class)
        void builderReturnsExpectedValues(Function<TutorialDao, Object> tutorialFunc, Function<TutorialDao.Builder, TutorialDao.Builder> builderFunc, Object expectedValue) {
            TutorialDao builderResult = builderFunc.apply(TutorialDao.tutorial()).build();
            assertThat(tutorialFunc.apply(builderResult)).isEqualTo(expectedValue);
        }

        @Test
        void builderMatchesResultOfToBuilder() {
            TutorialDao tutorial = new TutorialDao(1L, "title", "description", true);
            assertThat(tutorial.toBuilder().build()).isEqualTo(tutorial);
        }
    }

    private static class TutorialMethodReferencesProvider implements ArgumentsProvider {

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(named("Tutorial::id", ref(TutorialDao::id))),
                    arguments(named("Tutorial::title", ref(TutorialDao::title))),
                    arguments(named("Tutorial::description", ref(TutorialDao::description))),
                    arguments(named("Tutorial::isPublished", ref(TutorialDao::published)))
            );
        }

        private Function<TutorialDao, Object> ref(Function<TutorialDao, Object> func) {
            return func;
        }
    }
    private static class TutorialMethodReferencesWithExpectedValuesProvider implements ArgumentsProvider {

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(
                            named("Tutorial::id", ref(TutorialDao::id)),
                            named("BuilderUnmodified", builder(builder -> builder)),
                            null
                    ),
                    arguments(
                            named("Tutorial::title", ref(TutorialDao::title)),
                            named("Builder::withTitle", builder(builder -> builder.withTitle("value"))),
                            "value"
                    ),
                    arguments(
                            named("Tutorial::description", ref(TutorialDao::description)),
                            named("Builder::withDescription", builder(builder -> builder.withDescription("value"))),
                            "value"
                    ),
                    arguments(
                            named("Tutorial::isPublished", ref(TutorialDao::published)),
                            named("Builder::withPublished", builder(builder -> builder.withPublished(true))),
                            true
                    )
            );
        }

        private Function<TutorialDao, Object> ref(Function<TutorialDao, Object> func) {
            return func;
        }

        private Function<TutorialDao.Builder, TutorialDao.Builder> builder(Function<TutorialDao.Builder, TutorialDao.Builder> func) {
            return func;
        }
    }

}