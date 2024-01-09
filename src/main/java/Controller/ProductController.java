package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {

    public BorderPane productPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) productPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"))));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}
