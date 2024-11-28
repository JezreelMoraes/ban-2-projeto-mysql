package com.gymesc.infrastructure.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void warn(String message, Exception exception) {
        logger.warn(message + ": " + exception.getMessage(), exception);
    }

    public static void error(String message, Exception exception) {
        logger.error(message + ": " + exception.getMessage(), exception);
    }
}
