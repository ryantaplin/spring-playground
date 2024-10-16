package github.ryantaplin.infrastructure.database.tutorial;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TutorialRepository extends CrudRepository<TutorialDao, Long> {
    List<TutorialDao> findByTitle(String title);
}