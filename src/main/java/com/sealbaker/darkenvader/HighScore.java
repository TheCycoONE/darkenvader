package com.sealbaker.darkenvader;

import java.io.Serializable;

public class HighScore implements Serializable {
    private static final long serialVersionUID = 1l;

    private String name;

    private int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
