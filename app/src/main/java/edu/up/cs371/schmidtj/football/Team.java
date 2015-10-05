package edu.up.cs371.schmidtj.football;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by schmidtj on 9/23/2015.
 */
public class Team implements Serializable{

    private String teamsName;
    private int gamesWins;
    private int gamesPlayed;
    private int gamesLost;
    private String imageID;
    public ArrayList<String> playerList;
    private HashMap<String,Player> teamRoster;

    protected Team(String name, int games_Wins, int games_Lost)
    {
        this.teamsName=name;

        if(games_Wins < 0)
            games_Wins = 0;
        this.gamesWins=games_Wins;

        if(games_Lost < 0)
            games_Lost = 0;
        this.gamesLost=games_Lost;

        this.gamesPlayed = this.gamesLost+this.gamesWins;

        this.setImageID("green_cran");

        playerList = new ArrayList<String>();
        teamRoster = new HashMap<String,Player>();

        Player teamManager = new Player("TheBigFish",0,0);
        teamManager.setImageID("orange_fish");

        this.addAPlayer(teamManager);
    }

    public int addAPlayer(Player instPlayer)
    {
        try
        {
            teamRoster.put(instPlayer.getPlayersFullName(), instPlayer);
            if( playerList.indexOf(instPlayer.getPlayersFullName()) == -1)
                playerList.add(instPlayer.getPlayersFullName());
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public Player getPlayer(String playerName)
    {
        return teamRoster.get(playerName);
    }

    public int setImageID(String imageName)
    {
        try
        {
            this.imageID= imageName;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public String getImageID()
    {
        return this.imageID;
    }

    public void updateWin()
    {
        this.gamesWins++;
        this.updatePlayed();
    }

    public int getWin()
    {
        return this.gamesWins;
    }

    private void updatePlayed()
    {
        this.gamesPlayed++;
    }

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

    public int setWins(String games_Wins)
    {
        try
        {
            this.gamesWins=Integer.parseInt(games_Wins);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int getPlayed()
    {
        return this.gamesPlayed;
    }

    public void updateLoses()
    {
        this.gamesLost++;
        this.updatePlayed();
    }

    public int getLoses()
    {
        return this.gamesLost;
    }

    public int setLoses(int games_lost)
    {
        try
        {
            this.gamesLost=games_lost;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int setLoses(String games_lost)
    {
        try
        {
            this.gamesLost=Integer.parseInt(games_lost);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int setTeamFullName(String Name)
    {
        try
        {
            this.teamsName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public String getTeamFullName()
    {
        return this.teamsName;
    }

    public int updatePlayers(String playerName, int Assists, int Goals)
    {
        if( teamRoster.get(playerName) == null)
            return 0;

        teamRoster.get(playerName).setAssists(Assists);
        teamRoster.get(playerName).setAssists(Goals);

        return 1;
    }

}
