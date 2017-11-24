package com.example.maca.rentagame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maca.rentagame.model.Game;
import com.example.maca.rentagame.utils.GameListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private GameListAdapter adapter;
    private List<Game> games;
    private ListView listView;

    private int selectedId = 0;

    public static String name;
    public static String releaseYear;
    public static String producer;
    public static Integer id;

    public final static int REQ_CODE_CHILD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.gamesList);

        games = new ArrayList<>();
        games.add(new Game(1, "hoi4", "2015", "Paradox"));
        games.add(new Game(2, "eu4", "2016", "Paradox"));
        games.add(new Game(3, "fifa17", "2016", "EA Sports"));
        games.add(new Game(4, "AC Origins", "2017", "Ubisoft"));


        adapter = new GameListAdapter(this, games);

        listView.setAdapter(adapter);

        Log.v(TAG, "showed listview");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.v(TAG, "in click listener!");

//                Game game = adapter.getItem(position);
//
//                Intent intent = new Intent(MainActivity.this, GameEditActivity.class);
//
//                intent.putExtra("GAME", game);
//
//                startActivityForResult(intent, REQ_CODE_CHILD);
            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_CHILD)
        {
            Game game = (Game) data.getExtras().getSerializable("UPDATED_GAME");
            Log.v(TAG, "-- onActivityResult() --------------- game: " + game.toString()+" \n");
            adapter.updateGame(game);
        }

    }
}
