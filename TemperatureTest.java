/** 
 * Edited by Vanessa Jones [15/10/13]
 * ID: 260525970
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
	
	Temperature celsius4 = new Temperature(Double.MAX_VALUE, Temperature.Units.CELSIUS);
	Temperature kelvin4 = new Temperature(Double.MAX_VALUE, Temperature.Units.KELVIN);
	Temperature fahrenheit4 = new Temperature(Double.MAX_VALUE, Temperature.Units.FAHRENHEIT);
	
	Temperature celsius5 = new Temperature(Double.MIN_VALUE, Temperature.Units.CELSIUS);
	Temperature kelvin5 = new Temperature(Double.MIN_VALUE, Temperature.Units.KELVIN);
	Temperature fahrenheit5 = new Temperature(Double.MIN_VALUE, Temperature.Units.FAHRENHEIT);
	
	Temperature celsius6 = new Temperature(-50.0, Temperature.Units.CELSIUS);
	Temperature fahrenheit6 = new Temperature(-50.0, Temperature.Units.FAHRENHEIT);
	
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
	 * values do not return the IllegalArgumentException (they are the boundaries).
	 * 2) Test the zero values.
	 * 3) Test a random positive/negative value.
	 * 4) Min and max values of a double
	 * Secondly, with test_getValueBelow0Kelvin(), test_getValueBelow273Celsius(), test_getValueBelow459Fahrenheit(),
	 * test if the method throws IllegalArgumentExceptions when the values go below 0 K.
	 * 5) Test that temperatures below 0 K throw IllegalArgumentExceptions 
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
	
	//3) Test random negative/positive values;
	assertEquals(kelvin2.getValue(),50.0, PRECISION ); 
	assertEquals(celsius2.getValue(),50.0, PRECISION);
	assertEquals(fahrenheit2.getValue(),50.0,PRECISION);
	assertEquals(celsius6.getValue(),-50.0, PRECISION);
	assertEquals(fahrenheit6.getValue(),-50.0, PRECISION);
	
	//4) Test for maximum value of a double
	assertEquals(kelvin4.getValue(),Double.MAX_VALUE, PRECISION ); 
	assertEquals(celsius4.getValue(),Double.MAX_VALUE, PRECISION);
	assertEquals(fahrenheit4.getValue(), Double.MAX_VALUE, PRECISION);
	
	//4) Test for minimum value of a double
	assertEquals(kelvin5.getValue(),Double.MIN_VALUE, PRECISION ); 
	assertEquals(celsius5.getValue(),Double.MIN_VALUE, PRECISION);
	assertEquals(fahrenheit5.getValue(),Double.MIN_VALUE, PRECISION);
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

	/* Test the changeUnits method 
	 * Check if the value of the temperature has changed using getValue() and
	 * also check if the units have been changed using getUnits()
	 * Test the 6 general cases: Kelvin to Celsius, Kelvin to Fahrenheit, Celsius to Fahrenheit.
	 * Celsius to Kelvin, Fahrenheit to Celisus and Fahrenheit to Kelvin.
	 * For each case, test: 
	 * 1) Minimum values (to check if they don't return IllegalArgumentExceptions
	 * 2) Random general value (in this case, 50)
	 * 3) In the 6 methods below, check if even after conversion changeUnits(), values below 0 K will throw an exception
	 * */
	@Test
  public void test_changeUnits(){
		/* Convert Kelvin to Celsius */
	  Temperature kelvinToCel1 = new Temperature(0.0,Temperature.Units.KELVIN);
	  kelvinToCel1.changeUnits(Temperature.Units.CELSIUS);
	  assertEquals(kelvinToCel1.getValue(),-273.15, PRECISION);
	  assertTrue(kelvinToCel1.getUnits()==Temperature.Units.CELSIUS);
	  
	  Temperature kelvinToCel2 = new Temperature(50.0,Temperature.Units.KELVIN);
	  kelvinToCel2.changeUnits(Temperature.Units.CELSIUS);
	  assertEquals(kelvinToCel2.getValue(),-223.15, PRECISION);
	  assertTrue(kelvinToCel2.getUnits() == Temperature.Units.CELSIUS);
	  
	  /* Convert kelvin to fahrenheit */
	  Temperature kelvinToFar1 = new Temperature(0.0, Temperature.Units.KELVIN);
	  kelvinToFar1.changeUnits(Temperature.Units.FAHRENHEIT);
	  assertEquals(kelvinToFar1.getValue(),-459.67, PRECISION);
	  assertTrue(kelvinToFar1.getUnits() == Temperature.Units.FAHRENHEIT);
	  
	  Temperature kelvinToFar2 = new Temperature(50.0, Temperature.Units.KELVIN);
	  kelvinToFar2.changeUnits(Temperature.Units.FAHRENHEIT);
	  assertEquals(kelvinToFar2.getValue(),-369.67, PRECISION);
	  assertTrue(kelvinToFar2.getUnits() == Temperature.Units.FAHRENHEIT);
	  
	  /* Convert celsius to fahrenheit */
	  Temperature celsiusToFar1 = new Temperature(-273.15, Temperature.Units.CELSIUS);
	  celsiusToFar1.changeUnits(Temperature.Units.FAHRENHEIT);
	  assertEquals(celsiusToFar1.getValue(),-459.67, PRECISION);
	  assertTrue(celsiusToFar1.getUnits() == Temperature.Units.FAHRENHEIT);
	  
	  Temperature celsiusToFar2 = new Temperature(50.0, Temperature.Units.CELSIUS);
	  celsiusToFar2.changeUnits(Temperature.Units.FAHRENHEIT);
	  assertEquals(celsiusToFar2.getValue(),122, PRECISION);
	  assertTrue(kelvinToFar2.getUnits() == Temperature.Units.FAHRENHEIT);
	  
	  /* Convert celsius to kelvin */
	  Temperature celsiusToKelvin1 = new Temperature(-273.15, Temperature.Units.CELSIUS);
	  celsiusToKelvin1.changeUnits(Temperature.Units.KELVIN);
	  assertEquals(celsiusToKelvin1.getValue(), 0, PRECISION);
	  assertTrue(celsiusToKelvin1.getUnits() == Temperature.Units.KELVIN);
	  
	  Temperature celsiusToKelvin2 = new Temperature(50.0, Temperature.Units.CELSIUS);
	  celsiusToKelvin2.changeUnits(Temperature.Units.KELVIN);
	  assertEquals(celsiusToKelvin2.getValue(),323.15, PRECISION);
	  assertTrue(celsiusToKelvin2.getUnits() == Temperature.Units.KELVIN);
	  
	  
	  /* Convert farenheit to celsius */
	  Temperature farToCelsius1 = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
	  farToCelsius1.changeUnits(Temperature.Units.CELSIUS);
	  assertEquals(farToCelsius1.getValue(), -273.15, PRECISION);
	  assertTrue(farToCelsius1.getUnits() == Temperature.Units.CELSIUS);
	  
	  Temperature farToCelsius2 = new Temperature(50.0, Temperature.Units.FAHRENHEIT);
	  farToCelsius2.changeUnits(Temperature.Units.CELSIUS);
	  assertEquals(farToCelsius2.getValue(),10.0, PRECISION);
	  assertTrue(farToCelsius2.getUnits() == Temperature.Units.CELSIUS);
	  
	  /* Convert farenheit to kelvin */
	  Temperature farToKelvin1 = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
	  farToKelvin1.changeUnits(Temperature.Units.KELVIN);
	  assertEquals(farToKelvin1.getValue(), 0, PRECISION);
	  assertTrue(farToKelvin1.getUnits() == Temperature.Units.KELVIN);
	  
	  Temperature farToKelvin2 = new Temperature(50.0, Temperature.Units.FAHRENHEIT);
	  farToKelvin2.changeUnits(Temperature.Units.KELVIN);
	  assertEquals(farToKelvin2.getValue(), 283.15, PRECISION);
	  assertTrue(farToKelvin2.getUnits() == Temperature.Units.KELVIN);
	  
  }

  @Test
	public void test_changeUnitsExceptionKelvinToCelsius(){
		/* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Kelvin to Celsius
		 */
		thrown.expect(IllegalArgumentException.class);
		Temperature badKelvintoCelsius = new Temperature(-0.01,Temperature.Units.KELVIN);
		badKelvintoCelsius.changeUnits(Temperature.Units.CELSIUS);
		badKelvintoCelsius.getValue();
	}
  
  @Test
	public void test_changeUnitsExceptionKelvinToFahrenheit(){
	  /* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Kelvin to Fahrenheit
		 */
	  
		thrown.expect(IllegalArgumentException.class);
		Temperature badKelvinToFar = new Temperature(-0.01,Temperature.Units.KELVIN);
		badKelvinToFar.changeUnits(Temperature.Units.FAHRENHEIT);
		badKelvinToFar.getValue();
	}
  
  @Test
	public void test_changeUnitsExceptionCelsiusToFahrenheit(){
	  /* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Celsius to Fahrenheit
		 */
	  
		thrown.expect(IllegalArgumentException.class);
		Temperature badCelsiusToFar = new Temperature(-273.16,Temperature.Units.CELSIUS);
		badCelsiusToFar.changeUnits(Temperature.Units.FAHRENHEIT);
		badCelsiusToFar.getValue();
	}
  @Test
	public void test_changeUnitsExceptionCelsiusToKelvin(){
	  /* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Celsius to Kelvin
		 */
		thrown.expect(IllegalArgumentException.class);
		Temperature badCelsiusToKelvin = new Temperature(-273.16,Temperature.Units.CELSIUS);
		badCelsiusToKelvin.changeUnits(Temperature.Units.KELVIN);
		badCelsiusToKelvin.getValue();
	}
  
  @Test
	public void test_changeUnitsExceptionFarToCelsius(){
	  /* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Fahrenheit to Celsius
		 */
		thrown.expect(IllegalArgumentException.class);
		Temperature badFarToCelsius = new Temperature(-459.68,Temperature.Units.FAHRENHEIT);
		badFarToCelsius.changeUnits(Temperature.Units.CELSIUS);
		badFarToCelsius.getValue();
	}
  
  @Test
	public void test_changeUnitsExceptionFarToKelvin(){
	  /* Test to see if an error is still thrown after changeUnits() for values below 0K
		 * Fahrenheit to Kelvin
		 */
		thrown.expect(IllegalArgumentException.class);
		Temperature badFarToKelvin = new Temperature(-459.68,Temperature.Units.FAHRENHEIT);
		badFarToKelvin.changeUnits(Temperature.Units.KELVIN);
		badFarToKelvin.getValue();
	}
}
