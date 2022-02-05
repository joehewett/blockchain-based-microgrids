package com.microgrid.ethereum.impl;

import com.microgrid.ethereum.EthereumApi;

import java.io.File;
import javax.inject.Singleton;

import org.web3j.crypto.*;

@Singleton
public class EthereumApiImpl implements EthereumApi {

    @Override
    public String createWallet() throws Exception {
        String password = "password";
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard(password, keyPair);

        // TODO make configurable
        File cryptodir = new File("/root/cryptodir/");
        if (!cryptodir.exists()) {
            System.out.println("The file does not exits " + cryptodir.getAbsolutePath());
            return null;
        }
        String fileName = WalletUtils.generateNewWalletFile(password, cryptodir);

        System.out.println("Private key: " + keyPair.getPrivateKey().toString(16));
        System.out.println("Account: " + wallet.getAddress());

        return new File(cryptodir.getAbsolutePath(), fileName).getAbsolutePath();
    }
}
