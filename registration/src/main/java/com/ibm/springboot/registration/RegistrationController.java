package com.ibm.springboot.registration;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

//import ch.qos.logback.core.model.Model;

//@Service
//@RestController
@Controller
public class RegistrationController {
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String registerUser() {	
		return "register";
		
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	public String validateUser(@RequestParam String username, @RequestParam String password, @RequestParam String ipaddr, ModelMap model) {	
		
		try {
			String passValidation = validatePasswordCriteria(password);
			System.out.println(passValidation);
			if (!passValidation.isEmpty()) {
	/*			
	            model.addAttribute("passwd_message", "Password is valid.");          
	        } else {
	*/        
	            model.addAttribute("passwd_message", passValidation);
	            return "register";
	        }
			
			String ipValidation = validateIPGeoLocation(username, ipaddr);
			
	/*		if (ipValidation.isEmpty()) {
	            model.addAttribute("ip_message", "IP is in Canada.");          
	        } else {
	            model.addAttribute("nonCA_IP_message", ipValidation);
	            return "reg";
	        }
	*/        
			model.addAttribute("ip_message", ipValidation);    
			return "register";	
		} catch (Exception e) {
			
			model.addAttribute("message", "Something Went Wrong");
		}
		
		return "register";	
	
	}
	
    protected String validatePasswordCriteria(String password) {
        if (password.length() <= 8) {
            return "Password must be at greater than 8 characters long.";
        }
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least 1 number.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least 1 capitalized letter.";
        }
        if (!password.matches(".*[_#$%\\.].*")) {
            return "Password must contain at least 1 special character from the set: _ # $ % .";
        }
        return "";
    }
    
    protected String validateIPGeoLocation(String usrName, String ipAddr) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String apiUrl = "http://ip-api.com/json/" + ipAddr;
    	GeoLocationResponse resp = restTemplate.getForObject(apiUrl, GeoLocationResponse.class);
    	
    	if (resp != null) {
    		System.out.println("Response Status : " + resp.getStatus());
    	}
    	
    	if (resp.getStatus().equalsIgnoreCase("success") && resp.getCountryCode().equalsIgnoreCase("CA")) {
    		
    		//Generate a Random uuid between 1 and 100
    		Random random = new Random();
    		int uuid = random.nextInt(1, 100);
    		String welcomeMsg = "Your uuid : " + uuid + "\nWelcome " + usrName + " from " + resp.getCity();
    		
    		System.out.println(welcomeMsg);
    		return welcomeMsg;
    		
    	}
    	else {
    		return "IP is not in Canada.";
    	}

    }

}
