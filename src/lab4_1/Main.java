/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Abdallah
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminRole admin = new AdminRole();
        EmployeeRole employee = new EmployeeRole();
        
        int choice;
        do {
            System.out.println("\n--- INVENTORY MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. Add Product");
            System.out.println("5. View All Products");
            System.out.println("6. Purchase Product");
            System.out.println("7. Return Product");
            System.out.println("8. Apply Payment");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String empId = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();
                    admin.addEmployee(empId, name, email, address, phone);
                    
                    break;

                case 2:
                    EmployeeUser[] employees = admin.getListOfEmployees();
                    for (EmployeeUser e : employees)
                        System.out.println(e.lineRepresentation());
                    break;

                case 3:
                    System.out.print("Enter Employee ID to remove: ");
                    String removeId = sc.nextLine();
                    admin.removeEmployee(removeId);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    String productID = sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String productName = sc.nextLine();
                    System.out.print("Enter Manufacturer Name: ");
                    String manufacturer = sc.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String supplier = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Enter Price: ");
                    float price = sc.nextFloat();
                    employee.addProduct(productID, productName, manufacturer, supplier, quantity, price);
                    break;

                case 5:
                    Product[] products = employee.getListOfProducts();
                    for (Product p : products)
                        System.out.println(p.lineRepresentation());
                    break;

                case 6:
                    System.out.print("Enter Customer SSN: ");
                    String ssn = sc.nextLine();
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    LocalDate date = LocalDate.now();
                    if (employee.purchaseProduct(ssn, pid, date))
                        System.out.println("Purchase successful.");
                    else
                        System.out.println("Purchase failed (out of stock).");
                    break;

                case 7:
                    System.out.print("Enter Customer SSN: ");
                    String retSSN = sc.nextLine();
                    System.out.print("Enter Product ID: ");
                    String retPID = sc.nextLine();
                    System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                    LocalDate pDate = LocalDate.parse(sc.nextLine());
                    System.out.print("Enter Return Date (YYYY-MM-DD): ");
                    LocalDate rDate = LocalDate.parse(sc.nextLine());
                    double refund = employee.returnProduct(retSSN, retPID, pDate, rDate);
                    if (refund == -1)
                        System.out.println("Return failed.");
                    else
                        System.out.println("Return successful. Refund = " + refund);
                    break;

                case 8:
                    System.out.print("Enter Customer SSN: ");
                    String paySSN = sc.nextLine();
                    System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
                    LocalDate payDate = LocalDate.parse(sc.nextLine());
                    if (employee.applyPayment(paySSN, payDate))
                        System.out.println("Payment applied successfully.");
                    else
                        System.out.println("Payment failed or already paid.");
                    break;

                case 9:
                    admin.logout();
                    employee.logout();
                    System.out.println("Exiting... All data saved.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 9);
        
        sc.close();
    }
    
}
