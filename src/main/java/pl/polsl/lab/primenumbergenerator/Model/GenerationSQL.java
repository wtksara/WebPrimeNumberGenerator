package pl.polsl.lab.primenumbergenerator.Model;

/**
 * The type Generation sql.
 */
public class GenerationSQL {
    private Integer ID;
    private Integer informationID;
    private Integer input;
    private Integer amount;
    private Integer theHighest;
    private Integer sum;
    private Integer median;

    /**
     * Instantiates a new Generation sql.
     *
     * @param ID            the id
     * @param informationID the information id
     * @param input         the input
     * @param amount        the amount
     * @param theHighest    the the highest
     * @param sum           the sum
     * @param median        the median
     */
    public GenerationSQL(Integer ID, Integer informationID, Integer input, Integer amount, Integer theHighest, Integer sum, Integer median) {
        this.ID = ID;
        this.informationID = informationID;
        this.input = input;
        this.amount = amount;
        this.theHighest = theHighest;
        this.sum = sum;
        this.median = median;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Gets information id.
     *
     * @return the information id
     */
    public Integer getInformationID() { return informationID; }

    /**
     * Gets input.
     *
     * @return the input
     */
    public Integer getInput() {
        return input;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Gets the highest.
     *
     * @return the the highest
     */
    public Integer getTheHighest() {
        return theHighest;
    }

    /**
     * Gets sum.
     *
     * @return the sum
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * Gets median.
     *
     * @return the median
     */
    public Integer getMedian() {
        return median;
    }

}
