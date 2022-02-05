package com.microgrid.ethereum;


/* This class is inspired by the Geth Java implementation provided at
    https://github.com/web3j/web3j/blob/master/geth/src/main/java/org/web3j/protocol/geth/Geth.java

    However, this class is implemented for Android and is a large package making it easier to simply
    extract any necessary implementation

*/

import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.BooleanResponse;
import org.web3j.protocol.core.methods.response.MinerStartResponse;

public interface GethApi {
    Request<?, BooleanResponse> minerStop();

    Request<?, MinerStartResponse> minerStart(int threadCount);
}
