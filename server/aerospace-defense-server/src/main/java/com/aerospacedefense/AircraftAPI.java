package com.aerospacedefense;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

record WarningZones(String name, double maxLat, double maxLong, double minLat, double minLong) {}

//tells the class it should listen for web traffic
@RestController
public class AircraftAPI{
    private AircraftHashMap aircraftHashMap;
    private final WarningZones Buffalo = new WarningZones("Buffalo",42.95,-78.76,42.83,-78.91);
    private final WarningZones Syracuse = new WarningZones("Syracuse",43.12,-76.08,43.00,-76.20);
    private final WarningZones Albany = new WarningZones("Albany",42.78,-73.70,42.60,-73.88);
    private final WarningZones NYC = new WarningZones("New York City",40.92,-73.68,40.49,-73.91);

    private boolean isAircraftRestrictedZone(Aircraft aircraft, WarningZones warningZone){
        // returns true if Aircraft is inside the Restricted Zone
        return  ( (aircraft.getLongitude() >= warningZone.minLong() && aircraft.getLongitude() <= warningZone.maxLong()) 
        && (aircraft.getLatitude() >= warningZone.minLat() && aircraft.getLatitude() <= warningZone.maxLat()) );
    }

    public AircraftAPI(AircraftHashMap aircraftHashMap){
        //the @Service tag on AircraftHashMap.java, Spring Boot injects this map
        this.aircraftHashMap = aircraftHashMap;
    }

    // add our endpoints

    //POST request at URL: add-aircraft
    //@RequestBody deserializes the incoming HTTP Request (JSON) to the Aircraft Java object
    @PostMapping("/add-aircrafts")
    public void endpointAddAircraft(@RequestBody List<Aircraft> aircrafts){
        //Reset the Aircraft data
        aircraftHashMap.removeAllAircrafts();
        System.out.println("First Aircrafts: " + aircrafts.get(0).getAircraft_id());

        for (Aircraft aircraft : aircrafts){
            boolean restricted = isAircraftRestrictedZone(aircraft, Buffalo) || 
            isAircraftRestrictedZone(aircraft, Syracuse) || isAircraftRestrictedZone(aircraft, Albany) || isAircraftRestrictedZone(aircraft, NYC);

            aircraft.setIsRestricted(restricted);

            if (restricted){
                System.out.println(aircraft.toString());
            }

            aircraftHashMap.addAircraft(aircraft);
        }

        System.out.println("Length of list of aircrafts: " + String.valueOf(aircrafts.size()));

        
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