package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JPanel;

class HighScorePanel extends JPanel implements KeyListener
{
    private final Font font;
    private final HighScoreTable highScoreTable;

    public HighScorePanel(HighScoreTable highScoreTable)
    {
        this.addKeyListener(this);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        this.highScoreTable = highScoreTable;
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

        List<HighScore> scores = highScoreTable.getHighScores();
        int place = 1;
        for (HighScore score : scores)
        {
            g.drawString(
                    (place + ") " + score.getName() + ": " + score.getScore()),
                    200,
                    100 + (place * 25));
            place++;
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

