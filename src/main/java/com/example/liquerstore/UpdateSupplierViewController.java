package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateSupplierViewController {

    @FXML
    private TextField companyIdTextField, companyNameTextField, productIdTextField, priceTextField;

    private Database database;

    private Supplier selectedSupplier;


    public UpdateSupplierViewController(Database database, Supplier selectedSupplier) {
        this.database = database;
        this.selectedSupplier = selectedSupplier;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedSupplier);
        if(selectedSupplier != null) {
            companyIdTextField.setText(selectedSupplier.getCompany_id() + "");
            companyNameTextField.setText(selectedSupplier.getCompany_name());
            productIdTextField.setText(selectedSupplier.getP_id() + "");
            priceTextField.setText(selectedSupplier.getPrice() + "");
        }
    }

    @FXML
    private void onSupplierUpdated(){
        //TODO add check for the rest
        if(companyIdTextField.getText().trim().length() > 0) {
            int companyId = Integer.parseInt(companyIdTextField.getText().trim());
            String companyName = companyNameTextField.getText().trim();
            int productId = Integer.parseInt(productIdTextField.getText().trim());
            double price = Double.parseDouble(priceTextField.getText().trim());
            Supplier supplier = new Supplier(companyId, companyName, productId, price);

            int result = database.updateSupplier(supplier);
            if(result == 1){
                System.out.println("Success");
                Stage stage = (Stage)companyIdTextField.getScene().getWindow();
                stage.close();
            }
        }
    }
}
