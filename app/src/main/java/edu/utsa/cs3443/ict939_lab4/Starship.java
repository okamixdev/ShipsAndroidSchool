package edu.utsa.cs3443.ict939_lab4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Starship.java is a Java class containing the information of a Starship. It contains a constructor to
 * create a new instance of a ship. Different variables and to be highlighted it has an arrayList of
 * CrewMember in order to add members to the fleet, which is the main purpose of this class.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 4
 * Summer 2024
 */

public class Starship implements Parcelable {
    // variables
    private String name;
    private String registry;
    private String starshipClass;
    private ArrayList<CrewMember> crewMembers = new ArrayList<>();

    /**
     * Constructor to create a new Starship and assign name, registry and starshipClass.
     *
     * @param name          -> name of the starship.
     * @param registry      -> registry of the starship.
     * @param starshipClass -> starshipClass of the starship.
     */
    public Starship(String name, String registry, String starshipClass) {
        this.name = name;
        this.registry = registry;
        this.starshipClass = starshipClass;
    }

    protected Starship(Parcel in) {
        name = in.readString();
        registry = in.readString();
        starshipClass = in.readString();
        ClassLoader classLoader = CrewMember.class.getClassLoader();
        crewMembers = in.readArrayList(classLoader);
    }

    public static final Creator<Starship> CREATOR = new Creator<Starship>() {
        @Override
        public Starship createFromParcel(Parcel in) {
            return new Starship(in);
        }

        @Override
        public Starship[] newArray(int size) {
            return new Starship[size];
        }
    };

    /**
     * Setter for the name variable.
     *
     * @param name -> sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    ;

    /**
     * Setter for the registry variable.
     *
     * @param registry -> sets the registry.
     */
    public void setRegistry(String registry) {
        this.registry = registry;
    }

    ;

    /**
     * Setter for the starshipClass variable.
     *
     * @param starshipClass -> sets the starshipClass.
     */
    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    ;

    /**
     * Setter for the crewMembers array variable.
     *
     * @param crewMembers -> sets the crewMembers.
     */
    public void setCrewMemberArray(ArrayList<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    ;

    /**
     * Getter for the name variable.
     *
     * @return -> returns the name.
     */
    public String getName() {
        return name;
    }

    ;

    /**
     * Getter for the registry variable.
     *
     * @return -> returns the registry.
     */
    public String getRegistry() {
        return registry;
    }

    ;

    /**
     * Getter for the starshipClass variable.
     *
     * @return -> returns the starshipClass.
     */
    public String getStarshipClass() {
        return starshipClass;
    }

    ;

    /**
     * Getter for the crewMembers variable.
     *
     * @return -> returns the crewMembers.
     */
    public ArrayList<CrewMember> getCrewMemberArray() {
        return crewMembers;
    }



    /**
     * Function to add new crew members to the starship by adding them to
     * the arrayList called crewMembers.
     *
     * @param member -> takes in a member variable of type CrewMember.
     */
    public void addCrewMember(CrewMember member) {
        crewMembers.add(member);
    }

    /**
     * Getter gor the size of the crewMembers array.
     *
     * @return -> returns the size of the array (total personel on the ship).
     */
    public int getNumberOfPersonnel() {
        return crewMembers.size();
    }

    /**
     * Public function to print out the names of each of the members inside of the ship.
     */
    public void printCrew() {
        crewMembers.forEach((member) -> {
            System.out.println(member + "\n");
        });
    }

    /**
     * @return -> override of the toString method and returns the formatted version of the
     * starship and then the crew members assigned to it.
     */
    public String toString() {
        System.out.println(name + ", " + starshipClass + ". Registry: " + registry + "\n" + getNumberOfPersonnel() +
                " crew members assigned.\n\n");
        printCrew();
        return "";

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(registry);
        dest.writeString(starshipClass);
        dest.writeList(crewMembers);
    }



}
