package com.aerospacedefense;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//tells the class it should listen for web traffic
@RestController
public class AircraftAPI{
    private AircraftHashMap aircraftHashMap;

    public AircraftAPI(AircraftHashMap aircraftHashMap){
        //the @Service tag on AircraftHashMap.java, Spring Boot injects this map
        this.aircraftHashMap = aircraftHashMap;
    }

    // add our endpoints

    //POST request at URL: add-aircraft
    //@RequestBody deserializes the incoming HTTP Request (JSON) to the Aircraft Java object
    @PostMapping("/add-aircraft")
    public void endpointAddAircraft(@RequestBody Aircraft aircraft){
        aircraftHashMap.addAircraft(aircraft);
    }

    // GET Request to recieve a specific Aircraft based on id
    // PathVariable is to extract values from URI of incoming HTTP Request
    @GetMapping("/get-aircraft/{id}")
    public Aircraft endpointGetAircraft(@PathVariable String id){
        //Spring Boot automatically serializes java into JSON
        return aircraftHashMap.findAircraft(id);
    }

    // DeleteMapping is a route to delete
    // DELETE Request to remove a specific Aircraft based on id
    @DeleteMapping("/delete-aircraft/{id}")
    public void endpointRemoveAircraft(@PathVariable String id){
        aircraftHashMap.removeAircraft(id);
    }
}