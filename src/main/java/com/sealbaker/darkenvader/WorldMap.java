package com.sealbaker.darkenvader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class WorldMap {
    private final BufferedImage worldMap;
    private final BufferedImage worldMapMask;

    public WorldMap() throws IOException {
        worldMap = ImageIO.read(getClass().getResource("WorldMap.gif"));
        worldMapMask = ImageIO.read(getClass().getResource("WorldMask.gif"));
    }

    public BufferedImage getWorldMapImage() {
        return worldMap;
    }

    // TODO: Move behaviour into this class so that the mask doesn't leak
    public BufferedImage getWorldMapMask() {
        return worldMapMask;
    }

    public String readMessageAt(int x, int y) {
        int mask = worldMapMask.getRGB(x, y);

        if ((mask & 0xff0000) != 0) {
            return "There is nothing to read here.";
        }

        switch (mask & 0x00ff00) {
        case 0x002000:
            return "Fly! Fly! The atmosphere is thin, and freedom is a relative term.";
        case 0x004000:
            return "Welcome to Hell!";
        case 0x006000:
            return "Hither the lake of lost dreams.";
        default:
            return "It's too hard to make out." + mask;
        }
    }
}

