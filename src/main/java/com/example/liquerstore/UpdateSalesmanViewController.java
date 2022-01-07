package com.example.liquerstore;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateSalesmanViewController {
    @FXML
    private TextField SalesmanIdTextField, NameTextField, SurnameTextField, AgeTextField, PhoneTextField;

    private Database database;

    private Salesman selectedSalesman;


    public UpdateSalesmanViewController(Database database, Salesman selectedSalesman) {
        this.database = database;
        this.selectedSalesman = selectedSalesman;
    }

    @FXML
    private void initialize(){
        if(selectedSalesman != null) {
            SalesmanIdTextField.setText(selectedSalesman.getS_id() + "");
            NameTextField.setText(selectedSalesman.getName());
            SurnameTextField.setText(selectedSalesman.getSurname());
            AgeTextField.setText(selectedSalesman.getAge()+"");
            PhoneTextField.setText(selectedSalesman.getPhone());
        }
    }



    @FXML
    private void onSalesmanUpdated(){
        if((SalesmanIdTextField.getText().trim().length() > 0) && ( NameTextField.getText().trim().length() > 0) &&
        (SurnameTextField.getText().trim().length() > 0) && (AgeTextField.getText().trim().length() > 0) &&
        (PhoneTextField.getText().trim().length() > 0)) {
            int sId = Integer.parseInt(SalesmanIdTextField.getText().trim());
            String name = NameTextField.getText().trim();
            String surname = SurnameTextField.getText().trim();
            int age = Integer.parseInt(AgeTextField.getText().trim());
            String phone = PhoneTextField.getText().trim();
            Salesman salesman = new Salesman(sId, name, surname, age, phone);

            int result = database.updateSalesman(salesman);
            if(result == 1){
                System.out.println("Success");
                Stage stage = (Stage)SalesmanIdTextField.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
