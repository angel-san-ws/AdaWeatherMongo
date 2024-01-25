package com.adaschool.weathermongo.controllers;

import com.adaschool.weathermongo.repository.Weather;
import com.adaschool.weathermongo.repository.WeatherInsertDto;
import com.adaschool.weathermongo.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

        private final WeatherDataService weatherDataService;
        public WeatherController(@Autowired WeatherDataService weatherDataService){
            this.weatherDataService=weatherDataService;
        }

        @RequestMapping("/{id}")
        public ResponseEntity<Object> getWeatherByCityId(@PathVariable String id){
            return new ResponseEntity<> (weatherDataService.getWeather(id), HttpStatus.OK);
        }

        @PostMapping("/{id}")
        public ResponseEntity<String> createWeatherByCityId(@PathVariable String id, @RequestBody WeatherInsertDto weatherInsertDto){
            if(weatherDataService.insertWeather(id,weatherInsertDto.getTemp(), weatherInsertDto.getPressure(), weatherInsertDto.getHumidity()))
                return new ResponseEntity<>("Weather created successfully", HttpStatus.CREATED);
            else
                return new ResponseEntity<>("Error creating Weather", HttpStatus.BAD_REQUEST);
    }
}
