package edu.utsa.cs3443.ict939_lab4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * CrewMember.java is a Java class containing the information of crew members of the fleet. It serves
 * the purpose of creating new members and assigning information to later be added to a Starship.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 4
 * Summer 2024
 */
public class CrewMember implements Parcelable {

    // variables
    private String name;
    private String position;
    private String rank;
    private String species;
    private String assignment;
    private String file;

    /**
     * This is the constructor for the CrewMember Class. (all params)
     *
     * @param name -> sets the name.
     * @param position -> sets the position.
     * @param rank -> sets the rank.
     * @param species -> sets the species.
     * @param assignment -> sets the assignment.
     */
    public CrewMember(String name, String position, String rank, String species, String assignment)
    {
        this.name = name;
        this.position = position;
        this.rank = rank;
        this.species = species;
        this.assignment = assignment;
    }

    /**
     * This is the second constructor for the CrewMember Class. (all but assignment)
     *
     * @param name -> sets the name.
     * @param position -> sets the position.
     * @param rank -> sets the rank.
     * @param species -> sets the species.
     */
    public CrewMember(String name, String position, String rank, String species)
    {
        this.name = name;
        this.position = position;
        this.rank = rank;
        this.species = species;
    }


    protected CrewMember(Parcel in) {
        name = in.readString();
        position = in.readString();
        rank = in.readString();
        species = in.readString();
        assignment = in.readString();
        file = in.readString();
    }

    public static final Creator<CrewMember> CREATOR = new Creator<CrewMember>() {
        @Override
        public CrewMember createFromParcel(Parcel in) {
            return new CrewMember(in);
        }

        @Override
        public CrewMember[] newArray(int size) {
            return new CrewMember[size];
        }
    };

    /**
     * Setter for the name variable.
     *
     * @param file -> assigns a new file.
     */
    public void setFile(String file) {this.file = file;}
    /**
     * Setter for the name variable.
     *
     * @param name -> assigns a new name.
     */
    public void setName(String name) {this.name = name;}
    /**
     * Setter for the position variable.
     *
     * @param position -> assigns a new position.
     */
    public void setPosition(String position) {this.position = position;}
    /**
     * Setter for the rank variable.
     *
     * @param rank -> assigns a new rank.
     */
    public void setRank(String rank) {this.rank = rank;}
    /**
     * Setter for the species variable.
     *
     * @param species -> assigns a new species.
     */
    public void setSpecies(String species) {this.species = species;}
    /**
     * Setter for the assignment variable.
     *
     * @param assignment -> assigns a new assignment.
     */
    public void setAssignment(String assignment) {this.assignment = assignment;}


    /**
     * Setter for the name variable.
     *
     * @return file -> returns the file name.
     */
    public String getFile() {return file;}
    /**
     * Getter for the name variable.
     *
     * @return -> returns the name
     */
    public String getName() {return name;}
    /**
     * Getter for the position variable.
     *
     * @return -> returns the position
     */
    public String getPosition() {return position;}
    /**
     * Getter for the rank variable.
     *
     * @return -> returns the rank
     */
    public String getRank() {return rank;}
    /**
     * Getter for the species variable.
     *
     * @return -> returns the species
     */
    public String getSpecies() {return species;}
    /**
     * Getter for the assignment variable.
     *
     * @return -> returns the assignment
     */
    public String getAssignment() {return assignment;}




    /**
     * @return -> returns the information of the crew member in a user friendly format.
     */
    public String toString ()
    {
        return "- " + name + " (" + rank + ") - " + position + " [" + species + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(position);
        dest.writeString(rank);
        dest.writeString(species);
        dest.writeString(assignment);
        dest.writeString(file);
    }
}
