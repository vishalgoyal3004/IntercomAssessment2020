package practise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CustomerReadJson {
	static Map<Long, String> map = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		long userid;
		String name;
		double latitude;
		double longitude;
		double distance;
		
		try {
				FileReader reader = new FileReader("/Users/vishalgoyal/Customer.json");
				Object obj = parser.parse(reader);
				JSONArray customerList = (JSONArray)obj;
				for(int i=0;i<customerList.size();i++)
					{
						JSONObject xyz = new JSONObject((Map) customerList.get(i));
						latitude = Double.parseDouble((String) xyz.get("latitude"));
						longitude = Double.parseDouble((String) xyz.get("longitude"));
						userid = (long) xyz.get("user_id");
						name = xyz.get("name").toString();
						distance = distance(latitude,longitude);
						
						if(distance<=100)
						{
							//System.out.println("userid:-"+userid+"-----"+"Name:-"+name+"----"+"Distance:-"+distance);
							map.put(userid, name);
						}	
					}
				sortbykey(); 	
		
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static double distance(double lat1, double lon1) { 

			// The math module contains a function 
			// named toRadians which converts from 
			// degrees to radians. 
			double lon2 = -6.257664;
			double lat2 = 53.339428;
			lon1 = Math.toRadians(lon1); 
			lon2 = Math.toRadians(lon2); 
			lat1 = Math.toRadians(lat1); 
			lat2 = Math.toRadians(lat2); 
			
			// Haversine formula  
			double dlon = lon2 - lon1;  
			double dlat = lat2 - lat1; 
			double a = Math.pow(Math.sin(dlat / 2), 2) 
			        + Math.cos(lat1) * Math.cos(lat2) 
			        * Math.pow(Math.sin(dlon / 2),2); 
			     
			double c = 2 * Math.asin(Math.sqrt(a)); 
			
			// Radius of earth in kilometers. Use 3956  
			// for miles 
			double r = 6371; 
			
			// calculate the result
			return(c * r); 
		}  
	
	public static void sortbykey() 
    { 
        // TreeMap to store values of HashMap 
        TreeMap<Long, String> sorted = new TreeMap<>(); 
  
        // Copy all data from hashMap into TreeMap 
        sorted.putAll(map); 
  
        // Display the TreeMap which is naturally sorted 
        for (Entry<Long, String> entry : sorted.entrySet())  
            System.out.println("User_id = " + entry.getKey() +  
                         ", Name = " + entry.getValue());         
    } 
	

}
