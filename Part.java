package model;


public abstract class Part {

    protected int partID;
    protected String partName;
    protected double partPrice = 0.0;
    protected int partInStock;
    protected int min;
    protected int max;

    /** Part Constructor.
     * Defines a Part based on the below paramters
     * @param partID ID of the new Part
     * @param partName Name of the new part
     * @param partPrice Price of the new part
     * @param partInStock Inventory of the new part
     * @param min Minumum amount of the new part
     * @param max Maximum amount of the new part
     */
    public Part(int partID, String partName, double partPrice, int partInStock, int min, int max) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partInStock = partInStock;
        this.min = min;
        this.max = max;
    }

    public Part() {

    }

    /** Gets Part ID.
     *
     * @return Auto Generated Part ID
     */
    public int getPartID() {
        return partID;
    }

    /** Sets Part ID.
     *
     * @param partID Auto Generated Part ID
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }

    /** Gets name of the part.
     *
     * @return Returns Name of the part
     */
    public String getPartName() {
        return partName;
    }

    /** Sets name of the part.
     *
     * @param partName Sets name of the part
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /** Gets the price of the part.
     *
     * @return Returns the price of the part
     */
    public double getPartPrice() {
        return partPrice;
    }

    /** Sets the price of the part.
     *
     * @param partPrice Sets price of the part
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    /** Gets the inventory of the part.
     *
     * @return Returns number of parts in stock
     */
    public int getPartInStock() {
        return partInStock;
    }

    /** Sets the inventory of the part.
     *
     * @param partInStock Sets the number of parts in stock
     */
    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }

    /** Gets the minimum amount allowed.
     *
     * @return Minumum number allowed
     */
    public int getMin() {
        return min;
    }

    /** Sets the minumum number allowed.
     *
     * @param min Minimum number allowed
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Gets the maximum number allowed.
     *
     * @return Maximum number allowed
     */
    public int getMax() {
        return max;
    }

    /** Sets the maximum number allowed.
     *
     * @param max Maximum number allowed
     */
    public void setMax(int max) {
        this.max = max;
    }

}



