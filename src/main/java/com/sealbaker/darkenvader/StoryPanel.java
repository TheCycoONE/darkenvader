package com.sealbaker.darkenvader;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

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

    public void keyPressed(KeyEvent keyEvent)
    {
    }

    public void keyReleased(KeyEvent keyEvent)
    {
    }
}
