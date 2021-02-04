package model;

public class InHousePart extends Part{

    private int machineID;

    public InHousePart(int partID, String partName, double partPrice, int partInStock, int min, int max, int machineID) {
        super(partID, partName,partPrice,partInStock,min,max);
        this.machineID = machineID;
    }

    public InHousePart() {
        super();
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

}

/* GOING TO HAVE TO DO THE SAME WITH INVENTORY AND PRODUCT REFER TO VIDEO @ 27:00 */