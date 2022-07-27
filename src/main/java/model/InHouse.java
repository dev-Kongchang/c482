package model;

public class InHouse {
    private int machineId;

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public InHouse (int id, String name, double price, int stock, int min, int max, int machineId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.machineId = machineId;
    }

    public void setMachineId (int machineId){
        this.machineId = machineId;
    }
    public int getMachineId(){
        return this.machineId;
    }

}
