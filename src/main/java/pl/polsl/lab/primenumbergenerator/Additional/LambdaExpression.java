package pl.polsl.lab.primenumbergenerator.Additional;

/**
 * The type Lambda expression.
 */
public class LambdaExpression {
    /**
     * Instantiates a new Lambda expression.
     */
    public LambdaExpression(){};

    /**
     * Operate int.
     *
     * @param a  the first operand
     * @param b  the second operand
     * @param op the operation
     * @return result of calculation in int
     */
    public int operate (int a, int b, MathOperation op){
        return op.operation(a,b);
    }

    /**
     * The interface Math operation.
     */
    public interface MathOperation {
        int operation(int var1, int var2);
    }

}
