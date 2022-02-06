package com.microgrid.smartmeter;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.simple.SimpleHttpResponseFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/* This controller allows for the weather state to be changed via a HTTP PUT request.
*  The available states can be found in the Weather class: "sunny", "windy", "rainy"
*
* An example HTTP request would be:
*       curl -X PUT 127.0.0.1:8080/weather \
*       --header "Content-Type: application/json" \
*       --data '{"state":"sunny"}'
* */

@Controller("/weather")
public class WeatherDetector {

    @Inject
    private WeatherReader weatherReader;

    @Put
    public HttpResponse<String> setWeather(@NotNull String state) {
        Weather weather = Weather.fromName(state);
        if (weather == null)
            return new SimpleHttpResponseFactory().status(HttpStatus.BAD_REQUEST, state + "is not a valid weather type");

        weatherReader.updateWeather(Weather.fromName(state));
        return new SimpleHttpResponseFactory().ok("Set weather to " + state);
    }
}
