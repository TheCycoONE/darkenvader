package com.sealbaker.darkenvader;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

class GameOverPanel extends JPanel implements KeyListener
{
    ImageIcon gameOverScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public GameOverPanel()
    {
        this.addKeyListener(this);
        MediaTracker gameOverTracker = new MediaTracker(this);

        try
        {
            gameOverScrn = new ImageIcon(getClass().getResource("GameOver.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load game over Image");
        }

        JLabel background = new JLabel(gameOverScrn);
        this.add(background);
        requestFocus();
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}
