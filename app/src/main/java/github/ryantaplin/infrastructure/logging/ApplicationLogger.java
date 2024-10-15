package github.ryantaplin.infrastructure.logging;

import org.slf4j.LoggerFactory;

public class ApplicationLogger extends AbstractDelegatingLogger {
    public ApplicationLogger() {
        super(LoggerFactory.getLogger("APPLICATION"));
    }
}
