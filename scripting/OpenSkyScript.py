import requests
import time


def callAPI():
    URL = "https://opensky-network.org/api/states/all?lamin=40.4944&lomin=-79.765&lamax=45.0117&lomax=-71.7903"
    request = requests.get(URL)
    status_code = request.status_code
    print(request.status_code)
   
    request = request.json()
    time_range = request['time']
    states = request['states']
    print("time: ", time)
    print(len(states))

    all_plane_info = []

    for entry in states:
        if entry[5] == None or entry[6] == None:
            continue

        aircraft = {}
        aircraft['aircraft_id'] = entry[0]

        if entry[1] == None:
            aircraft['radio_callsign'] = ""
        else:
            aircraft['radio_callsign'] = entry[1].strip()
        
        aircraft['longitude'] = entry[5]

        aircraft['latitude'] = entry[6]

        if entry[7] == None:
            aircraft['altitude'] = -1
        else:
            aircraft['altitude'] = entry[7]
        
        if entry[9] == None:
            aircraft['velocity'] = -1
        else:
            aircraft['velocity'] = entry[9]
        
        all_plane_info.append(aircraft)

    
    requests.post("http://localhost:8080/add-aircrafts", json=all_plane_info)
    


def main():
    #infinite look that sleeps for 10 seconds that recalls OpenSky API
    while True:
        callAPI()
        time.sleep(10) 


main()