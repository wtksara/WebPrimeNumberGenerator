package pl.polsl.lab.primenumbergenerator.Controller;

import pl.polsl.lab.primenumbergenerator.Additional.LambdaExpression;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;
import pl.polsl.lab.primenumbergenerator.View.ConsoleView;

import java.lang.Math;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author Sara Witek
 * @version  1.0
 *
 * The type Console controller.
 */
public class ConsoleController {
    private ConsoleView view;
    private ConsoleModel model;

    /**
     * Instantiates a new Console controller.
     *
     * @param view  the view
     * @param model the model
     */
    public ConsoleController(ConsoleView view, ConsoleModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Generate prime numbers depends from user input parameters
     */
    public void generatePrimeNumbers() {

        // Using lambda expression to replace operation modulo
        LambdaExpression math = new LambdaExpression();
        LambdaExpression.MathOperation modulo = (a, b) -> {
            return a % b;
        };

        Integer primeNumber = model.getInputNumber();
        Integer range = model.getQuantity();
        boolean state;
        Integer position = 0;

        while ( position < range) { // Check if we have enough numbers generated
            state=true;

            if(primeNumber>1) {
                if (primeNumber > 2) { // Algorithm for checking if the number is a prime number
                    Integer rest = math.operate(primeNumber, 2, modulo);
                    if (math.operate(primeNumber, 2, modulo) != 0) {
                        Integer sqrtFromPrimeNumber = (int) Math.sqrt(primeNumber);

                        for (Integer i = 3; i <= sqrtFromPrimeNumber; i += 2)
                            if (math.operate(primeNumber, i, modulo) == 0) {
                                state = false;
                                break;
                            }
                    } else state = false;
                }
                if (state) // Adding the number to array and to the sum of prime numbers
                {
                    model.addValueToPrimeNumberArray(primeNumber);
                    model.addNumberToNumberSum(primeNumber);
                    position++;
                }
            }
            primeNumber++;
        }
    }

    /**
     * Generate spacing values between already generated prime numbers in array
     */
    public void generateSpacingValues(){
        List <Integer> spacingValues = new ArrayList<Integer>();

        for (int i = 0; i < (model.getQuantity() -1); i++){
            Integer value = model.getValueFromPrimeNumberArray(i+1)-model.getValueFromPrimeNumberArray(i);
            spacingValues.add(value); // Calculate the spacing between prime numbers
            if (value>model.getTheHighestSpacingValue()){
                model.setTheHighestSpacingValue(value); // Setting the highest spacing value
            }
        }
        model.setArrayOfSpacingValues(spacingValues);
    }
    /**
     * Calculate median value of prime numbers with using a stream
     */
    public void calculateMedianValue(){

        // Using stream to convert integer list to double list
        List <Double> primeNumberArrayOnDouble = model.getArrayOfPrimeNumbers().parallelStream().mapToDouble(i->i).boxed().collect(Collectors.toList());
        DoubleStream stream =primeNumberArrayOnDouble.stream().mapToDouble(Double::doubleValue);
        // Calculate if the median value is a average of two value or only one value
        double median = primeNumberArrayOnDouble.size() % 2 == 0?
                stream.skip(primeNumberArrayOnDouble.size()/2-1).limit(2).average().getAsDouble():
                stream.skip(primeNumberArrayOnDouble.size()/2).findFirst().getAsDouble();
        model.setMedian(median); // Setting median value on model
    }
}
