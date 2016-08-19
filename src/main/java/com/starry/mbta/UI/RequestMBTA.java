/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starry.mbta.UI;

import java.io.IOException;
import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Starry
 * the Class is used for make connection with MBTA api
 */
public class RequestMBTA {
    
    String key ="HGTqBvgz5UCqLp5ts4SX9w";
    //Ask MBTA to return all the routes
    public String getAllRoutes(){
         
         String requestURL = "http://realtime.mbta.com/developer/api/v2/routes?"
                 + "api_key="+key+"&format=json";
         CloseableHttpClient myClient = HttpClients.createDefault();
         HttpGet myGetRequest = new HttpGet(requestURL);
         String response = "";
         try{
            CloseableHttpResponse myResponse = myClient.execute(myGetRequest);
            try{
                HttpEntity myEntity = myResponse.getEntity();
                response = EntityUtils.toString(myEntity);
            
            }finally{
                myResponse.close();
            }
        }catch(IOException ex){
           java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;         
     }
    //query MBTA for all the stops that serve the specific route
     public String getStopsByRoute(String route_id){
        
         String requestURL = "http://realtime.mbta.com/developer/api/v2/stopsbyroute?"
                 + "api_key="+key+"&route="+route_id+"&format=json";
         CloseableHttpClient myClient = HttpClients.createDefault();
         HttpGet myGetRequest = new HttpGet(requestURL);
         String response = "";
         try{
            CloseableHttpResponse myResponse = myClient.execute(myGetRequest);
            try{
                HttpEntity myEntity = myResponse.getEntity();
                response = EntityUtils.toString(myEntity);
            
            }finally{
                myResponse.close();
            }
        }catch(IOException ex){
           java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return response;         
     }
     //Get the prediction for one stop
     public String getPredictionsByStop(String stopid){
         
         String requestURL = "http://realtime.mbta.com/developer/api/v2/predictionsbystop?"
                 + "api_key="+key+"&stop="+stopid+"&format=json";
         CloseableHttpClient myClient = HttpClients.createDefault();
         HttpGet myGetRequest = new HttpGet(requestURL);
         String response = "";
         try{
            CloseableHttpResponse myResponse = myClient.execute(myGetRequest);
            try{
                HttpEntity myEntity = myResponse.getEntity();
                response = EntityUtils.toString(myEntity);
            
            }finally{
                myResponse.close();
            }
        }catch(IOException ex){
           java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.err.println("You entered " + response + 1); 
        return response;         
     }
     
     //Get the stops near a location
     public String getStopsByLocation(String lat, String lon ){
        //URI field to for realtime MBTA connection
        
        String requestURL = "http://realtime.mbta.com/developer/api/v2/stopsbylocation?"
                + "api_key="+key+"&lat="+lat+"&lon="+lon+"&format=json";
        CloseableHttpClient myClient = HttpClients.createDefault();
        HttpGet myGetRequest = new HttpGet(requestURL);  
        
        String response = "";
  
        try{
            CloseableHttpResponse myResponse = myClient.execute(myGetRequest);
            try{
                HttpEntity myEntity = myResponse.getEntity();
                response = EntityUtils.toString(myEntity);
                
            }finally{
                myResponse.close();
            }
        }catch(IOException ex){
           java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.err.println("You entered " + response + 1); 
        return response;
        
    }
     
     public String getRouteByStop(String stopid ){
        //URI field to for realtime MBTA connection

        String requestURL = "http://realtime.mbta.com/developer/api/v2/routesbystop?"
                + "api_key="+key+"&stop="+stopid+"&format=json";
        CloseableHttpClient myClient = HttpClients.createDefault();
        HttpGet myGetRequest = new HttpGet(requestURL);  
        
        String response = "";
  
        try{
            CloseableHttpResponse myResponse = myClient.execute(myGetRequest);
            try{
                HttpEntity myEntity = myResponse.getEntity();
                response = EntityUtils.toString(myEntity);
                
            }finally{
                myResponse.close();
            }
        }catch(IOException ex){
           java.util.logging.Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.err.println("You entered " + response + 1); 
        return response;
        
    }
    
}
