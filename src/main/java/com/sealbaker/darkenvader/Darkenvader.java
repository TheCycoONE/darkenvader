package com.sealbaker.darkenvader;

// Darkenvader
// An adventure
// by Stephen E. Baker

import javax.swing.SwingUtilities;

public class Darkenvader {
	public static void main(String[] args) throws Exception {
		FileSystem fs = new FileSystem();
		HighScoreTable highScoreTable = new HighScoreTable(fs);
		Player player = new Player();

		SwingUtilities.invokeLater(() -> {
			try {
				Console c = new Console(player, highScoreTable);
				c.pack();
				c.setResizable(false);
				c.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		});
	}
}