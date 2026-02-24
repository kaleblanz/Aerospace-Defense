# Aerospace-Defense
**Real-Time Restricted Zone Aircraft Tracker**
This application monitors live Aircraft data by creating a script that re-reads from the OpenSky API, processing the restriced logic in the SpringBoot backend, and visualies the map, Restricted Zones and Aircrafts using Angular and Leaflet.

**Image of GUI:**

<img width="665" height="580" alt="aerospace-defense-pic" src="https://github.com/user-attachments/assets/22747a57-f872-4400-91ec-d4908ea406b4" />



# System Overview:

**Data Injestion:**
Runs a script that reads from the OpenSky API every 6 seconds with Aircrafts that are in New York State and surrond New York State. This sends a POST Request to our SpringBoot backend every time the script receives new data from the OpenSky API.

**Backend:**
Created a SpringBoot API that stores the data given from the Python script in Aircraft objects, performs logic to see if Aircrafts are in a Restriced Zone and also serializes the data being sent to the front end.

**Frontend:**
Using Angular and Leaflet, I create an interactive map that fetches the Aircraft data from our SpringBoot backend every 4 seconds and plots the new data on the map without refreshing. Creates a Red Box over the restricted cites and will turn the Aircraft node to be red instead of blue if Aircraft is in a restricted zone.
