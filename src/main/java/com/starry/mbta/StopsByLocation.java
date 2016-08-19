/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta;

import java.util.ArrayList;


/**
 *
 * @author Starry
 */
public class StopsByLocation {
    //http://realtime.mbta.com/developer/api/v2/stopsbylocation?api_key=wX9NwuHnZU2ToO7GmGR9uw&lat=42.352913&lon=-71.064648&format=json 
    
    //Root object of the response
    String lat;
    String lon;
    ArrayList<Stop> stop;
    //Property of the stop list
    
    
    //Connect to MBTA API and return response in string

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public ArrayList<Stop> getStop() {
        return stop;
    }

    public void setStop(ArrayList<Stop> stop) {
        this.stop = stop;
    }

    public StopsByLocation() {
        this.lat = null;
        this.lon = null;
        this.stop = null;
    }
    
    public void ClearALL(){
        this.lat = null;
        this.lon = null;
        this.stop.clear();
    }
    
   
}
