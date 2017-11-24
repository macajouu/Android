package com.example.maca.rentagame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static int globalId = 1;
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
    }
}
