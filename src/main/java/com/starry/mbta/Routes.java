/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta;

import com.starry.mbta.Mode;
import java.util.ArrayList;
/**
 *
 * @author Starry
 */

//store the routes response from the MBTA 
public class Routes {
    ArrayList<Mode> mode;

    public ArrayList<Mode> getMode() {
        return mode;
    }

    public void setMode(ArrayList<Mode> mode) {
        this.mode = mode;
    }
    
   
}
