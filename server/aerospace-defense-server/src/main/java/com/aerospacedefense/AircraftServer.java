//Launch the Spring Boot framework, starts a builtin web server and creates memory to hold AircraftHashMap
package com.aerospacedefense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//tells the computer to turn on web server, scan my other files for @Service and manage memory automatically
@SpringBootApplication
public class AircraftServer{

    public static void main(String[] args) {
        //tells Spring Boot to use AircraftServer as a blueprint
        // creates digital container that holds AircraftHashMap
        // scans for @Services and @RestController
        // launches Tomcat server
        // exposes api
        SpringApplication.run(AircraftServer.class, args);
    }
}