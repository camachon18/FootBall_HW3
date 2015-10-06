/*
* Player.java description:
*  Class used for Player objects in the Fantasy Football app. Constructor creates a Player object and
*  initializes all variables that a player needs. Also, contains setters and getters for all attributes
*  of a Player object.
*/

package edu.up.cs371.schmidtj.football;

import java.io.Serializable;

/**
 * Created by schmidtj on 9/23/2015.
 */
public class Player implements Serializable{

    //Declaration of instance variables for each player object
    private String playersFullName;
    private int Goals;
    private int Assists;
    private String imageID;

    //Constructor for Player object
    protected Player(String name, int GoalsStat, int AssistsStat)
    {
        //Sets players name and stats to parameters
        this.playersFullName=name;
        this.Goals=GoalsStat;
        this.Assists=AssistsStat;

        //Sets imageID to green_cran (default)
        this.setImageID("green_cran");
    }

    //setImageID method: takes a string parameter and sets a Player's imageID to parameter
    public int setImageID(String imageName)
    {
        //return 1 if success
        try
        {
            this.imageID= imageName;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //getImageID method: returns imageID of Player object
    public String getImageID()
    {
        return this.imageID;
    }

    //setGoals method: sets a Player object's goals to the int parameter plus a Player's existing goals
    public int setGoals(int GoalsStat)
    {
        //return 1 for success
        try
        {
            this.Goals+=GoalsStat;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //setGoals method: takes a string as a parameter then converts string into int and sets a Player's
    //goals to be the sum of the converted int and a Player's existing goals.
    public int setGoals(String GoalsStat)
    {
        //return 1 for success
        try
        {
            this.Goals+=Integer.parseInt( GoalsStat );
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //getGoals method: returns the number of goals of a Player object
    public int getGoals()
    {
        return this.Goals;
    }

    //setAssists method: sets a Player object's assists to the int parameter plus a Player's existing assists
    public int setAssists(int AssistsStat)
    {
        //return 1 for success
        try
        {
            this.Assists+=AssistsStat;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //setAssists method: takes a string as a parameter then converts string into int and sets a Player's
    //goals to be the sum of the converted int and a Player's existing assists.
    public int setAssists(String AssistsStat)
    {
        //return 1 for success
        try
        {
            this.Assists+=Integer.parseInt( AssistsStat );
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //getAssistsStat method: returns the number of assists of a Player object
    public int getAssistsStat()
    {
        return this.Assists;
    }

    //setPlayersFullName method: takes a string as a parameter and sets a Player object's name to the
    //string parameter
    public int setPlayersFullName(String Name)
    {
        //return 1 for success
        try
        {
            this.playersFullName=Name;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //getPlayersFullName method: returns the name of a Player object
    public String getPlayersFullName()
    {
        return this.playersFullName;
    }

}
