//AircraftHashMap maintains all created Aircrafts in a key-value map with key being Aircraft ID and value being Aircraft Object

package com.aerospacedefense;


import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class AircraftHashMap{
    // Map that stores all information on Aircraft objects
    private HashMap<Integer,Aircraft> aircraft_map = new HashMap<>();


    public void addAircraft(Aircraft aircraft){
        aircraft_map.put(aircraft.getAirCraft_ID(), aircraft);
    }

    public Aircraft findAircraft(int aircraft_id){
        return aircraft_map.get(aircraft_id);
    }

    public void removeAircraft(int aircraft_id){
        aircraft_map.remove(aircraft_id);
    }
}