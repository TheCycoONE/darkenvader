package com.sealbaker.darkenvader;

// Darkenvader
// An adventure
// by Stephen E. Baker

import javax.swing.SwingUtilities;

public class Darkenvader {
	public static void main(String[] args) throws Exception {
		FileSystem fs = new FileSystem();
		HighScoreTable highScoreTable = new HighScoreTable(fs);

		SwingUtilities.invokeLater(() -> {
			Console c = new Console(highScoreTable);
			c.pack();
			c.setResizable(false);
			c.setVisible(true);
		});
	}
}
