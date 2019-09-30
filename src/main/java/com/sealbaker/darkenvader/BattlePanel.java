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

class BattlePanel extends JPanel implements KeyListener {
	Image monsterPic;
	Monster monster;

	JTextArea plrStatBox;
	JTextArea mtrStatBox;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	public BattlePanel(int m) {
		requestFocus();
		this.setLayout(null);
		Insets insets = this.getInsets();

		monster = new Monster(m);
		monsterPic = toolkit.getImage(getClass().getResource(monster.picFile));

		plrStatBox = new JTextArea();
		mtrStatBox = new JTextArea();
		this.add(plrStatBox);
		this.add(mtrStatBox);

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
		plrStatBox.setText("Stats:\nSpirit: " + Global.PC.spirit + "\nAttack: " + Global.PC.attack + "\nDefence: "
				+ Global.PC.defence);
		mtrStatBox.setText(monster.type + "\nSpirit: " + monster.spirit + "\nAttack: " + monster.attack + "\nDefence: "
				+ monster.defence);
	}

	public void keyTyped(KeyEvent event) {
		if (event.getKeyChar() == 'v') {
			Global.PC.score = Global.PC.score + monster.score;
			this.setVisible(false);
		} else if (event.getKeyChar() == 'd') {
			Global.PC.spirit = 0;
			this.setVisible(false);
		}
	}

	public void keyPressed(KeyEvent keyEvent) {
	}

	public void keyReleased(KeyEvent keyEvent) {
	}
}
