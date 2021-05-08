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

//    private void deleteLibrarian(String id, String name) throws IOException {
//        // handles if the user enters table header
//        if (id.equals("Id") || name.equals("Name")) {
//            // Shows error message
//            JOptionPane.showMessageDialog(this, "Librarian not found", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        String filename = "librarians.csv";
//        // Gets the absolute path of the file from current working directory
//        String absoluteFilePath = System.getProperty("user.dir") + File.separator + "database" + File.separator + filename;
//
//        boolean deleted = false;
//        // Opens the file
//        try (FileWriter file = new FileWriter(absoluteFilePath)) {
//            // loop over librarians
//            int index = 1;
//            for (String[] librarian : librarians) {
//                // save all except librarian to be deleted
//                if (!librarian[0].equals(id) || !librarian[1].equals(name)) {
//                    if (!librarian[0].equals("Id")) {
//                        librarian[0] = String.valueOf(index++);
//                    }
//                    file.append(String.join(",", librarian) + "\n");
//                } else {
//                    deleted = true;
//                }
//
//            }
//        }
//
//        // Resets the input fields
//        this.nameTextField.setText("");
//        this.idTextField.setText("");
//
//        // Show appropriate message
//        if (deleted == true) {
//            // Shows sucess message
//            JOptionPane.showMessageDialog(this, "Librarian Deleted Successfully");
//        } else {
//            // Shows error message
//            JOptionPane.showMessageDialog(this, "Librarian not found", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//    }
}
