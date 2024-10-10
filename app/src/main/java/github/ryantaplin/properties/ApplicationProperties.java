package github.ryantaplin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.info")
public record ApplicationProperties(String name, String version) { }
