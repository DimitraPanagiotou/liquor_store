package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class AddReceiptViewController {
    @FXML
    private TextField BarcodeTextField, SalesmanIdTextField, ProductIdTextField, QuantityTextField, TotalValueTextField, DateTextField;

    private Database database;


    @FXML
    private void onReceiptAdded(){
        if((BarcodeTextField.getText().trim().length() > 0) && (SalesmanIdTextField.getText().trim().length() > 0) &&
        (ProductIdTextField.getText().trim().length() > 0) && (QuantityTextField.getText().trim().length() > 0) &&
        (TotalValueTextField.getText().trim().length() > 0) && (DateTextField.getText().trim().length() > 0)) {
            int barcode = Integer.parseInt(BarcodeTextField.getText().trim());
            int s_id = Integer.parseInt(SalesmanIdTextField.getText().trim());
            int p_id = Integer.parseInt(ProductIdTextField.getText().trim());
            int quantity = Integer.parseInt(QuantityTextField.getText().trim());
            double total_value = Double.parseDouble(TotalValueTextField.getText().trim());
            Date date = Date.valueOf(DateTextField.getText());
            Receipt receipt = new Receipt(barcode,s_id ,p_id ,date ,quantity ,total_value );

            int result = database.insertReceipt(receipt);
            if(result == 1){
                System.out.println("Success");
                Stage stage = (Stage)BarcodeTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
