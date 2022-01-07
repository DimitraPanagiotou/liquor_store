package com.example.liquerstore;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

/* This class handles every interaction with the main javafx window hello-view.fxml. Hello-view is */
/* the first opening window that consists of 5 tabs Suppliers, Salesmen, Products, Receipts and    */
/* Statistics. The first 4 tabs enable basic interactions with the database such as addition,      */
/* deleteion, update and search by all their attributes. The statistics tab performs more complex  */
/* queries that aim to present the overall image of the company and how well it functions          */
public class HelloController {
    private Database db;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private TableColumn<Supplier, Integer> CompanyId;

    @FXML
    private TableColumn<Supplier, String> CompanyName;

    @FXML
    private TableColumn<Supplier, Integer> ProductId;

    @FXML
    private TableColumn<Supplier, Float> Price;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> PProductId;

    @FXML
    private TableColumn<Product, String> PProductName;

    @FXML
    private TableColumn<Product, String> PCategory;

    @FXML
    private TableColumn<Product, Integer> PStock;

    @FXML
    private TableColumn<Product, Double> PPrice;

    @FXML
    private TableView<Salesman> salesmanTable;

    @FXML
    private TableColumn<Salesman, Integer> SSalesmanId, SAge;

    @FXML
    private TableColumn<Salesman, String> SName, SSurname;

    @FXML
    private TableColumn<Product, String> SPhone;

    @FXML
    private TableView<Receipt> receiptTable;

    @FXML
    private TableColumn<Receipt, Integer> RBarcode;

    @FXML
    private TableColumn<Receipt, Integer> RSalesmanId;

    @FXML
    private TableColumn<Receipt, Integer> RProductId;

    @FXML
    private TableColumn<Receipt, Date> RDate;

    @FXML
    private TableColumn<Receipt, Integer> RQuantity;

    @FXML
    private TableColumn<Receipt, Double> RTotal_value;

    @FXML
    private Label topRevenueLabel, maxCountReceiptsLabel, TopSellerByRecNameSurname, topSalesProductByCategoryValue, topSalesProductByCategoryName, topSalesProductValue, topSalesProductName, topSellerByRevenueName, totalRevenueValue;

    @FXML
    private Button refreshSuppliersBtn, addSuppliersBtn, deleteSuppliersBtn, editSuppliersBtn;

    @FXML
    private Button refreshSalesmenBtn, addSalesmenBtn, deleteSalesmenBtn, editSalesmenBtn;

    @FXML
    private Button refreshProductsBtn, addProductsBtn, deleteProductsBtn, editProductsBtn;

    @FXML
    private Button refreshReceiptsBtn, addReceiptsBtn, deleteReceiptsBtn, editReceiptsBtn;

    @FXML
    private TextField welcomeTextField;

    private Supplier selectedSupplier;

    private Salesman selectedSalesman;

    private Product selectedProduct;

    private Receipt selectedReceipt;

    @FXML
    private HBox ageBox, stockBox;

    @FXML
    private MenuButton salesmanSearchByMenu, ageMenuButton, productSearchByMenu, stockMenuButton, supplierSearchByMenu;

    @FXML
    private TextField genericSalesmanSearchField, ageTextField, genericProductSearchField, stockTextField, genericSupplierSearchField, topProductCategoryField;

    private String selectedSalesmanSearchByItem = "SalesMan Id";
    private String selectedSalesmanAgeComparator = "=";
    private String selectedProductSearchByItem = "Product Id";
    private String selectedProductStockComparator = "=";
    private String selectedSupplierSearchByItem = "Company Id";

    /* the function below initialises every menu box and menuItems to change in accordance with user's selections*/
    @FXML
    public void initialize_menu_boxes() {
        for (MenuItem searchByItem : ageMenuButton.getItems()) {
            searchByItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedSalesmanAgeComparator = ((MenuItem) event.getSource()).getText();
                    ageMenuButton.setText(selectedSalesmanAgeComparator);
                }
            });
        }

        for (MenuItem searchByItem : stockMenuButton.getItems()) {
            searchByItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedProductStockComparator = ((MenuItem) event.getSource()).getText();
                    stockMenuButton.setText(selectedProductStockComparator);
                }
            });
        }

        for (MenuItem searchByItem : salesmanSearchByMenu.getItems()) {
            searchByItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedSalesmanSearchByItem = ((MenuItem) event.getSource()).getText();
                    salesmanSearchByMenu.setText(selectedSalesmanSearchByItem);
                    genericSalesmanSearchField.setPromptText(selectedSalesmanSearchByItem);
                    if (selectedSalesmanSearchByItem.equals("Age")) {
                        genericSalesmanSearchField.setVisible(false);
                        ageBox.setVisible(true);
                    } else {
                        if (ageBox.isVisible()) {
                            ageBox.setVisible(false);
                            genericSalesmanSearchField.setVisible(true);
                        }
                    }
                }
            });
        }

        for (MenuItem searchByItem : productSearchByMenu.getItems()) {
            searchByItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedProductSearchByItem = ((MenuItem) event.getSource()).getText();
                    productSearchByMenu.setText(selectedProductSearchByItem);
                    genericProductSearchField.setPromptText(selectedProductSearchByItem);
                    if (selectedProductSearchByItem.equals("Stock")) {
                        genericProductSearchField.setVisible(false);
                        stockBox.setVisible(true);
                    } else {
                        if (stockBox.isVisible()) {
                            stockBox.setVisible(false);
                            genericProductSearchField.setVisible(true);
                        }
                    }
                }
            });
        }

        for (MenuItem searchByItem : supplierSearchByMenu.getItems()) {
            searchByItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedSupplierSearchByItem = ((MenuItem) event.getSource()).getText();
                    supplierSearchByMenu.setText(selectedSupplierSearchByItem);
                    genericSupplierSearchField.setPromptText(selectedSupplierSearchByItem);
                }
            });
        }
    }


    /* Initialisation of table view and its columns to present database's tables. */
    /* Instantiations of statistics tab functions */
    /* Instantiations of database class which handles interactions with the database */
    @FXML
    public void initialize() {
        initialize_menu_boxes();

        db = new Database();
        refreshProductStatistics();
        refreshTotalRevenue();
        //assert supplierTable != null : "fx:id=\"supplierTable\" was not injected: check your FXML file 'UserMaster.fxml'.";
        CompanyId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("company_id"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("company_name"));
        ProductId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("p_id"));
        Price.setCellValueFactory(new PropertyValueFactory<Supplier, Float>("price"));

        PProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("p_id"));
        PProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("product_name"));
        PCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        PStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        PPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        SSalesmanId.setCellValueFactory(new PropertyValueFactory<Salesman, Integer>("s_id"));
        SName.setCellValueFactory(new PropertyValueFactory<Salesman, String>("name"));
        SSurname.setCellValueFactory(new PropertyValueFactory<Salesman, String>("surname"));
        SAge.setCellValueFactory(new PropertyValueFactory<Salesman, Integer>("age"));
        SPhone.setCellValueFactory(new PropertyValueFactory<Product, String>("phone"));

        RBarcode.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("barcode"));
        RSalesmanId.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("s_id"));
        RProductId.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("receipt_p_id"));
        RDate.setCellValueFactory(new PropertyValueFactory<Receipt, Date>("date"));
        RQuantity.setCellValueFactory(new PropertyValueFactory<Receipt, Integer>("quantity"));
        RTotal_value.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("total_value"));


        supplierTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
                selectedSupplier = observableValue.getValue();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        editSuppliersBtn.setDisable(false);
                        deleteSuppliersBtn.setDisable(false);
                    }
                });
            }
        });

        onRefreshSuppliersTableClicked();

        salesmanTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Salesman>() {
            @Override
            public void changed(ObservableValue<? extends Salesman> observableValue, Salesman salesman, Salesman t1) {
                selectedSalesman = observableValue.getValue();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        editSalesmenBtn.setDisable(false);
                        deleteSalesmenBtn.setDisable(false);
                    }
                });
            }
        });

        onRefreshSalesmenTableClicked();

        productTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                selectedProduct = observableValue.getValue();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        editProductsBtn.setDisable(false);
                        deleteProductsBtn.setDisable(false);
                    }
                });
            }
        });

        onRefreshProductsTableClicked();

        receiptTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Receipt>() {
            @Override
            public void changed(ObservableValue<? extends Receipt> observableValue, Receipt receipt, Receipt t1) {
                selectedReceipt = observableValue.getValue();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        editReceiptsBtn.setDisable(false);
                        deleteReceiptsBtn.setDisable(false);
                    }
                });
            }
        });

        onRefreshReceiptTableClicked();
    }

    /* SUPPLIERS BUTTONS */
    @FXML
    protected void onAddSupplierClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("add-supplier-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.setTitle("Add New Supplier");
            stage.setScene(scene);
            stage.show();
            AddSupplierViewController addSupplierViewController = fxmlLoader.getController();
            addSupplierViewController.setDatabase(db);
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onUpdateSupplierClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("update-supplier-view.fxml"));

            UpdateSupplierViewController updateSupplierViewController = new UpdateSupplierViewController(db, selectedSupplier);
            fxmlLoader.setController(updateSupplierViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.setTitle("Update Supplier");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteSupplierClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("delete-supplier-view.fxml"));

            DeleteSupplierViewController deleteSupplierViewController = new DeleteSupplierViewController(db, selectedSupplier);
            fxmlLoader.setController(deleteSupplierViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 189);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.setResizable(false);
            stage.setTitle("Delete Supplier");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Failed to open. Reason: "+e.getMessage());
        }
    }

    /* PRODUCT BUTTONS */
    @FXML
    protected void onUpdateProductClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("update-product-view.fxml"));

            UpdateProductViewController updateProductViewController = new UpdateProductViewController(db, selectedProduct);
            fxmlLoader.setController(updateProductViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.setTitle("Update Product");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onAddProductClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("add-product-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            Stage stage = new Stage();
            stage.setTitle("Add New Product");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
            AddProductViewController addProductViewController = fxmlLoader.getController();
            addProductViewController.setDatabase(db);
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteProductClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("delete-product-view.fxml"));

            DeleteProductViewController deleteProductViewController = new DeleteProductViewController(db, selectedProduct);
            fxmlLoader.setController(deleteProductViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 189);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.setTitle("Delete Product");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    /* SALESMEN BUTTONS */
    @FXML
    protected void onUpdateSalesmanClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("update-salesman-view.fxml"));

            UpdateSalesmanViewController updateSalesmanViewController = new UpdateSalesmanViewController(db, selectedSalesman);
            fxmlLoader.setController(updateSalesmanViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            Stage stage = new Stage();
            stage.setTitle("Update Salesman");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onAddSalesmanClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("add-salesman-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            Stage stage = new Stage();
            stage.setTitle("Add New Salesman");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
            AddSalesmanViewController addSalesmanViewController = fxmlLoader.getController();
            addSalesmanViewController.setDatabase(db);
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteSalesmanClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("delete-salesman-view.fxml"));

            DeleteSalesmanViewController deleteSalesmanViewController = new DeleteSalesmanViewController(db, selectedSalesman);
            fxmlLoader.setController(deleteSalesmanViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 189);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.setTitle("Delete Salesman");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    /* RECEIPTS BUTTONS */
    @FXML
    protected void onUpdateReceiptClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("update-receipt-view.fxml"));
            UpdateReceiptViewController updateReceiptViewController = new UpdateReceiptViewController(db, selectedReceipt);
            fxmlLoader.setController(updateReceiptViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.setTitle("Update Receipt");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onAddReceiptClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("add-receipt-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 352, 400);
            Stage stage = new Stage();
            stage.setTitle("Add New Receipt");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
            AddReceiptViewController addReceiptViewController = fxmlLoader.getController();
            addReceiptViewController.setDatabase(db);
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteReceiptClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("delete-receipt-view.fxml"));
            DeleteReceiptViewController deleteReceiptViewController = new DeleteReceiptViewController(db, selectedReceipt);
            fxmlLoader.setController(deleteReceiptViewController);

            Scene scene = new Scene(fxmlLoader.load(), 352, 189);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            Stage stage = new Stage();
            stage.setTitle("Delete Receipt");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tabPane.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open. Reason: " + e.getMessage());
        }
    }

    /* REFRESH OR SHOW ALL BUTTONS MANIPULATIONS */
    @FXML
    protected void onRefreshSuppliersTableClicked() {
        ObservableList<Supplier> supplierList;
        supplierList = db.displaySupplier();
        supplierTable.setItems(supplierList);
    }

    @FXML
    protected void onRefreshSalesmenTableClicked() {
        ObservableList<Salesman> salesmenList;
        salesmenList = db.displaySalesman();
        salesmanTable.setItems(salesmenList);
    }

    @FXML
    protected void onRefreshProductsTableClicked() {
        ObservableList<Product> productList;
        productList = db.displayProduct();
        productTable.setItems(productList);
    }

    @FXML
    protected void onRefreshReceiptTableClicked() {
        ObservableList<Receipt> receiptList;
        receiptList = db.displayReceipt();
        receiptTable.setItems(receiptList);
    }

    /* Statistics' Function */
    /* Tab statistics offers information relevant with the performance of the liquer store that */
    /* respond to complex queries to the database. More specifically statistics tab offer info  */
    /* relevant with top seller, best seller product and total revenue. This info is presented  */
    /* by 5 cards. */
    @FXML
    protected void onTabSelectionChanged(Event event) {
        Tab selectedTab = (Tab) event.getTarget();

        if (selectedTab.getText().equals("Statistics") && selectedTab.isSelected()) {
            ArrayList<String> result;
            refreshProductStatistics();
            refreshTotalRevenue();
            result = db.topSellerbyRevenue();

            topRevenueLabel.setText(db.getTopRevenue() + " euro");

            VBox vBox = new VBox();
            for (String seller : result) {
                Label sellerLabel = new Label(seller);
                vBox.getChildren().add(sellerLabel);
            }
            Tooltip tooltip = new Tooltip();
            tooltip.setGraphic(vBox);

            if (result.size() == 1) {
                topSellerByRevenueName.setText(result.get(0));
            } else {
                topSellerByRevenueName.setTooltip(tooltip);
                topSellerByRevenueName.setText(result.get(0) + "\nand " + (result.size() - 1) + " more");
            }


            String result_rec;
            result_rec = db.topSellerbyReceiptCount();

            maxCountReceiptsLabel.setText(db.getmaxCountReceipts() + " receipts");
            TopSellerByRecNameSurname.setText(result_rec);

            refreshProductStatistics();
        }
    }

    private void refreshTotalRevenue(){
        totalRevenueValue.setText(db.getTotalRevenue()+" Euro");
    }

    @FXML
    private void onFindTopProductByCategory() {
        if (topProductCategoryField.getText().trim().length() > 0) {
            refreshTopProductByCategory(topProductCategoryField.getText());
        }
    }

    /* SEARCH FIELDS FOR SALESMAN, SUPPLIER AND PRODUCT */
    /* Search for attributes like age or price is implemented with HBox that consists of a label */
    /* a menu box( to select the comparator ) and an input field to specify the value of intrest */
    @FXML
    private void onSalesmanSearch() {
        ObservableList<Salesman> salesmanList;
        int salesmanID, age;
        String phone, name, surname;

        if (selectedSalesmanSearchByItem.equals("Age")) {
            age = Integer.parseInt(ageTextField.getText().trim());
            salesmanList = db.searchSalesmanwithAge(selectedSalesmanAgeComparator, age);
            salesmanTable.setItems(salesmanList);
        } else if (selectedSalesmanSearchByItem.equals("SalesMan Id")) {
            salesmanID = Integer.parseInt(genericSalesmanSearchField.getText().trim());
            salesmanList = db.searchSalesmanwithSID(salesmanID);
            salesmanTable.setItems(salesmanList);
        } else if (selectedSalesmanSearchByItem.equals("Phone")) {
            phone = genericSalesmanSearchField.getText().trim();
            salesmanList = db.searchSalesman("phone", phone);
            salesmanTable.setItems(salesmanList);
        } else if (selectedSalesmanSearchByItem.equals("Name")) {
            name = genericSalesmanSearchField.getText().trim();
            salesmanList = db.searchSalesman("name", name);
            salesmanTable.setItems(salesmanList);
        } else if (selectedSalesmanSearchByItem.equals("SurName")) {
            surname = genericSalesmanSearchField.getText().trim();
            salesmanList = db.searchSalesman("surname", surname);
            salesmanTable.setItems(salesmanList);
        }
    }

    @FXML
    private void onProductSearch() {
        ObservableList<Product> productList;
        int productID, stock;
        String name, category;
        Double price;

        if (selectedProductSearchByItem.equals("Stock")) {
            stock = Integer.parseInt(stockTextField.getText().trim());
            productList = db.searchProductwithStock(selectedProductStockComparator, stock);
            productTable.setItems(productList);
        } else if (selectedProductSearchByItem.equals("Product Id")) {
            productID = Integer.parseInt(genericProductSearchField.getText().trim());
            productList = db.searchProduct("p_id", String.valueOf(productID));
            productTable.setItems(productList);
        } else if (selectedProductSearchByItem.equals("Category")) {
            category = genericProductSearchField.getText().trim();
            productList = db.searchProduct("category", category);
            productTable.setItems(productList);
        } else if (selectedProductSearchByItem.equals("Product Name")) {
            name = genericProductSearchField.getText().trim();
            productList = db.searchProduct("product_name", name);
            productTable.setItems(productList);
        } else if (selectedProductSearchByItem.equals("Price")) {
            price = Double.parseDouble(genericProductSearchField.getText().trim());
            productList = db.searchProduct("price", String.valueOf(price));
            productTable.setItems(productList);
        }
    }

    @FXML
    private void onSupplierSearch() {
        ObservableList<Supplier> supplierList;
        int companyID, productID;
        String name, category;
        Double price;

        if (selectedSupplierSearchByItem.equals("Product Id")) {
            productID = Integer.parseInt(genericSupplierSearchField.getText().trim());
            supplierList = db.searchSupplier("p_id", String.valueOf(productID));
            supplierTable.setItems(supplierList);
        } else if (selectedSupplierSearchByItem.equals("Company Name")) {
            name = genericSupplierSearchField.getText().trim();
            supplierList = db.searchSupplier("company_name", name);
            supplierTable.setItems(supplierList);
        } else if (selectedSupplierSearchByItem.equals("Company Id")) {
            companyID = Integer.parseInt(genericSupplierSearchField.getText().trim());
            supplierList = db.searchSupplier("company_id", String.valueOf(companyID));
            supplierTable.setItems(supplierList);
        } else if (selectedSupplierSearchByItem.equals("Price")) {
            price = Double.parseDouble(genericSupplierSearchField.getText().trim());
            supplierList = db.searchSupplier("price", String.valueOf(price));
            supplierTable.setItems(supplierList);
        }
    }

    private void refreshTopProductByCategory(String category) {
        TopProduct topProduct = db.getTopProductByCategory(category);
        if (topProduct == null) {
            topSalesProductByCategoryValue.setText("0 Sales");
            topSalesProductByCategoryName.setText("No data");
        } else {
            topSalesProductByCategoryValue.setText(topProduct.getMax_quantity() + " Sales");
            topSalesProductByCategoryName.setText(topProduct.getProduct_name());
        }
    }

    private void refreshProductStatistics() {
        refreshTopProductByCategory("");
        TopProduct topProduct = db.getTopProduct();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (topProduct == null) {
                    topSalesProductValue.setText("0 Sales");
                    topSalesProductName.setText("No data");
                } else {
                    topSalesProductValue.setText(topProduct.getMax_quantity() + " Sales");
                    topSalesProductName.setText(topProduct.getProduct_name());
                }
            }
        });
    }
}