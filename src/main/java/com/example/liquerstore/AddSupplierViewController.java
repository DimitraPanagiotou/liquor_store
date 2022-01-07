package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSupplierViewController {
    @FXML
    private TextField companyIdTextField, companyNameTextField, productIdTextField, priceTextField;

    private Database database;


    @FXML
    private void onSupplierAdded(){
        //TODO add check for the rest
        if(companyIdTextField.getText().trim().length() > 0) {
            int companyId = Integer.parseInt(companyIdTextField.getText().trim());
            String companyName = companyNameTextField.getText().trim();
            int productId = Integer.parseInt(productIdTextField.getText().trim());
            double price = Double.parseDouble(priceTextField.getText().trim());
            Supplier supplier = new Supplier(companyId, companyName, productId, price);

            int result = database.insertSupplier(supplier);
            if(result == 1){
                System.out.println("Success");
                Stage stage = (Stage)companyIdTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
