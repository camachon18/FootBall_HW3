/*
* MainActivity.java description:
 * MainActivity.java implements the design of the activity_main.xml layout. Allows user to create a
 * new team, look at existing teams, edit the teams stats, and look at team pictures. It implements
 * View.OnCLickLisetener and AdapterView.OnItemSelectedListener. Also, contains instances of the Team class.
 * Contains a button to take the user to the second activity.
*/

package edu.up.cs371.schmidtj.football;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    //Declaration of variables to be used throughout class
    //Buttons: widget that performs an action when clicked
    public Button clearStat;
    public Button addAnotherTeam;
    public ImageButton clickToTeamRoster;
    //Edit Text: widget that allows users to type
    public EditText teamWins;
    public EditText teamLoses;
    public EditText teamName;
    //Array Lists to store strings for spinner
    public ArrayList<String> listOfTeams;
    public ArrayList<String> listImageSelector;
    //Spinner: widget that allows users to select an option fom a drop down menu
    public Spinner teamSpinner;
    public Spinner imageTeamSelector;
    //Hash map to store teams
    public HashMap<String,Team> Teams;


    //onCreate method: Initialization of variables, and addition of elements to
    //arrayLists and Hashmap
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialize Buttons to appropriate button in the layout
        clearStat= (Button) findViewById(R.id.clearStat);
        addAnotherTeam= (Button) findViewById(R.id.createTeam);
        clickToTeamRoster= (ImageButton) findViewById(R.id.to_new_activity);
        //Set on click listeners for buttons
        clearStat.setOnClickListener(this);
        addAnotherTeam.setOnClickListener(this);
        clickToTeamRoster.setOnClickListener(this);
        //Initialize EditText fields to appropriate text field in layout
        teamWins= (EditText) findViewById(R.id.teamWins);
        teamLoses= (EditText) findViewById(R.id.teamLoses);
        teamName= (EditText) findViewById(R.id.teamName);


        //Instance of Team class, initializes name, stats, and imageID
        Team defualtTeam1 = new Team("Dragons",0,0);
        defualtTeam1.setImageID("blue_dragons");
        //Instance of Team class, initializes name, stats, and imageID
        Team defualtTeam2 = new Team("Butterfly",0,0);
        defualtTeam2.setImageID("orange_butterfly");

        //Initialize hashmap and add the two teams to the hashmap
        Teams=new HashMap<String,Team>();

        Teams.put("Dragons",defualtTeam1);
        Teams.put("Butterfly",defualtTeam2);


        //Initialize arrayList and add the two team names
        listOfTeams = new ArrayList<String>();
        listOfTeams.add("Dragons");
        listOfTeams.add("Butterfly");

        //Initialize arrayList and add names of team pictures
        listImageSelector = new ArrayList<String>();
        listImageSelector.add("orange_butterfly");
        listImageSelector.add("pink_butterfly");
        listImageSelector.add("green_cran");
        listImageSelector.add("blue_dragons");
        listImageSelector.add("green_dragons");
        listImageSelector.add("red_dragons");
        listImageSelector.add("orange_fish");
        listImageSelector.add("blue_pegasus");


        //Initialize Spinners to appropriate spinner in layout, create arrayAdapter for Spinner,
        //set the adapter to appropriate spinner, and setOnItemSelectedListener for spinners
        teamSpinner = (Spinner) findViewById(R.id.teamSpinner);
        ArrayAdapter<String> teamSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listOfTeams);
        teamSpinner.setAdapter(teamSpinnerAdapter);

        imageTeamSelector = (Spinner) findViewById(R.id.imageTeamSelector);
        ArrayAdapter<String> imageTeamSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imageTeamSelector.setAdapter(imageTeamSelectorAdapter);

        teamSpinner.setOnItemSelectedListener(this);
        imageTeamSelector.setOnItemSelectedListener(this);

        //Assign index of Dragons in Hashmap to variable index, then set the first option in the
        //imageTeamSelector spinner to be Dragons
        int index = listImageSelector.indexOf(Teams.get("Dragons").getImageID());
        imageTeamSelector.setSelection( index );
    }



    //onClick method: handles actions to be performed when a button is clicked
    @Override
    public void onClick(View view) {

        //if clearStat button is clicked
        if(view == clearStat)
        {
            //Set wins, losses, and teamName text fields to blank. Set teamSpinner to index 0
            teamWins.setText("");
            teamLoses.setText("");
            teamName.setText("");
            teamSpinner.setSelection(0);
        }

        //if clickToTeamRoster is clicked
        if(view == clickToTeamRoster)
        {
            //Create intent to move to second activity
            Intent intent = new Intent(this, TeamBoard.class);
            //Send data of team selected from teamSpinner in Hashmap to next activity
            intent.putExtra("aTeam", Teams.get( teamSpinner.getSelectedItem().toString() ) );
            //intent.putStringArrayListExtra("Player",listOfTeams);

            //intent.putExtra("hi", Teams);

            //Start Activity using intent created and request code 100
            startActivityForResult(intent,100);

            //startActivity(intent);
        }

        //if addAnotherTeam is clicked
        if(view == addAnotherTeam)
        {

            //if the editText fields for a teams name, wins, and losses is empty, do nothing because these are needed to create team
            if(String.valueOf(teamWins.getText()).isEmpty() || String.valueOf(teamLoses.getText()).isEmpty() || String.valueOf(teamName.getText()).isEmpty())
                return;
            //if a new team name is entered then create a temp Team object, and set the name, wins, and losses to what user entered in
            //text fields. Set imageID to the item selected in the imageTeamSelector spinner. Then, add new team to Hashmap and arrayList
            if( listOfTeams.indexOf( String.valueOf(teamName.getText()) ) == -1 )
            {
                Team pTemp = new Team(String.valueOf(teamName.getText()), Integer.parseInt(String.valueOf(teamWins.getText())), Integer.parseInt(String.valueOf(teamLoses.getText())));
                pTemp.setImageID( imageTeamSelector.getSelectedItem().toString() );

                Teams.put(String.valueOf(teamName.getText()), pTemp);

                listOfTeams.add(String.valueOf(teamName.getText()));
            }
            else
            //else just edit the stats of existing team
            {
                Teams.get(String.valueOf(teamName.getText())).setLoses(String.valueOf(teamLoses.getText()));
                Teams.get(String.valueOf(teamName.getText())).setWins(String.valueOf(teamWins.getText()));
                Teams.get(String.valueOf(teamName.getText())).setImageID( imageTeamSelector.getSelectedItem().toString() );
            }

            //Set the item selected in teamSpinner to be name of team created or edited
            teamSpinner.setSelection( listOfTeams.indexOf( String.valueOf(teamName.getText()) ));

        }

    }

    //onActivityResult method: method with instructions for receiving data from second Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //if request code and result code from second activity are 100 and 1 respectively, then create
        //a temp Team object and assign it the data of the Team object sent from the second activity and
        //put the temp team object into the Teams Hashmap
        if (requestCode == 100)
        {
            if (resultCode == 1)
            {
                Team tTemp = (Team)  data.getSerializableExtra("returnATeam");
                Teams.put( tTemp.getTeamFullName().toString(), tTemp);
            }
        }
    }

    //onItemSelected method: handles actions to be performed when an item from a spinner is selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //If an item from the teamSpinner is selected display that teams name, wins, and losses in text field.
        //Then set the item selected in the imageTeamSelector spinner to be the imageID of the team selected
        if(adapterView == teamSpinner)
        {
            teamLoses.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getLoses()));
            teamName.setText(teamSpinner.getSelectedItem().toString());
            teamWins.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getWin()));

            int index = listImageSelector.indexOf(Teams.get(teamSpinner.getSelectedItem().toString()).getImageID());
            imageTeamSelector.setSelection( index );
        }
        //If an item from the imageTeamSelector spinner is selected, then set the appropriate image that corresponds to the
        //item selected to be displayed on the clickToTeamRoster ImageView Button
        if(adapterView == imageTeamSelector)
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + imageTeamSelector.getSelectedItem().toString(), null, null);
            clickToTeamRoster.setImageResource(id);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
