/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

import java.io.*;
import java.util.*;

public class ProductDatabase extends Database{

    public ProductDatabase(String filename) {
        super(filename);
    }

 
//    public void readFromFile() {
//        records.clear(); 
//        try (Scanner scan = new Scanner(new File(filename))) {
//            while (scan.hasNextLine()) {
//                String line = scan.nextLine();
//                Product product = createRecordFrom(line);
//                if (product != null) {
//                    records.add(product);
//                }
//            }
//            System.out.println("Products file read successfully.");
//        } catch (FileNotFoundException e) {
//            System.out.println("Error: file not found " + filename);
//        }
//    }

    public Item createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            try {
                String productId = parts[0].trim();
                String productName = parts[1].trim();
                String manufacturerName = parts[2].trim();
                String supplierName = parts[3].trim();
                int quantity = Integer.parseInt(parts[4].trim());
                float price = Float.parseFloat(parts[5].trim());
                return new Product(productId, productName, manufacturerName, supplierName, quantity, price);
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
                return null;
            }
        } else {
            System.out.println("Invalid line format: " + line);
            return null;
        }
    }

   

//    public void insertRecord(Product record) {
//        if (record == null) {
//            System.out.println("Cannot insert a null record.");
//            return;
//        }
//        if (contains(record.getSearchKey())) {
//            System.out.println("Product with ID " + record.getSearchKey() + " already exists.");
//        } else {
//            records.add(record);
//            System.out.println("Product added successfully.");
//        }
//    }

//    public void deleteRecord(String key) {
//        boolean found = false;
//        Iterator<Product> iterator = records.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getSearchKey().equals(key)) {
//                iterator.remove();
//                found = true;
//                System.out.println("Product with ID " + key + " deleted successfully.");
//                break;
//            }
//        }
//        if (!found) {
//            System.out.println("No product found with ID " + key);
//        }
//    }
//
//    public void saveToFile() {
//        try (FileWriter writer = new FileWriter(filename, false)) { 
//            for (int i = 0; i < records.size(); i++) {
//                writer.write(records.get(i).lineRepresentation());
//                if (i < records.size() - 1) {
//                    writer.write("\n");
//                }
//            }
//            System.out.println("Product data saved successfully to " + filename);
//        } catch (IOException e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//        }
//    }
}