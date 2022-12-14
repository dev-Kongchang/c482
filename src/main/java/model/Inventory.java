package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Author: Kong Chang     Course: C482
 */

public class Inventory {

    // allParts is created as ObservableList with Part Object. Then into a collection/arraylist
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    // allProducts is created as ObservableList with Product Object. Then into a collection/arraylist
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *  adds the newPart into the allParts List
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * adds the newProduct into the allProduct List
     */
    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     *  to lookup part, we run for loop to compare each part
     *  until the part is found, if part is found, we return it
     *  if not, it will get back a null value
     */
    public static Part lookupPart (int partId){
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
    public static Product lookupProduct (int productId){
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
    public static ObservableList<Part> lookupPart (String partName){
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
    public static ObservableList<Product> lookupProduct (String productName){
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
    public static void updatePart (int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     *  updates Product with index and newProduct
     */
    public static void updateProduct (int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     *  Deletes the selectedPart from allParts section
     */
    public static boolean deletePart (Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /**
     *  Deletes the selectedProduct from the allproducts
     */
    public static boolean deleteProduct (Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     *  returns allParts as a list
     */
    public static ObservableList<Part> getAllParts (){
        if (allParts.isEmpty()){ // This is to alert the programmer if an error occurs later on
            System.out.println("Inventory: No Parts Available, allParts Object is empty!");
        }
        return allParts;
    }

    /**
     *  returns allProducts as a list
     */
    public static ObservableList<Product> getAllProduct (){
        if (allProducts.isEmpty()){
            System.out.println("Inventory: No Products Available, Object allProducts is Empty!");
        }
        return allProducts;
    }


}
