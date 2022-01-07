package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteSalesmanViewController {
    @FXML
    private TextField SalesmanIdTextField;

    private Database database;

    private Salesman selectedSalesman;

    @FXML
    private Label title;

    public DeleteSalesmanViewController(Database database, Salesman selectedSalesman) {
        this.database = database;
        this.selectedSalesman = selectedSalesman;
    }

    @FXML
    private void initialize(){
        System.out.println(selectedSalesman);
        if(selectedSalesman != null) {
            title.setText("Are you sure you want to delete Salesman " + selectedSalesman.getName() + " " + selectedSalesman.getSurname() + "?");
        }
    }


    @FXML
    private void onSalesmanDelete(){
        int result = database.deleteSalesman(selectedSalesman);
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
