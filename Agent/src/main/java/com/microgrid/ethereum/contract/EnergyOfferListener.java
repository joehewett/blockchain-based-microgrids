package com.microgrid.ethereum.contract;

import com.contract.generated.contracts.EnergyContract;
import com.microgrid.energy.SmartMeter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;

import java.math.BigInteger;

@Slf4j
@AllArgsConstructor
public class EnergyOfferListener implements io.reactivex.functions.Consumer<EnergyContract.AnnounceSaleIntentionEventResponse> {

    private final SmartMeter smartMeter;
    private final EnergyContract contract;
    private final Credentials credentials;

    @Override
    public void accept(EnergyContract.AnnounceSaleIntentionEventResponse saleEvent) throws Exception {
        // Ignore your own sales
        if (!credentials.getAddress().equals(saleEvent.seller)) {

            long result = smartMeter.getConsumedEnergy() - smartMeter.getProducedEnergy();
            if (result > 0) {
                // DO we want it to be blocking?
                // This assumes a price of 1
                contract.buyEnergy(BigInteger.valueOf(result), BigInteger.valueOf(result)).send();
                log.info("Bought energy");
            } else {
                log.debug("Ignoring nothing to buy");
            }
        }
        // Check if I need to sell
    }


}
