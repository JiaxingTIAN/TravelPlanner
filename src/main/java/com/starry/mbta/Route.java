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
public class Route {
    String route_id;
    String route_name;
    String route_hide;
    
    ArrayList<Direction> direction;

    public ArrayList<Direction> getDirection() {
        return direction;
    }

    public void setDirection(ArrayList<Direction> direction) {
        this.direction = direction;
    }

    public String getRoute_hide() {
        return route_hide;
    }

    public void setRoute_hide(String route_hide) {
        this.route_hide = route_hide;
    }
    
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

    
}
