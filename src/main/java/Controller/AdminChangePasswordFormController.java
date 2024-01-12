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

public class AdminChangePasswordFormController {
    public BorderPane adminChangePasswordPane;
    public JFXPasswordField txtPassword;
    public JFXTextField txtEmail;


    AdminBo bo= BoFactory.getInstance().boType(BoType.ADMIN);
    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminChangePasswordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminLoginForm.fxml"))));
        stage.show();
    }

    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminChangePasswordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AdminLoginForm.fxml"))));
        stage.show();
    }

    public void changePasswordButtonOnAction(ActionEvent actionEvent) {
        boolean isChanged = bo.changePassword(
                new AdminDto(
                        txtEmail.getText(),
                        txtPassword.getText()
                )
        );

        if (isChanged){
            new Alert(Alert.AlertType.CONFIRMATION,"Password Changed :)").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION,"Try again :(").show();
        }
    }
}
