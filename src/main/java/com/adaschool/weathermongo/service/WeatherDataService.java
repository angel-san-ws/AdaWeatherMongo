package com.adaschool.weathermongo.service;

import com.adaschool.weathermongo.repository.Location;
import com.adaschool.weathermongo.repository.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoOperations;

@Service
public class WeatherDataService {
    private MongoOperations mongoOperations;
    @Autowired
    private LocationDataService locationDataService;

    public WeatherDataService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Weather getWeather(String locationId){
        Query query = new Query(Criteria.where("location.id").is(locationId));
        Weather weather = (Weather) mongoOperations.findOne(query,Weather.class);
        System.out.println(weather);
        return weather;
    }

    public boolean insertWeather(String locationId, float temp, float pressure, float humidity){
        if(!locationId.isEmpty()){
            Location location = locationDataService.findById(locationId);
            System.out.println(location);
            if(location!=null){
                Weather weather=new Weather(location, temp, pressure, humidity);
                this.mongoOperations.insert(weather);
                return true;
            }
        }
        return false;
    }

}
