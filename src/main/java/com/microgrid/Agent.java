package com.microgrid;

import com.microgrid.generated.contracts.HelloWorld;
import io.micronaut.context.annotation.Value;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.inject.Singleton;

/**
 * <p>This is the generated class for <code>web3j new helloworld</code></p>
 * <p>It deploys the Hello World contract in src/main/solidity/ and prints its address</p>
 * <p>For more information on how to run this project, please refer to our <a href="https://docs.web3j.io/quickstart/#deployment">documentation</a></p>
 */
@Singleton
public class Agent {

    // TODO: this should be extracted to a Configuration Singleton
    // will do for now
//    @Value("${blockchain.node-url}")
    private String nodeUrl = "http://localhost:8545";
    @Value("${blockchain.wallet.password}")
    private String walletPassword;
    @Value("${blockchain.wallet.dir}")
    private String walletPath;

    public void start_agent() throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletPath);
        Web3j web3j = Web3j.build(new HttpService(nodeUrl));

        // We need to use the transaction manager to specify the ChainID
        RawTransactionManager transactionM = new RawTransactionManager(web3j, credentials, 987);
        System.out.println("Deploying HelloWorld contract ... " + nodeUrl);
        HelloWorld helloWorld = HelloWorld.deploy(web3j, transactionM, new DefaultGasProvider(), "Hello Blockchain World!").send();
        System.out.println("Contract address: " + helloWorld.getContractAddress());
        System.out.println("Greeting method result: " + helloWorld.greeting().send());
    }
}

