package com.microgrid.smartmeter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
public enum Weather {
    SUNNY("sunny"),
    RAINY("rainy"),
    CLOUDY("cloudy");

    // Static map for lookups
    private static final Map<String, Weather> nameMap =
            Arrays.stream(Weather.values())
                    .collect(Collectors.toMap(weather -> weather.name, weather -> weather));

    private final String name;

    Weather(String name) {
        this.name = name;
    }

    public static Weather fromName(String name) {
        Weather weather = nameMap.get(name.trim().toLowerCase(Locale.ROOT));
        if(weather == null)
            log.error("No Weather state for name {}", name);
        return weather;
    }
}
