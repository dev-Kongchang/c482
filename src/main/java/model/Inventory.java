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

    // we must declare ID startpoints
    private static int partID = 1;
    private static int productID = 1;
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart){
        if (allParts.contains(newPart)) {
            System.out.println("Inventory: Part already Exists in the Inventory");
        } else {
            allParts.add(newPart);
        }
    }

    public void addProduct (Product newProduct){
        if(allProducts.contains(newProduct)){
            System.out.println("Inventory: Product already Exists in the Inventory");
        } else{
            allProducts.add(newProduct);
        }
    }

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

    public ObservableList<Part> lookupPart (String partName){
        // we need to local variable to acts as ObservableList<Part> to return
        ObservableList<Part> newList = FXCollections.observableArrayList();
        // loop through for the desired part
        for(Part part : allParts){
            // To make life easy, if we compare names, there can be a mistake for
            // upper or lower case characters, so we'll compare both names a same format
            if (part.getName().toLowerCase().contains(partName.toLowerCase())){
                newList.add(part);
            }
        }
        // Regardless we'll either return a null or actual part
        return newList;
    }

    public ObservableList<Product> lookupProduct (String productName){
        // local variable holder
        ObservableList<Product> newlist = FXCollections.observableArrayList();
        // Loop through for the desired product
        for(Product product : allProducts){
            // To make life easy, if we compare names, there can be a mistake for
            // upper or lower case characters, so we'll compare both names a same format
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                newlist.add(product);
            }
        }
        // Regardless we'll return null or a product
        return newlist;
    }

    public void updatePart (int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public void updateProduct (int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    public boolean deletePart (Part selectedPart){
        return allParts.remove(selectedPart);
    }

    public boolean deleteProduct (Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts (){
        if (allParts.isEmpty()){ // This is to alert the programmer if an error occurs later on
            System.out.println("Inventory: No Parts Available, allParts Object is empty!");
        }
        return allParts;
    }

    public ObservableList<Product> getAllProduct (){
        if (allProducts.isEmpty()){
            System.out.println("Inventory: No Products Available, Object allProducts is Empty!");
        }
        return allProducts;
    }

    public static int getNextPartID(){
        return partID++;
    }

    public static int getNextProductID(){
        return productID++;
    }



}
