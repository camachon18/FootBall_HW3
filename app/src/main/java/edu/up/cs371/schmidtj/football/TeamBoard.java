/*
* TeamBoard.java description:
 * TeamBoard.java implements the design of the activity_team_board.xml layout. Allows user to create
 * a new player, look at existing players,edit the player's stats, and look at a player's picture. It implements
 * View.OnCLickLisetener and AdapterView.OnItemSelectedListener. Also, contains instances of the Team and
 * Player class. Contains a button to take the user back to the Main Activity.
*/

package edu.up.cs371.schmidtj.football;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class TeamBoard extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    //Declaration of variables to be used throughout class
    //Intent to send/receive data and ImageView to display a player's image
    Intent intent;
    ImageView playerImage;

    //Edit Text: widget that allows users to type
    public EditText playerGoals;
    public EditText playerAssists;
    public EditText playerName;

    //Button: widget that performs an action when clicked
    public Button addAnotherPlayer;

    //Instance of Team class
    public Team theTeam;

    //Spinner: widget that allows users to select an option fom a drop down menu
    public Spinner playerSpinner;
    public Spinner imagePlayerSelector;
    //Array Lists to store strings for spinner
    public ArrayList<String> listImageSelector;

    //onCreate method: Initialization of variables, instructions to receiving data, and addition of
    // elements to arrayLists.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_board);

        //Initialize Button to appropriate button in the layout and setOnClickListener
        addAnotherPlayer= (Button) findViewById(R.id.createPlayer);
        addAnotherPlayer.setOnClickListener(this);

        //Initialize EditText fields to appropriate text field in layout
        playerGoals= (EditText) findViewById(R.id.playerGoals);
        playerAssists= (EditText) findViewById(R.id.playerAssists);
        playerName= (EditText) findViewById(R.id.playerName);

        //Receive intent data from MainActivity
        intent = getIntent();
        //Initialize playerImage to appropriate widget in layout
        playerImage = (ImageView) findViewById( R.id.playerImage );

        //Get the information of the team sent from MainActivity and assign to theTeam
        theTeam = (Team)  intent.getSerializableExtra("aTeam");

        //Display stats of TheBigFish player
        playerGoals.setText( theTeam.getPlayer("TheBigFish").getPlayersFullName() );
        playerAssists.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getGoals()) );
        playerName.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getAssistsStat()) );

        //Set the image displayed in the imageView to be image of TheBigFish
        int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + theTeam.getPlayer("TheBigFish").getImageID(), null, null);
        playerImage.setImageResource(id);

        //Initialize arrayList and add names of player photos
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
        playerSpinner = (Spinner) findViewById(R.id.playerSpinner);
        ArrayAdapter<String> playerSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, theTeam.playerList);
        playerSpinner.setAdapter(playerSpinnerAdapter);

        imagePlayerSelector = (Spinner) findViewById(R.id.imagePlayerSelector);
        ArrayAdapter<String> imagePlayerSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imagePlayerSelector.setAdapter(imagePlayerSelectorAdapter);

        playerSpinner.setOnItemSelectedListener(this);
        imagePlayerSelector.setOnItemSelectedListener(this);
    }

    //return_back_click method: sends data of a Team object back to MainActivity with resultCode: 1
    public void return_back_click(View view)
    {
        intent.putExtra("returnATeam",theTeam);
        setResult(1,intent);
        finish();
    }

    //onClick method: handles actions to be performed when a button is clicked
    @Override
    public void onClick(View view) {

        //if addAnotherPlayer button is clicked
        if(view == addAnotherPlayer)
        {

            //if the editText fields for a player's name, goals, and assists is empty, do nothing because these are needed to create a player
            if(String.valueOf(playerGoals.getText()).isEmpty() || String.valueOf(playerAssists.getText()).isEmpty() || String.valueOf(playerName.getText()).isEmpty())
                return;

            //if a new player name is entered then create a temp Player object, and set the name, goals, and assists to what user entered in
            //text fields. Set imageID to the item selected in the imagePlayerSelector spinner. Then, call the addAPlayer method from the
            //Team class which adds the player to a Hashmap
            if(theTeam.playerList.indexOf(String.valueOf(playerName.getText())) == -1 )
            {
                Player pTemp = new Player(String.valueOf(playerName.getText()), Integer.parseInt(String.valueOf(playerGoals.getText())), Integer.parseInt(String.valueOf(playerAssists.getText())));
                pTemp.setImageID( imagePlayerSelector.getSelectedItem().toString() );

                theTeam.addAPlayer(pTemp);

            }
            else
            //else just edit the stats of existing player
            {
                theTeam.getPlayer(String.valueOf(playerName.getText())).setGoals( Integer.parseInt(String.valueOf(playerGoals.getText())) );
                theTeam.getPlayer(String.valueOf(playerName.getText())).setAssists(Integer.parseInt(String.valueOf(playerAssists.getText())) );
                theTeam.getPlayer(String.valueOf(playerName.getText())).setImageID( imagePlayerSelector.getSelectedItem().toString() );
            }

            //Set the item selected in playerSpinner to be name of player created or edited
            playerSpinner.setSelection( theTeam.playerList.indexOf(String.valueOf(playerName.getText())) );

        }

    }

    //onItemSelected method: handles actions to be performed when an item from a spinner is selected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //If an item from the playerSpinner is selected display that player's name, goals, and assists in text field.
        //Then set the item selected in the imagePlayerSelector spinner to be the imageID of the player selected,
        //and display the appropriate player image in the playerImage ImageView
        if(adapterView == playerSpinner)
        {
            playerGoals.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getGoals()));
            playerName.setText(playerSpinner.getSelectedItem().toString());
            playerAssists.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getAssistsStat()));

            int index = listImageSelector.indexOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID());
            imagePlayerSelector.setSelection( index );

            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID(), null, null);
            playerImage.setImageResource(id);
        }
        //If an item from the imagePlayerSelector spinner is selected, then set the appropriate image that corresponds to the
        //item selected to be displayed on the playerImage ImageView
        if(adapterView == imagePlayerSelector)
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  imagePlayerSelector.getSelectedItem().toString(), null, null);
            playerImage.setImageResource(id);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
