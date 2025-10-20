/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

/**
 *
 * @author Gehad
 */

import java.time.LocalDate;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("========== SYSTEM TEST START ==========\n");

      
        AdminRole admin = new AdminRole();
        System.out.println(">>> Testing Admin Role...");

       
        admin.addEmployee("EMP-001", "Ahmed", "ahmed@email.com", "Cairo", "0100000001");
        admin.addEmployee("EMP-002", "Sara", "sara@email.com", "Alex", "0100000002");
        admin.addEmployee("EMP-003", "Omar", "omar@email.com", "Giza", "0100000003");

        
        System.out.println("\nList of Employees:");
        for (EmployeeUser emp : admin.getListOfEmployees()) {
            System.out.println(" - " + emp.lineRepresentation());
        }

        
        admin.removeEmployee("EMP-002");

        
        System.out.println("\nAfter removal:");
        for (EmployeeUser emp : admin.getListOfEmployees()) {
            System.out.println(" - " + emp.lineRepresentation());
        }

        admin.logout();
        System.out.println("Admin Role tested successfully!\n");


       
        EmployeeRole employee = new EmployeeRole();
        System.out.println(">>> Testing Employee Role...");

      
        employee.addProduct("LPT-001", "MacBook Pro 16-inch", "Apple", "BestBuy", 2, 5000);
        employee.addProduct("SMT-002", "Samsung Galaxy S22", "Samsung", "Amazon", 3, 1200);
        employee.addProduct("GMC-003", "PlayStation 5", "Sony", "GameStop", 4, 500);
        employee.addProduct("SPK-004", "Google Nest Audio", "Google", "Walmart", 1, 200);
        employee.addProduct("WTCH-005", "Fitbit Versa 3", "Fitbit", "Target", 2, 150);

        
        System.out.println("\nList of Products:");
        for (Product p : employee.getListOfProducts()) {
            System.out.println(" - " + p.lineRepresentation());
        }

     
        System.out.println("\n>>> Testing Customer Purchases and File Writing...");

        String customer1 = "1111111";
        String customer2 = "2222222";
        String customer3 = "3333333";

        CustomerProduct oP1 = new CustomerProduct(customer1, "LPT-001", LocalDate.of(2023, 6, 1));
        CustomerProduct oP2 = new CustomerProduct(customer1, "LPT-001", LocalDate.of(2023, 6, 5));
        CustomerProduct oP3 = new CustomerProduct(customer2, "SPK-004", LocalDate.of(2023, 5, 11));
        CustomerProduct oP4 = new CustomerProduct(customer3, "SPK-004", LocalDate.of(2023, 5, 12));
        CustomerProduct oP5 = new CustomerProduct(customer3, "SPK-004", LocalDate.of(2023, 5, 20));

        boolean result;
        result = employee.purchaseProduct(oP1.getCustomerSSN(), oP1.getProductID(), oP1.getPurchaseDate());
        result = employee.purchaseProduct(oP2.getCustomerSSN(), oP2.getProductID(), oP2.getPurchaseDate());
        result = employee.purchaseProduct(oP3.getCustomerSSN(), oP3.getProductID(), oP3.getPurchaseDate());
        result = employee.purchaseProduct(oP4.getCustomerSSN(), oP4.getProductID(), oP4.getPurchaseDate()); 
        result = employee.purchaseProduct(oP5.getCustomerSSN(), oP5.getProductID(), oP5.getPurchaseDate());

        // Return one product
        employee.returnProduct(oP3.getCustomerSSN(), oP3.getProductID(), oP3.getPurchaseDate(), LocalDate.of(2023, 5, 17));

       
        System.out.println("Customer Purchases:");
        for (CustomerProduct c : employee.getListOfPurchasingOperations()) {
            System.out.println(" - " + c.lineRepresentation());
        }

      
        employee.logout();

        System.out.println("Employee Role tested successfully!");
        System.out.println("========== SYSTEM TEST COMPLETE ==========");
    }
}
