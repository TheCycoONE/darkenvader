package com.sealbaker.darkenvader;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

class SplashPanel extends JPanel implements KeyListener
{
    Image splashScrn;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public SplashPanel()
    {
        this.addKeyListener(this);
        MediaTracker splashTracker = new MediaTracker(this);

        try
        {
            splashScrn = toolkit.getImage(getClass().getResource("Splash.gif"));
            splashTracker.addImage(splashScrn, 0);
        }
        catch (Exception ex)
        {
            System.err.println("Problem Loading Image");
        }

        try
        {
            splashTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.err.println("Error loading splash screen!!!");
            System.exit(0);
        }

        requestFocus();
    }

    public boolean isFocusable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(splashScrn, 1, 1, this);
    }


    public void keyTyped(KeyEvent event)
    {
        if (event.getKeyChar() == 'i')
        {
            Global.curPanel = Global.INSTRUCTION_PANEL;
            this.setVisible(false);

        }
        else if (event.getKeyChar() == 's')
        {
            Global.curPanel = Global.SETUP_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'r')
        {
            Global.curPanel = Global.STORY_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'n')
        {
            Global.curPanel = Global.HIGHSCORE_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'c')
        {
            Global.curPanel = Global.CREDIT_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'q')
        {
            System.exit(0);
        }
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}
