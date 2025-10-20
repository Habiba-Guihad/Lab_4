/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustomerProductDatabase extends Database {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }
    @Override
    public Item createRecordFrom(String line) {
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
}