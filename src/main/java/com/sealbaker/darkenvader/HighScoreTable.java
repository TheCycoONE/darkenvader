package com.sealbaker.darkenvader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the High Score file, which is saved in highscore.dat
 */
class HighScoreTable {
	private static final int MAX_HIGH_SCORES = 10;
	
	private FileSystem fileSystem;

	public HighScoreTable(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public List<HighScore> getHighScores() {
		List<HighScore> highScores = new ArrayList<HighScore>();
		try {
			File highScoreFile = getHighScoreFile();
			if (!highScoreFile.exists()) {
				return getDefaultHighScores();
			}

			try (DataInputStream in = new DataInputStream(new FileInputStream(highScoreFile))) {
				for (int i = 0; i < MAX_HIGH_SCORES; i++) {
					int score = in.readInt();
					String name = in.readUTF();
					HighScore highScore = new HighScore(name, score);
					highScores.add(highScore);
				}
			}
		} catch (IOException e) {
			highScores = getDefaultHighScores();
		}

		return highScores;
	}

	public void recordScore(HighScore score) {
		List<HighScore> scores = getHighScores();
		int insertIndex = MAX_HIGH_SCORES;
		while (score.getScore() > scores.get(insertIndex - 1).getScore()) {
			insertIndex--;
			if (insertIndex == 0) {
				break;
			}
		}
		scores.add(insertIndex, score);
		scores.remove(MAX_HIGH_SCORES);

		writeHighScores(scores);
	}

	public int getMinimumRecordedScore() {
		return getHighScores().get(MAX_HIGH_SCORES - 1).getScore();
	}

	private void writeHighScores(List<HighScore> highScores) {
		try {
			File highScoreFile = getHighScoreFile();

			try (DataOutputStream out = new DataOutputStream(new FileOutputStream(highScoreFile))) {
				for (HighScore score : highScores) {
					out.writeInt(score.getScore());
					out.writeUTF(score.getName());
				}
			}
		} catch (IOException e) {
		}
	}

	private File getHighScoreFile() throws IOException {
		Path highScorePath = Paths.get(fileSystem.getDataPath().toString(), "highscores.dat");
		File highScoreFile = highScorePath.toFile();
		return highScoreFile;
	}

	private List<HighScore> getDefaultHighScores() {
		List<HighScore> scores = new ArrayList<HighScore>();
		for (int i = 0; i < MAX_HIGH_SCORES; i++) {
			scores.add(new HighScore("no one", 0));
		}
		return scores;
	}
}
