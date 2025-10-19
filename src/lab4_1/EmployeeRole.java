/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 *
 * @author Gehad
 */
public class EmployeeRole extends UserRole  {
   private ProductDatabase productsDatabase;
   private CustomerProductDatabase customerProductDatabase;
//constructor
    public EmployeeRole() {
        super("Employee");
        productsDatabase = new ProductDatabase("Products.txt");
        productsDatabase.readFromFile();
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        customerProductDatabase.readFromFile();
    }
    //method 1
    public void addProduct(String productID, String productName, String manufacturerName, String 
supplierName, int quantity, float price){
       Product p= new Product(productID,productName,
manufacturerName,supplierName,quantity,price);
       productsDatabase.insertRecord(p);
       productsDatabase.saveToFile();
      }
    
    //method 2
    public Product[] getListOfProducts(){
        ArrayList<Product> list = productsDatabase.returnAllRecords();
        Product[] array = new Product[list.size()];
        return list.toArray(array); 
    }
    //method 3
public CustomerProduct[]getListOfPurchasingOperations(){
    ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        CustomerProduct[] array = new CustomerProduct[list.size()];
        return list.toArray(array); 
}
//method 4
public boolean purchaseProduct(String customerSSN, String productID,LocalDate purchaseDate) {
        Product product = productsDatabase.getRecord(productID);

        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }

        else if (product.getQuantity() == 0) {
            System.out.println("Product out of stock.");
            return false;
        }

        else{
            // decrease quantity
        product.setQuantity(product.getQuantity() - 1);
        productsDatabase.saveToFile();

        // Create new record
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        cp.setPaid(false);
        customerProductDatabase.insertRecord(cp);
        customerProductDatabase.saveToFile();

        System.out.println("Purchase successful for product " + productID);
        return true;
    }}

    // method 5
    public double returnProduct(String customerSSN, String productID,
                                LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println("Return date cannot be before purchase date.");
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        if (product == null) {
            System.out.println("Product not found in database.");
            return -1;
        }

        String key = customerSSN + "," + productID + "," +
                String.format("%02d-%02d-%04d",
                        purchaseDate.getDayOfMonth(),
                        purchaseDate.getMonthValue(),
                        purchaseDate.getYear());

        if (!customerProductDatabase.contains(key)) {
            System.out.println("No purchase record found for this product and date.");
            return -1;
        }

        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            System.out.println("Return period (14 days) has expired.");
            return -1;
        }

        // Increment quantity
        product.setQuantity(product.getQuantity() + 1);
        productsDatabase.saveToFile();

        // Remove the purchase record
        customerProductDatabase.deleteRecord(key);
        customerProductDatabase.saveToFile();

        System.out.println("Product returned successfully.");
        return product.getPrice();
    }

    // method 6
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<CustomerProduct> allRecords = customerProductDatabase.returnAllRecords();

        for (CustomerProduct cp : allRecords) {
            if (cp.getCustomerSSN().equals(customerSSN) &&
                cp.getPurchaseDate().equals(purchaseDate)) {

                if (cp.isPaid()) {
                    System.out.println("Purchase already paid.");
                    return false;
                } else {
                    cp.setPaid(true);
                    customerProductDatabase.saveToFile();
                    System.out.println("Payment applied successfully.");
                    return true;
                }
            }
        }

        System.out.println("Purchase not found.");
        return false;
    }

    // method 7
   @Override
    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("All data saved. Employee logged out.");
    }
}
