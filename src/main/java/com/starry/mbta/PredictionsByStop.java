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
public class PredictionsByStop {
    String stop_id;
    String stop_name;
    ArrayList<Mode> mode;
    ArrayList<Alert> alert_headers;

 

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

    public ArrayList<Mode> getMode() {
        return mode;
    }

    public void setMode(ArrayList<Mode> mode) {
        this.mode = mode;
    }

    public ArrayList<Alert> getAlert_headers() {
        return alert_headers;
    }

    public void setAlert_headers(ArrayList<Alert> alert_headers) {
        this.alert_headers = alert_headers;
    }

    public PredictionsByStop() {
        this.stop_id = null;
        this.stop_name = null;
        this.mode = null;
        this.alert_headers = null;
    }
     public void ClearAll(){
        this.stop_id = null;
        this.stop_name = null;
        this.mode.clear();
        this.alert_headers.clear();
         
     }
    
    
    
    
}
