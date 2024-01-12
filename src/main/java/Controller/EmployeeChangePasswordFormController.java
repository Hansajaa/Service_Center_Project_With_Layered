package Controller;

import Bo.BoFactory;
import Bo.Custom.EmployeeBo;
import Dao.util.BoType;
import Dto.EmployeeDto;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeChangePasswordFormController {
    public BorderPane employeeChangePasswordPane;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    EmployeeBo bo= BoFactory.getInstance().boType(BoType.EMPLOYEE);
    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) employeeChangePasswordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/EmployeeLoginForm.fxml"))));
        stage.show();
    }

    public void changePasswordButtonOnAction(ActionEvent actionEvent) {
        try{
            boolean isChanged = bo.changePassword(
                    new EmployeeDto(
                            null,
                            txtEmail.getText(),
                            txtPassword.getText()
                    )
            );

            if (isChanged){
                new Alert(Alert.AlertType.CONFIRMATION,"Password Changed :)").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Try again :(").show();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.CONFIRMATION,"Try again :(").show();
        }

    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) employeeChangePasswordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/EmployeeLoginForm.fxml"))));
        stage.show();
    }
}
