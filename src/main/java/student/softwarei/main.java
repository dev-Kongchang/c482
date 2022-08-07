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
     *  JavaDoc Folder is located under the src folder. The logic runtime error and future enhancement, and is located in the modify product controller.
     *
     *  We set some test data that will be added to the Inventory object.
     *  This allows us to load the data to start using when the main application is launched
     */
    @Override
    public void start(Stage stage) throws IOException {

        InHouse brakes = new InHouse(1, "Brakes" , 15.00, 10, 1, 20, 1298);
        InHouse Wheel = new InHouse(2, "Wheels", 11.00, 16, 1, 100, 9021);
        InHouse Seat = new InHouse(3, "Seat", 15.00, 10, 1, 80, 1234);
        Inventory.addPart(brakes);
        Inventory.addPart(Wheel);
        Inventory.addPart(Seat);
        
        Product Giant_Bike = new Product(1, "Giant Bike", 300, 5, 1, 999);
        Product Tricycle = new Product(2, "Tricycle", 99, 3, 1, 999);
        Inventory.addProduct(Giant_Bike);
        Inventory.addProduct(Tricycle);


        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Main_Form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1011, 420);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * We'll Load products then parts before launching the application
     */
    public static void main(String[] args) {
        launch();
    }
}