/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureTest {
	private final double PRECISION = 0.000001;
	
  // Add multiple tests to check all functions of
  // {@Code Temperature} class.
	
	/* Create temperature objects to use for testing in test_getUnits() and test_getValue() */
	Temperature celsius1 = new Temperature(0.0, Temperature.Units.CELSIUS);
	Temperature kelvin1 = new Temperature(0.0, Temperature.Units.KELVIN);
	Temperature fahrenheit1 = new Temperature(0.0, Temperature.Units.FAHRENHEIT);
	
	Temperature celsius2 = new Temperature(50.0, Temperature.Units.CELSIUS);
	Temperature kelvin2 = new Temperature(50.0, Temperature.Units.KELVIN);
	Temperature fahrenheit2 = new Temperature(50.0, Temperature.Units.FAHRENHEIT);

	Temperature celsius3 = new Temperature(-50.0, Temperature.Units.CELSIUS);
	Temperature kelvin3 = new Temperature(-50.0, Temperature.Units.KELVIN);
	Temperature fahrenheit3 = new Temperature(-50.0, Temperature.Units.FAHRENHEIT);
	
	@Test
  public void test_getUnits() {
		
	/* Test if the getUnits() method return the right units for the temperature objects 
	 * Since the value of the temperature here does not matter, we only check to the correct
	 * return of the Temperature.Units cases {CELSIUS, KELVIN, FAHRENHEIT}
	 * 								3 cases: 
	 * 				1) returns correct units for CELSIUS
	 * 				2) returns correct units for KELVIN
	 * 				3) returns correct units for FAHRENHEIT      
	 * */
		
    System.out.println("Test if the getUnits returns the right units...");
    assertTrue(celsius1.getUnits() == Temperature.Units.CELSIUS);   
    assertTrue(kelvin1.getUnits() == Temperature.Units.KELVIN); 
    assertTrue(fahrenheit1.getUnits() == Temperature.Units.FAHRENHEIT); 
  }
	
	@Test
  public void test_getValue(){
		
	/* Test if the getValue() method returns the right value for the temperature objects
	 * According to the teacher, the temperature.java should have a precision of 0.000001  
	 * */
		
	System.out.println("Test if the getsValue returns the right value...");
	assertTrue(kelvin1.getValue() == 0.0 );   
	assertTrue(celsius1.getValue() == 0.0);
	assertTrue(fahrenheit1.getValue() > (0.0-PRECISION) && fahrenheit1.getValue()< (0.0+PRECISION));
	assertTrue(kelvin2.getValue() == 50.0 ); 
	assertTrue(celsius2.getValue() == 50.0);
	assertTrue(fahrenheit2.getValue() > (50.0-PRECISION) && fahrenheit2.getValue()<(50.0+PRECISION));
  }
 
  public void test_changeUnits(){
	  /* Convert kelvin to celsius */
	  Temperature kelvinToCel1 = new Temperature(0.0,Temperature.Units.KELVIN);
	  kelvinToCel1.changeUnits(Temperature.Units.CELSIUS);
	  assertTrue(kelvinToCel1.getValue() == -273.15);
	  
	  /* Convert kelvin to fahrenheit */
	  Temperature kelvinToFar1 = new Temperature(0.0, Temperature.Units.KELVIN);
	  kelvinToFar1.changeUnits(Temperature.Units.FAHRENHEIT);
	  assertTrue(kelvinToFar1.getValue() < (-459.67+PRECISION) && kelvinToFar1.getValue()> (-459.67-PRECISION));	  /* Convert celsius to fahrenheit */
	  
	  /* Convert celsius to kelvin */
	  
	  /* Convert farenheit to celsius */
	  /* Convert farenheit to kelvin */
  }
 
}
