package com.microgrid;

import com.microgrid.config.Configuration;
import com.microgrid.ethereum.EthereumApi;
import com.microgrid.ethereum.GethApi;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.MinerStartResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    private Web3jService httpService;

    @Inject
    private Configuration config;

    @Inject
    private GethApi gethApi;

    public void start_agent() throws Exception {
        Web3j web3j = Web3j.build(httpService);
        log.info("Successfully connected at {}", config.getUrl());

        MinerStartResponse sent = gethApi.minerStart(1).send();
        if(sent.hasError()) {
            log.error("Failed to start mining: {}", sent.getError().getMessage());
            return;
        } else {
            log.info("Sucesfully started mining");
        }



        //        // We need to use the transaction manager to specify the ChainID
        ////        RawTransactionManager transactionM = new RawTransactionManager(web3j, credentials,
        // 987);
        //        System.out.println("Deploying HelloWorld contract ... " + nodeUrl);
        //        HelloWorld helloWorld = HelloWorld.deploy(web3j, transactionM, new
        //         DefaultGasProvider(), "Hello Blockchain World!").send();
        //        System.out.println("Contract address: " + helloWorld.getContractAddress());
        //        System.out.println("Greeting method result: " + helloWorld.greeting().send());
        //
        // Print acounts
        System.out.println("Agents:");

        web3j.ethAccounts().send().getAccounts().forEach(System.out::println);
    }
}
