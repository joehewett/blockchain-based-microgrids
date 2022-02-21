package com.microgrid.energy;


import com.contract.generated.contracts.EnergyContract;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@AllArgsConstructor
public class EnergySaleTask implements Runnable {

    private final SmartMeter smartMeter;
    private final EnergyContract contract;

    @Override
    public void run() {
        long excessEnergy = smartMeter.getProducedEnergy() - smartMeter.getConsumedEnergy();
        log.info("Produced: {}, Consumed: {}", smartMeter.getProducedEnergy(), smartMeter.getConsumedEnergy());
        if (excessEnergy > 0) {
            log.info("Selling energy {}", excessEnergy);
            contract.sellEnergy(BigInteger.valueOf(excessEnergy)).sendAsync();
        } else {
            log.info("No energy to sell {}", excessEnergy);
        }
    }
}
