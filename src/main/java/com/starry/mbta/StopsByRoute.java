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
//Store the response captured from the MBTA query
public class StopsByRoute {
    String route_id;
    String route_name;
    ArrayList<Direction> direction;

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public ArrayList<Direction> getDirection() {
        return direction;
    }

    public void setDirection(ArrayList<Direction> direction) {
        this.direction = direction;
    }

    
}
