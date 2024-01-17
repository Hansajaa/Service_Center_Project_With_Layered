package Controller;

import Bo.BoFactory;
import Bo.Custom.OrderBo;
import Bo.Custom.ProductBo;
import Dao.Custom.OrderDao;
import Dao.DaoFactory;
import Dao.util.BoType;
import Dao.util.DaoType;
import Dto.OrderDto;
import Dto.ProductDto;
import Entity.ProductEntity;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlaceOrderFormController {

    public BorderPane placeOrderPane;
    public Text lblDate;
    public Text lblOrderId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtCustomerContactNumber;
    public ComboBox cmbProduct;
    public TextArea txtDescription;
    public JFXTextField txtEmailOfUser;
    public JFXTextField txtProductId;
    public JFXRadioButton electronicRdBtn;
    public JFXRadioButton electricalRdBtn;

    OrderBo orderBo = BoFactory.getInstance().boType(BoType.ORDER);

    ProductBo productBo=BoFactory.getInstance().boType(BoType.ITEM);
    List<ProductDto> products;

    public void initialize(){
        generateOrderId();
        runDate();

        loadProducts();

        cmbProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, o, selectedName) -> {
            for (ProductDto product:products) {
                if (product.getProductName().equals(selectedName)){
                    txtProductId.setText(product.getProductId());
                }
            }
        });
    }

    private void loadProducts() {
        products = productBo.getAllProducts();
    }

    private void generateOrderId(){
        String lastOrderId = null;
        try {
            lastOrderId = orderBo.getLastOrderId();
            if (lastOrderId!=null){
                lblOrderId.setText(lastOrderId);
            }else {
                lblOrderId.setText("ORD001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void electronicCategoryOnAction(ActionEvent actionEvent) {
        ObservableList<String> electronicProducts= FXCollections.observableArrayList();

        for (ProductDto product:products) {
            if (product.getCategory().equals("electronic")){
                electronicProducts.add(product.getProductName());
            }
        }

        cmbProduct.setItems(electronicProducts);
    }

    public void electricalCategoryOnAction(ActionEvent actionEvent) {
        ObservableList<String> electricalProducts= FXCollections.observableArrayList();

        for (ProductDto product:products) {
            if (product.getCategory().equals("electrical")){
                electricalProducts.add(product.getProductName());
            }
        }

        cmbProduct.setItems(electricalProducts);
    }

    public void placeOrderButtonOnAction(ActionEvent actionEvent){
        if (txtCustomerName.getText().isEmpty() || txtCustomerEmail.getText().isEmpty() || txtCustomerContactNumber.getText().isEmpty() || txtEmailOfUser.getText().isEmpty() || (electricalRdBtn.isDisable() && electronicRdBtn.isDisable()) || txtProductId.getText().isEmpty() || txtDescription.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Fill All Details...").show();
        }else {
            String category=null;

            if (electronicRdBtn.isSelected()){
                category="electronic";
            }else {
                category="electrical";
            }

            String selectedProduct = (String) cmbProduct.getValue();

            boolean isSaved = orderBo.saveOrder(
                    new OrderDto(
                            lblOrderId.getText(),
                            txtCustomerContactNumber.getText(),
                            txtCustomerEmail.getText(),
                            txtCustomerName.getText(),
                            category,
                            selectedProduct,
                            txtDescription.getText(),
                            txtEmailOfUser.getText(),
                            lblDate.getText(),
                            "Pending",
                            "None",
                            0.0
                    )
            );

            if (isSaved){
                generateOrderId();
                txtCustomerName.setText("");
                txtCustomerContactNumber.setText("");
                txtCustomerEmail.setText("");
                txtEmailOfUser.setText("");
                txtProductId.setText("");
                txtDescription.setText("");
                electronicRdBtn.setSelected(false);
                electricalRdBtn.setSelected(false);
                new Alert(Alert.AlertType.CONFIRMATION,"Order Saved Successfully : )").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Something went wrong...Try Again...").show();
            }

        }
    }
}
