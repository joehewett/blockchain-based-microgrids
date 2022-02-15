package com.microgrid.energy;


public class EnergyFlowTask implements Runnable {

    public static final int ENERGY = 10000;

    private final double productionRate;
    private final double consumptionRate;
    private final SmartMeter smartMeter;

    public EnergyFlowTask(SmartMeter smartMeter, double consumptionRate, double productionRate) {
        this.smartMeter = smartMeter;
        this.productionRate = productionRate;
        this.consumptionRate = consumptionRate;
    }

    @Override
    public void run() {
        smartMeter.addConsumedEnergy((int)(ENERGY * productionRate));
        smartMeter.addConsumedEnergy((int)(ENERGY * consumptionRate));
    }
}
