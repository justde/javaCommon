package org.justd.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zhangjd
 * @title: Example
 * @description:
 * @date 2020/1/523:20
 */
public class ExampleLogback {
    public static void main(String[] args) throws InterruptedException {
        final Logger logger = LoggerFactory.getLogger(ExampleLogback.class);

        logger.debug("log debug");
        logger.info("log info");
        logger.error("log error");

        Thread.sleep(10000L);
        logger.debug("saveUserLog debug");
        logger.info("saveUserLog info");
        logger.error("saveUserLog error");
    }
}
