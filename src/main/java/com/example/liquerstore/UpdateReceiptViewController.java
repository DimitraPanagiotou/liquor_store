package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class UpdateReceiptViewController {
    @FXML
    private TextField BarcodeTextField, SalesmanIdTextField, ProductIdTextField, QuantityTextField, TotalValueTextField, DateTextField;

    private Database database;

    private Receipt selectedReceipt;


    public UpdateReceiptViewController(Database database, Receipt selectedReceipt) {
        this.database = database;
        this.selectedReceipt = selectedReceipt;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedReceipt);
        if(selectedReceipt != null) {
            BarcodeTextField.setText(selectedReceipt.getBarcode()+"");
            ProductIdTextField.setText(selectedReceipt.getReceipt_p_id()+"");
            SalesmanIdTextField.setText(selectedReceipt.getS_id()+"");
            DateTextField.setText(selectedReceipt.getDate()+"");
            QuantityTextField.setText(selectedReceipt.getQuantity()+"");
            TotalValueTextField.setText(selectedReceipt.getTotal_value()+"");
        }
    }


    @FXML
    private void onReceiptUpdated(){
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

            int result = database.updateReceipt(receipt);
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
