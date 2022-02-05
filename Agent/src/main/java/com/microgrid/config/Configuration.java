package com.microgrid.config;

import io.micronaut.context.annotation.Value;
import lombok.Getter;
import lombok.ToString;

import javax.inject.Singleton;

@Getter
@Singleton
@ToString
public class Configuration {
    @Value("${blockchain.network.chainId}")
    private long chainId;

    @Value("${blockchain.network.scheme}")
    private String scheme;

    @Value("${blockchain.network.host}")
    private String host;

    @Value("${blockchain.network.port}")
    private int port;

    @Value("${blockchain.wallet.password}")
    private String walletPassword;

    @Value("${blockchain.wallet.dir}")
    private String walletPath;

    public String getUrl()  {
        return String.format("%s://%s:%d", scheme, host, port);
    }

}
