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
public class AdminRole {
private EmployeeUserDatabase database;

    public AdminRole() {
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
     database.deleteRecords(key); 
            database.saveToFile(); 
 }
 public void logout() {
        database.saveToFile();
        System.out.println("Admin logged out.");
    }
 
   
}


 

