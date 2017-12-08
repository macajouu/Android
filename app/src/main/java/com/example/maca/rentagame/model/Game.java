package com.example.maca.rentagame.model;

import java.io.Serializable;

/**
 * Created by Maca on 11/24/2017.
 */

public class Game implements Serializable
{
    private String name;
    private String releaseYear;
    private String producer;

    public Game()
    {
        name = releaseYear = producer = "";
    }

    public Game(String name, String releaseYear, String producer) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getProducer() {
        return producer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Game{" +
                ", name='" + name + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}
