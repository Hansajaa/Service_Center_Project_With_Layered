package Controller;

import Bo.BoFactory;
import Bo.Custom.AdminBo;
import Dao.util.BoType;
import Dto.AdminDto;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {
    public BorderPane adminLoginPane;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    AdminBo bo= BoFactory.getInstance().boType(BoType.ADMIN);
    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        try {
            boolean isValid = bo.authenticate(
                    new AdminDto(
                            txtEmail.getText(),
                            txtPassword.getText()
                    )
            );

            if (isValid){
                Stage stage = (Stage) adminLoginPane.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
                stage.setTitle("Dashboard");
                stage.show();

                new Alert(Alert.AlertType.CONFIRMATION,"Welcome:)").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Wrong Email or Password :(").show();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Wrong Email or Password :(").show();
        }

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
