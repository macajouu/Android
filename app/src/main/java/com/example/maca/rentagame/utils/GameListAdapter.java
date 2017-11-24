package com.example.maca.rentagame.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maca.rentagame.R;
import com.example.maca.rentagame.model.Game;

import java.util.List;

/**
 * Created by Maca on 11/24/2017.
 */

public class GameListAdapter extends BaseAdapter
{
    private Context context;
    private List<Game> games;

    public GameListAdapter(Context context, List<Game> games)
    {
        this.context = context;
        this.games = games;
    }

    @Override
    public int getCount()
    {
        return games.size();
    }

    @Override
    public Game getItem(int position)
    {
        return games.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public Game getGameById(Integer id)
    {
        for(Game g : games)
        {
            if(g.getId() == id)
            {
                return g;
            }
        }
        return  games.get(id);
    }

    public void addGame(Game game)
    {
        games.add(game);
    }

    public void updateGame(Game game)
    {
        Game g = getGameById(game.getId());

        g.setName(game.getName());
        g.setReleaseYear(game.getReleaseYear());
        g.setProducer(game.getProducer());
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View view = View.inflate(this.context, R.layout.game, null);

        TextView nameView = (TextView) view.findViewById(R.id.nameTextView);
        TextView yearView = (TextView) view.findViewById(R.id.yearTextView);
        TextView producerView = (TextView) view.findViewById(R.id.producerTextView);

        Game currentGame = games.get(pos);

        nameView.setText(currentGame.getName());
        yearView.setText(currentGame.getReleaseYear());
        producerView.setText(currentGame.getProducer());

        view.setTag(currentGame);

        return view;
    }
}
