/*
* Team.java description:
*  Class used for Team objects in Fantasy Football app. Constructor creates a Team object and initializes
*  all variables that a team needs. Handles storage of Player objects in a certain Team object.
*  Contains methods to edit a Team's variables and a Player object's stats. Also contains setters
*  and getters for several variables.
*/

package edu.up.cs371.schmidtj.football;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by schmidtj on 9/23/2015.
 */
public class Team implements Serializable{

    //Declaration of instance variables for each team object, arrayList of player names, and
    //Hashmap of team roster
    private String teamsName;
    private int gamesWins;
    private int gamesPlayed;
    private int gamesLost;
    private String imageID;
    public ArrayList<String> playerList;
    private HashMap<String,Player> teamRoster;

    //Team constructor: initializes instance variables for a Team object created, and creates ArrayList
    //and Hashmap of players for each team. Also, creates a manager for each team.
    protected Team(String name, int games_Wins, int games_Lost)
    {
        this.teamsName=name;

        //no negative number of wins possible
        if(games_Wins < 0)
            games_Wins = 0;
        this.gamesWins=games_Wins;

        //no negative number of losses possible
        if(games_Lost < 0)
            games_Lost = 0;
        this.gamesLost=games_Lost;

        //amount of games played for each team
        this.gamesPlayed = this.gamesLost+this.gamesWins;

        //imageID for team
        this.setImageID("green_cran");

        //Hashmap and ArrayList of players for each team
        playerList = new ArrayList<String>();
        teamRoster = new HashMap<String,Player>();

        //Create team manager and add manager to team
        Player teamManager = new Player("TheBigFish",0,0);
        teamManager.setImageID("orange_fish");

        this.addAPlayer(teamManager);
    }

    //addAPlayer method: adds a player object to the Hashmap of the team object that invoked this method
    public int addAPlayer(Player instPlayer)
    {
        //put player object from parameter in Tean Hashmap and if their name is not in the arrayList
        //add it to the arrayList. Return 1 for success
        try
        {
            teamRoster.put(instPlayer.getPlayersFullName(), instPlayer);
            if( playerList.indexOf(instPlayer.getPlayersFullName()) == -1)
                playerList.add(instPlayer.getPlayersFullName());
            return 1;
        }
        //If there is an exception or something goes wrong return 0
        finally
        {
            return 0;
        }
    }

    //getPlayer method: returns the Player object from a Team's hashmap using the player's name as the key
    public Player getPlayer(String playerName)
    {
        return teamRoster.get(playerName);
    }

    //setImageID method: assign imageName parameter to be imageID of Team object that invoked this method
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

    //getImageID method: returns imageID of Team object that invoked this method
    public String getImageID()
    {
        return this.imageID;
    }

    //updateWin method: increments a Team object's wins by 1 and calls updatePlayed to increments games
    //played for this team object by 1.
    public void updateWin()
    {
        this.gamesWins++;
        this.updatePlayed();
    }

    //getWin method: returns the wins of the Team object that invoked this method
    public int getWin()
    {
        return this.gamesWins;
    }

    //updatePlayed method: increments the gamesPlayed of Team object that invoked this method by 1
    private void updatePlayed()
    {
        this.gamesPlayed++;
    }

    //setWins method: sets a Team object's wins to the int parameter passed with method call
    public int setWins(int games_Wins)
    {
        try
        {
            this.gamesWins=games_Wins;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    //setWins method: takes a string parameter and converts it to an integer, then sets a Team object's
    //wins to the newly converted integer
    public int setWins(String games_Wins)
    {
        //return 1 for success
        try
        {
            this.gamesWins=Integer.parseInt(games_Wins);
            return 1;
        }
        //return 0 for success
        finally
        {
            return 0;
        }
    }

    //getPlayed method: return number of gamesPlayed for Team object that invoked method
    public int getPlayed()
    {
        return this.gamesPlayed;
    }

    //updateLoses method: increments a Team object's losses by 1 and calls updatePlayed to increments games
    //played for this team object by 1.
    public void updateLoses()
    {
        this.gamesLost++;
        this.updatePlayed();
    }

    //getLoses method: returns the losses of the Team object that invoked this method
    public int getLoses()
    {
        return this.gamesLost;
    }

    //setLoses method: sets a Team object's losses to the int parameter passed with method call
    public int setLoses(int games_lost)
    {
        //return 1 for success
        try
        {
            this.gamesLost=games_lost;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //setLoses method: takes a string parameter and converts it to an integer, then sets a Team object's
    //losses to the newly converted integer
    public int setLoses(String games_lost)
    {
        //return 1 for success
        try
        {
            this.gamesLost=Integer.parseInt(games_lost);
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //setTeamFullName method: takes a string parameter and sets a team objects name to the string parameter
    public int setTeamFullName(String Name)
    {
        //return 1 for success
        try
        {
            this.teamsName=Name;
            return 1;
        }
        //return 0 if exception or something goes wrong
        finally
        {
            return 0;
        }
    }

    //getTeamFullName method: returns the name of a team
    public String getTeamFullName()
    {
        return this.teamsName;
    }

    //updatePlayers method: edit the stats of a player object in a Team's hashmap
    public int updatePlayers(String playerName, int Assists, int Goals)
    {
        //if the player is not in the hashmap return 0
        if( teamRoster.get(playerName) == null)
            return 0;

        //set assists and goals. Return 1 for success.
        teamRoster.get(playerName).setAssists(Assists);
        teamRoster.get(playerName).setAssists(Goals);

        return 1;
    }

}
