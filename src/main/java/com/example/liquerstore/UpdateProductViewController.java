package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProductViewController {
    @FXML
    private TextField ProductIdTextField, ProductNameTextField, CategoryTextField, StockTextField, PriceTextField;

    private Database database;

    private Product selectedProduct;


    public UpdateProductViewController(Database database, Product selectedProduct) {
        this.database = database;
        this.selectedProduct = selectedProduct;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedProduct);
        if(selectedProduct != null) {
            ProductIdTextField.setText(selectedProduct.getP_id() + "");
            ProductNameTextField.setText(selectedProduct.getProduct_name());
            PriceTextField.setText(selectedProduct.getPrice()+ "");
            CategoryTextField.setText(selectedProduct.getCategory());
            StockTextField.setText(selectedProduct.getStock()+"");
        }
    }



    @FXML
    private void onProductUpdated(){
        if((ProductIdTextField.getText().trim().length() > 0) && (ProductNameTextField.getText().trim().length() > 0) &&
        (CategoryTextField.getText().trim().length() > 0) && (StockTextField.getText().trim().length() > 0) &&
        (PriceTextField.getText().trim().length() > 0)) {
            int productId = Integer.parseInt(ProductIdTextField.getText().trim());
            String productName = ProductNameTextField.getText().trim();
            String category = CategoryTextField.getText().trim();
            int stock = Integer.parseInt(StockTextField.getText().trim());
            double price = Double.parseDouble(PriceTextField.getText().trim());
            Product product = new Product(productId, productName, category, stock, price);

            int result = database.updateProduct(product);
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
