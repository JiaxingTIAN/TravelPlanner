/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta;

import com.starry.mbta.Stop;
import java.util.ArrayList;

/**
 *
 * @author Starry
 */
public class Direction {
    String direction_id;
    String direction_name;
    ArrayList<Stop> stop;
    ArrayList<Trip> trip;

    public ArrayList<Trip> getTrip() {
        return trip;
    }

    public void setTrip(ArrayList<Trip> trip) {
        this.trip = trip;
    }

    public ArrayList<Stop> getStop() {
        return stop;
    }

    public void setStop(ArrayList<Stop> stop) {
        this.stop = stop;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    public String getDirection_name() {
        return direction_name;
    }

    public void setDirection_name(String direction_name) {
        this.direction_name = direction_name;
    }

    
    
}
