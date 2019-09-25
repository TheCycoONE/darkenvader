package com.sealbaker.darkenvader;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

class InstructionPanel extends JPanel implements KeyListener
{
    Image instrucScrn;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public InstructionPanel()
    {
        try
        {
            instrucScrn = toolkit.getImage(getClass().getResource("Instructions.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load Instructions Image");
        }

        MediaTracker instrucTracker = new MediaTracker(this);
        instrucTracker.addImage(instrucScrn, 0);

        try
        {
            instrucTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error loading instruction screen!!!");
            System.exit(0);
        }

        requestFocus();
        addKeyListener(this);
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(instrucScrn, 1, 1, this);
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
