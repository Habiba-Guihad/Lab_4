/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class CustomerProductDatabase {

    private ArrayList<CustomerProduct> records;
    private String filename;

    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        records.clear();//3shan at2aked en list empty abl mah 2a2ra
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                CustomerProduct custprod = createRecordFrom(line);
                if (custprod != null) {
                    records.add(custprod);
                }
            }
            scan.close();
            System.out.println("Customer Product File read successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            try {
                String customerSSN = parts[0];
                String productID = parts[1];
                LocalDate date = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                boolean paid = Boolean.parseBoolean(parts[3]);
                CustomerProduct custmprod = new CustomerProduct(customerSSN, productID, date);
                custmprod.setPaid(paid);
                return custmprod;
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
                return null;
            }
        } else {
            System.out.println("Invalid line" + line);
            return null;
        }
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            CustomerProduct custmprod = records.get(i);
            if (custmprod.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public CustomerProduct getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            CustomerProduct custmprod = records.get(i);
            if (custmprod.getSearchKey().equals(key)) {
                return custmprod;
            }
        }
        return null;
    }

    public void insertRecord(CustomerProduct record) {
        if (record == null) {
            System.out.println("There is no record");
            return;
        }
        if (contains(record.getSearchKey())) {
            System.out.println("Record already exist");
        } else {
            records.add(record);
            System.out.println("Record added successfully");
        }
    }

    public void deleteRecord(String key) {
        boolean found = false;
        for (int i = 0; i < records.size(); i++) {
            CustomerProduct custmprod = records.get(i);
            if (custmprod.getSearchKey().equals(key)) {
                records.remove(i);
                System.out.println("Record with key  " + key + " removed successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No record found with key  " + key);
        }
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(filename, false);//false di=over write old file
            for (int i = 0; i < records.size(); i++) {
                CustomerProduct custmprod = records.get(i);
                writer.write(custmprod.lineRepresentation());
                if (i < records.size() - 1) {
                    writer.write("\n");//to add new line
                }
            }
            writer.close();
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e)//input aw output exception w el e heya object mn no3 exception
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
     public static void main(String[] args) {

        // Create the employee role (this loads existing data)
        EmployeeRole employee = new EmployeeRole();

        System.out.println("\n===== Adding New Product =====");
        employee.addProduct("P1001", "Laptop", "Apple", "TechSupplier", 5, 1500f);

        System.out.println("\n===== Purchasing Product =====");
        employee.purchaseProduct("123456789", "P1001", LocalDate.of(2025, 10, 10));

        System.out.println("\n===== Applying Payment =====");
        employee.applyPayment("123456789", LocalDate.of(2025, 10, 10));

        System.out.println("\n===== Returning Product =====");
        employee.returnProduct("123456789", "P1001",
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 20)); // within 14 days

        System.out.println("\n===== All Products in File =====");
        Product[] allProducts = employee.getListOfProducts();
        for (Product p : allProducts) {
            System.out.println(p.lineRepresentation());
        }

        System.out.println("\n===== All Customer Purchases in File =====");
        CustomerProduct[] allPurchases = employee.getListOfPurchasingOperations();
        for (CustomerProduct c : allPurchases) {
            System.out.println(c.lineRepresentation());
        }

        System.out.println("\n===== Logging Out =====");
        employee.logout();
    }
}