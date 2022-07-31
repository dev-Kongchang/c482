package student.softwarei;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;

public class main extends Application {
    /**
     * We are initializing an Inventory Object first
     */
    private static Inventory inventory = null;

    /**
     * Populates parts into the inventory first
     */
    public static void add_parts_to_Inventory(){

        InHouse brakes = new InHouse(1, "Brakes" , 15.00, 10, 1, 20, 1298);
        InHouse Wheel = new InHouse(2, "Wheels", 11.00, 16, 1, 100, 9021);
        InHouse Seat = new InHouse(3, "Seat", 15.00, 10, 1, 80, 1234);
        inventory.addPart(brakes);
        inventory.addPart(Wheel);
        inventory.addPart(Seat);

    }

    /**
     *
     * Populates Products into the inventory
     */
    public static void add_products_to_Inventory(){
        Product Giant_Bike = new Product(1, "Giant Bike", 299.99, 5, 1, 999);
        Product Tricycle = new Product(2, "Tricycle", 99.99, 3, 1, 999);
        inventory.addProduct(Giant_Bike);
        inventory.addProduct(Tricycle);
    }

    @Override
    public void start(Stage stage) throws IOException {
        inventory = new Inventory();


        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Main_Form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1011, 420);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * We'll Load products then parts before launching the application
     */
    public static void main(String[] args) {
        add_products_to_Inventory();
        add_parts_to_Inventory();
        launch();
    }
}