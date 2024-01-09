package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFormController {

    public BorderPane homePane;

    public void adminButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) homePane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminLoginForm.fxml"))));
        stage.show();
    }

    public void employeeButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) homePane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/EmployeeLoginForm.fxml"))));
        stage.show();
    }
}
