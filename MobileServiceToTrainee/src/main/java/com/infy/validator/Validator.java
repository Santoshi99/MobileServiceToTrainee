package com.infy.validator;

import java.util.List;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {

	public void validate(ServiceRequest service) throws MobileServiceException {	
		//your code goes here
		if(!isValidBrand(service.getBrand())) {
			throw new MobileServiceException("Sorry! we do not provide service for this brand");
		}
		else if(!isValidIssues(service.getIssues())) {
			throw new MobileServiceException("Please provide the device only if there are issues.");
		}
		else if(!isValidIMEINumber(service.getiMEINumber())) {
			throw new MobileServiceException("Sorry! we’re not able to detect the IMEI number for this device");
		}
		else if(!isValidContactNumber(service.getContactNumber())) {
			throw new MobileServiceException("Please provide a valid contact number");
		}
		else if(!isValidCustomerName(service.getCustomerName()))	{
			throw new MobileServiceException("Please provide a valid customer name");
		}
		
	}	

	
	// validates the brand
	// brand should always start with a upper case alphabet 
	// and can be followed by one or more alphabets (lower case or upper case) 
	public Boolean isValidBrand(String brand){
		String regex = "([A-Z]+[a-z]+)+";
		if(brand.matches(regex))
			return true;
		else
			return false;
	}
	
	
	// validates the list of issues
	// checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		if(issues.isEmpty())
			return false;
		else 
			return true;
	}

	// validates the IMEINumber
	// it should be a 16 digit number 
	public Boolean isValidIMEINumber(Long iMEINumber) {
		if(iMEINumber.toString().length()==16)
			return true;
		return false;
	}
	
	// validates the contact number
	// should contain 10 numeric characters and should not contain 10 repetitive characters
	public Boolean isValidContactNumber(Long contactNumber) {
		String regex1 = "[0-9]{10,}";
		String regex2 = "([0-9])\\1{9,}";

		if(contactNumber.toString().matches(regex1)&&!contactNumber.toString().matches(regex2))
		return true;
		return false;
	}
	
	
	// validates the customer name
	// should contain at least one word and each word separated by a single space should contain at least one letter.
	// the first letter of every word should be an upper case character 
	public static Boolean isValidCustomerName(String customerName) {
		String regex = "([A-Z][a-z]+(\\s)?)+";
		if(customerName.matches(regex))
		return true;
		return false;
	}
}
