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
public class Vehicle {
    
    String vehicle_id;
    String vehicle_lat;
    String vehicle_lon;
    String vehicle_bearing;
    String vehicle_speed;
    String vehicle_timestamp;

    public String getVehicle_speed() {
        return vehicle_speed;
    }

    public void setVehicle_speed(String vehicle_speed) {
        this.vehicle_speed = vehicle_speed;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_lat() {
        return vehicle_lat;
    }

    public void setVehicle_lat(String vehicle_lat) {
        this.vehicle_lat = vehicle_lat;
    }

    public String getVehicle_lon() {
        return vehicle_lon;
    }

    public void setVehicle_lon(String vehicle_lon) {
        this.vehicle_lon = vehicle_lon;
    }

    public String getVehicle_bearing() {
        return vehicle_bearing;
    }

    public void setVehicle_bearing(String vehicle_bearing) {
        this.vehicle_bearing = vehicle_bearing;
    }


    public String getVehicle_timestamp() {
        return vehicle_timestamp;
    }

    public void setVehicle_timestamp(String vehicle_timestamp) {
        this.vehicle_timestamp = vehicle_timestamp;
    }
    
    
}
