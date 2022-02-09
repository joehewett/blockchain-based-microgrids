package com.contract.config;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/* Standard Singleton Class for configurations loaded from */

@Slf4j
@Getter
@ToString
public class Configuration {

    private static Configuration instance = null;

    private int port;
    private String host;
    private String scheme;
    private String walletPath;
    // @ToString.Exclude would be needed for security
    private String walletPassword;
    private long chainId;

    private Configuration() {
        loadProperties();
    }

    private void loadProperties() {
        scheme = System.getenv().getOrDefault("URL_SCHEME", "http");
        host = System.getenv().getOrDefault("URL_HOST", "localhost");
        port = Integer.parseInt(System.getenv().getOrDefault("URL_PORT", "8085"));
        walletPath = System.getenv().getOrDefault("WALLET_PATH", "/root/");
        walletPassword = System.getenv().getOrDefault("WALLET_PASSWORD", "password");
        chainId = Long.parseLong(System.getenv().getOrDefault("CHAIN_ID", "1"));
    }

    public String getWalletPath() {
        // Hack for now to avoid changing file name on startup
        File dir = new File(walletPath);

        if (!dir.isDirectory()) {
            log.error("The wallet path must be a directory for now {}", walletPath);
            return null; // FIXME
        }
        Optional<File> wallet = Arrays.stream(dir.listFiles())
                .filter(file -> file.getName().startsWith("UTC"))
                .findFirst();
        if (wallet.isEmpty()) {
            log.error("Could not find wallet in {}", walletPath);
            return null;
        }

        return wallet.get().getAbsolutePath();
    }

    public String getUrl() {
        return String.format("%s://%s:%d", scheme, host, port);
    }

    public static Configuration getInstance() {
        if (Objects.isNull(instance)) {
            Configuration.instance = new Configuration();
        }
        return Configuration.instance;
    }

}
