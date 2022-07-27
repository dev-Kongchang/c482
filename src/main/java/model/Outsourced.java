package model;

public class Outsourced {
    private String companyName;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName(){
        return this.companyName;
    }

}
