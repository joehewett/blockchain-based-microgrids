package com.microgrid;

import com.microgrid.config.Configuration;
import com.microgrid.ethereum.EnergyContract;
import com.microgrid.ethereum.GethApi;
import lombok.extern.slf4j.Slf4j;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int16;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.response.MinerStartResponse;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * This is the generated class for <code>web3j new helloworld</code>
 *
 * <p>It deploys the Hello World contract in src/main/solidity/ and prints its address
 *
 * <p>For more information on how to run this project, please refer to our <a
 * href="https://docs.web3j.io/quickstart/#deployment">documentation</a>
 */
@Singleton
@Slf4j
public class Agent {

    @Inject
    private Configuration config;
    @Inject
    private GethApi gethApi;
    @Inject
    private Credentials credentials;
    @Inject
    private Web3jService httpService;

    public void start_agent() throws Exception {
        Web3j web3j = Web3j.build(httpService);
        log.info("Successfully connected at {}", config.getUrl());

//        0xb91187c57a9e329bad32a547877960d3f3df0df2

        RawTransactionManager transactionManager =
                new RawTransactionManager(web3j, credentials, config.getChainId());

        EnergyContract contract = EnergyContract.load("0x57b138d4acc0a8872b0b9951d9139f909cd0a20d", web3j, transactionManager, new DefaultGasProvider());

        log.error(contract.sellEnergy(BigInteger.TEN).send().toString());
        log.error(contract.getToBeSold().send().toString());

        web3j.ethAccounts().send().getAccounts().forEach(System.out::println);
    }
}
