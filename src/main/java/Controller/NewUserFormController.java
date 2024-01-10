package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserFormController {

    public BorderPane newUserPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) newUserPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.setTitle("Dashboard");
        stage.show();
    }
}
