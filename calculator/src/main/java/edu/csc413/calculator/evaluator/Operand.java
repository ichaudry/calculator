package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

  //Local class variable to store operand value
  private int operand;
  /**
  * construct operand from string token.
  */  
  public Operand( String token ) {
    //Convert the string token to an integer and store the value as operand variable
    operand=Integer.valueOf(token);

  }
  /**
   * construct operand from integer
   */
  public Operand( int value ) {
    //Constructor to instantiate the operand variable using the int value passed to it
    operand=value;
  }

  public Operand(double value){
    //This constructor typecasts the double to an int and instantiates the operand variable using it
    operand=(int) value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {
    //Returns the operand
    return operand;
  }

  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {
    //This expression uses regular expression to check if the token passed has any numbers in it.
    return token.matches("[0-9]+");
  }
}
