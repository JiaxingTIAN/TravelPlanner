/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta.UI;

import java.util.ArrayList;
import com.starry.mbta.Stop;
/**
 *
 * @author Starry
 */
public class Transition {
    //when current > 0 means it is the level of transition times
    //when current = -1 means it is searched
    //when current = -3 means it is to be sreached next
    int current = 0;
    int frontier = 0;   //indict if the array list is a frontier
    //The paret stop is an arraylist of transition stop for display the planner
    //it will copy all the parent stop from its parent transition stop
    //when the destination is found, the array list will be a complete path
    ArrayList<String> parentStop = new ArrayList();
    ArrayList<Stop> transStops = new ArrayList();

    public ArrayList<String> getParentStop() {
        return parentStop;
    }

    public void setParentStop(ArrayList<String> parentStop) {
        this.parentStop = parentStop;
    }

    public int getFrontier() {
        return frontier;
    }

    public void setFrontier(int frontier) {
        this.frontier = frontier;
    }
    

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public ArrayList<Stop> getTransStops() {
        return transStops;
    }

    public void setTransStops(ArrayList<Stop> transStops) {
        this.transStops = transStops;
    }
    

    
    
}
