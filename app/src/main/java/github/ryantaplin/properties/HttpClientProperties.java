package github.ryantaplin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("app.infrastructure.http-client")
public record HttpClientProperties(Duration connectTimeout) { }
