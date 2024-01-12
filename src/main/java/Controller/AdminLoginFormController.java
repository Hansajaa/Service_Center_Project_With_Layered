package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {
    public BorderPane adminLoginPane;

    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminLoginPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminLoginPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/HomeForm.fxml"))));
        //stage.setTitle("Admin Dashboard");
        stage.show();
    }

    public void forgotPasswordButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminLoginPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminChangePasswordForm.fxml"))));
        //stage.setTitle("Admin Dashboard");
        stage.show();
    }
}
