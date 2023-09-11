package com.ibm.springboot.registration;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.springframework.web.client.RestTemplate;

class RegistrationControllerTest {
	
    private RegistrationController registrationController;
 
    @BeforeEach
    public void setup() {
        registrationController = new RegistrationController();
    }

    @Test
    public void testValidatePasswordCriteria() {
        String validPassword = "Password1$";
        //String invalidPassword = "invalidp";

        assertEquals("", registrationController.validatePasswordCriteria(validPassword));
        assertEquals("Password must be at least 8 characters long.", registrationController.validatePasswordCriteria("less8"));
        assertEquals("Password must contain at least 1 number.", registrationController.validatePasswordCriteria("noNumbers$"));
        assertEquals("Password must contain at least 1 capitalized letter.", registrationController.validatePasswordCriteria("nocapital2$"));
        assertEquals("Password must contain at least 1 special character from the set: _ # $ % .", registrationController.validatePasswordCriteria("noSecial9"));
    }
    
    @Test
    public void testValidateIPGeoLocation() {
        String validCanadianIP = "134.41.12.28";
        String invalidIP = "1.2.3.4";

        assertEquals("IP is not in Canada.", registrationController.validateIPGeoLocation("User", invalidIP));
        assertEquals(true, registrationController.validateIPGeoLocation("User", validCanadianIP).toLowerCase().contains("welcome"));
    } 
}
