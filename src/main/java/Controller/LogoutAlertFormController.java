package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoutAlertFormController {

    public AnchorPane logoutPane;

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logoutPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void logoutButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logoutPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/HomeForm.fxml"))));
        stage.setTitle("Home");
        stage.show();
    }
}
