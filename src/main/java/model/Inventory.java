package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Author: Kong Chang Course: C482
 *  to make commenting easier, I am going to implement if statements to
 *  check if either allParts or allProducts is empty or the part already
 *  exists so that way it's
 *  easier to know what to look for when doing error checking or
 *  unit testing
 */

public class Inventory {

    // Declaring PartID
    private static int partID = 1;

    //Declaring ProductID
    private static int productID = 1;

    // allParts is created as ObservableList with Part Object. Then into a collection/arraylist
    private ObservableList<Part> allParts = FXCollections.observableArrayList();


    // allProducts is created as ObservableList with Product Object. Then into a collection/arraylist
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *  adds the newPart into the allParts List
     */
    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * adds the newProduct into the allProduct List
     */
    public void addProduct (Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     *  to lookup part, we run for loop to compare each part
     *  until the part is found, if part is found, we return it
     *  if not, it will get back a null value
     */
    public Part lookupPart (int partId){
        // run for loop to check each part in allParts
        for (Part part : allParts){
            if(part.getId() == partId){
                return part;
            }
        }

        // Once it reaches here, that means the part do not exist
        System.out.println("Inventory: Part don't exist!");
        return null;
    }

    /**
     *  To lookup products, we run for loop to compare each product
     *  until product is found, if product is found, we return it,
     *  if not, it will return null.
     */
    public Product lookupProduct (int productId){
        // Check each product in allProduct
        for (Product product : allProducts){
            if(product.getId() == productId){
                return product;
            }
        }
        // Once it reaches here, the product doesn't exist
        System.out.println("Inventory: Product don't exist!");
        return null;
    }

    /**
     *  First, we create local variable placeholder which will be what we
     *  are returning. Next, we loop through the allParts list to see if
     *  the part exists, if so, return the desired part, if not, null will be returned
     */
    public ObservableList<Part> lookupPart (String partName){
        // we need to local variable to acts as ObservableList<Part> to return
        ObservableList<Part> newList = FXCollections.observableArrayList();
        // loop through for the desired part
        for(Part part : allParts){
            // To make life easy, if we compare names, there can be a mistake for
            // upper or lower case characters, so we'll compare both names a same format
            if (part.getName().toLowerCase().contains(partName.toLowerCase())){
                newList.add(part);
                break;
            }
        }
        // Regardless we'll either return a null or actual part
        return newList;
    }

    /**
     *  First, we create local variable placeholder which will be what we
     *  are returning. Next, we loop through the allProduct list to see if
     *  the product exists, if so, return the desired product, if not, null will be returned
     */
    public ObservableList<Product> lookupProduct (String productName){
        // local variable holder
        ObservableList<Product> newlist = FXCollections.observableArrayList();
        // Loop through for the desired product
        for(Product product : allProducts){
            // To make life easy, if we compare names, there can be a mistake for
            // upper or lower case characters, so we'll compare both names a same format
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                newlist.add(product);
                break;
            }
        }
        // Regardless we'll return null or a product
        return newlist;
    }

    /**
     *  Sets the selected part with index
     */
    public void updatePart (int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     *  updates Product with index and newProduct
     */
    public void updateProduct (int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     *  Deletes the selectedPart from allParts section
     */
    public boolean deletePart (Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     *  Deletes the selectedProduct from the allproducts
     */
    public boolean deleteProduct (Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     *  returns allParts as a list
     */
    public ObservableList<Part> getAllParts (){
        if (allParts.isEmpty()){ // This is to alert the programmer if an error occurs later on
            System.out.println("Inventory: No Parts Available, allParts Object is empty!");
        }
        return allParts;
    }

    /**
     *  returns allProducts as a list
     */
    public ObservableList<Product> getAllProduct (){
        if (allProducts.isEmpty()){
            System.out.println("Inventory: No Products Available, Object allProducts is Empty!");
        }
        return allProducts;
    }

    /**
     *  this
     */
    public static int getNextPartID(){
        return partID++;
    }

    /**
     *
     */
    public static int getNextProductID(){
        return productID++;
    }



}
