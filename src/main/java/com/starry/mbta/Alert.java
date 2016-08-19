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
public class Alert {
    String alert_id;
    String header_text;
    String effect_name;

    public String getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(String alert_id) {
        this.alert_id = alert_id;
    }

    public String getHeader_text() {
        return header_text;
    }

    public void setHeader_text(String header_text) {
        this.header_text = header_text;
    }

    public String getEffect_name() {
        return effect_name;
    }

    public void setEffect_name(String effect_name) {
        this.effect_name = effect_name;
    }
    
    
}
