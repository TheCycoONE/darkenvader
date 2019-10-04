package com.sealbaker.darkenvader;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

class InstructionPanel extends JPanel implements KeyListener {
    private final Image instrucScrn;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public InstructionPanel() {
        instrucScrn = toolkit.getImage(getClass().getResource("Instructions.gif"));

        MediaTracker instrucTracker = new MediaTracker(this);
        instrucTracker.addImage(instrucScrn, 0);

        try {
            instrucTracker.waitForAll();
        } catch (InterruptedException ex) {
            System.out.println("Error loading instruction screen!!!");
            System.exit(0);
        }

        requestFocus();
        addKeyListener(this);
    }

    public boolean isFocusable() {
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(instrucScrn, 1, 1, this);
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
