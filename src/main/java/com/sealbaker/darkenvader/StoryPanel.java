package com.sealbaker.darkenvader;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

class StoryPanel extends JPanel implements KeyListener
{
    Image storyScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public StoryPanel()
    {
        this.addKeyListener(this);

        try
        {
            storyScrn = toolkit.getImage(getClass().getResource("Story.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Can not load story screen!");
        }

        MediaTracker storyTracker = new MediaTracker(this);
        storyTracker.addImage(storyScrn, 0);

        try
        {
            storyTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error loading story screen!!!");
            System.exit(0);
        }

        requestFocus();
        repaint();
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(storyScrn, 1, 1, this);
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
