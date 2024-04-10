package com.erick.technicaltest.springbootcrudspaceships.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.erick.technicaltest.springbootcrudspaceships.controllers.ShipsController.view(..)) && args(id,..)")
    public void logNegativeId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("Attempted to get a ship with a negative ID: {}", id);
        }
    }

}