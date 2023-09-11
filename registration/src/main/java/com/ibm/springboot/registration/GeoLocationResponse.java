package com.ibm.springboot.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocationResponse {
	
    @JsonProperty("countryCode")
    private String countryCode;
    
    @JsonProperty("status")
    private String status;    
 
    @JsonProperty("city")
    private String city;        
    
    public String getCountryCode() {
    	return countryCode;
    }

    public String getStatus() {
    	return status;
    }
    
    public String getCity() {
    	return city;
    }    
    // Other fields as needed
    // Getters and setters
}
