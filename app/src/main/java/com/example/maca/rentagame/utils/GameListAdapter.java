package com.example.maca.rentagame.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.maca.rentagame.GamesApplication;
import com.example.maca.rentagame.R;
import com.example.maca.rentagame.model.Game;
import com.google.firebase.database.DatabaseReference;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maca on 11/24/2017.
 */

public class GameListAdapter extends BaseAdapter
{
    private Context context;
    private List<Map.Entry<String, Game>> games;
    DatabaseReference myRef;

    public GameListAdapter(Context context, List<Map.Entry<String, Game>> games, DatabaseReference myRef)
    {
        this.context = context;
        this.games = games;
        this.myRef = myRef;
    }

    @Override
    public int getCount()
    {
        return games.size();
    }

    @Override
    public Game getItem(int position)
    {
        return games.get(position).getValue();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public void addGameLocally(String key, Game g)
    {
        Map.Entry<String, Game> entry = new AbstractMap.SimpleEntry<String, Game>(key, g);
        games.add(entry);
        notifyDataSetChanged();
    }
    public void updateGameLocally(String key ,Game g)
    {
        for(Map.Entry<String,Game> entry : games)
        {
            if(entry.getKey() == key)
            {
                entry.setValue(g);
            }
        }
        notifyDataSetChanged();
    }
    public void deleteGameLocally(String key)
    {
        int i=0;
        while(true)
        {
            if(games.get(i).getKey() == key)
            {
                games.remove(i);
                break;
            }
            i++;
        }
        notifyDataSetChanged();
    }

    public void saveGame(int index ,Game g)
    {
        if(index == -1)//create
        {
            String id = myRef.push().getKey();
            myRef.child(id).setValue(g);
        }
        else//update
        {
            String id = games.get(index).getKey();
            myRef.child(id).setValue(g);
        }

        notifyDataSetChanged();
    }



    private void deleteGame(int index)
    {
        String key = games.get(index).getKey();
        myRef.child(key).removeValue();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View view = View.inflate(this.context, R.layout.game, null);

        TextView nameView = (TextView) view.findViewById(R.id.nameTextView);
        TextView yearView = (TextView) view.findViewById(R.id.yearTextView);
        TextView producerView = (TextView) view.findViewById(R.id.producerTextView);

        Game currentGame = games.get(pos).getValue();

        nameView.setText(currentGame.getName());
        yearView.setText(currentGame.getReleaseYear());
        producerView.setText(currentGame.getProducer());

        view.setTag(currentGame);

        final int finalPos = pos;

        //delete button
        Button deleteButtonView = (Button) view.findViewById(R.id.deleteButton);
        deleteButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGame(finalPos);
            }
        });

        return view;
    }
}
