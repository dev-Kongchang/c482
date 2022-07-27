package model;

public class InHouse extends Part{
    /**
     *  Declares the machineId variable
     */
    private int machineId;

    /**
     *  Since InHouse inherits Part, we can pass the parameters to it, and
     *  only need to change the machineId
     */
    public InHouse (int id, String name, double price, int stock, int min, int max, int machineId){
        // InHouse already inherits Part
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *  Changing the machineId with the given one
     */
    public void setMachineId (int machineId){
        this.machineId = machineId;
    }

    /**
     *  returns the current machineId
     */
    public int getMachineId(){
        return this.machineId;
    }

}
