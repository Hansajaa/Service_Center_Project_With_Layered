package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlaceOrderFormController {

    public BorderPane placeOrderPane;
    public Text lblDate;

    public void initialize(){
        runDate();
    }

    private void runDate() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")))
        ), new KeyFrame(Duration.hours(24)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
       Stage stage = (Stage) placeOrderPane.getScene().getWindow();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
       stage.setTitle("Dashboard");
       stage.show();
    }
}
