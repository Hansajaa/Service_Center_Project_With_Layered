package Controller;

import Bo.BoFactory;
import Bo.Custom.EmployeeBo;
import Dao.util.BoType;
import Dto.EmployeeDto;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserFormController {

    public BorderPane newUserPane;
    public JFXTextField txtUsername;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;

    EmployeeBo bo= BoFactory.getInstance().boType(BoType.EMPLOYEE);

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) newUserPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void newAccountButtonOnAction(ActionEvent actionEvent) {
        boolean isRegistered = bo.registerEmployee(
                new EmployeeDto(
                        txtUsername.getText(),
                        txtEmail.getText(),
                        txtPassword.getText()
                )
        );

        if (isRegistered){
            new Alert(Alert.AlertType.CONFIRMATION,"New User Created :)").show();
            txtUsername.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Something Went Wrong :(").show();
        }
    }
}
