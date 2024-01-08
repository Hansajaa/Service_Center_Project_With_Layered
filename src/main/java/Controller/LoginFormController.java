package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public BorderPane loginPane;

    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loginPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"))));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}
