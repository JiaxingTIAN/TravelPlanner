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
public class Trip {
    String trip_id;
    String trip_name;
    String trip_headsign;
    String sch_arr_dt;
    String sch_dep_dt;
    String pre_dt;
    String pre_away;
    
    Vehicle vehicle;

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public String getSch_arr_dt() {
        return sch_arr_dt;
    }

    public void setSch_arr_dt(String sch_arr_dt) {
        this.sch_arr_dt = sch_arr_dt;
    }

    public String getSch_dep_dt() {
        return sch_dep_dt;
    }

    public void setSch_dep_dt(String sch_dep_dt) {
        this.sch_dep_dt = sch_dep_dt;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPre_dt() {
        return pre_dt;
    }

    public void setPre_dt(String pre_dt) {
        this.pre_dt = pre_dt;
    }

    public String getPre_away() {
        return pre_away;
    }

    public void setPre_away(String pre_away) {
        this.pre_away = pre_away;
    }
    
    
    
}
