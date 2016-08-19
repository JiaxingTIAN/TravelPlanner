/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta;

/**
 *
 * @author Starry
 */
public class Stop {
    String stop_order;
    String stop_id;
    String stop_name;
    String parent_station;
    String parent_station_name;
    String stop_lat;
    String stop_lon;
    String distance;



    
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setParent_station(String parent_station) {
        this.parent_station = parent_station;
    }

   
    public String getParent_station() {
        return parent_station;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getParent_station_name() {
        return parent_station_name;
    }

    public void setParent_station_name(String parent_station_name) {
        this.parent_station_name = parent_station_name;
    }

    public String getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(String stop_lat) {
        this.stop_lat = stop_lat;
    }

    public String getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(String stop_lon) {
        this.stop_lon = stop_lon;
    }

    public String getStop_order() {
        return stop_order;
    }

    public void setStop_order(String stop_order) {
        this.stop_order = stop_order;
    }
    
    
    
}
