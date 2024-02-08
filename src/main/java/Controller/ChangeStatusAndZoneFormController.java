package Controller;

import Bo.BoFactory;
import Bo.Custom.ChangeStatusAndZoneBo;
import Bo.Custom.OrderBo;
import Dao.util.BoType;
import Dto.ChangeStatusAndZoneDto;
import Dto.OrderDto;
import Dto.Tm.ChangeStatusAndZoneTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChangeStatusAndZoneFormController {

    public BorderPane changeZonePane;
    public Text lblDate;
    public ComboBox cmbStatus;
    public ComboBox cmbZone;
    public JFXTextField txtOrderId;
    public TableView<ChangeStatusAndZoneTm> tblOrder;
    public TableColumn colOrderId;
    public TableColumn colStatus;
    public TableColumn colZone;
    public TableColumn colPlaceDate;
    public TableColumn colSendAlert;
    public TableColumn colBill;

    ChangeStatusAndZoneBo bo= BoFactory.getInstance().boType(BoType.CHANGE_STATUS_ZONE);

    OrderBo orderBo=BoFactory.getInstance().boType(BoType.ORDER);

    public void initialize(){
        runDate();
        loadStatus();
        loadZones();

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPlaceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colZone.setCellValueFactory(new PropertyValueFactory<>("zone"));
        colSendAlert.setCellValueFactory(new PropertyValueFactory<>("btnSendAlert"));
        colBill.setCellValueFactory(new PropertyValueFactory<>("btnBill"));

        loadOrders();

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, changeStatusAndZoneTm, newOrder) -> {
            if (newOrder!=null){
                setData(newOrder);
            }
        });
    }

    private void setData(ChangeStatusAndZoneTm newOrder) {
        txtOrderId.setEditable(false);
        txtOrderId.setText(newOrder.getOrderId());
    }

    private void loadOrders() {
        List<OrderDto> allOrders = orderBo.getAllOrders();

        ObservableList<ChangeStatusAndZoneTm> tms=FXCollections.observableArrayList();

        for (OrderDto dto:allOrders) {
            JFXButton btnSendAlert=new JFXButton("Send Alert");
            JFXButton btnBill=new JFXButton("Bill");
            btnSendAlert.setStyle("-fx-background-color:  #d93535; -fx-text-fill: white;");
            btnBill.setStyle("-fx-background-color:  #2A9D8F; -fx-text-fill: white;");

            btnSendAlert.setOnAction(actionEvent -> {
                sendAlert(dto.getOrderId());
            });

            btnBill.setOnAction(ActionEvent->{
                sendBill(dto.getOrderId());
            });
            tms.add(
                    new ChangeStatusAndZoneTm(
                            dto.getOrderId(),
                            dto.getDate(),
                            dto.getStatus(),
                            dto.getZone(),
                            btnSendAlert,
                            btnBill
                    )
            );
        }

        tblOrder.setItems(tms);
    }

    private void sendBill(String orderId) {
        boolean isBillSend = bo.sendBill(orderId);
        if (isBillSend){
            new Alert(Alert.AlertType.CONFIRMATION,"Bill Send Successfully :)").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong :(").show();
        }
    }

    private void sendAlert(String orderId) {
        boolean isAlertSend = bo.sendAlert(orderId);

        if (isAlertSend){
            new Alert(Alert.AlertType.CONFIRMATION,"Alert Send Successfully :)").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong :(").show();
        }
    }

    private void loadZones() {
        ObservableList<String> zones= FXCollections.observableArrayList();

        List<String> zoneList = bo.getZones();

        for (String zone:zoneList) {
            zones.add(zone);
        }

        cmbZone.setItems(zones);
    }

    private void loadStatus() {
        ObservableList<String> statuses= FXCollections.observableArrayList();

        List<String> statusList = bo.getStatuses();

        for (String status:statusList) {
            statuses.add(status);
        }

        cmbStatus.setItems(statuses);
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
        Stage stage = (Stage) changeZonePane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        if (txtOrderId.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Select an Order...").show();
        } else if (cmbStatus.getSelectionModel().isEmpty() || cmbZone.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Select Status and Zone...").show();
        } else {
            OrderDto order = orderBo.getOrder(txtOrderId.getText());

            if (order.getStatus().toLowerCase().equals("closed")){
                new Alert(Alert.AlertType.ERROR,"This Order is already closed...").show();
            }else {
                String status = (String) cmbStatus.getValue();
                String zone = (String) cmbZone.getValue();

                boolean isUpdated = bo.update(
                        new ChangeStatusAndZoneDto(
                                txtOrderId.getText(),
                                status,
                                zone
                        )
                );

                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated : )").show();
                    txtOrderId.setText("");

                    loadOrders();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Not Updated...Try Again...").show();
                }
            }
        }
    }
}
