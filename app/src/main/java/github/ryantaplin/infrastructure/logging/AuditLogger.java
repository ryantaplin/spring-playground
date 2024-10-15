package github.ryantaplin.infrastructure.logging;

import org.slf4j.LoggerFactory;

public class AuditLogger extends AbstractDelegatingLogger {
    public AuditLogger() {
        super(LoggerFactory.getLogger("AUDIT"));
    }
}
