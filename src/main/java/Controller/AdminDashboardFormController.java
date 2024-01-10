package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {


    public BorderPane adminDashboardPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminLoginForm.fxml"))));
        stage.setTitle("Login");
        stage.show();
    }

    public void newuserButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/NewUserForm.fxml"))));
        stage.setTitle("Create New User");
        stage.show();
    }

    public void productButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ProductForm.fxml"))));
        stage.setTitle("Product");
        stage.show();
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/PlaceOrderForm.fxml"))));
        stage.setTitle("Place Order");
        stage.show();
    }

    public void ordersButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrderForm.fxml"))));
        stage.setTitle("Orders");
        stage.show();
    }

    public void changeZoneButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminDashboardPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ChangeStatusAndZoneForm.fxml"))));
        stage.setTitle("Change Zone & Change Status");
        stage.show();
    }
}
