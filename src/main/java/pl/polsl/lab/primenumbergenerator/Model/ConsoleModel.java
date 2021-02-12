package pl.polsl.lab.primenumbergenerator.Model;
import pl.polsl.lab.primenumbergenerator.View.ConsoleView;

import java.util.*;

/**
 * @author Sara Witek
 * @version  1.0
 *
 * The type Console model.
 */
public class ConsoleModel {

    private ConsoleView view; // View controller
    private Integer inputNumber; // The number from which it should be started generation
    private Integer quantity; // Amount of numbers to generate
    private List<Integer> arrayOfPrimeNumbers; // Array of prime numbers
    private List<Integer> arrayOfSpacingValues; // Array with value of spacing between prime numbers
    private Integer theHighestSpacingValue; // Index of the highest spacing value
    private Integer numberSum; // Sum of all prime numbers
    private Double median; // Median of prime numbers

    /**
     * Instantiates a new Console model.
     */
    public ConsoleModel() {
        this.inputNumber = 0;
        this.quantity = 0;
        this.arrayOfPrimeNumbers= new ArrayList<Integer>();
        this.arrayOfSpacingValues= new ArrayList<Integer>();
        this.theHighestSpacingValue =0;
        this.numberSum =0;
        this.median = 0.0;
    }

    /**
     * Gets input number.
     *
     * @return the prime number
     */
    public Integer getInputNumber() {
        return inputNumber;
    }

    /**
     * Sets input number, in case of incorrect value of input number throws a exception
     *
     * @param inputNumber the input number
     * @throws InputMismatchException if the input number is below 0
     */
    public void setInputNumber(Integer inputNumber) throws InputMismatchException {
        if (inputNumber <0) {
            throw new InputMismatchException();
        }
        this.inputNumber = inputNumber;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() { return quantity;}

    /**
     * Sets quantity, in case of incorrect input value of quantity (below 0 or above 300 ) throws a exception
     *
     * @param quantity the quantity
     * @throws InputMismatchException if the quantity in not in the range
     */
    public void setQuantity(Integer quantity) throws InputMismatchException {
        if (quantity>300 || quantity<=0) {
            throw new InputMismatchException();
        }
        this.quantity = quantity;
    }

    /**
     * Gets array of prime numbers.
     *
     * @return the array of prime numbers
     */
    public List<Integer> getArrayOfPrimeNumbers() {
        return arrayOfPrimeNumbers;
    }

    /**
     * Gets array value according to passed position
     *
     * @param position the position
     * @return the array value
     */
    public Integer getValueFromPrimeNumberArray(int position) { return arrayOfPrimeNumbers.get(position); }

    /**
     * Add value to prime number array according to passed position
     *
     * @param value the value
     */
    public void addValueToPrimeNumberArray(int value) { this.arrayOfPrimeNumbers.add(value);}

    /**
     * Gets array of spacing values.
     *
     * @return the array of spacing values
     */
    public List<Integer>  getArrayOfSpacingValues() {
        return arrayOfSpacingValues;
    }


    /**
     * Sets array of spacing values.
     *
     * @param spacingArray the spacing array
     */
    public void setArrayOfSpacingValues(List <Integer> spacingArray) { this.arrayOfSpacingValues = spacingArray; }

    /**
     * Gets spacing value according to passed position
     *
     * @param position the position
     * @return the spacing value
     */
    public Integer getSpacingValue(int position) { return arrayOfSpacingValues.get(position); }

    /**
     * Gets the highest spacing value.
     *
     * @return the highest spacing value
     */
    public Integer getTheHighestSpacingValue() { return theHighestSpacingValue; }

    /**
     * Sets the highest spacing value.
     *
     * @param theHighestSpacingValue the highest spacing value
     */
    public void setTheHighestSpacingValue(Integer theHighestSpacingValue) { this.theHighestSpacingValue = theHighestSpacingValue; }

    /**
     * Gets number sum.
     *
     * @return the number sum
     */
    public Integer getNumberSum() { return numberSum; }

    /**
     * Sets number sum.
     *
     * @param numberSum the number sum
     */
    public void setNumberSum(Integer numberSum) { this.numberSum = numberSum; }

    /**
     * Gets median.
     *
     * @return the median
     */
    public Double getMedian() {
        return median;
    }

    /**
     * Sets median.
     *
     * @param median the median
     */
    public void setMedian(Double median) {
        this.median = median;
    }


    /**
     * Add number to number sum.
     *
     * @param numberValue the number value
     */
    public void addNumberToNumberSum(Integer numberValue) { this.numberSum +=numberValue; }


}

