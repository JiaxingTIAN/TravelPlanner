package com.starry.mbta.UI;

import com.starry.mbta.Routes;
import com.starry.mbta.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.RequestHandler;
import com.vaadin.server.Sizeable;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.mycompany.mbta_travelplanner.MyAppWidgetset")
public class MyUI extends UI {
    
    int searchType = 0;
    //Make a instence for query MBTA 
    RequestMBTA myRequest =null;
    StopsByLocation srcStops = new StopsByLocation();
    StopsByLocation dstStops = new StopsByLocation();
    //Start and destination location
    LatLon start;
    LatLon dest;
    String searchRouteId;
    
    
    double result = -1; //to indicate whether there is a plan
    
    //Object mapper to map the json string to object
    ObjectMapper om = new ObjectMapper();
    //Array list to store the response stops for each route
    ArrayList<StopsByRoute> st = new ArrayList();
    ArrayList<PredictionsByStop> predict = new ArrayList();
    //Class to store the response of retrun all routes
    Routes rt;

    //Google Map get the nearby stops by click in the map
    Panel map = new Panel("TripPlanner---Boston"); 
    HorizontalSplitPanel back = new HorizontalSplitPanel();
    VerticalLayout plan = new VerticalLayout();
    
    HorizontalLayout top = new HorizontalLayout();
    ComboBox selectSearch = new ComboBox();
    ComboBox transTime = new ComboBox();
            
    Label displayPlan = new Label();
    Label estTime = new Label();
    GoogleMap googleMap;
    
    //flage to indicate if the click location is start or destination
    int flag = -1;
    int tran = 1;   //Transistion times 

    
    ArrayList<Stop> destStops = new ArrayList<>();
    ArrayList<Stop> temp = new ArrayList<>();
    ArrayList<Transition> transition = new ArrayList<>();
    
    ArrayList<String> planner = new ArrayList<>();
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        rt=null;
        st.clear();
        predict.clear();
        myRequest = new RequestMBTA();
        flag = 0;

        Graph();
        configureGoogleMap();
        
        selectSearch.addItem("Depth first search");
        selectSearch.addItem("Heuristic search");
        selectSearch.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String searchT = (String) selectSearch.getValue(); 
                if(searchT.equals("Depth first search")){
                    searchType = 0;
                }else{
                    searchType = 1;
                }
            }
        });
        transTime.addItem("0");
        transTime.addItem("1");
        transTime.addItem("2");
        transTime.addItem("3");
        transTime.addListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
               String tranTime = (String) transTime.getValue(); //To change body of generated methods, choose Tools | Templates.
               tran = Integer.parseInt(tranTime);
            }
        });
        
        top.addComponent(selectSearch);
        top.addComponent(transTime);
        
        plan.addComponent(top);
        
        map.setIcon(FontAwesome.CAR);
        map.setContent(googleMap);
        map.setSizeFull();
        
        back.setSplitPosition(20,Sizeable.UNITS_PERCENTAGE);
        back.setLocked(true);
        back.addComponent(plan);
        back.addComponent(map);
 
        setContent(back);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

     
    //Query to MBTA and store all the response into obeject and set the display to table
    public void Graph(){
        String myRoutes = myRequest.getAllRoutes();
        try{
            rt = om.readValue(myRoutes, Routes.class);
            for(int k=0;k<rt.getMode().size();k++){
                for(int n=0;n<rt.getMode().get(k).getRoute().size();n++){
                    
                    String route_id = rt.getMode().get(k).getRoute().get(n).getRoute_id();              
                    String myStops = myRequest.getStopsByRoute(route_id);
                    StopsByRoute stop;
                    try {
                        stop = om.readValue(myStops, StopsByRoute.class);
                        stop.setRoute_id(route_id);
                        stop.setRoute_name(rt.getMode().get(k).getRoute().get(n).getRoute_name());
                        System.out.println("stop:"+ stop.getDirection().get(0).getStop().get(0).getStop_name());
                        
                        
//                        for(int j=0;j<stop.getDirection().size();j++){
//                            
//                            for(int i=0;j<stop.getDirection().get(j).getStop().size();i++){
//                                String stpid = stop.getDirection().get(j).getStop().get(i).getStop_id();
//                                String myPredict = myRequest.getPredictionsByStop(stpid);
//                                PredictionsByStop pre = new PredictionsByStop();
//                                
//                                int away = -1;
//                                int speed = -1;
//                                try{
//                                     pre = om.readValue(myPredict, PredictionsByStop.class);
//                                     
//                                     predict.add(pre);
//                                    
//                                } catch (IOException e) {
//                                    java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE,null,e);
//                                }
//                                if(pre.getMode().size()>0){
//                                 away=Integer.parseInt(pre.getMode().get(0).
//                                         getRoute().get(0).getDirection().get(0).getTrip().get(0).getPre_away());
//                                 speed=Integer.parseInt(pre.getMode().get(0).
//                                         getRoute().get(0).getDirection().get(0).getTrip().get(0).getVehicle().getVehicle_speed());
//                                }       
//                                stop.getDirection().get(j).getStop().get(i).setAway(away);
//                                stop.getDirection().get(j).getStop().get(i).setSpeed(speed);
//                                System.out.println("away" + away);
//                            }
//                        }
                        
                        st.add(stop);
                   
                    } catch (IOException e) {
                        java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE,null,e);
                    }
                }
            }
        }catch (IOException ex) {
            java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void configureGoogleMap(){
        
        //Google map
        googleMap  = new GoogleMap("apiKey",null,"english");
        //Focus on Boston
        googleMap.setCenter(new LatLon(42.360082, -71.058880));
        googleMap.setZoom(13);
        googleMap.setSizeFull();
        googleMap.setMinZoom(10);
        googleMap.setMaxZoom(20);
        googleMap.setSizeFull();
        googleMap.setImmediate(true);
        
        //Set the default location to be Center
        googleMap.addMarker("Pin",new LatLon(42.360082, -71.058880), true, null);
        
        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                
                if(flag == 0){
                    
                    result = 0;
                    //first click to indict start location
                    flag = 1;
                    //Clear all the previous marker
                    googleMap.clearMarkers();               
                    //Add pin to the click point in the map
                    googleMap.addMarker("Start",position, true, null);
                    //Set the pin to be focus as center
                    googleMap.setCenter(position);
                    googleMap.setZoom(17);
                    //add the start location
                    
                    start=position;
                    System.out.println("start location");

                }
                else {
                    flag = 0;
                    googleMap.addMarker("dst", position, true, null);
                    googleMap.setCenter(position);
                    //add the destination location
                    dest = position;
                    System.out.println("end location");
                    //perform search
                    
                    search();
                    
                }

            }
        });
         
     }
    
    int search(){
        
        result = 0;
        transition.clear();
        
        System.out.println("search");
        int end = 0;
        int current = 0;
        //record the time for the planner
        
        int time = -1;
        double distance = -1;
        
        String srcLat = "" + start.getLat();
        String srcLon = "" + start.getLon();
        String dstLat = "" + dest.getLat();
        String dstLon = "" + dest.getLon();
        
        //for the recode of the planner
        
        String minDest="";
        double mindist = 10000;
        
        String mysrcStop = myRequest.getStopsByLocation(srcLat, srcLon);
            
        try{
             srcStops = om.readValue(mysrcStop, StopsByLocation.class);              
        }catch (Exception e) {
             System.err.println("stop mapping error");
             java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, e);
        }
        String initial_lat = srcStops.getStop().get(0).getStop_lat();
        String initial_lon = srcStops.getStop().get(0).getStop_lon();
        //initial the destination stops
        String mydstStop = myRequest.getStopsByLocation(dstLat, dstLon);
        try{     
             dstStops = om.readValue(mydstStop, StopsByLocation.class);
        }catch (Exception e) {
             System.err.println("stop mapping error");
             java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, e);
        }
        for(int y=0;y<dstStops.getStop().size();y++){
            destStops.add(dstStops.getStop().get(y));
        }
        String final_lat = dstStops.getStop().get(0).getStop_lat();
        String final_lon = dstStops.getStop().get(0).getStop_lon();
        
        double final_time = 0 + getDist(srcLon, initial_lon, srcLat, initial_lat)/3 + getDist(dstLon, final_lon, dstLat, final_lat)/3;
  
        //Begin to search
        while(end == 0) {
            
            String myStop = myRequest.getStopsByLocation(srcLat, srcLon);
            
            try{
                 srcStops = om.readValue(myStop, StopsByLocation.class);              
            }catch (Exception e) {
                 System.err.println("stop mapping error");
                 java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, e);
            }
            
            Transition t0 = new Transition();
            t0.setCurrent(-1);
            //Initial the search stop to be all the stops near start location
            for(int x=0;x<srcStops.getStop().size();x++){         
                System.out.println("Searching"+srcStops.getStop().get(x));
                t0.getTransStops().add(srcStops.getStop().get(x));                 
            }
            transition.add(t0);

            int z=transition.size()-1;   
            //get all the nearby stops
            //search through all the start stops and destination stops
            for(int k=0; k<transition.get(z).getTransStops().size();k++){
                for(int j=0;j<destStops.size();j++){

                    String destStopId = destStops.get(j).getStop_id();
                    String srcStopId = transition.get(z).getTransStops().get(k).getStop_id();
                    
                    distance = searchRoute(srcStopId, destStopId, current);
                    if(distance>=0){
                        planner.add(minDest);
                        planner.add("walk to destination");
                        System.out.println("Plan:"+planner);
                    }
                    //Found the solution
                    if(distance < mindist && distance >= 0){
                        mindist = distance; //if mindist = 0, do not need to take the train
                        minDest = dstStops.getStop().get(j).getStop_name();
                        //add the transition stop and destination to the planner 
                        end = 2;
                        
                        
                    }
                    if(end == 2){
                        break;
                    }
                }
                if(end == 2){
                    break;
                }
            }
            current++;
            if(current>tran){
                current = tran;
            }
            
            //If found in this transition search
            if(end == 2){
                //output the planner result and complete time
                
                
                System.out.println(planner);
                result = final_time + mindist/20;
                System.out.println("Found"+ mindist);
                
                String disPlanner ="";
                for(int m=0;m<planner.size();m++){
                    disPlanner = disPlanner +" "+ planner.get(m);
                }
                Label display = new Label(disPlanner);
                Label disTime = new Label("Estimat time:"+ result+" hour" );
                plan.addComponent(display);
                plan.addComponent(disTime);
                
                end = 1;
                break;
            } 
            if(searchType == 0 && end == 0){
                //set up for next transition search
                for(int c=0;c<transition.size();c++){
                    if(transition.get(c).getCurrent()==current && transition.get(c).getFrontier()==1){
                        if(transition.get(c).getTransStops().isEmpty()){
                            current--;
                            c=0;
                            if(current==0){
                                end = 1;
                                break;
                            }

                        }else{
                            srcLat = transition.get(c).getTransStops().get(0).getStop_lat();
                            srcLon = transition.get(c).getTransStops().get(0).getStop_lon();
                            planner.add(transition.get(c).getTransStops().get(0).getStop_name());
                            transition.get(c).getTransStops().remove(0); 
                            break;
                        }
                    }
                }
            }else if(searchType == 1 && end == 0){
                int minc = -1;                       
                double mind = 10000;
                int min_index = -1;
                //set up for next transition search
                for(int c=0;c<transition.size();c++){

                    if(transition.get(c).getFrontier() == 1 ){
                        if(transition.get(c).getTransStops().isEmpty()){
                            continue;

                        }else{
                            //calculate the distance of each frontier
                            for(int a=0;a<transition.get(c).getTransStops().size();a++){
                                String lon1 = transition.get(c).getTransStops().get(a).getStop_lon();
                                String lat1 = transition.get(c).getTransStops().get(a).getStop_lat();
                                double d = getDist(lon1, dstLon, lat1, dstLat);
                                if(d<mind){
                                    mind = d;
                                    min_index = a;
                                    minc = c;
                                }
                            }                                        
                        }
                    }    
                }
                //frontier is not empty
                if(min_index>0){
                    //next search is the node with minimun distance to the destination
                    srcLat = transition.get(minc).getTransStops().get(min_index).getStop_lat();
                    srcLon = transition.get(minc).getTransStops().get(min_index).getStop_lon();
                    current =transition.get(minc).getCurrent();
                    transition.get(minc).getTransStops().remove(min_index);
                }else{
                    end = 1;    //frontier is empty exite
                }
            }
            
            if(end == 1){
                break;
            }
        }
        
        return time;
    }
   
    
    
    //Search if the route contain two stops output 1 if yes 
    double searchRoute(String srcStopid,String dstStopid, int cur){
        
        String parent = "";
        String routeName = "";
        temp.clear();
        //initialize the result
        double dist = -1;
        int srcidx = 0;
        int dstidx = 0;
        cur++;
        
        RouteByStop srcRoute = new RouteByStop();
        //return the route by stop id
        String searchRoute = myRequest.getRouteByStop(srcStopid);
        try{
            srcRoute = om.readValue(searchRoute, RouteByStop.class);
        }catch (Exception e){
            System.err.println("route mapping error");
            java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, e);
        }

        for(int j=0;j<srcRoute.getMode().get(0).getRoute().size();j++){

            searchRouteId = srcRoute.getMode().get(0).getRoute().get(j).getRoute_id();
            for(StopsByRoute st1:st){
                dstidx = 0; srcidx = 0; dist = -1;
                //find the route in the graph
                if(searchRouteId.equals(st1.getRoute_id())){
                    //found the route
                    //see if the dst stop in the same route
                    for(int i=0;i<st1.getDirection().get(0).getStop().size();i++){
                        //store the route for transition
                        temp.add(st1.getDirection().get(0).getStop().get(i));
                        //get the index of source stop
                        if(srcStopid.equals(st1.getDirection().get(0).getStop().get(i).getStop_id())){
                            srcidx = i;
                            parent = st1.getDirection().get(0).getStop().get(i).getStop_name();
                            routeName = st1.getRoute_name();
                            temp.remove(i);
                        }
                        //get the index of destination stop if exist 
                        if(dstStopid.equals(st1.getDirection().get(0).getStop().get(i).getStop_id())){
                            dstidx = i;
                            dist = 0;
                            
                        }
                    }
                }
                //Found the result calculate the distance
                if(dist == 0){
                    int s = srcidx;
                    int t = dstidx;
                    if(srcidx > dstidx){
                        s = dstidx;
                        t = srcidx;
                    }
                    for(int n=s; n<t-1; n++){
                        String srcLat = st1.getDirection().get(0).getStop().get(n).getStop_lat();
                        String srcLon = st1.getDirection().get(0).getStop().get(n).getStop_lon();
                        String dstLat = st1.getDirection().get(0).getStop().get(n+1).getStop_lat();
                        String dstLon = st1.getDirection().get(0).getStop().get(n+1).getStop_lon();
                        dist = dist + getDist(srcLon,dstLon,srcLat,dstLat);
                   
                    }
                    break;
            
                }
            }
            if(dist>0){
                break;
            }
        }
        
        if(cur<=tran){
            //store all the stops as frontier for the next transitioin 
            Transition t = new Transition();
            for(int n=0;n<transition.size();n++){
                if(transition.get(n).getCurrent()==(cur-1)){
                    t.getParentStop().addAll(transition.get(n).getParentStop());
                    break;
                }
            }
            t.getParentStop().add(parent);
            t.getParentStop().add("via");
            t.getParentStop().add(routeName);
            t.setCurrent(cur);
            t.setFrontier(1);
            t.setTransStops(temp);
            transition.add(t);
            
            planner.clear();
            planner.add("walk to stop");
            planner.addAll(t.getParentStop());
        }
        
        
        System.out.println("return distance:"+ dist);
        return dist;
    }
    

    //Calculate the distance between to location in miles
    double getDist(String myLon1, String myLon2, String myLat1, String myLat2){
        double d = -1,c = -1, a = -1;
        double dlon = -1;
        double dlat = -1;
        double lon1 = Math.toRadians(Double.parseDouble(myLon1));
        double lon2 = Math.toRadians(Double.parseDouble(myLon2));
        double lat1 = Math.toRadians(Double.parseDouble(myLat1));
        double lat2 = Math.toRadians(Double.parseDouble(myLat2));
        double r = 3959;    //radius of earth in mile
        
        dlon = lon2 - lon1;
        dlat = lat2 - lat1; 
        a = (sin(dlat/2))*(sin(dlat/2)) + cos(lat1) * cos(lat2) * 
                (sin(dlon/2))*(sin(dlon/2)); 
        c = 2 * atan2( sqrt(a), sqrt(1-a) ); 
        d = r * c;
        return d;
    }
    
}
