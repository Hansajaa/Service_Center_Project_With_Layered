package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderFormController {

    public BorderPane placeOrderPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
       Stage stage = (Stage) placeOrderPane.getScene().getWindow();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminDashboardForm.fxml"))));
       stage.setTitle("Admin Dashboard");
       stage.show();
    }
}
