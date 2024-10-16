package github.ryantaplin.infrastructure.database.tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.List;
import java.util.Optional;

import static github.ryantaplin.infrastructure.database.tutorial.TutorialDao.tutorial;
import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
public class TutorialRepositoryTest {

    @Test
    void saveTutorial() {
        final TutorialDao result = repository.save(TUTORIAL_BUILDER.build());
        assertThat(result.id()).isNotNull();
    }

    @Test
    void readTutorial() {
        TutorialDao persistedTutorial = repository.save(TUTORIAL_BUILDER.build());

        Optional<TutorialDao> result = repository.findById(persistedTutorial.id());
        assertThat(result).isPresent();
        assertThat(result.map(TutorialDao::title)).contains(TUTORIAL_NAME);
        assertThat(result.map(TutorialDao::description)).contains(TUTORIAL_DESCRIPTION);
        assertThat(result.map(TutorialDao::published)).contains(true);
    }

    @Test
    void findAllByTitle() {
        List.of("titleA", "titleB", "titleA", "titleC").forEach(title -> repository.save(TUTORIAL_BUILDER.withTitle(title).build()));
        assertThat(repository.findByTitle("titleA")).hasSize(2);
    }

    private static final String TUTORIAL_NAME = "name";
    private static final String TUTORIAL_DESCRIPTION = "description";

    private static final TutorialDao.Builder TUTORIAL_BUILDER = tutorial()
            .withTitle(TUTORIAL_NAME)
            .withDescription(TUTORIAL_DESCRIPTION)
            .withPublished(true);

    @Autowired
    private TutorialRepository repository;
}