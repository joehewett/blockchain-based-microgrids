package com.contract.ethereum;

import com.contract.config.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.JsonRpc2_0Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.Request;

import java.io.IOException;

@Slf4j
public class Administrator {

    private final Configuration config;
    private final Web3jService service;

    public Administrator(Web3jService service) {
        this.service = service;
        this.config = Configuration.getInstance();
    }

    public void unlockAccount(Credentials credentials) throws IOException {
        JsonRpc2_0Admin admin = new JsonRpc2_0Admin(service);

        String address = credentials.getAddress();

        String walletPassword = config.getWalletPassword();
        Request<?, PersonalUnlockAccount> personalUnlockAccountRequest = admin.personalUnlockAccount(address, walletPassword);
        PersonalUnlockAccount unlockResponse = personalUnlockAccountRequest.send();
        log.info(unlockResponse.accountUnlocked().toString());
        if (unlockResponse.accountUnlocked()) {
            log.error("Failed to unlock account for user {}", credentials.getAddress());
            // Just shut down we cannot recover
            // admittedly runtime is generic but will do
            throw new RuntimeException("Failed to unlock account");
        }
    }
}
