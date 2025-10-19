/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4_1;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Gehad
 */
public class AdminRole extends UserRole{
private EmployeeUserDatabase database;

    public AdminRole() {
        super("Admin");
        // Creates 2el database object
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile(); //loads 2el data men file
    }
    
 public void addEmployee(String employeeId, String name, String email, String 
address, String phoneNumber){
            EmployeeUser newEmp = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            database.insertRecord(newEmp);
            database.saveToFile();  
    }
 public EmployeeUser[] getListOfEmployees() {
    ArrayList<EmployeeUser> list = database.returnAllRecords();
        EmployeeUser[] array = new EmployeeUser[list.size()];
        return list.toArray(array); 
 }
 public void removeEmployee(String key) {
     database.deleteRecord(key); 
            database.saveToFile(); 
 }
@Override
 public void logout() {
        database.saveToFile();
        System.out.println("Admin logged out.");
    }
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AdminRole admin = new AdminRole(); // automatically loads data
        int choice;

        do {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Logout and Save");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    String id = input.nextLine();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    System.out.print("Enter Address: ");
                    String address = input.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = input.nextLine();

                    admin.addEmployee(id, name, email, address, phone);
                    break;

                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    String removeId = input.nextLine();
                    admin.removeEmployee(removeId);
                    break;

                case 3:
                    System.out.println("\n--- All Employees ---");
                    EmployeeUser[] list = admin.getListOfEmployees();
                    if (list.length == 0) {
                        System.out.println("No employees found.");
                    } else {
                        for (EmployeeUser emp : list) {
                            System.out.println(emp.lineRepresentation());
                        }
                    }
                    break;

                case 4:
                    admin.logout();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }

        } while (choice != 4);

        input.close();
    }
}
