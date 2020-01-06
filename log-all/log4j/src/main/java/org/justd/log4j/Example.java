package org.justd.log4j;

import org.apache.log4j.Logger;

/**
 * @author Zhangjd
 * @title: Example
 * @description:
 * @date 2020/1/516:39
 */
public class Example {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Example.class);
        final Logger saveUserLog = Logger.getLogger("saveUserLog");

        logger.debug("log debug");
        logger.info("log info");
        logger.error("log error");

        saveUserLog.debug("saveUserLog debug");
        saveUserLog.info("saveUserLog info");
        saveUserLog.error("saveUserLog error");
    }
}
