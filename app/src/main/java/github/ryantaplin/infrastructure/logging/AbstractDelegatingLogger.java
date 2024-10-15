package github.ryantaplin.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;

public abstract class AbstractDelegatingLogger implements Logger {

    private final Logger delegateLogger;

    public AbstractDelegatingLogger(Logger delegateLogger) {
        this.delegateLogger = delegateLogger;
    }

    @Override
    public String getName() {
        return delegateLogger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return delegateLogger.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        delegateLogger.trace(s);
    }

    @Override
    public void trace(String s, Object o) {
        delegateLogger.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        delegateLogger.trace(s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        delegateLogger.trace(s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        delegateLogger.trace(s, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return delegateLogger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String s) {
        delegateLogger.trace(marker, s);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        delegateLogger.trace(marker, s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        delegateLogger.trace(marker, s, o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        delegateLogger.trace(marker, s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        delegateLogger.trace(marker, s, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return delegateLogger.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        delegateLogger.debug(s);
    }

    @Override
    public void debug(String s, Object o) {
        delegateLogger.debug(s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        delegateLogger.debug(s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects) {
        delegateLogger.debug(s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        delegateLogger.debug(s, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return delegateLogger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String s) {
        delegateLogger.debug(marker, s);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        delegateLogger.debug(marker, s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        delegateLogger.debug(marker, s, o, o1);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        delegateLogger.debug(marker, s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        delegateLogger.debug(marker, s, throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return delegateLogger.isInfoEnabled();
    }

    @Override
    public void info(String s) {
        delegateLogger.info(s);
    }

    @Override
    public void info(String s, Object o) {
        delegateLogger.info(s, o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        delegateLogger.info(s, o, o1);
    }

    @Override
    public void info(String s, Object... objects) {
        delegateLogger.info(s, objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        delegateLogger.info(s, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return delegateLogger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String s) {
        delegateLogger.info(marker, s);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        delegateLogger.info(marker, s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        delegateLogger.info(marker, s, o, o1);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        delegateLogger.info(marker, s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        delegateLogger.info(marker, s, throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return delegateLogger.isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        delegateLogger.warn(s);
    }

    @Override
    public void warn(String s, Object o) {
        delegateLogger.warn(s, o);
    }

    @Override
    public void warn(String s, Object... objects) {
        delegateLogger.warn(s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        delegateLogger.warn(s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        delegateLogger.warn(s, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return delegateLogger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String s) {
        delegateLogger.warn(marker, s);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        delegateLogger.warn(marker, s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        delegateLogger.warn(marker, s, o, o1);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        delegateLogger.warn(marker, s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        delegateLogger.warn(marker, s, throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return delegateLogger.isErrorEnabled();
    }

    @Override
    public void error(String s) {
        delegateLogger.error(s);
    }

    @Override
    public void error(String s, Object o) {
        delegateLogger.error(s, o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        delegateLogger.error(s, o, o1);
    }

    @Override
    public void error(String s, Object... objects) {
        delegateLogger.error(s, objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        delegateLogger.error(s, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return delegateLogger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String s) {
        delegateLogger.error(marker, s);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        delegateLogger.error(marker, s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        delegateLogger.error(marker, s, o, o1);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        delegateLogger.error(marker, s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        delegateLogger.error(marker, s, throwable);
    }
}
