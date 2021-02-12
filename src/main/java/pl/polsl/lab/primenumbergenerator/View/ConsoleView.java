package pl.polsl.lab.primenumbergenerator.View;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;
import java.util.InputMismatchException;
/**
 * @author Sara Witek
 * @version  1.0
 *
 * The type Console view.
 */
public class ConsoleView {

    private ConsoleModel model;
    private Integer index;
    /**
     * Instantiates a new Console view.
     *
     */
    public ConsoleView() { this.index = 0; };

    /**
     * Instantiates a new Console view and initialize the model
     *
     * @param model the model
     */
    public ConsoleView(ConsoleModel model) {
        this.model = model;
        this.index = 0;
    }

    /**
     * Gets parameters from user, passed them to model to check if there are correct
     *
     * @param inputPrimeNumber the input prime number
     * @param inputRange       the input range
     * @return if the initialization was successful
     */
    public boolean getParametersFromUser(String inputPrimeNumber, String inputRange) throws NumberFormatException, InputMismatchException {

        // Checking if the inputs are in correct range and if there are characters
        model.setInputNumber(Integer.parseInt(inputPrimeNumber));
        model.setQuantity(Integer.parseInt(inputRange));;
        return true;
    }
}
