package com.microgrid.energy;

import lombok.NoArgsConstructor;

import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@NoArgsConstructor
public class SmartMeter {

    private final AtomicLong producedEnergy = new AtomicLong(0L);
    private final AtomicLong consumedEnergy = new AtomicLong(0L);

    public long getConsumedEnergy() {
        return consumedEnergy.get();
    }

    public long getProducedEnergy() {
        return producedEnergy.get();
    }

    public void addConsumedEnergy(int energy) {
        consumedEnergy.addAndGet(energy);
    }

    public void addProducedEnergy(int energy) {
        producedEnergy.addAndGet(energy);
    }

}
