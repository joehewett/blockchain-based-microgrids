package com.microgrid.smartmeter;

import javax.inject.Singleton;

@Singleton
public class WeatherReader { // TODO: this name is awful

    private Weather currentReading;

    public Weather getWeatherReading() {
        return currentReading;
    }

    public void updateWeather(Weather meterReading) {
        currentReading = meterReading;
    }

}
