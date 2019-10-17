package com.sealbaker.darkenvader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sealbaker.darkenvader.monsters.Monster;

class BattlePanel extends JPanel implements KeyListener {
    private final Image monsterPic;
    private final Monster monster;
    private final Player player;
    private final JTextArea plrStatBox;
    private final JTextArea mtrStatBox;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public BattlePanel(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;

        requestFocus();
        this.setLayout(null);

        monsterPic = toolkit.getImage(getClass().getResource(monster.picFile));

        plrStatBox = new JTextArea();
        mtrStatBox = new JTextArea();
        this.add(plrStatBox);
        this.add(mtrStatBox);

        Insets insets = this.getInsets();
        plrStatBox.setBounds(320 + insets.left, 300 + insets.top, 319, 180);
        plrStatBox.setBackground(new Color((float) 0.2, (float) 0.0, (float) 0.0));
        plrStatBox.setForeground(new Color((float) 1.0, (float) 0.0, (float) 0.0));
        plrStatBox.setEditable(false);

        mtrStatBox.setBounds(1 + insets.left, 300 + insets.top, 319, 180);
        mtrStatBox.setBackground(new Color((float) 0.2, (float) 0.0, (float) 0.0));
        mtrStatBox.setForeground(new Color((float) 1.0, (float) 0.0, (float) 0.0));
        mtrStatBox.setEditable(false);

        plrStatBox.setVisible(true);
        mtrStatBox.setVisible(true);

        addKeyListener(this);
    }

    public boolean isFocusable() {
        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.drawImage(monsterPic, 1, 1, this);
        plrStatBox.setText(
                "Stats:\nSpirit: " + player.spirit + "\nAttack: " + player.attack + "\nDefence: " + player.defence);
        mtrStatBox.setText(monster.type + "\nSpirit: " + monster.spirit + "\nAttack: " + monster.attack + "\nDefence: "
                + monster.defence);
    }

    public void keyTyped(KeyEvent event) {
        if (event.getKeyChar() == 'v') {
            player.addToScore(monster.score);
            this.setVisible(false);
        } else if (event.getKeyChar() == 'd') {
            player.spirit = 0;
            this.setVisible(false);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
    }
}
