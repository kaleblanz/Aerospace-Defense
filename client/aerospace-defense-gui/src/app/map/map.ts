import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet'; // import everything from Leaflet as var L
import { AircraftService } from '../aircraft';
import { CommonModule } from '@angular/common';

// @Component tells Angular the following class is a web component
@Component({
  selector: 'app-map', //Custom HTML tag used in app.html
  imports: [CommonModule],  
  templateUrl: './map.html', //links to html skeleton file
  styleUrl: './map.css', //links to CSS file
  standalone: true
})

export class MapComponent implements OnInit{
  //map holds our Map object
  private map!: L.Map;

  //array to hold on plane icons we draw
  private markers: L.Layer[] = [];

  //inject the AircraftService so the map can talk to the AircraftService
  constructor(private aircraftService: AircraftService) {}

  //runs as soon as the componet is loaded
  ngOnInit(): void {
    this.initMap()
    this.loadAircraft();

    setInterval(() => {
      this.loadAircraft();}, 4000); //slightly quicker than how fast python re-runs the script
  }

  private initMap():void{
    // L.map('map') looks for the id=map in map.html to use as container
    // .setView([lat,long], zoom) tells where to look and how close to look
    this.map = L.map('map').setView([43.0, -75.5],7) // Starting Point when we load

    //L.titlelayer defines the images of the world
    // url used is address of OpenStreetMap server that creates image tiles
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 10, //limit of zoom in level
      attribution: '© OpenStreetMap contributors' //add for legal reasons
    }).addTo(this.map) //add this new image to our created map object

    //restricted zones
    const zones = [
      { name: "Buffalo", coords: [[42.83,-78.91],[42.95, -78.76]] },
        { name: "Syracuse", coords: [[43.12,  -76.08], [43.00, -76.20]] },
        { name: "Albany", coords: [[42.78, -73.70],[42.60, -73.88]] },
        { name: "New York City", coords: [[40.92, -73.68], [40.49, -73.91]] },
        { name: "Toronto", coords: [[43.90, -79.20], [43.50, -79.80]] }
    ]

    zones.forEach(zone =>{
      L.rectangle(zone.coords as L.LatLngBoundsExpression,{
        color: 'red',
        weight: 2,
        fillColor: 'red',
        fillOpacity: 0.3
      }).addTo(this.map).bindPopup(`Restricted Zone: ${zone.name}`)
    });



  }


  private loadAircraft(): void{
    //initate the data stream
    this.aircraftService.getAircraftData().subscribe((data: any[]) => {
      //reset the aircrafts shown everytime this function is called
      this.markers.forEach(m => this.map.removeLayer(m));
      this.markers = [];


      data.forEach(plane => {
        var iconColor = 'blue'
        if (plane.isRestricted){
          iconColor = 'red'
        }

        //create a circle marker 
        const marker = L.circleMarker([plane.latitude,plane.longitude], {
          radius: 8,
          fillColor: iconColor,
          color: '#fff',
          weight: 1,
          opacity: 1,
          fillOpacity: 0.8
        }).addTo(this.map);

        if (plane.isRestricted){
          marker.bindPopup(`<div style="color:red; font-weight:bold;"> WARNING: RESTRICTED AIRSPACE<br>Callsign: ${plane.radio_callsign}   ICAO24: ${plane.aircraft_id}`);
        }else{
            marker.bindPopup(`<b>${plane.radio_callsign || 'N/A'}</b><br>Alt: ${plane.altitude}`)
        }

        this.markers.push(marker);

      });
     
    });


    
  }
}
