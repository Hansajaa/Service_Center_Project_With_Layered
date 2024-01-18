package Controller;

import Bo.BoFactory;
import Bo.Custom.ProductBo;
import Dao.util.BoType;
import Dto.ProductDto;
import Dto.Tm.ProductTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductFormController {

    public BorderPane productPane;
    public ToggleGroup category;
    public JFXTextField txtProductName;
    public RadioButton electronicRButton;
    public RadioButton electricalRButton;
    public JFXTextField txtEmailOfUser;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colCategory;
    public TableColumn colOption;
    public TableView<ProductTm> tblProduct;
    public Text lblNextProductId;
    public Text lblSelectedProductId;

    ProductBo bo= BoFactory.getInstance().boType(BoType.ITEM);
    public void initialize(){
        generateProductId();

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadProductTable();

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }
        });
    }

    private void setData(ProductTm newValue) {
        if (newValue!=null){
            txtProductName.setText(newValue.getProductName());
            txtEmailOfUser.setText(newValue.getEmailOfUser());
            lblSelectedProductId.setText(newValue.getProductId());
            if (newValue.getCategory().equals("electronic")){
                electronicRButton.setSelected(true);
            }else {
                electricalRButton.setSelected(true);
            }
        }
    }



    private void loadProductTable() {
        ObservableList<ProductTm> productTms= FXCollections.observableArrayList();

        List<ProductDto> allProducts = bo.getAllProducts();

        for (ProductDto dto:allProducts) {
            JFXButton btn=new JFXButton("Delete");
            ProductTm tm = new ProductTm(
                    dto.getProductId(),
                    dto.getProductName(),
                    dto.getCategory(),
                    dto.getEmailOfUser(),
                    btn
            );
            btn.setStyle("-fx-background-color: #d93535; -fx-text-fill: white;");

            btn.setOnAction(ActionEvent->{
                deleteProduct(tm.getProductId());
            });

            productTms.add(tm);
        }

        tblProduct.setItems(productTms);

    }

    private void deleteProduct(String productId) {
        boolean isDeleted = bo.deleteProduct(productId);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Product Delete Successfuly").show();
            generateProductId();
            loadProductTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again").show();
        }
    }

    private void generateProductId(){
        try {
            String lastId = bo.getLastId();
            if (lastId!=null){
                lblNextProductId.setText(lastId);
            }else {
                lblNextProductId.setText("P001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) productPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"))));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
        if (txtProductName.getText()!=null && txtEmailOfUser.getText()!=null && (electricalRButton.isSelected() || electronicRButton.isSelected()) ){
            String category=null;

            if (electronicRButton.isSelected()){
                category="electronic";
            }else {
                category="electrical";
            }
            boolean isSaved = bo.productSave(
                    new ProductDto(
                            lblNextProductId.getText(),
                            txtProductName.getText(),
                            category,
                            txtEmailOfUser.getText()
                    )
            );

            if (isSaved){
                loadProductTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Product Saved : )").show();
                txtProductName.setText("");
                txtEmailOfUser.setText("");
                electronicRButton.setSelected(false);
                electricalRButton.setSelected(false);
                generateProductId();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product Not Saved : (").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Enter All Details...").show();
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try{
            if (txtProductName.getText()!=null && txtEmailOfUser.getText()!=null && (electricalRButton.isSelected() || electronicRButton.isSelected())){
                String category=null;
                if (electronicRButton.isSelected()){
                    category="electronic";
                }else {
                    category="electrical";
                }
                boolean isUpdated = bo.updateProduct(
                        new ProductDto(
                                lblSelectedProductId.getText(),
                                txtProductName.getText(),
                                category,
                                txtEmailOfUser.getText()
                        )
                );

                if (isUpdated){
                    loadProductTable();
                    new Alert(Alert.AlertType.CONFIRMATION,"Product Updated : )").show();
                    txtProductName.setText("");
                    txtEmailOfUser.setText("");
                    electricalRButton.setSelected(false);
                    electronicRButton.setSelected(false);

                    lblSelectedProductId.setText("P000");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Product Not Updated : (").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"Enter All Details...").show();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.WARNING,"Something went wrong...Try Again..").show();
        }
    }
}
