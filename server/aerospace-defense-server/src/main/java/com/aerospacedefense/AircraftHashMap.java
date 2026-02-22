//AircraftHashMap maintains all created Aircrafts in a key-value map with key being Aircraft ID and value being Aircraft Object

package com.aerospacedefense;


import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class AircraftHashMap{
    // Map that stores all information on Aircraft objects
    private HashMap<String,Aircraft> aircraft_map = new HashMap<>();


    public void addAircraft(Aircraft aircraft){
        aircraft_map.put(aircraft.getAircraft_id(), aircraft);
    }

    public Aircraft findAircraft(String aircraft_id){
        return aircraft_map.get(aircraft_id);
    }

    public void removeAircraft(String aircraft_id){
        aircraft_map.remove(aircraft_id);
    }

    public void removeAllAircrafts(){
        aircraft_map.clear();
    }
}