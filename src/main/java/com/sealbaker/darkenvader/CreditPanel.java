package com.sealbaker.darkenvader;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

class CreditPanel extends JPanel implements KeyListener
{
    Image creditScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public CreditPanel()
    {
        this.addKeyListener(this);

        try
        {
            creditScrn = toolkit.getImage(getClass().getResource("Credits.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Can not load story screen!");
        }

        MediaTracker creditTracker = new MediaTracker(this);
        creditTracker.addImage(creditScrn, 0);

        try
        {
            creditTracker.waitForAll();
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
        g.drawImage(creditScrn, 1, 1, this);
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