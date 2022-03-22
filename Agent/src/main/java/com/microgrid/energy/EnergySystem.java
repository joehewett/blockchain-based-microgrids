package com.microgrid.energy;


import com.microgrid.config.Configuration;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// TODO: this could have a cleaner interface
@Slf4j
@Singleton
public class EnergySystem {

    @Inject
    private SmartMeter smartMeter;

    @Inject
    private Configuration config;

    private final ScheduledExecutorService executorService =
            Executors.newSingleThreadScheduledExecutor();

    // Should it be atomic?
    private boolean active;

    public boolean powerUpAppliances() {
        if (!active) {
            executorService.scheduleWithFixedDelay(
                    new EnergyFlowTask(smartMeter, config.getConsumptionRate(), config.getProductionRate()),
                    5, 5, TimeUnit.SECONDS);
            return (active = true);
        }
        log.info("Tried no power up a running system");
        return false;
    }

    public void shutDownAppliances() {
        if (active && executorService.isShutdown()) {
            executorService.shutdown();
        } else {
            log.debug("Tried to shutdown a cancelled");
        }
    }

}
