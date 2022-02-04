package com.microgrid.ethereum.impl;

import com.microgrid.ethereum.EthAuthentication;
import io.micronaut.context.annotation.Factory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class EthAuthenticationImpl implements EthAuthentication {

    @Inject
    private

    @Singleton
    public Credentials fetchCredentials() {

        WalletUtils.loadCredentials(String password,String source)
    }

}
