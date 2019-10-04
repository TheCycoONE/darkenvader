package com.sealbaker.darkenvader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

class Player extends Actor {
    private int score;
    private Direction direction = Direction.DOWN;

    private final EnumMap<Direction, BufferedImage> sprites;

    public Player() throws IOException {
        sprites = new EnumMap<>(Direction.class);

        sprites.put(Direction.UP, ImageIO.read(getClass().getResource("PCU0.gif")));
        sprites.put(Direction.LEFT, ImageIO.read(getClass().getResource("PCL0.gif")));
        sprites.put(Direction.DOWN, ImageIO.read(getClass().getResource("PCD0.gif")));
        sprites.put(Direction.RIGHT, ImageIO.read(getClass().getResource("PCR0.gif")));
    }

    public void reset(int dfclty) {
        x = 700;
        y = 700;
        speed = 4;
        score = 0;
        switch (dfclty) {
        case 1:
            spirit = 100;
            attack = 20;
            defence = 20;
            break;
        case 2:
            spirit = 50;
            attack = 15;
            defence = 15;
            break;
        case 3:
            spirit = 25;
            attack = 10;
            defence = 10;
            break;
        default:
            spirit = 100;
            attack = 100;
            defence = 100;
            break;
        }
        mapPicURL = "PCD0.gif";
    }

    public BufferedImage getSprite() {
        return sprites.get(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int change) {
        score = score + change;
    }
}
