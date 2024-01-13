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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserConfirmationFormController {
    public JFXTextField txtEmail;
    public AnchorPane userConfirmationPane;
    public Text lblWarning;
    public JFXPasswordField txtPassword;

    AdminBo adminBo= BoFactory.getInstance().boType(BoType.ADMIN);
    public void backToDashboardButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userConfirmationPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }

    public void confirmButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
            lblWarning.setText("Enter Email and Password...");
        }else {
            boolean isAuthenticate = adminBo.authenticate(
                    new AdminDto(
                            txtEmail.getText(),
                            txtPassword.getText()
                    )
            );

            if (isAuthenticate){
                Stage stage = (Stage) userConfirmationPane.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/NewUserForm.fxml"))));
                stage.show();
            }else {
                lblWarning.setText("Incorrect Email or Password...Try Again");
            }
        }
    }
}
