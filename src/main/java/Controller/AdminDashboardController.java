package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {


    public BorderPane adminDashboardPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"))));
        stage.setTitle("Login");
        stage.show();
    }

    public void newuserButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/NewUser.fxml"))));
        stage.setTitle("Create New User");
        stage.show();
    }

    public void productButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Product.fxml"))));
        stage.setTitle("Product");
        stage.show();
    }
}
