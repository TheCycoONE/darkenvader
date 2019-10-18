package com.sealbaker.darkenvader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class WorldMap {
    private final BufferedImage worldMap;
    private final BufferedImage worldMapMask;

    private final int WALL = 0xff000000;
    private final int POISON = 0xffff00ff;

    public WorldMap() throws IOException {
        worldMap = ImageIO.read(getClass().getResource("WorldMap.gif"));
        worldMapMask = ImageIO.read(getClass().getResource("WorldMask.gif"));
    }

    public BufferedImage getWorldMapImage() {
        return worldMap;
    }

    private int maskAt(int x, int y) {
        return worldMapMask.getRGB(x, y);
    }

    public boolean isRectTouchingWall(int x, int y, int w, int h) {
        return isRectTouching(x, y, w, h, WALL);
    }

    public boolean isRectTouchingPoison(int x, int y, int w, int h) {
        return isRectTouching(x, y, w, h, POISON);
    }

    private boolean isRectTouching(int x, int y, int w, int h, int target) {
        for (int tx = x; tx < x + w; tx++) {
            if (maskAt(tx, y) == target) { return true; }
            if (maskAt(tx, y + h - 1) == target) { return true; }
        }
        for (int ty = y; ty < y + h; ty++) {
            if (maskAt(x, ty) == target) { return true; }
            if (maskAt(x + w - 1, ty) == target) { return true; }
        }
        return false;
    }

    public String readMessageAt(int x, int y) {
        int mask = maskAt(x, y);

        if ((mask & 0x00ff0000) != 0) {
            return "There is nothing to read here.";
        }

        switch (mask & 0x0000ff00) {
        case 0x00002000:
            return "Fly! Fly! The atmosphere is thin, and freedom is a relative term.";
        case 0x00004000:
            return "Welcome to Hell!";
        case 0x00006000:
            return "Hither the lake of lost dreams.";
        default:
            return "It's too hard to make out." + mask;
        }
    }
}

