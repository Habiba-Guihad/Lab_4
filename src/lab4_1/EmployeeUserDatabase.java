/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;
/**
 *
 * @author Dell
 */
class EmployeeUserDatabase extends Database
{

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }
    public EmployeeUser createRecordFrom(String line)
    {
        String[] parts=line.split(",");
        if(parts.length==5)
        {
            return new EmployeeUser(parts[0],parts[1],parts[2],parts[3],parts[4]);
        }
        else
        {
            System.out.println("Invalid line"+line);
            return null;
        }
    }
}