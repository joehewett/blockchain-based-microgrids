package com.microgrid.ethereum;

import com.microgrid.config.Configuration;
import io.micronaut.context.annotation.Factory;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Factory
public class Web3JObjFactory {

    @Inject
    private Configuration config;

    @Singleton
    @Named("walletCredentials")
    public Credentials fetchCredentials() throws CipherException, IOException {
        try {
            // Hack for now to avoid changing file name on startup
            File dir = new File(config.getWalletPath());
            if(!dir.isDirectory()) {
                log.error("The wallet path must be a directory for now");
                return null; // FIXME
            }
            Optional<File> wallet = Arrays.stream(dir.listFiles()).findFirst();
            if(wallet.isEmpty()) {
                log.error("Could not find wallet in {}", config.getWalletPath());
                return null;
            }

            return WalletUtils.loadCredentials(config.getWalletPassword(), wallet.get().getAbsolutePath());
        } catch (Exception ex) {
            log.error("Failed to load wallet at address {}", config.getWalletPath(), ex);

            // Rethrow exceptions as we cannot recover from not finding the wallet key
            throw ex;
        }
    }

    @Singleton
    public Web3jService createService() {
        return new HttpService(config.getUrl());
    }


}
