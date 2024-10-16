package github.ryantaplin.infrastructure.database.tutorial;

import org.springframework.data.annotation.Id;

public record Tutorial(@Id Long id,
                       String title,
                       String description,
                       Boolean published
) {

    public Builder toBuilder() {
        return new Builder(id, title, description, published);
    }

    public static Builder tutorial() {
        return new Builder(null, null, null, null);
    }

    public static class Builder {

        private final Long id;

        private String title;
        private String description;
        private Boolean published;

        public Builder(Long id, String title, String description, Boolean published) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.published = published;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPublished(boolean isPublished) {
            this.published = isPublished;
            return this;
        }

        public Tutorial build() {
            return new Tutorial(id, title, description, published);
        }
    }
}