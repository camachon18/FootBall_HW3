package edu.up.cs371.schmidtj.football;

import java.io.Serializable;

/**
 * Created by schmidtj on 9/23/2015.
 */
public class Player implements Serializable{

    private String playersFullName;
    private int Goals;
    private int Assists;
    private String imageID;

    protected Player(String name, int GoalsStat, int AssistsStat)
    {
        this.playersFullName=name;
        this.Goals=GoalsStat;
        this.Assists=AssistsStat;

        this.setImageID("green_cran");
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

    public int setGoals(int GoalsStat)
    {
        try
        {
            this.Goals+=GoalsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int setGoals(String GoalsStat)
    {
        try
        {
            this.Goals+=Integer.parseInt( GoalsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int getGoals()
    {
        return this.Goals;
    }

    public int setAssists(int AssistsStat)
    {
        try
        {
            this.Assists+=AssistsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int setAssists(String AssistsStat)
    {
        try
        {
            this.Assists+=Integer.parseInt( AssistsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public int getAssistsStat()
    {
        return this.Assists;
    }

    public int setPlayersFullName(String Name)
    {
        try
        {
            this.playersFullName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    public String getPlayersFullName()
    {
        return this.playersFullName;
    }

}
