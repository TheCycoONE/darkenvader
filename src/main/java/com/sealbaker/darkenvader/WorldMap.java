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
}

