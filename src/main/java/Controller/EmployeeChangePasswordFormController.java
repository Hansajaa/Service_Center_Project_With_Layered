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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeChangePasswordFormController {
    public BorderPane employeeChangePasswordPane;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public TextField otpNum1;
    public TextField otpNum2;
    public TextField otpNum3;
    public TextField otpNum4;
    public TextField otpNum5;
    public TextField otpNum6;

    EmployeeBo bo= BoFactory.getInstance().boType(BoType.EMPLOYEE);

    String currentOtpCode;
    boolean isEmailVerified=false;


    public void changePasswordButtonOnAction(ActionEvent actionEvent) {

        String otp = otpNum1.getText()+otpNum2.getText()+otpNum3.getText()+otpNum4.getText()+otpNum5.getText()+otpNum6.getText();

        if (currentOtpCode.equals(otp) && isEmailVerified){
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
                    txtEmail.setText("");
                    txtPassword.setText("");
                    otpNum1.setText("");
                    otpNum2.setText("");
                    otpNum3.setText("");
                    otpNum4.setText("");
                    otpNum5.setText("");
                    otpNum6.setText("");
                    isEmailVerified=false;
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Try again :(").show();
                }
            }catch (RuntimeException e){
                new Alert(Alert.AlertType.CONFIRMATION,"Try again :(").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Enter Valid OTP").show();
        }

    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) employeeChangePasswordPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/EmployeeLoginForm.fxml"))));
        stage.show();
    }

    public void verifyEmailButtonOnAction(ActionEvent actionEvent) {
        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please enter your Email and new Password !").show();
        }else {
            String otp = bo.sendOtp(txtEmail.getText());
            if (otp!=null){
                currentOtpCode=otp;
                isEmailVerified=true;
                new Alert(Alert.AlertType.CONFIRMATION,"Send OTP to "+txtEmail.getText()).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Incorrect Email Address :(").show();
            }
        }
    }
}
