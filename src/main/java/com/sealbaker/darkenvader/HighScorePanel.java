package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JPanel;

class HighScorePanel extends JPanel implements KeyListener
{
    Font font;
    public HighScorePanel()
    {
        this.addKeyListener(this);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        repaint();
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString("Noteable Adventurers!", 200, 50);

        try
        {
            File highscores = new File("highscores.txt");
            if (!highscores.exists())
            {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(highscores));
                for (int i = 0; i < 10; i ++)
                {
                    out.writeChars("no one");
                    out.writeChar('\t');
                    out.writeInt(0);
                }
                out.close();
            }
            DataInputStream in = new DataInputStream(new FileInputStream(highscores));
            for (int i = 0; i < 10; i++)
            {
                char chr;
                StringBuffer sBuffer = new StringBuffer();

                while ((chr = in.readChar()) != '\t')
                {
                    sBuffer.append(chr);
                }
                g.drawString(((i + 1) + ") " + sBuffer.toString() + ": " + in.readInt()), 200, 100 + (i * 25));
            }
        }
        catch(IOException e)
        {
        }
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

