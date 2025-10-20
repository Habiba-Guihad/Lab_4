/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;
public class ProductDatabase extends Database{

    public ProductDatabase(String filename) {
        super(filename);
    }
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
}