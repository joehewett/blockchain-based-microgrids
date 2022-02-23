package com.microgrid.ethereum.contract;

import com.contract.generated.contracts.EnergyContract;
import com.microgrid.energy.SmartMeter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;

@Slf4j
@AllArgsConstructor
public class EnergySaleListener implements io.reactivex.functions.Consumer<EnergyContract.SaleGoneThroughEventResponse> {

    private final Credentials credentials;
    private final SmartMeter smartMeter;

    @Override
    public void accept(EnergyContract.SaleGoneThroughEventResponse sale) throws Exception {
        final String myAddress = credentials.getAddress();

        if (myAddress.equals(sale.buyer)) {
            smartMeter.addProducedEnergy(sale.amount.intValue());
        } else if (myAddress.equals(sale.seller)) {
            smartMeter.addConsumedEnergy(sale.amount.intValue());
        }
    }
}
