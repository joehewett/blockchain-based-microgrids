package com.microgrid.energy;

import com.contract.generated.contracts.EnergyContract;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@AllArgsConstructor
public class EnergySaleListener implements io.reactivex.functions.Consumer<EnergyContract.AnnounceSaleIntentionEventResponse> {

    private final SmartMeter smartMeter;
    private final EnergyContract contract;

    @Override
    public void accept(EnergyContract.AnnounceSaleIntentionEventResponse saleEvent) throws Exception {
        // Check if I need to sell
        long result = smartMeter.getConsumedEnergy() - smartMeter.getProducedEnergy();
        if(result > 0) {
            contract.buyEnergy(BigInteger.valueOf(result));
            log.info("Buying energy");
        } else {
            log.info("Ignoring nothing to sell");
        }
    }


}
