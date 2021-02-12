package pl.polsl.lab.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.primenumbergenerator.Controller.ConsoleController;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;
import pl.polsl.lab.primenumbergenerator.View.ConsoleView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Sara Witek
 * @version  1.0
 *
 * The type Console model.
 */

/**
 * Class for testing the methods in the rest of the program
 */
public class Tests {

    private ConsoleModel model = new ConsoleModel();
    private ConsoleView view = new ConsoleView(model);
    private ConsoleController controller = new ConsoleController(view,model);

    /**
     * Test function which gets parameters from user.
     * Exception should be thrown for
     * When any input is a string or is not a number
     * When any input is not in range - prime number has to be in range 0 to infinity, quantity in range 0 to 300
     *
     * @param inputPrimeNumber the input prime number
     * @param quantity       the input qunatity
     */

    @ParameterizedTest
    @CsvSource({"0,0","c,0", "0,c","c,c","-30,1","0,1","1,0","1,-30","5,5","5,300","350,301"})
    public void testGetParametersFromUser(String inputPrimeNumber,String quantity) {
        try {
           view.getParametersFromUser(inputPrimeNumber,quantity);
            fail("An exception should be thrown when the quantity or prime number is not in range");
        }
        catch (Exception e) {
        }
    }

    /**
     * Sets up before all asserts test. Fill the prime number array and set the quantity
     */
    @BeforeEach
    public void setUp() {
        model.addValueToPrimeNumberArray(0);
        model.addValueToPrimeNumberArray(5);
        model.addValueToPrimeNumberArray(8);
        model.addValueToPrimeNumberArray(11);
        model.setQuantity(4);
    }

    /**
     * Test calculate median value.
     */
    @Test
    public void testCalculateMedianValue(){
        controller.calculateMedianValue();
        assertEquals(6.5,model.getMedian());
    }

    /**
     * Test the returned highest spacing value generated during calculation of spacing values
     */
    @Test
    public void testTheHighestSpacingValue(){
        controller.generateSpacingValues();
        assertEquals(5,model.getTheHighestSpacingValue());
    }

}