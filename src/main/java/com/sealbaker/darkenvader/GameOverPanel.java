package com.sealbaker.darkenvader;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GameOverPanel extends JPanel implements KeyListener {
    private final ImageIcon gameOverScrn;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public GameOverPanel() {
        this.addKeyListener(this);

        gameOverScrn = new ImageIcon(getClass().getResource("GameOver.gif"));

        JLabel background = new JLabel(gameOverScrn);
        this.add(background);
        requestFocus();
    }

    public boolean isFocusable() {
        return true;
    }

    public void keyTyped(KeyEvent event) {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
    }
}
