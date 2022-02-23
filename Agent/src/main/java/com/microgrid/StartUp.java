package com.microgrid;

import com.microgrid.config.Configuration;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class StartUp implements ApplicationEventListener<StartupEvent> {

  @Inject
  private Agent agent;
  @Inject
  private Configuration config;

  @Override
  public void onApplicationEvent(StartupEvent event) {
    try {
      log.info("Starting agent with config: \n {}", config.toString());
      agent.start_agent();
    } catch (Exception e) {
      log.error("Failed to start agent");
      e.printStackTrace();
    }
  }
}
