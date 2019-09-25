package com.sealbaker.darkenvader;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

class GamePanel extends JPanel implements KeyListener, ComponentListener
{
    MediaTracker gameStartTracker = new MediaTracker(this);

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    Image worldMap;
    Image mapOnScreen;
    Image worldMapMask;

    Insets insets;
    int pixels [];
    int iscore;
    Player PC = Global.PC;

    JTextArea statBox = null;
    JTextArea msgBox = null;
    BattlePanel battle = null;

    public GamePanel()
    {
        PC.reset(0);
        addKeyListener(this);

        this.setLayout(null);
        insets = this.getInsets();

        try
        {
            worldMap = toolkit.getImage(getClass().getResource("WorldMap.gif"));
            worldMapMask = toolkit.getImage(getClass().getResource("WorldMask.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load world map or it's mask");
        }

        gameStartTracker.addImage(worldMap, 0);
        gameStartTracker.addImage(worldMapMask, 1);
        try
        {
            gameStartTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Can not process Game Image");
        }

        // Grabs all of the the pixels from the mask
        pixels = new int [worldMapMask.getWidth(this) * worldMapMask.getHeight(this)];
        PixelGrabber pg = new PixelGrabber(worldMapMask, 0, 0, worldMapMask.getWidth(this), worldMapMask.getHeight(this), pixels, 0, worldMapMask.getWidth(this));

        try
        {
            pg.grabPixels();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Internal difficulty");
        }

        worldMap.flush();
        worldMapMask.flush();
        startGame();
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (mapOnScreen != null && PC.mapIcon != null)
        {
            g.drawImage(mapOnScreen, 1, 1, this);
            g.drawImage(PC.mapIcon, 320, 150, this);
        }
    }

    private void runWork()
    {
        if (PC.spirit <= 0)
        {
            Global.curPanel = Global.GAMEOVER_PANEL;
            this.removeAll();
            this.setVisible(false);
        }
        else
        {
            statBox.setText("Stats:\nSpirit: " + PC.spirit + "\nAttack: " + PC.attack + "\nDefence: " + PC.defence + "\nScore: " + PC.score);

            mapOnScreen = createImage(new FilteredImageSource(worldMap.getSource(), new CropImageFilter(PC.x - 320, PC.y - 150, 639, 299)));

            MediaTracker gameRunTracker = new MediaTracker(this);

            gameRunTracker.addImage(mapOnScreen, 0);
            gameRunTracker.addImage(PC.mapIcon, 1);

            try
            {
                gameRunTracker.waitForAll();
            }
            catch (InterruptedException ex)
            {
                System.out.println("Can not process image");
            }
            repaint();
        }
    }

    public void startGame()
    {
        removeAll();

        statBox = new JTextArea();
        msgBox = new JTextArea();
        add(statBox);
        add(msgBox);

        statBox.setBounds(500 + insets.left, 300 + insets.top, 139, 180);
        statBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        statBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        statBox.setEditable(false);

        msgBox.setBounds(1 + insets.left, 300 + insets.top, 499, 180);
        msgBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        msgBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        msgBox.setEditable(false);

        statBox.setVisible(true);
        msgBox.setVisible(true);

        PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));

        msgBox.setText("Welcome, here you have no name, you will be called only Darkenvader.\nBecause you have invaded my domain, but rest assured Darkenvader,\nwithout the light you will fail, and you will accept this.");
        runWork();
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {

        int tx = PC.x;
        int ty = PC.y;

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if (!PC.mapPicURL.equals("PCR0.gif"))
            {
                PC.mapPicURL = "PCR0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            tx = PC.x + PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if (!PC.mapPicURL.equals("PCL0.gif"))
            {
                PC.mapPicURL = "PCL0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            tx = PC.x - PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if (!PC.mapPicURL.equals("PCD0.gif"))
            {
                PC.mapPicURL = "PCD0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            ty = PC.y + PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
            if (!PC.mapPicURL.equals("PCU0.gif"))
            {
                PC.mapPicURL = "PCU0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            ty = PC.y - PC.speed;
        }

        else if (keyEvent.getKeyCode() == KeyEvent.VK_R)
        {
            int pixel = pixels [PC.y * worldMapMask.getWidth(this) + PC.x];
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;

            if (red == 0)
            {
                switch (green)
                {
                    case 32:
                        msgBox.setText("Fly! Fly! The atmosphere is thin, and freedom is a relative term.");
                        break;
                    case 64:
                        msgBox.setText("Welcome to Hell!");
                        break;
                    case 96:
                        msgBox.setText("Hither the lake of lost dreams.");
                        break;
                    default:
                        msgBox.setText("It's too hard to make out." + green);
                        break;
                }
            }
            else
            {
                msgBox.setText("There is nothing to read here.");
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_V)
        {
            if (PC.score >= 100)
            {
                this.removeAll();
                Global.curPanel = Global.VICTORY_PANEL;
                setVisible(false);
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_Q)
        {
            PC.reset(0);
            Global.curPanel = Global.SPLASH_PANEL;
            setVisible(false);
        }

        int tlPixel = pixels [ty * worldMapMask.getWidth(this) + tx];
        int tlRed = (tlPixel >> 16) & 0xff;
        int tlGreen = (tlPixel >> 8) & 0xff;
        int tlBlue = (tlPixel) & 0xff;

        int trPixel = pixels [ty * worldMapMask.getWidth(this) + tx + PC.mapIcon.getWidth(this)];
        int trRed = (trPixel >> 16) & 0xff;
        int trGreen = (trPixel >> 8) & 0xff;
        int trBlue = (trPixel) & 0xff;

        int blPixel = pixels [(ty + PC.mapIcon.getHeight(this)) * worldMapMask.getWidth(this) + tx];
        int blRed = (blPixel >> 16) & 0xff;
        int blGreen = (blPixel >> 8) & 0xff;
        int blBlue = (blPixel) & 0xff;

        int brPixel = pixels [(ty + PC.mapIcon.getHeight(this)) * worldMapMask.getWidth(this) + (tx + PC.mapIcon.getWidth(this))];
        int brRed = (brPixel >> 16) & 0xff;
        int brGreen = (brPixel >> 8) & 0xff;
        int brBlue = (brPixel) & 0xff;

        if ( !(tlRed == 0 && tlBlue == 0 && tlGreen == 0) && !(trRed == 0 && trBlue == 0 && trGreen == 0) && !(blRed == 0 && blBlue == 0 && blGreen == 0) && !(brRed == 0 && brBlue == 0 && brGreen == 0))
        {
            PC.x = tx;
            PC.y = ty;
        }
        if ( (tlRed == 255 && tlBlue == 255 && tlGreen == 0) || (trRed == 255 && trBlue == 255 && trGreen == 0) || (blRed == 255 && blBlue == 255 && blGreen == 0) || (brRed == 255 && brBlue == 255 && brGreen == 0))
        {
            PC.spirit = PC.spirit - 1;
        }

        Random rand = new Random();

        if (rand.nextInt(10) == 9)
        {
            battle = new BattlePanel(rand.nextInt(2)+1);
            this.add(battle);
            battle.setBounds(1 + insets.left, 1 + insets.top, 639, 479);
            battle.setVisible(true);
            battle.requestFocus();
            battle.addComponentListener(this);
            iscore = PC.score;
        }

        runWork();
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyTyped(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void componentHidden(java.awt.event.ComponentEvent componentEvent)
    {
        this.remove(battle);
        battle = null;
        this.requestFocus();
        if (iscore == 0 && PC.score > 0)
        {
            msgBox.setText("So, you have brought peace to one of my minons.\nDon't be too proud of yourself Darkenvader.");
        }
        else if (iscore <75 && PC.score >=75)
        {
            msgBox.setText("You're arrogant young one.\nDo you not know that all your power comes from me?\nI think it's time for you to be humbled!");
            PC.defence = 10;
            PC.attack = 10;
            PC.spirit = 25;
        }
        else if (iscore < 100 && PC.score >= 100)
        {
            msgBox.setText("Impressive... I admire your spirit, and your request is granted.\nYou may leave my world at any time with your girl.\nI'm sure that in your world your deeds will be renouned,\nand this is your opportunity to augment them.\nAnytime you'd like to leave simply press 'v'.");
        }
        runWork();
    }

    public void componentMoved(java.awt.event.ComponentEvent componentEvent)
    {
    }

    public void componentResized(java.awt.event.ComponentEvent componentEvent)
    {
    }

    public void componentShown(java.awt.event.ComponentEvent componentEvent)
    {
    }
}
