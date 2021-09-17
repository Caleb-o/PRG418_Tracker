package com.coolcompany.tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public final class FileIO {

    /**
     * @return a List of PersonData from file
     */
    public static List<PersonData> read() {

        List<PersonData> pdata = new ArrayList<PersonData>();

        // Try to read the file as persondata
        try {
            File file = new File("friend_data.csv");
            Scanner reader = new Scanner(file);
            int failCount = 0;

            // Read each line
            while(reader.hasNextLine()) {
                String[] data = reader.nextLine().split(", ");
                
                // Check if correct amount of items were read
                if (Array.getLength(data) == 5) {
                    pdata.add(new PersonData(data));
                } else {
                    failCount++;
                }
            }
            
            if (failCount > 0) {
                JOptionPane.showMessageDialog(null, String.format("%d entries failed to be read.", failCount), "Read Error", JOptionPane.ERROR_MESSAGE);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Could not read file: \"friend_data.csv\".");
            return new ArrayList<PersonData>();
        }
        return pdata;
    }

    /**
     * Create a new Person record in the friend_data.csv
     * @param person
     */
    public static void create(final PersonData person) {
        try {
            FileWriter writer = new FileWriter("friend_data.csv", true);
            writer.write(person.getCSVData() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write to file: \"friend_data.csv\".");
        }
    }


    /**
     * Update the data on disk with all entries
     * @param personData
     */
    public static void update() {
        try {
            FileWriter writer = new FileWriter("friend_data.csv");
            for(PersonData person : Tracker.instance.getPersonData()) {
                writer.write(person.getCSVData() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write to file: \"friend_data.csv\".");
        }
    }
}
