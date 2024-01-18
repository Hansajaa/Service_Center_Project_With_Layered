package Controller;

import Bo.BoFactory;
import Bo.Custom.OrderBo;
import Bo.Custom.OrderDetailBo;
import Bo.Custom.ProductBo;
import Dao.Custom.OrderDetailDao;
import Dao.util.BoType;
import Dto.OrderDetailDto;
import Dto.OrderDto;
import Dto.ProductDto;
import Dto.Tm.OrdersTm;
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

public class OrderFormController {

    public BorderPane orderPane;
    public Text lblDate;
    public ComboBox cmbProducts;
    public JFXTextField txtProductId;
    public JFXTextField txtOrderId;
    public JFXTextField txtTotal;
    public JFXTextField txtPrice;
    public TableView<OrdersTm> tblOrder;
    public TableColumn colOrderId;
    public TableColumn colPlaceDate;
    public TableColumn colTotal;
    public TableColumn colBill;
    public TableColumn colSendAlert;
    public TableColumn colProduct;
    public JFXTextField txtServiceCharge;

    ProductBo productBo=BoFactory.getInstance().boType(BoType.ITEM);

    OrderBo orderBo=BoFactory.getInstance().boType(BoType.ORDER);
    List<ProductDto> allProducts = productBo.getAllProducts();

    OrderDetailBo orderDetailBo = BoFactory.getInstance().boType(BoType.ORDER_DETAIL);
    public void initialize(){
        loadProducts();
        runDate();
        loadOrderTable();
        cmbProducts.getSelectionModel().selectedItemProperty().addListener((observableValue, o, selectedProductName) -> {
            for (ProductDto dto:allProducts) {
                if (selectedProductName.equals(dto.getProductName())){
                    txtProductId.setEditable(false);
                    txtProductId.setText(dto.getProductId());
                }
            }
        });

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("mainItem"));
        colPlaceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colSendAlert.setCellValueFactory(new PropertyValueFactory<>("btnSendAlert"));
        colBill.setCellValueFactory(new PropertyValueFactory<>("btnBill"));

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, ordersTm, newRow) -> {
            if (newRow!=null){
                setData(newRow.getOrderId());
            }
        });
    }

    private void setData(String orderId) {
        OrderDto order = orderBo.getOrder(orderId);
        if (order!=null){
            txtOrderId.setEditable(false);
            txtOrderId.setText(order.getOrderId());

            txtTotal.setEditable(false);
            txtTotal.setText(String.valueOf(order.getTotal()));
        }
    }

    private void loadOrderTable() {
        List<OrderDto> allOrders = orderBo.getAllOrders();

        ObservableList<OrdersTm> ordersTms=FXCollections.observableArrayList();

        for (OrderDto dto:allOrders) {

            JFXButton btnSendAlert=new JFXButton("Send Alert");
            JFXButton btnBill=new JFXButton("Bill");
            btnSendAlert.setStyle("-fx-background-color:  #d93535; -fx-text-fill: white;");
            btnBill.setStyle("-fx-background-color:  #2A9D8F; -fx-text-fill: white;");

            ordersTms.add(
                    new OrdersTm(
                            dto.getOrderId(),
                            dto.getMainItem(),
                            dto.getDate(),
                            dto.getTotal(),
                            btnSendAlert,
                            btnBill
                    )
            );


        }

        tblOrder.setItems(ordersTms);
    }

    private void loadProducts() {
        ObservableList<String> productNames= FXCollections.observableArrayList();

        for (ProductDto dto:allProducts) {
            productNames.add(
                    dto.getProductName()
            );
        }

        cmbProducts.setItems(productNames);
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
        Stage stage = (Stage) orderPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.show();
    }

    public void addItemButtonOnAction(ActionEvent actionEvent) {
        if (txtOrderId.getText().isEmpty() || txtTotal.getText().isEmpty() || txtProductId.getText().isEmpty() || txtPrice.getText().isEmpty()){

            new Alert(Alert.AlertType.WARNING,"Enter All Details...").show();

        }else {
            boolean isSaved = orderDetailBo.saveItems(
                    new OrderDetailDto(
                            txtOrderId.getText(),
                            Double.parseDouble(txtTotal.getText()),
                            txtProductId.getText(),
                            Double.parseDouble(txtPrice.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Added").show();
                txtOrderId.setText("");
                txtTotal.setText("");
                txtProductId.setText("");
                txtPrice.setText("");
                loadOrderTable();
            }else {
                new Alert(Alert.AlertType.WARNING,"Something went wrong...Try Again...").show();
            }
        }
    }

    public void btnServiceCharge(ActionEvent actionEvent) {
        if (txtOrderId.getText().isEmpty() || txtTotal.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Select an Order...").show();
        } else if (txtServiceCharge.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Enter Service Charge...").show();
        }else {
            OrderDto dto=new OrderDto();
            dto.setOrderId(txtOrderId.getText());
            dto.setServiceCharge(Double.parseDouble(txtServiceCharge.getText()));

            boolean isAdded = orderBo.addServiceCharge(dto);

            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Service Charge Added...").show();
                loadOrderTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Service Charge Not Added...Try Again").show();
            }
        }
    }
}
