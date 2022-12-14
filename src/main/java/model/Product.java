package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Author = Kong Chang
 */
public class Product {
    // make ObservableList object as an arraylist to hold all associated parts for the product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    // Constructor for product id
    private int id;

    // Constructor for product name
    private String name;

    // Constructor for product price
    private double price;

    // Constructor for product inventory stock
    private int stock;

    // Constructor for product min value
    private int min;

    // Constructor for product max value
    private int max;

    // Constructor for product Product class
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *  This adds part to associatedParts
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     *  The selected part is removed from associatedParts
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     *  Returns an observable list of associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
