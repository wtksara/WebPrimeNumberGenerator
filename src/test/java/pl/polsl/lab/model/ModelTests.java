package pl.polsl.lab.model;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * @author Sara Witek
 * @version  1.0
 *
 * The type Console model.
 */

/**
 * Class for testing the model methods
 */
public class ModelTests {
    ConsoleModel model = new ConsoleModel();

    /**
     * Test function which sets input number, an exception should be thrown when the input number is below zero
     *
     * @param canditate the tested input number
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -20, 50, 500})
    public void testSetInputNumber(Integer canditate) {
        try {
            model.setInputNumber(canditate);
            fail("An exception should be thrown when the input number is below zero");
        } catch (InputMismatchException e) {
        }
    }
    /**
     * Test function which sets quantity, an exception should be thrown when quantity is below or equal to zero or above 300
     *
     * @param canditate the tested input quantity
     */
    @ParameterizedTest
    @ValueSource(ints = {0, -30, 200, 300, 301, 450})
    public void testSetQuantity(Integer canditate) {
        try {
            model.setQuantity(canditate);
            fail("An exception should be thrown when the quantity is below zero or above 300");
        } catch (InputMismatchException e) {
        }
    }

}