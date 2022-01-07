package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteReceiptViewController {
    @FXML
    private TextField BarcodeTextField;

    private Database database;

    private Receipt selectedReceipt;

    @FXML
    private Label title;

    public DeleteReceiptViewController(Database database, Receipt selectedReceipt) {
        this.database = database;
        this.selectedReceipt = selectedReceipt;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedReceipt);
        if(selectedReceipt != null) {
            title.setText("Are you sure you want to delete receipt with barcode: " + selectedReceipt.getBarcode() + "?");
        }
    }


    @FXML
    private void onReceiptDelete(){
        int result = database.deleteReceipt(selectedReceipt);
        if(result == 1) {
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
