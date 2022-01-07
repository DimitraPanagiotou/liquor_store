package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductViewController {
    @FXML
    private TextField ProductIdTextField, ProductNameTextField, CategoryTextField, StockTextField, PriceTextField;

    private Database database;


    @FXML
    private void onProductAdded(){
        if((ProductIdTextField.getText().trim().length() > 0) && (ProductNameTextField.getText().trim().length() > 0) &&
        (CategoryTextField.getText().trim().length() > 0) && (StockTextField.getText().trim().length() > 0) &&
        (PriceTextField.getText().trim().length() > 0)) {
            int productId = Integer.parseInt(ProductIdTextField.getText().trim());
            String productName = ProductNameTextField.getText().trim();
            String category = CategoryTextField.getText().trim();
            int stock = Integer.parseInt(StockTextField.getText().trim());
            double price = Double.parseDouble(PriceTextField.getText().trim());
            Product product = new Product(productId, productName, category, stock, price);

            int result = database.insertProduct(product);
            if(result == 1){
                System.out.println("Success");
                Stage stage = (Stage)ProductIdTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
