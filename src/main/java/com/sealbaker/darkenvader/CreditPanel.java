package com.sealbaker.darkenvader;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class CreditPanel extends JPanel implements KeyListener {
    private final BufferedImage creditScrn;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public CreditPanel() throws IOException {
        this.addKeyListener(this);

        creditScrn = ImageIO.read(getClass().getResource("Credits.gif"));

        requestFocus();
        repaint();
    }

    public boolean isFocusable() {
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(creditScrn, 1, 1, this);
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
