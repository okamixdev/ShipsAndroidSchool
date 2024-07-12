package edu.utsa.cs3443.ict939_lab4;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Fleet.java is a Java class containing the information of the Fleet, with its starships and
 * crew members. It has a constructor that just assigns the name of the ship but it has
 * many functions to get the information, load new ships/members and add them to the fleet, which
 * is the main purpose of this class.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 4
 * Summer 2024
 */
public class Fleet {

    // variables
    private String name;
    private ArrayList<Starship> fleet = new ArrayList<>();

    /**
     * Main constructor for Fleet class and only assigns a name to the Fleet.
     *
     * @param name -> assigns the name of the fleet.
     */
    public Fleet(String name) {
        this.name = name;
    }

    /**
     * Setter for the name of the Fleet.
     *
     * @param name -> sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the Fleet.
     *
     * @return fleet -> returns the name.
     */
    public String getName() {
        return name;
    }

    ;

    /**
     * Getter for the fleet array of the Fleet.
     *
     * @return fleet -> returns the fleet array.
     */

    public ArrayList<Starship> getStarshipArray() {
        return fleet;
    }

    /**
     * Setter for the fleet array of the Fleet.
     *
     * @param fleet -> sets the fleet array.
     */
    public void setStarshipArray(ArrayList<Starship> fleet) {
        this.fleet = fleet;
    }


    /**
     * Getter for the size of the fleet via arrayList.
     *
     * @return -> returns the size of the fleet.
     */
    public int getSizeOfFleet() {
        return fleet.size();
    }

    /**
     * Function to add a new Starship to the fleet (arrayList named fleet).
     *
     * @param ship -> ship of type Starship to add a new starship to the fleet.
     */
    public void addStarship(Starship ship) {
        fleet.add(ship);
    }

    /*
     * Function to print each ship inside of the fleet.
     */
    public void printShips() // print ships
    {
        fleet.forEach((ship) -> {
            System.out.println(ship + "\n");
        });
    }

    /**
     * Function to load all starships/crewMembers from all of the text files inside
     * of a directory called data. After it captures each file it then make use of the
     * scanner class to scan the file and once it reads it all of the required values
     * are assigned to its respective place using new constructors for the ships and members.
     *
     * @param activity -> takes in the activity on where the files to load are.
     *                 Throws an exception in case the file is not found or readable.
     */
    public void loadStarships(MainActivity activity) {
        AssetManager manager = activity.getAssets();
        Scanner scanner = null;
        Scanner scanner2 = null;
        String filename = "fleet.csv";
        String filename2 = "personnel.csv";


        try {
            InputStream file = manager.open(filename);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                Starship ship = new Starship(tokens[0], tokens[1], tokens[2]);
                addStarship(ship);

                try {
                    InputStream file2 = manager.open(filename2);
                    scanner2 = new Scanner(file2);

                    while (scanner2.hasNextLine()) {

                        System.out.println();
                        String line2 = scanner2.nextLine();
                        String[] tokens2 = line2.split(",");

                        if (tokens2[3].equals(tokens[1])) {
                            CrewMember member = new CrewMember(tokens2[0], tokens2[1], tokens2[2], tokens2[4], tokens2[3]);
                            String[] pictureName = tokens2[0].split(" ");
                            String pictureFile = pictureName[pictureName.length-1].trim().toLowerCase();
                            member.setFile(pictureFile);
                            ship.addCrewMember(member);
                            System.out.println("added member");
                        }
                    }
//                    // close down the scanners.
//                    scanner.close();
//                    scanner2.close();
                } catch (Exception er2) {
                    System.out.println("Error Opening FILE");
//                    System.out.println(er2);
                }
            }

        } catch (IOException er1) {
            System.out.println("Error Opening FILE");
//            System.out.println(er1);
        }
    }

    /**
     * @return -> override of the toString method and just prints out the fleet name and the ships with all
     * the crew members information.
     */
    public String toString() {
        System.out.println("-----------------------------------\n" + name + "\n-----------------------------------\n\n");
        printShips();
        return "";
    }

}
