package com.example.maca.rentagame;

import android.app.Application;

import com.example.maca.rentagame.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maca on 12/4/2017.
 */

public class GamesApplication extends Application
{
    private List<Game> games;

    public GamesApplication()
    {
        games = new ArrayList<>();
        games.add(new Game("hoi4", "2015", "Paradox"));
        games.add(new Game("eu4", "2016", "Paradox"));
        games.add(new Game("fifa17", "2016", "EA Sports"));
        games.add(new Game("AC Origins", "2017", "Ubisoft"));
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
