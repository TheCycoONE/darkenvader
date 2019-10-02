package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

class SetupPanel extends JPanel implements KeyListener {
    Font font;
    private Player player;

    public SetupPanel(Player player) {
        this.player = player;

        this.addKeyListener(this);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        repaint();
    }

    public boolean isFocusable() {
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawRect(180, 120, 270, 190);
        g.drawString("Choose your difficulty!", 200, 160);
        g.drawString("Press:", 200, 190);
        g.drawString("1) Easy", 200, 220);
        g.drawString("2) Medium", 200, 250);
        g.drawString("3) Difficult", 200, 280);
    }

    public void keyTyped(KeyEvent event) {
        if (event.getKeyChar() == '1') {
            Global.curPanel = Global.GAME_PANEL;
            player.reset(1);
            setVisible(false);
        } else if (event.getKeyChar() == '2') {
            Global.curPanel = Global.GAME_PANEL;
            player.reset(2);
            setVisible(false);
        } else if (event.getKeyChar() == '3') {
            Global.curPanel = Global.GAME_PANEL;
            player.reset(3);
            setVisible(false);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
    }
}
