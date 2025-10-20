/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Abdallah
 */
public abstract class Database {

    private ArrayList<Item> records;
    private String filename;

    public Database(String filename) {
        records=new ArrayList<>();
        this.filename = filename;
    }

    public void readFromFile() {
        records.clear();//3shan at2aked en list empty abl mah 2a2ra
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Item custprod = createRecordFrom(line);
                if (custprod != null) {
                    records.add(custprod);
                }
            }
            scan.close();
            System.out.println("Customer Product File read successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found!");
        }
    }

    public abstract Item createRecordFrom(String line);

    public ArrayList<Item> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            Item x = records.get(i);
            if (x.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Item getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            Item custmprod = records.get(i);
            if (custmprod.getSearchKey().equals(key)) {
                return custmprod;
            }
        }
        return null;
    }

    public void insertRecord(Item record) {
        if (record == null) {
            System.out.println("There is no record");
            return;
        }
        if (contains(record.getSearchKey())) {
            System.out.println("Record already exist");
        } else {
            records.add(record);
            System.out.println("Record added successfully");
        }
    }

    public void deleteRecord(String key) {
        boolean found = false;
        for (int i = 0; i < records.size(); i++) {
            Item custmprod = records.get(i);
            if (custmprod.getSearchKey().equals(key)) {
                records.remove(i);
                System.out.println("Record with key  " + key + " removed successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No record found with key  " + key);
        }
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(filename, false);//false di=over write old file
            for (int i = 0; i < records.size(); i++) {
                Item custmprod = records.get(i);
                writer.write(custmprod.lineRepresentation());
                if (i < records.size() - 1) {
                    writer.write("\n");//to add new line
                }
            }
            writer.close();
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e)//input aw output exception w el e heya object mn no3 exception
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
