package org.claytantor;

/**
 * Created by claytongraham on 5/24/18.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
public class IntpickApp {

    private static final Logger logger = LogManager.getLogger(IntpickApp.class);

    public static void main(String[] args) {
        logger.info("IntpickApp spring application has started.");
        SpringApplication.run(IntpickApp.class, args);
    }



}