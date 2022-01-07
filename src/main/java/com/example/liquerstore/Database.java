package com.example.liquerstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection con;
    private double topRevenue;
    private int maxCountReceipts;

    public Database() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://45.77.137.230:3306/liquer_store?autoReconnect=true", "dimi1", "makaronia2@");
            Statement statement = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Salesman> displaySalesman() {
        ResultSet resultSet;
        //Declare a observable List which comprises of Suppliers objects
        ObservableList<Salesman> salesmanList = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();

            resultSet = statement.executeQuery("select * from liquer_store.salesman");

            while(resultSet.next()){
                Salesman salesman = new Salesman(0,"","",0,"");
                salesman.setS_id(resultSet.getInt("s_id"));
                salesman.setName(resultSet.getString("name"));
                salesman.setSurname(resultSet.getString("surname"));
                salesman.setAge(resultSet.getInt("age"));
                salesman.setPhone(resultSet.getString("phone"));
                salesmanList.add(salesman);
            }

            return  salesmanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Product> displayProduct() {
        ResultSet resultSet;
        //Declare a observable List which comprises of Suppliers objects
        ObservableList<Product> productList = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();

            resultSet = statement.executeQuery("select * from liquer_store.product");

            while(resultSet.next()){
                Product product = new Product(0,"","",0,0);
                product.setP_id(resultSet.getInt("p_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setCategory(resultSet.getString("category"));
                product.setStock(resultSet.getInt("stock"));
                product.setPrice(resultSet.getDouble("price"));
                productList.add(product);
            }

            return  productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Receipt> displayReceipt() {
        ResultSet resultSet;
        //Declare a observable List which comprises of Receipts objects
        ObservableList<Receipt> receiptList = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();

            resultSet = statement.executeQuery("select * from liquer_store.receipt");

            while(resultSet.next()){
                Receipt receipt = new Receipt(0,0,0, Date.valueOf("2000-12-31"),0,0);
                receipt.setBarcode(resultSet.getInt("barcode"));
                receipt.setS_id(resultSet.getInt("s_id"));
                receipt.setReceipt_p_id(resultSet.getInt("receipt_p_id"));
                receipt.setDate(resultSet.getDate("date"));
                receipt.setQuantity(resultSet.getInt("quantity"));
                receipt.setTotal_value(resultSet.getDouble("total_value"));
                receiptList.add(receipt);
            }

            return receiptList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Supplier> displaySupplier() {
        ResultSet resultSet;
        //Declare a observable List which comprises of Suppliers objects
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        try {
            Statement statement = con.createStatement();

            resultSet = statement.executeQuery("select * from liquer_store.supplier");

            while(resultSet.next()){
                Supplier supplier = new Supplier(0,"",0,0);
                supplier.setCompany_id(resultSet.getInt("company_id"));
                supplier.setCompany_name(resultSet.getString("company_name"));
                supplier.setP_id(resultSet.getInt("p_id"));
                supplier.setPrice(resultSet.getDouble("price"));
                supplierList.add(supplier);
            }

            return  supplierList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateSupplier(Supplier supplier) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("UPDATE `liquer_store`.`supplier`\n" +
                    "SET `company_id` =" + supplier.getCompany_id() + ",\n" +
                    "`company_name` =  '"+ supplier.getCompany_name() + "',\n" +
                    "`p_id` =" + supplier.getP_id() + ",\n" +
                    "`price` = "+ supplier.getPrice() + "\n" +
                    "WHERE `company_id` = "+ supplier.getCompany_id() + ";");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertSupplier(Supplier supplier) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("INSERT INTO liquer_store.supplier (company_id, company_name, p_id, price) "
                    + "VALUES ('" + supplier.getCompany_id() + "', '" + supplier.getCompany_name() + "'," +
                    "" + supplier.getP_id() + ", " + supplier.getPrice() + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteSupplier(Supplier supplier) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("DELETE FROM liquer_store.supplier WHERE company_id =" + supplier.getCompany_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateProduct(Product product) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("UPDATE `liquer_store`.`product`\n" +
                    "SET `p_id` =" + product.getP_id() + ",\n" +
                    "`price` =  "+ product.getPrice() + ",\n" +
                    "`stock` =" + product.getStock() + ",\n" +
                    "`category` = '"+ product.getCategory() + "',\n" +
                    "`product_name` = '"+ product.getProduct_name() + "'\n" +
                    "WHERE `p_id` = "+ product.getP_id() + ";");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertProduct(Product product) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("INSERT INTO `liquer_store`.`product`(`p_id`,`price`,`stock`,`category`,`product_name`) "
                    + "VALUES ('" + product.getP_id() + "', " + product.getPrice() + " , " +
                    "" + product.getStock() + ", '" + product.getCategory() + "', '" + product.getProduct_name() + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int deleteProduct(Product product) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("DELETE FROM liquer_store.product WHERE p_id =" + product.getP_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateSalesman(Salesman salesman) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("UPDATE `liquer_store`.`salesman`\n" +
                    "SET `s_id` =" + salesman.getS_id() + ",\n" +
                    "`name` =  '"+ salesman.getName() + "',\n" +
                    "`surname` = '" + salesman.getSurname() + "',\n" +
                    "`age` = '"+ salesman.getAge() + "',\n" +
                    "`phone` = '"+ salesman.getPhone() + "'\n" +
                    "WHERE `s_id` = "+ salesman.getS_id() + ";");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertSalesman(Salesman salesman) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("INSERT INTO `liquer_store`.`salesman`(`s_id`,`name`,`surname`,`age`,`phone`) "
                    + "VALUES ('" + salesman.getS_id() + "', '" + salesman.getName() + "','" + salesman.getSurname()+ "',"
                    + salesman.getAge() + ", '" + salesman.getPhone() + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteSalesman(Salesman salesman) {
        try {
            Statement statement = con.createStatement();
            return statement.executeUpdate("DELETE FROM liquer_store.salesman WHERE s_id =" + salesman.getS_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateReceipt(Receipt receipt) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("UPDATE `liquer_store`.`receipt`\n" +
                    "SET `barcode` =" + receipt.getBarcode() + ",\n" +
                    "`s_id` =  '"+ receipt.getS_id() + "',\n" +
                    "`receipt_p_id` = '" + receipt.getReceipt_p_id() + "',\n" +
                    "`date` = '"+ receipt.getDate() + "',\n" +
                    "`quantity` = '"+ receipt.getQuantity() + "',\n" +
                    "`total_value` = '"+ receipt.getTotal_value() + "'\n" +
                    "WHERE `barcode` = "+ receipt.getBarcode() + ";");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertReceipt(Receipt receipt) {
        try {
            Statement statement = con.createStatement();

            return statement.executeUpdate("INSERT INTO `liquer_store`.`receipt`(`barcode`,`s_id`,`receipt_p_id`,`quantity`,`total_value`,`date`) "
                    + "VALUES ('" + receipt.getBarcode() + "', '" + receipt.getS_id() + "','" + receipt.getReceipt_p_id()+ "',"
                    + receipt.getQuantity() + ", '" + receipt.getTotal_value() + "' , '"+ receipt.getDate() + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteReceipt(Receipt receipt) {
        try {
            Statement statement = con.createStatement();
            return statement.executeUpdate("DELETE FROM liquer_store.receipt WHERE barcode =" + receipt.getBarcode());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> topSellerbyRevenue() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet;
            ObservableList<Salesman> salesmenList = FXCollections.observableArrayList();
            String name, surname, phone;
            ArrayList<String> result = new ArrayList<>();

            resultSet = statement.executeQuery("SELECT s.name, s.surname, s.phone, total_value " +
                    "FROM " +
                    "(SELECT salesman.s_id, salesman.name, salesman.surname, salesman.phone " +
                    "FROM liquer_store.salesman) as s "+ "" +
                    "INNER JOIN (" +
                    "SELECT receipt.s_id, sum(receipt.total_value) as total_value " +
                    "FROM  liquer_store.receipt " +
                    "GROUP BY receipt.s_id " +
                    ")as r ON s.s_id = r.s_id " +
                    "WHERE total_value = (" +
                    "SELECT sum(receipt.total_value) as total " +
                    "FROM  liquer_store.receipt " +
                    "GROUP BY receipt.s_id " +
                    "order by total desc limit 1)");

            while(resultSet.next()) {
                topRevenue = resultSet.getDouble("total_value");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                phone = resultSet.getString("phone");
                result.add(name + " "+ surname);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String topSellerbyReceiptCount() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet;
            ObservableList<Salesman> salesmenList = FXCollections.observableArrayList();
            String result, name, surname, phone;
            result = "";

            resultSet = statement.executeQuery("SELECT s.name, s.surname, s.phone, total_receipts " +
                    "FROM " +
                    "(SELECT salesman.s_id, salesman.name, salesman.surname, salesman.phone " +
                    "FROM liquer_store.salesman )as s " +
                    "INNER JOIN (" +
                    "SELECT receipt.s_id, count(receipt.receipt_p_id) as total_receipts " +
                    "FROM  liquer_store.receipt " +
                    "GROUP BY receipt.s_id )as r ON s.s_id = r.s_id " +
                    "WHERE total_receipts = (" +
                    "SELECT count(receipt.receipt_p_id) as total " +
                    "FROM  liquer_store.receipt " +
                    "GROUP BY receipt.s_id order by total desc limit 1)");

            while(resultSet.next()) {
                maxCountReceipts = resultSet.getInt("total_receipts");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                phone = resultSet.getString("phone");
                result = result + name + " " + surname + " " + "\n";
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Salesman> searchSalesmanwithSID(int s_id) {
        ObservableList<Salesman> salesmanList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.salesman where salesman.s_id = " + s_id);
            salesmanList = setSalesmanResultSet(resultSet);
            return salesmanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Salesman> searchSalesmanwithAge(String comparator, int age) {
        ObservableList<Salesman> salesmanList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.salesman where salesman.age "+comparator + " " + age);
            salesmanList = setSalesmanResultSet(resultSet);
            return salesmanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Salesman> searchSalesman(String searchType, String searchField) {
        ObservableList<Salesman> salesmanList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.salesman where salesman."+searchType+ " = "+ "'" + searchField +"'");
            salesmanList = setSalesmanResultSet(resultSet);
            return salesmanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Product> searchProductwithStock(String comparator, int stock) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.product where product.stock "+comparator + " " + stock);
            productList = setProductResultSet(resultSet);
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Product> searchProduct(String searchType, String searchField) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.product where product."+searchType+ " = "+ "'" + searchField +"'");
            productList = setProductResultSet(resultSet);
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Supplier> searchSupplier(String searchType, String searchField) {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select * from liquer_store.supplier where supplier."+searchType+ " = "+ "'" + searchField +"'");
            supplierList = setSupplierResultSet(resultSet);
            return supplierList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList <Salesman> setSalesmanResultSet(ResultSet resultSet) {
        ObservableList<Salesman> salesmanList = FXCollections.observableArrayList();
        try {
            while(resultSet.next()) {
                Salesman salesman = new Salesman(0, "", "", 0, "");
                salesman.setS_id(resultSet.getInt("s_id"));
                salesman.setName(resultSet.getString("name"));
                salesman.setSurname(resultSet.getString("surname"));
                salesman.setAge(resultSet.getInt("age"));
                salesman.setPhone(resultSet.getString("phone"));
                salesmanList.add(salesman);
            }
            return salesmanList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Product> setProductResultSet(ResultSet resultSet) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            while(resultSet.next()){
                Product product = new Product(0,"","",0,0);
                product.setP_id(resultSet.getInt("p_id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setCategory(resultSet.getString("category"));
                product.setStock(resultSet.getInt("stock"));
                product.setPrice(resultSet.getDouble("price"));
                productList.add(product);
            }

            return  productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public ObservableList<Supplier> setSupplierResultSet(ResultSet resultSet) {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        try {
              while(resultSet.next()){
                Supplier supplier = new Supplier(0,"",0,0);
                supplier.setCompany_id(resultSet.getInt("company_id"));
                supplier.setCompany_name(resultSet.getString("company_name"));
                supplier.setP_id(resultSet.getInt("p_id"));
                supplier.setPrice(resultSet.getDouble("price"));
                supplierList.add(supplier);
            }

            return  supplierList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TopProduct getTopProductByCategory(String category) {
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT p.p_id, p.product_name, p.category, max_quantity\n" +
                    "        FROM\n" +
                    "                (SELECT product.p_id, product.category, product.product_name\n" +
                    "                        FROM liquer_store.product\n" +
                    "                        WHERE product.category = '"+category+"'\n" +
                    "                )as p\n" +
                    "        INNER JOIN (\n" +
                    "                SELECT receipt.receipt_p_id, sum(receipt.quantity) as max_quantity\n" +
                    "        FROM  liquer_store.receipt\n" +
                    "        GROUP BY receipt.receipt_p_id\n" +
                    "\t)as r ON p.p_id = r.receipt_p_id\n" +
                    "        order by max_quantity desc limit 1;");

            if(resultSet.next()) {
                int p_id = resultSet.getInt("p_id");
                String product_name = resultSet.getString("product_name");
                String cat = resultSet.getString("category");
                int max_quantity = resultSet.getInt("max_quantity");
                return new TopProduct(p_id, product_name, cat, max_quantity);
            }
             return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TopProduct getTopProduct() {
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select  p_id, product_name, sum(receipt.quantity) as max_quantity, product.category from liquer_store.receipt\n" +
                    "INNER JOIN liquer_store.product ON product.p_id = receipt.receipt_p_id\n" +
                    "GROUP BY receipt.receipt_p_id\n" +
                    "order by max_quantity desc limit 1;");

            if(resultSet.next()) {
                int p_id = resultSet.getInt("p_id");
                String product_name = resultSet.getString("product_name");
                String cat = resultSet.getString("category");
                int max_quantity = resultSet.getInt("max_quantity");
                return new TopProduct(p_id, product_name, cat, max_quantity);
            }
             return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getTotalRevenue() {
        try {
            ResultSet resultSet;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select sum(receipt.total_value) as total_value from liquer_store.receipt;");

            if(resultSet.next()) {
                return resultSet.getDouble("total_value");
            }
             return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTopRevenue() {
        return topRevenue;
    }

    public int getmaxCountReceipts() {
        return maxCountReceipts;
    }
}


