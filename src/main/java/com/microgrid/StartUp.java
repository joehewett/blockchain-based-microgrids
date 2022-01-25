package com.microgrid;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Slf4j
public class StartUp implements ApplicationEventListener<StartupEvent> {

    @Inject
    private Agent agent;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        try {
            log.info("Starting agent");
            agent.start_agent();
        } catch (Exception e) {
            log.error("Failed to start agent");
            e.printStackTrace();
        }
    }
}
