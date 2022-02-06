package com.microgrid.energy;

import com.microgrid.smartmeter.WeatherReader;
import jnr.ffi.annotations.In;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class Consumer {

    @Inject
    private WeatherReader weatherReader;

    private static final long BASE_CONSUMPTION = 10000;

    int consume_energy() {

        weatherReader.getWeatherReading();



        // What are the units
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

}
