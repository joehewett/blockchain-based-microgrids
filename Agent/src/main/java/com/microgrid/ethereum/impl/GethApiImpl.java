package com.microgrid.ethereum.impl;

import com.microgrid.ethereum.GethApi;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.BooleanResponse;
import org.web3j.protocol.core.methods.response.MinerStartResponse;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collections;

@Singleton
public class GethApiImpl implements GethApi {

    private Web3jService httpClient;

    // Handled by micronaut through constructor injection
    public GethApiImpl(Web3jService httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Request<?, MinerStartResponse> minerStart(int threadCount) {
        return new Request<>(
                "miner_start",
                Collections.singletonList(threadCount),
                httpClient,
                MinerStartResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> minerStop() {
        return new Request<>(
                "miner_stop",
                Collections.<String>emptyList(),
                httpClient,
                BooleanResponse.class);
    }
}
