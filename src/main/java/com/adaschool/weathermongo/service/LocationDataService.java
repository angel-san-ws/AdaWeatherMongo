package com.adaschool.weathermongo.service;

import com.adaschool.weathermongo.repository.Location;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class LocationDataService {
    private MongoOperations mongoOperations;

    public LocationDataService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Location findById(String locationId){
        return mongoOperations.findById(locationId,Location.class);
    }
}
