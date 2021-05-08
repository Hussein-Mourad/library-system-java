/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hussein
 */
public class FileOperations {

    public static void main(String[] args) {
        System.out.println(getFileCount("kljlkjlk.csv"));
    }

    // Used to get the number of lines in a file which helps in generating id for each record
    private static int getFileCount(String filename) {
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
}
