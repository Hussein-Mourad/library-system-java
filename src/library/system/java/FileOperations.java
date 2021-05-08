/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hussein
 */
public class FileOperations {

    public static void main(String[] args) {
        System.out.println(deleteRecord("librarians.csv", "5", "kljklj"));
    }

    // Used to get the number of lines in a file which helps in generating id for each record
    public static int getFileCount(String filename) {
        int count = 0;
        // Gets the absolute path of the file from current working directory
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
        String line;
        // Opens the file
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            while ((line = br.readLine()) != null) //reads the content of the file
            {
                if (!line.startsWith("Id")) {
                    count++; // counts the lines except table header
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    // appends a line to a file
    public static void appendLineToFile(String filename, String line) {
        // Gets the absolute path of the file from current working directory
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
        // Opens the file

        try (FileWriter fileWriter = new FileWriter(absoluteFilePath, true)) {
            fileWriter.append(line + "\n");
            // close the file
            fileWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // reads data from a csv file and returns table data
    public static Object[][] readTableData(String filename) {
        // Gets the absolute path of the file from current working directory
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
        String line; // reads line by line from file
        Object[][] tableDataObj = null; // holds table rows as object of objects
        ArrayList<String[]> tableRows = new ArrayList<>();  // reads table rows

        //parsing a CSV file into BufferedReader class constructor
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            while ((line = br.readLine()) != null) //returns a Boolean value
            {
                String[] arr = line.split(",");
                tableRows.add(arr);
            }
            tableDataObj = new String[tableRows.size() - 1][];
            for (int i = 0; i < tableRows.size() - 1; i++) {
                Object[] row = tableRows.get(i + 1);
                tableDataObj[i] = row;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableDataObj;
    }

    // reads data from a csv file and table headers
    public static String[] readTableHeaders(String filename) {
        // Gets the absolute path of the file from current working directory
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
        String line; // reads line by line from file
        // opens the file
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            while ((line = br.readLine()) != null) //returns a Boolean value
            {
                String[] tableHeaders = line.split(",");
                return tableHeaders; // returns first line
            }
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // if failed return null
    }

    // deletes a record by id and name
    public static boolean deleteRecord(String filename, String id, String name) {
        // Gets the absolute path of the file from current working directory
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
        boolean deleted = false;
        ArrayList<String[]> data = new ArrayList<>();
        String line;

        // handles if the user enters table header
        if (id.equals("Id") || name.equals("Name")) {
            return false;
        }

        // open the file to read all data
        try (BufferedReader br = new BufferedReader(new FileReader(absoluteFilePath))) {
            while ((line = br.readLine()) != null) //returns a Boolean value
            {
                String[] arr = line.split(",");
                data.add(arr); // reads all the lines into an arraylist;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Opens the file to write
        try (FileWriter file = new FileWriter(absoluteFilePath)) {
            // loop over librarians
            int index = 1;
            for (String[] record : data) {
                // save all except librarian to be deleted
                if (!record[0].equals(id) || !record[1].equals(name)) {
                    if (!record[0].equals("Id")) {
                        record[0] = String.valueOf(index++);
                    }
                    file.append(String.join(",", record) + "\n");
                } else {
                    deleted = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }

}
