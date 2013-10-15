/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

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

	Temperature celsius3 = new Temperature(-273.15, Temperature.Units.CELSIUS);
	Temperature kelvin3 = new Temperature(0.0, Temperature.Units.KELVIN);
	Temperature fahrenheit3 = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
	
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
	
	/* Test the getValue() method. 
	 * First, with test_getValue(), test if the method returns the correct values for CELSIUS, FAHRENHEIT and KELVIN
	 * 1) Test the minimum values, whereas scientifically the temperature can never be below 0K. Also, check that the 
	 * values do not return the IllegalArgumentException.
	 * 2) Test the zero values.
	 * 3) Test a random positive value.
	 * 4) Test that temperatures below 0 K throw IllegalArgumentExceptions (this is seen in the methods below:
	 * test_getValueBelow0Kelvin(), test_getValueBelow273Celsius(), test_getValueBelow459Fahrenheit())
	 */
	@Test
  public void test_getValue(){
		
	/* Test if the getValue() method returns the right value for the temperature objects.
	 * The values must have a precision of 0.000001, which explains with assertEquals is used to test 
	 * these values instead of assertTrue.
	 * */
	
	// 1) Test if the minimum values return the right values, and do not throw Exceptions		
	assertEquals(kelvin3.getValue(), 0.0, PRECISION ); 
	assertEquals(celsius3.getValue(),-273.15, PRECISION);
	assertEquals(fahrenheit3.getValue(),-459.67,PRECISION);
	
	//2) Test the zero values
	assertEquals(kelvin1.getValue(),0.0, PRECISION);   // this test was done again, easier to read
	assertEquals(celsius1.getValue(),0.0, PRECISION);
	assertEquals(fahrenheit1.getValue(),0.0, PRECISION);
	
	//3) Test random positive values;
	assertEquals(kelvin2.getValue(),50.0, PRECISION ); 
	assertEquals(celsius2.getValue(),50.0, PRECISION);
	assertEquals(fahrenheit2.getValue(),50.0,PRECISION);
  }
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test_getValueBelow0Kelvin(){
		/* Test to see if getValue() throws an "IllegalArgumentException" when
		 * the temperature in KELVIN is below 0 K.
		 */
		System.out.println("Kelvin can never be below 0, test for when it is");
		thrown.expect(IllegalArgumentException.class);
		
		Temperature badKelvin = new Temperature(-1.0,Temperature.Units.KELVIN);
		badKelvin.getValue();
	}
	
	@Test
	public void test_getValueBelow273Celsius(){
		/* Test to see if getValue() throws an "IllegalArgumentException" when
		 * the temperature in CELSIUS is below 273.15 C.
		 */
		System.out.println("Celsius can never be below -273.15, test for when it is");
		thrown.expect(IllegalArgumentException.class);
		Temperature badCelsius = new Temperature(-273.16,Temperature.Units.CELSIUS);
		badCelsius.getValue();
	}
	
	@Test
	public void test_getValueBelow459Fahrenheit(){
		/* Test to see if getValue() throws an "IllegalArgumentException" when
		 * the temperature in FAHRENHEIT is below 459.67 K.
		 */
		System.out.println("Fahrenheit can never be below -459.67, test for when it is");
		thrown.expect(IllegalArgumentException.class);
		Temperature badFahrenheit = new Temperature(-459.68,Temperature.Units.FAHRENHEIT);
		badFahrenheit.getValue();
	}

	/* Test the changeUnits method */
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
