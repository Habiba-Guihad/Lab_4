/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

import java.io.IOException;
import java.time.LocalDate;

public class TestClass {

    private AdminRole admin;
    private EmployeeRole employee;

    public TestClass() {
        admin = new AdminRole();
        employee = new EmployeeRole();
    }

    // ---------------------- Test Admin Role ----------------------
    public void testAdminRole() throws IOException {
        // Remove all old employees
        for (EmployeeUser emp : admin.getListOfEmployees()) {
            admin.removeEmployee(emp.getSearchKey());
        }
        admin.logout();

        // Reopen and check empty
        admin = new AdminRole();
        if (admin.getListOfEmployees().length != 0) {
            System.out.println("Error: old employees were not removed properly.");
            return;
        }

        // Add new employees
        admin.addEmployee("EMP-001", "Ahmed", "ahmed@email.com", "123 Street Cairo", "+20123456789");
        admin.addEmployee("EMP-002", "Mohamed", "mohamed@email.com", "456 Boulevard Giza", "+20198765432");
        admin.addEmployee("EMP-003", "Mariam", "mariam@email.com", "789 Avenue Alexandria", "+20145678923");
        admin.addEmployee("EMP-004", "Hossam", "hossam@email.com", "101 Lane Luxor", "+20132165498");
        admin.addEmployee("EMP-005", "Nour", "nour@email.com", "202 Drive Aswan", "+20165432178");

        admin.logout();

        // Verify employees count
        admin = new AdminRole();
        if (admin.getListOfEmployees().length != 5) {
            System.out.println("Error: not all employees were added correctly.");
            return;
        }

        admin.logout();
        System.out.println("Admin role test passed successfully.\n");
    }

    // ---------------------- Helper for Purchase ----------------------
    private boolean addPurchase(CustomerProduct purchase) throws IOException {
        return employee.purchaseProduct(
                purchase.getCustomerSSN(),
                purchase.getProductID(),
                purchase.getPurchaseDate());
    }

    // ---------------------- Test Employee Role ----------------------
    public void testEmployeeRole() throws IOException {
        Product[] oldProducts = employee.getListOfProducts();

        // Add products
        employee.addProduct("LPT-001", "MacBook Pro 16-inch", "Apple", "BestBuy", 2, 5000);
        employee.addProduct("SMT-002", "Samsung Galaxy S22", "Samsung", "Amazon", 3, 1200);
        employee.addProduct("GMC-003", "PlayStation 5", "Sony", "GameStop", 4, 500);
        employee.addProduct("SPK-004", "Google Nest Audio", "Google", "Walmart", 1, 200);
        employee.addProduct("WTCH-005", "Fitbit Versa 3", "Fitbit", "Target", 2, 150);

        Product[] newProducts = employee.getListOfProducts();
        if (newProducts.length - oldProducts.length != 5) {
            System.out.println("Error: not all products were added to file or arraylist.");
            return;
        }

        // Create test purchases
        CustomerProduct p1 = new CustomerProduct("1111111", "LPT-001", LocalDate.of(2023, 6, 1));
        CustomerProduct p2 = new CustomerProduct("1111111", "LPT-001", LocalDate.of(2023, 6, 5));
        CustomerProduct p3 = new CustomerProduct("2222222", "SPK-004", LocalDate.of(2023, 5, 11));
        CustomerProduct p4 = new CustomerProduct("3333333", "SPK-004", LocalDate.of(2023, 5, 12));
        CustomerProduct p5 = new CustomerProduct("3333333", "SPK-004", LocalDate.of(2023, 5, 20));

        // Perform purchases
        if (!addPurchase(p1)) { System.out.println("Purchase1 failed but should be valid."); return; }
        if (!addPurchase(p2)) { System.out.println("Purchase2 failed but should be valid."); return; }
        if (!addPurchase(p3)) { System.out.println("Purchase3 failed but should be valid."); return; }
        if (addPurchase(p4)) { System.out.println("Purchase4 succeeded but should be invalid."); return; }

        // Check records count
        CustomerProduct[] newOps = employee.getListOfPurchasingOperations();
        CustomerProduct[] oldOps = employee.getListOfPurchasingOperations();
        if (newOps.length - oldOps.length != 3) {
            System.out.println("Error: purchases not recorded properly in file or arraylist.");
            return;
        }

        // Return product test
        double refund = employee.returnProduct(p3.getCustomerSSN(), p3.getProductID(),
                p3.getPurchaseDate(), LocalDate.of(2023, 5, 17));
        if (refund != newProducts[oldProducts.length + 3].getPrice()) {
            System.out.println("Error: return product logic incorrect.");
            return;
        }

        // Final purchase test
        if (!addPurchase(p5)) {
            System.out.println("Purchase5 failed but should be valid.");
            return;
        }

        employee.logout();
        System.out.println("Employee role test passed successfully.\n");
    }

    // ---------------------- Main ----------------------
    public static void main(String[] args) throws IOException {
        System.out.println("========== SYSTEM TEST START ==========\n");
        TestClass test = new TestClass();

        test.testAdminRole();

        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");

        test.testEmployeeRole();

        System.out.println("========== SYSTEM TEST COMPLETE ==========");
    }
}
