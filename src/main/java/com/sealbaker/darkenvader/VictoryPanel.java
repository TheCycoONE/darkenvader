package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class VictoryPanel extends JPanel implements KeyListener
{
    ImageIcon victoryScrn;
    String playerName;
    JTextField plrname;
    boolean doneVictory;

    public VictoryPanel()
    {
        setBackground(Color.black);
    }

    public void initVictory()
    {
        removeAll();
        doneVictory = false;

        JTextArea label = new JTextArea("Congradulations, you have earned a name for yourself...\nWhat is it?");
        this.add(label);
        label.setEditable(false);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label.setBackground(Color.black);
        label.setForeground(Color.red);
        label.setVisible(true);

        plrname = new JTextField();
        this.add(plrname);
        plrname.addKeyListener(this);
        plrname.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        plrname.setColumns(20);
        plrname.setBackground(Color.white);
        plrname.setForeground(Color.black);
        plrname.setVisible(true);
        plrname.requestFocus();

        repaint();
    }

    public void highScore()
    {
        try
        {
            String[] names = new String[10];
            int[] scores = new int[10];

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
                names[i] = sBuffer.toString();
                scores[i] = in.readInt();
            }
            in.close();

            if (Global.PC.score > scores[9])
            {
                int i = 8;
                while (Global.PC.score > scores[i] && i > 0)
                {
                    i--;
                    names[i + 1] = names[i];
                    scores[i + 1]= scores[i];
                }
                names[i] = playerName;
                scores[i] = Global.PC.score;
            }

            DataOutputStream out = new DataOutputStream(new FileOutputStream(highscores));
            for (int i = 0; i < 10; i++)
            {
                out.writeChars(names[i]);
                out.writeChar('\t');
                out.writeInt(scores[i]);
            }
            out.close();

            removeAll();

            victoryScrn = new ImageIcon(getClass().getResource("Victory.gif"));
            JLabel background = new JLabel(victoryScrn);

            Insets insets = this.getInsets();

            add(background);
            setLayout(null);
            background.setBounds(1 + insets.left,1 + insets.top, 639, 479);
            background.setVisible(true);

            addKeyListener(this);
            requestFocus();
            doneVictory = true;
            repaint();
        }
        catch(IOException e)
        {
            System.err.print("Error: " + e);
        }
    }

    public boolean isFocusable()
    {
        return true;
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
        if (!doneVictory)
        {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER && plrname.getText().length() > 1)
            {
                playerName = plrname.getText();
                highScore();
            }
        }
        else
        {
            Global.curPanel = Global.HIGHSCORE_PANEL;
            setVisible(false);
        }
    }

    public void keyReleased(KeyEvent keyEvent)
    {
    }
}
