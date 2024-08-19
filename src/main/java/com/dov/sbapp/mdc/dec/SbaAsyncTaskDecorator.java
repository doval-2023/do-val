package com.dov.sbapp.mdc.dec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.HashMap;
import java.util.Map;

public class SbaAsyncTaskDecorator implements TaskDecorator {

    public static final Logger logger = LogManager.getLogger(SbaAsyncTaskDecorator.class);
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        if(contextMap == null)
            contextMap = new HashMap<>();
        try{
            logger.info("Populating contextMap into mdc ");
            MDC.setContextMap(contextMap);
            runnable.run();
        }finally {
            MDC.clear();
        }

        return runnable;
    }
}
