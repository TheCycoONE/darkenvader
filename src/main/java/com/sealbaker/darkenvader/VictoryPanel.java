package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class VictoryPanel extends JPanel implements KeyListener {
    private ImageIcon victoryScrn;
    private String playerName;
    private JTextField plrname;
    private boolean doneVictory;
    private final HighScoreTable highScoreTable;
    private final Player player;

    public VictoryPanel(Player player, HighScoreTable highScoreTable) {
        this.player = player;
        this.highScoreTable = highScoreTable;
        setBackground(Color.black);
    }

    public void initVictory() {
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

    public void highScore() {
        HighScore score = new HighScore(playerName, player.getScore());
        highScoreTable.recordScore(score);

        removeAll();

        victoryScrn = new ImageIcon(getClass().getResource("Victory.gif"));
        JLabel background = new JLabel(victoryScrn);

        Insets insets = this.getInsets();

        add(background);
        setLayout(null);
        background.setBounds(1 + insets.left, 1 + insets.top, 639, 479);
        background.setVisible(true);

        addKeyListener(this);
        requestFocus();
        doneVictory = true;
        repaint();
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyTyped(KeyEvent event) {
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent) {
        if (!doneVictory) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER && plrname.getText().length() > 1) {
                playerName = plrname.getText();
                highScore();
            }
        } else {
            Global.curPanel = Global.HIGHSCORE_PANEL;
            setVisible(false);
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
    }
}
