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

class TutorialTest {

    @Nested
    class Builder {
        @ParameterizedTest
        @ArgumentsSource(TutorialMethodReferencesProvider.class)
        void builderReturnsExpectedDefaultValues(Function<Tutorial, Object> tutorialFunc) {
            assertThat(tutorialFunc.apply(Tutorial.tutorial().build())).isEqualTo(null);
        }

        @ParameterizedTest
        @ArgumentsSource(TutorialMethodReferencesWithExpectedValuesProvider.class)
        void builderReturnsExpectedValues(Function<Tutorial, Object> tutorialFunc, Function<Tutorial.Builder, Tutorial.Builder> builderFunc, Object expectedValue) {
            Tutorial builderResult = builderFunc.apply(Tutorial.tutorial()).build();
            assertThat(tutorialFunc.apply(builderResult)).isEqualTo(expectedValue);
        }

        @Test
        void builderMatchesResultOfToBuilder() {
            Tutorial tutorial = new Tutorial(1L, "title", "description", true);
            assertThat(tutorial.toBuilder().build()).isEqualTo(tutorial);
        }
    }

    private static class TutorialMethodReferencesProvider implements ArgumentsProvider {

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(named("Tutorial::id", ref(Tutorial::id))),
                    arguments(named("Tutorial::title", ref(Tutorial::title))),
                    arguments(named("Tutorial::description", ref(Tutorial::description))),
                    arguments(named("Tutorial::isPublished", ref(Tutorial::published)))
            );
        }

        private Function<Tutorial, Object> ref(Function<Tutorial, Object> func) {
            return func;
        }
    }
    private static class TutorialMethodReferencesWithExpectedValuesProvider implements ArgumentsProvider {

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(
                            named("Tutorial::id", ref(Tutorial::id)),
                            named("BuilderUnmodified", builder(builder -> builder)),
                            null
                    ),
                    arguments(
                            named("Tutorial::title", ref(Tutorial::title)),
                            named("Builder::withTitle", builder(builder -> builder.withTitle("value"))),
                            "value"
                    ),
                    arguments(
                            named("Tutorial::description", ref(Tutorial::description)),
                            named("Builder::withDescription", builder(builder -> builder.withDescription("value"))),
                            "value"
                    ),
                    arguments(
                            named("Tutorial::isPublished", ref(Tutorial::published)),
                            named("Builder::withPublished", builder(builder -> builder.withPublished(true))),
                            true
                    )
            );
        }

        private Function<Tutorial, Object> ref(Function<Tutorial, Object> func) {
            return func;
        }

        private Function<Tutorial.Builder, Tutorial.Builder> builder(Function<Tutorial.Builder, Tutorial.Builder> func) {
            return func;
        }
    }

}