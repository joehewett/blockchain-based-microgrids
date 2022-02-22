## Contract Lib
This repository includes the code for the Smart Contracts and the Java Wrappers for the solidity 
contracts to be used by any java agent that wishes to interact with the contract.

### Building and Publishing
In order to generate the solidity java wrappers run:
```bash
./gradlew build
```
> ``JavaCompile`` is dependent on all other tasks required to run web3j and workaround methods (see below)

In order to publish the lib to ``mavenLocal`` you can run the following:
```bash
./gradlew publishToMavenLocal
```

> All the above is included as part of the ./local-build.sh script. We use maven local as there are many components 
> that are expected to use these wrappers so duplication of the code will just lead to errors. For now we assume the lack 
> of online repository


### Web3j (Solidity to Java)
The generation of the Java files is done by ``Web3j`` who offer a gradle plugin which 
includes all certain tasks necessary to automate the file generation.



#### External bugs and workarounds
One known bug is the invalid generation of any functions in the solidity file 
marked as ``payable``

> ``Payable`` is the modifier that indicates a smart contract function will accept payment

___Github ticket___: https://github.com/web3j/web3j/issues/1268

The proposed workaround is to modify the java wrappers post build by creating custom 
gradle tasks namely ``task fixPayableBug(type: Copy, dependsOn: 'generateContractWrappers')`` and ``task deletePreContracts(type: Delete)`` which will modified the generated code and append to it. Implementation can be found in ``build.gradle``

While functional, this solution does limit the automation of the it does have the following drawbacks:
* Hardcoding of payable function, cannot modify just solidity but ``build.gradle`` along with it
* Extra invalid function included as part of the wrapper, it will be down to users of this library to know not to use it

Considered workarounds: 
* Open PR to Web3j but ticket is 2 years old :(
* Locally build gradle plugin and ``web3j`` library, but bug is as yet unknown and the source code is very large to include as part of our project (build time rather than space)