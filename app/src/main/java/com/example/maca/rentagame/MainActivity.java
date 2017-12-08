package com.example.maca.rentagame;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.maca.rentagame.model.Game;
import com.example.maca.rentagame.utils.GameListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private GameListAdapter adapter;
    private ListView listView;
    GamesApplication application;

    private List<Map.Entry<String, Game>> games;

    private int operation = 0;
    private int pos;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("games");
    public static final String DIALOG_DATE = "GameItemActivity.DateDialog";

    public final static int REQ_CODE_CHILD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        listView = (ListView) findViewById(R.id.gamesList);

        games = new ArrayList<>();

        //application = (GamesApplication)getApplication();

        //SEED DB
        //this.seedDataBase();

        //Adapter
        adapter =  new GameListAdapter(this, games, myRef);
        listView.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    Game game = dataSnapshot.getValue(Game.class);
                    adapter.addGameLocally(dataSnapshot.getKey(), game);
                    Log.v(TAG, "create ---" + game + "----");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    adapter.updateGameLocally(dataSnapshot.getKey(), dataSnapshot.getValue(Game.class));
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    adapter.deleteGameLocally(dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                System.out.println("Hehe");
                Log.v(TAG, "in click listener!");

                Game game = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, GameEditActivity.class);

                intent.putExtra("GAME", game);

                operation = 0;
                pos = position;

                startActivityForResult(intent, REQ_CODE_CHILD);
            }
        });

        Button addGameButton = (Button) findViewById(R.id.addButton);

        addGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Game game = new Game();
                Intent intent = new Intent(MainActivity.this, GameEditActivity.class);

                intent.putExtra("GAME", game);

                operation = 1;
                startActivityForResult(intent, REQ_CODE_CHILD);
            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        System.out.println("Hehe1");
        if(requestCode == REQ_CODE_CHILD)
        {
            //System.out.println("Hehe2");
            Game game = (Game) data.getSerializableExtra("UPDATED_GAME");
            System.out.println("-- onActivityResult() --------------- game: " + game.toString()+" \n");

            if (operation == 1)//add
            {
                adapter.saveGame(-1, game);
            } else if (operation == 0)//update
            {
                adapter.saveGame(pos, game);
            }
        }

    }

    private void seedDataBase()
    {
        String gameId;

        Game g = new Game("hoi4", "2015", "Paradox");
        gameId = myRef.push().getKey();
        myRef.child(gameId).setValue(g);

        g = new Game("eu4", "2016", "Paradox");
        gameId = myRef.push().getKey();
        myRef.child(gameId).setValue(g);

        g = new Game("fifa17", "2016", "EA Sports");
        gameId = myRef.push().getKey();
        myRef.child(gameId).setValue(g);

        g = new Game("AC Origins", "2017", "Ubisoft");
        gameId = myRef.push().getKey();
        myRef.child(gameId).setValue(g);

        g = new Game("CS GO", "2014", "Valve");
        gameId = myRef.push().getKey();
        myRef.child(gameId).setValue(g);

    }

}
