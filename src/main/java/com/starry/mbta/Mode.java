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
public class Mode {
    
    String route_type;
    String mode_name;
    ArrayList<Route> route;
    
    public ArrayList<Route> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }
    
}
