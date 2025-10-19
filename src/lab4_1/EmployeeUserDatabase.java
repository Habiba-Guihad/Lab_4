/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Dell
 */
public class EmployeeUserDatabase extends Database
{
    private ArrayList<EmployeeUser> records;
    private String filename;
    public EmployeeUserDatabase(String filename)
    {
        this.filename=filename;
        this.records=new ArrayList<>();
    }
    @Override
    public void readFromFile()
    {
       records.clear();//3shan at2aked en list empty abl mah 2a2ra
       try
       {
           File file=new File(filename);
           Scanner scan=new Scanner(file);
           while(scan.hasNextLine())
           {
               String line=scan.nextLine();
               EmployeeUser employee=createRecordFrom(line);
               if(employee!=null)
               {
                   records.add(employee);
               }
           }
           scan.close();
           System.out.println("Employee File read successfully");
       }
       catch(FileNotFoundException e)
       {
           System.out.println("Error file not found!");
       }
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
    public ArrayList<EmployeeUser> returnAllRecords()
    {
        return records;
    }
    public boolean contains(String key )
    {
        for(int i=0;i<records.size();i++)
        {
            EmployeeUser employee=records.get(i);
            if(employee.getSearchKey().equals(key))
                return true;
        }
        return false;
    }
    public EmployeeUser getRecord(String key)
    {
        for(int i=0;i<records.size();i++)
        {
            EmployeeUser employee=records.get(i);
            if(employee.getSearchKey().equals(key))
                return employee;
        }
        return null;
    }
    public void insertRecord(EmployeeUser record)
    {
        if(record==null)
        {
            System.out.println("There is no record");
            return;
        }
        if(contains(record.getSearchKey()))
        {
            System.out.println("This employee with id" +record.getSearchKey()+ "already exist");
        }
        else
        {
            records.add(record);
            System.out.println("Employee with id " +record.getSearchKey()+ " added successfully");
        }
    }
    public void deleteRecord(String key)
    {
        boolean found=false;
        for(int i=0;i<records.size();i++)
        {
            EmployeeUser employee=records.get(i);
            if(employee.getSearchKey().equals(key))
            {
                records.remove(i);
                System.out.println("Employee with id "+key+" removed successfully");
                found=true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("No employee found with id "+key);
        }
    }
    public void saveToFile()
    {
        try
        {
            FileWriter writer=new FileWriter(filename,false);//false di=over write old file
            for(int i=0;i<records.size();i++)
            {
                EmployeeUser employee=records.get(i);
                writer.write(employee.lineRepresentation());
                if(i<records.size()-1)
                    writer.write("\n");//to add new line
            }
            writer.close();
            System.out.println("Data saved successfully to " +filename);
        }
        catch(IOException e)//input aw output exception w el e heya object mn no3 exception
        {
           System.out.println("Error writing to file: " +e.getMessage()); 
        }
    }
}

