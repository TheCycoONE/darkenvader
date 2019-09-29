package com.sealbaker.darkenvader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class HighScoreTable {
    private static final int MAX_HIGH_SCORES = 10;

    public HighScoreTable()
    {
    }

    public List<HighScore> getHighScores()
    {
        List<HighScore> highScores = new ArrayList<HighScore>();
        try
        {
            File highScoreFile = openOrCreateHighScoreFile();

            try (DataInputStream in = new DataInputStream(new FileInputStream(highScoreFile)))
            {
                for (int i = 0; i < MAX_HIGH_SCORES; i++)
                {
                    char chr;
                    StringBuffer sBuffer = new StringBuffer();

                    while ((chr = in.readChar()) != '\t')
                    {
                        sBuffer.append(chr);
                    }
                    HighScore score = new HighScore(sBuffer.toString(), in.readInt());
                    highScores.add(score);
                }
            }
        }
        catch(IOException e)
        {
        }

        return highScores;
    }

    public void recordScore(HighScore score)
    {
        List<HighScore> scores = getHighScores();
        int insertIndex = MAX_HIGH_SCORES;
        while (score.getScore() > scores.get(insertIndex - 1).getScore())
        {
            insertIndex--;
            if (insertIndex == 0) { break; }
        }
        scores.add(insertIndex, score);
        scores.remove(MAX_HIGH_SCORES);

        writeHighScores(scores);
    }

    public int getMinimumRecordedScore()
    {
        return getHighScores().get(MAX_HIGH_SCORES - 1).getScore();
    }

    private void writeHighScores(List<HighScore> highScores)
    {
        try
        {
            File highScoreFile = openOrCreateHighScoreFile();

            try (DataOutputStream out = new DataOutputStream(
                        new FileOutputStream(highScoreFile)))
            {
                for (HighScore score : highScores)
                {
                    out.writeChars(score.getName());
                    out.writeChar('\t');
                    out.writeInt(score.getScore());
                }
            }
        }
        catch (IOException e)
        {
        }
    }

    private File openOrCreateHighScoreFile() throws IOException
    {
        File highScoreFile = new File("highscores.txt");
        if (!highScoreFile.exists())
        {
            try (DataOutputStream out = new DataOutputStream(
                        new FileOutputStream(highScoreFile))) {
                for (int i = 0; i < MAX_HIGH_SCORES; i++)
                {
                    out.writeChars("no one");
                    out.writeChar('\t');
                    out.writeInt(0);
                }
            }
        }

        return highScoreFile;
    }
}