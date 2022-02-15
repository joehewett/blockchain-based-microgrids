package com.microgrid.energy;

import javax.inject.Singleton;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class Producer {

    public int produce_energy() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }
}
