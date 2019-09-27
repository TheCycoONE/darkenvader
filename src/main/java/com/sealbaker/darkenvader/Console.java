package com.sealbaker.darkenvader;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

// Main window
class Console extends JFrame implements ComponentListener
{
    Container cp;
    SplashPanel splash;
    CreditPanel credits;
    GamePanel game;
    InstructionPanel instruc;
    StoryPanel story;
    SetupPanel setup;
    GameOverPanel gameOver;
    VictoryPanel victory;
    HighScorePanel highScore;

    public Console()
    {
        setTitle("Darkenvader");
        setLocation(1,1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cp = getContentPane();
        cp.setPreferredSize(new Dimension(640,480));

        splash = new SplashPanel();
        game = new GamePanel();
        instruc = new InstructionPanel();
        credits = new CreditPanel();
        story = new StoryPanel();
        setup = new SetupPanel();
        gameOver = new GameOverPanel();
        victory = new VictoryPanel();
        highScore = new HighScorePanel();

        cp.add(splash);
        splash.setVisible(true);

        splash.addComponentListener(this);
        story.addComponentListener(this);
        game.addComponentListener(this);
        instruc.addComponentListener(this);
        credits.addComponentListener(this);
        setup.addComponentListener(this);
        gameOver.addComponentListener(this);
        victory.addComponentListener(this);
        highScore.addComponentListener(this);
    }

    public void componentHidden(ComponentEvent event)
    {
        cp.removeAll();

        switch (Global.curPanel)
        {
            case Global.SPLASH_PANEL:
                cp.add(splash);
                splash.setVisible(true);
                splash.requestFocus();
                splash.repaint();
                break;

            case Global.STORY_PANEL:
                cp.add(story);
                story.setVisible(true);
                story.requestFocus();
                story.repaint();
                break;

            case Global.INSTRUCTION_PANEL:
                cp.add(instruc);
                instruc.setVisible(true);
                instruc.requestFocus();
                instruc.repaint();
                break;

            case Global.CREDIT_PANEL:
                cp.add(credits);
                credits.setVisible(true);
                credits.requestFocus();
                credits.repaint();
                break;

            case Global.HIGHSCORE_PANEL:
                cp.add(highScore);
                highScore.setVisible(true);
                highScore.requestFocus();
                highScore.repaint();
                break;

            case Global.SETUP_PANEL:
                cp.add(setup);
                setup.setVisible(true);
                setup.requestFocus();
                setup.repaint();
                break;

            case Global.GAME_PANEL:
                cp.add(game);
                game.startGame();
                game.setVisible(true);
                game.requestFocus();
                game.repaint();
                break;

            case Global.GAMEOVER_PANEL:
                cp.add(gameOver);
                gameOver.setVisible(true);
                gameOver.requestFocus();
                gameOver.repaint();
                break;

            case Global.VICTORY_PANEL:
                cp.add(victory);
                victory.setVisible(true);
                victory.requestFocus();
                victory.repaint();
                victory.initVictory();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public void componentMoved(ComponentEvent event)
    {
    }

    public void componentResized(ComponentEvent event)
    {
    }

    public void componentShown(ComponentEvent event)
    {
    }
}
