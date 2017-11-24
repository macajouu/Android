package com.example.maca.rentagame.model;

import java.io.Serializable;

/**
 * Created by Maca on 11/24/2017.
 */

public class Game implements Serializable
{
    private Integer id;
    private String name;
    private String releaseYear;
    private String producer;

    public Game()
    {
        id = 0;
        name = releaseYear = producer = "";
    }

    public Game(Integer id, String name, String releaseYear, String producer) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.producer = producer;
    }

    public Integer getId() { return id; }

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}
