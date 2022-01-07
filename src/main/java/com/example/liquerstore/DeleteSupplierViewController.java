package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteSupplierViewController {

    private Database database;

    private Supplier selectedSupplier;

    @FXML
    private Label title;

    public DeleteSupplierViewController(Database database, Supplier selectedSupplier) {
        this.database = database;
        this.selectedSupplier = selectedSupplier;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedSupplier);
        if(selectedSupplier != null) {
            title.setText("Are you sure you want to delete Supplier " + selectedSupplier.getCompany_name() + "?");
        }
    }

    @FXML
    private void onSupplierDelete(){
        int companyId = selectedSupplier.getCompany_id();

        int result = database.deleteSupplier(selectedSupplier);
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
}
