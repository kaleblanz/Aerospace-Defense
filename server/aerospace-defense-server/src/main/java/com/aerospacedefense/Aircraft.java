package com.aerospacedefense;

import java.io.Serializable;

//implments Serializable to actually put our serialVersionUID into use for serialzation/de-serializatoin
public class Aircraft implements Serializable{
    private int aircraft_id;
    private String radio_callsign;
    private double longitude;
    private double latitude;
    private double altitude;
    private double velocity;
    //our version stamp for which version of Aircraft object is this
    private static final long serialVersionUID = 1L;
    
    //Constructor for our Aircraft Object
    public Aircraft(int aircraft_id , String radio_callsign, double longitude, double latitude, double altitude, double velocity){
        this.aircraft_id = aircraft_id;
        this.radio_callsign = radio_callsign;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.velocity = velocity;
    }

    //Default Constructor for Aircraft Obj
    public Aircraft(){

    }

    public static void main (String[] args){
        Aircraft plane1 = new Aircraft(5,"ds",1.11,1.019,4.34,91.1);
        System.out.println(plane1);

    }

    //Overide toString to be able to print AirCraft objects
    @Override
    public String toString() {
        return "Plain ID: " + String.valueOf(this.aircraft_id) + ", with Radio CallSign: " + this.radio_callsign;
    }

    //Declare our Getters for Aircraft object fields
    public int getAirCraft_ID(){
        return this.aircraft_id;
    }

    public String getRadio_callSign(){
        return this.radio_callsign;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getAltitude(){
        return this.altitude;
    }

    public double getVelocity(){
        return this.velocity;
    }

    //declare our Setters for our Aircraft object fields
    public void setAirCraft_ID(int aircraft_id){
         this.aircraft_id = aircraft_id;
    }

    public void setRadio_callSign(String radio_callsign){
        this.radio_callsign = radio_callsign;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setAltitude(double altitude){
        //Invalid for an altitude to be under 0
        if (altitude >= 0){
            this.altitude = altitude;
        }
    }

    public void setVelocity(double velocity){
        this.velocity = velocity;
    }
    

    


}