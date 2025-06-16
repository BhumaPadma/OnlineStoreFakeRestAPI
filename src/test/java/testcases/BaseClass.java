package testcases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utils.ConfigReader;

public class BaseClass {

	ConfigReader configReader;
	
	//For logging
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;

	@BeforeClass
	public void setUp() throws FileNotFoundException {
		RestAssured.baseURI = Routes.BASE_URL;

		configReader = new ConfigReader();
		
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
		PrintStream log = new PrintStream(fos,true);
		
		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);
		RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
		
	}

	// Helper method to check whether the list is sorted in descending order
	boolean isOrderDescending(List<Integer> list) {
		for (int i = 0; i < list.size()-1; i++) {
			// if i(5) is less than i-1(4) then it will continue the loop else it will return false
			if (list.get(i) < list.get(i+1)) {
				return false;
			}
		}
		return true;
	}

	// Helper method to check whether the list is sorted in descending order
	boolean isOrderAscending(List<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			// if i(5) is greater than i-1(4) then it will continue the loop else it will return false
			if (list.get(i) > list.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	
	//Helper method to check dates fall within the specified range

	 public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {
	       
	    	LocalDate start = LocalDate.parse(startDate, FORMATTER);
	    	
	        LocalDate end = LocalDate.parse(endDate, FORMATTER);

	        for (String dateTime : cartDates) 
	        	{
	            LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), FORMATTER);
	            if (cartDate.isBefore(start) || cartDate.isAfter(end)) {
	                return false; // Immediately return false if any cart date is out of range
	            }
	        }
	        return true; // All dates are within range
	    }
	    

	
	

}
