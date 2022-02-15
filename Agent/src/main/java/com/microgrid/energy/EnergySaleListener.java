package com.microgrid.energy;

import com.contract.generated.contracts.EnergyContract;
import jnr.ffi.annotations.In;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.math.BigInteger;

@Slf4j
@AllArgsConstructor
public class EnergySaleListener implements io.reactivex.functions.Consumer<EnergyContract.AnnounceSaleIntentionEventResponse> {

    private final Producer producer;
    private final Consumer consumer;
    private final EnergyContract contract;

    @Override
    public void accept(EnergyContract.AnnounceSaleIntentionEventResponse saleEvent) throws Exception {
        // Check if I need to sell
        int result = consumer.consume_energy() - producer.produce_energy();
        if(result > 0) {
            contract.buyEnergy(BigInteger.valueOf(result));
            log.info("Buying energy");
        } else {
            log.info("Ignoring nothing to sell");
        }
    }


}
