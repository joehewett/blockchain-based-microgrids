package com.microgrid.energy;

import javax.inject.Singleton;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class Consumer {

    int consume_energy() {
        // What are the units
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

}
