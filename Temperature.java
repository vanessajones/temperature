import java.math.BigDecimal;

/**
 * Edited by Vanessa Jones [15/10/13]
 * ID: 260525970
 * 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Program for ECSE 321, Assignment 2, Fall 2013
 */


/**
 * The {@code Temperature} class allows a user to convert temperature from on
 * unit to another.
 *
 * Sample usage
 * <pre><code>
 *    Temperature averageHighInOctober = new Temperature (15, Temperature.CELSIUS);
 *    Temperature converted = new Temperature(averageHighInOctober);
 *    converted.changeUnits(Temperature.FAHRENHEIT);
 *    System.out.println(
 *          String.format(
 *              "The average temperature of Montreal in October 2012 was %s (%s)",
 *              averageHighInOctober.toString(),
 *              converted.toString()));
 * </code></pre>
 */

public class Temperature {

  /** Enumerator for different temperature units */
  public static enum Units { FAHRENHEIT, CELSIUS, KELVIN }

  private final double valueInKelvin;
  private Units units;

  /**
   * Create a new {@code Temperature} with given attributes
   * @param value numerical value of {@code Temperature}
   * @param units {@code Units} of {@code Temperature}
   */
  public Temperature (double value, Temperature.Units units) {
      this.units    = units;
      valueInKelvin = convertToKelvin(value);
  }

  /**
   * Create a copy of existing {@code Temperature} object
   * @param temperature an existing {@code Temperature} object
   */
  public Temperature (Temperature temperature) {
      this.valueInKelvin = temperature.valueInKelvin;
      this.units = temperature.units;
  }

  /** Convert a {@code Temperature} value from  {@code Units} to Kelvin
   * @param value numerical value of Temperature
   */

  protected double convertToKelvin(double value) {
      double convertedValue;

      /* Temperatures cannot go below 0 KELVIN, throw IllegalArgumentException() exception if it does */ 
      /* When FAHRENHEIT is the MAX_VALUE for doubles, set it as MAX_VALUE and do not convert */
      
      switch (units) {
          case KELVIN:    convertedValue = value;
          					if (convertedValue<0){
          						throw new IllegalArgumentException();
          					}
          					break;
          case CELSIUS:    convertedValue = value + 273.15;
          					if (convertedValue<0){
          						throw new IllegalArgumentException();
          					}
                           break;
          case FAHRENHEIT: if (value==Double.MAX_VALUE){	
								convertedValue = Double.MAX_VALUE;
							}
          					else {
          						convertedValue = (value + 459.67) * 5.0/9.0;
          						if(convertedValue<0){
          							throw new IllegalArgumentException();
          						}
          					}
          					break;
          default:         throw new IllegalArgumentException();
      }
      return convertedValue;
  }

  /** Convert a {@code Temperature} value from  Kelvin to {@code Units} 
   * @param value numerical value of Temperature
   */
  protected double convertFromKelvin(double value) {
      double convertedValue;
 
      /* Temperatures cannot go below 0 KELVIN, throw exception if it does */ 
      /* When FAHRENHEIT is the MAX_VALUE for doubles, set it as MAX_VALUE and do not convert since it becomes out of bounds
       * otherwise */
      
      switch (units) {
          case KELVIN:    convertedValue = value; 
          					if (convertedValue < 0) {	
          						throw new IllegalArgumentException();
          					}
                           break;
                           
          case CELSIUS:    convertedValue = value - 273.15;
          					if (convertedValue < -273.15) {	
          						throw new IllegalArgumentException();
          					}	
                           break;
                           
          case FAHRENHEIT: if (value==Double.MAX_VALUE){	
        	  					convertedValue = Double.MAX_VALUE;
          					}
	          				else {
	          					convertedValue = value * 9.0/5.0 - 459.67;
	          					if (convertedValue < -459.67) {	
	          						throw new IllegalArgumentException();
	          					}
	          				}
                           break;
          default:         throw new IllegalArgumentException();
      }

      return convertedValue;
  }

  /** Format {@code Units}
   */

  protected String unitsToString() {
    String formattedUnits;

      switch (units) {
          case KELVIN:     formattedUnits = "K";
                           break;
          case CELSIUS:    formattedUnits = "°C";
                           break;
          case FAHRENHEIT: formattedUnits = "°F";
                           break;
          default:         throw new IllegalArgumentException();
      }

      return formattedUnits;
  }



  /**
    * Get the value of the {@code Temperature}
    * @return numerical value of the {@code Temperature} in the current {@code Units}
    */
  public double getValue() { 
      return convertFromKelvin(valueInKelvin);
  }

  /**
   * Get the current {@code Units} of the {@code Temperature}
   * @return current {@code Units} of {@code Temperature}
   */
  public Units getUnits() {
      return units;
  }

  /**
   * Change the current {@code Units} of the {@code Temperature}. 
   * Changing the {@code Units} also changes the numerical value 
   * in a consistent manner.
   * @param units the new {@code Units} 
   */
  public void changeUnits(Units units) {
      this.units = units;
  }

  /** 
   * Convert the {@code Temperature} to {@code String}. The output is
   * as follows
   * <pre><code>
   *    Temperature temperature = new Temperature(0, Temperature.Units.CELSIUS);
   *    System.out.println(temperature.toString()); // prints "0 °C"
   *    temperature.changeUnits(Temperature.Units.FAHRENHEIT);
   *    System.out.println(temperature.toString()); // prints "32 °F"
   *    temperature.changeUnits(Temperature.Units.KELVIN);
   *    System.out.println(temperature.toString()); // prints "273.15 K"
   * </code></pre>
   */
  public String toString() {
    return getValue() + " " + unitsToString();
  }
}
