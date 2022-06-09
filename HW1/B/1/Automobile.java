/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author christina
 */
public class Automobile {
    private int vehicleID;
    private float miles_driven;
    private int month;
    private int day;
    private int year;
    private String last_maintenance;
    
    public Automobile(int _vehicleID, float _miles_driven, int _month, int _day, int _year) {
        vehicleID = _vehicleID;
        miles_driven = _miles_driven;
        month = _month;
        day = _day;
        year = _year; 
        last_maintenance = ""+month+"/"+day+"/"+year;
    }
    
    public int getVehicleID( ) {
      return vehicleID;
   }
    
    public float getMiles_driven() {
        return miles_driven;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getDay() {
        return day;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getLast_maintenance() {
        return last_maintenance;
    }    
    
    public String toString() {
        String vehicle_info = "Vehicle ID: "+vehicleID+"\n";
        vehicle_info += "Miles Driven: "+miles_driven+"\n";
        vehicle_info += "Date of Last Maintenance: "+last_maintenance+"\n";
        return vehicle_info;

    }
}
