package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteProductViewController {
    @FXML
    private TextField ProductIdTextField;

    private Database database;

    private Product selectedProduct;

    @FXML
    private Label title;

    public DeleteProductViewController(Database db, Product selectedProduct) {
        this.database = db;
        this.selectedProduct = selectedProduct;
    }
    @FXML
    private void initialize(){
        System.out.println(selectedProduct);
        if(selectedProduct != null) {
            title.setText("Are you sure you want to delete Product " + selectedProduct.getProduct_name() + "?");
        }
    }

    @FXML
    private void onProductDelete(){
        int result = database.deleteProduct(selectedProduct);
        if(result == 1){
            System.out.println("Success");
            Stage stage = (Stage)title.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void onCancel(){
        Stage stage = (Stage)title.getScene().getWindow();
        stage.close();
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
