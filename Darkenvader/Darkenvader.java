package Darkenvader;

// Darkenvader
// An adventure
// by Stephen E. Baker

// Libraries
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Darkenvader
{
    public static void main(String[] args)
    {
        JFrame c = new Console();
        c.pack();
        c.setResizable(false);
        c.setVisible(true);
    }
}

// Main window in which program runs
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
        // Setup Frame
        setTitle("Darkenvader");
        setLocation(1,1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //For swing containers to hold components
        cp = getContentPane();
        cp.setPreferredSize(new Dimension(640,480));

        // Load Panels
        splash = new SplashPanel();
        game = new GamePanel();
        instruc = new InstructionPanel();
        credits = new CreditPanel();
        story = new StoryPanel();
        setup = new SetupPanel();
        gameOver = new GameOverPanel();
        victory = new VictoryPanel();
        highScore = new HighScorePanel();
        // Run the first Panel
        cp.add(splash);
        splash.setVisible(true);

        //Component Listeners detect panel activity and
        //allows me to switch panels.
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

    // panel.setVisible(false); calls this.
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

    // Rest of ComponentListener methods.
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



class SplashPanel extends JPanel implements KeyListener
{
    Image splashScrn;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public SplashPanel()
    {
        this.addKeyListener(this);
        MediaTracker splashTracker = new MediaTracker(this);

        try
        {
            splashScrn = toolkit.getImage(getClass().getResource("Splash.gif"));
            splashTracker.addImage(splashScrn, 0);
        }
        catch (Exception ex)
        {
            System.err.println("Problem Loading Image");
        }

        try
        {
            splashTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.err.println("Error loading splash screen!!!");
            System.exit(0);
        }

        requestFocus();
    }

    public boolean isFocusTraversable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(splashScrn, 1, 1, this);
    }


    public void keyTyped(KeyEvent event)
    {
        if (event.getKeyChar() == 'i')
        {
            Global.curPanel = Global.INSTRUCTION_PANEL;
            this.setVisible(false);

        }
        else if (event.getKeyChar() == 's')
        {
            Global.curPanel = Global.SETUP_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'r')
        {
            Global.curPanel = Global.STORY_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'n')
        {
            Global.curPanel = Global.HIGHSCORE_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'c')
        {
            Global.curPanel = Global.CREDIT_PANEL;
            this.setVisible(false);
        }
        else if (event.getKeyChar() == 'q')
        {
            System.exit(0);
        }
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}



class StoryPanel extends JPanel implements KeyListener
{
    Image storyScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public StoryPanel()
    {
        this.addKeyListener(this);

        try
        {
            storyScrn = toolkit.getImage(getClass().getResource("Story.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Can not load story screen!");
        }

        MediaTracker storyTracker = new MediaTracker(this);
        storyTracker.addImage(storyScrn, 0);

        try
        {
            storyTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error loading story screen!!!");
            System.exit(0);
        }

        requestFocus();
        repaint();
    }


    public boolean isFocusTraversable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(storyScrn, 1, 1, this);
    }


    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }


    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}

class InstructionPanel extends JPanel implements KeyListener
{
    Image instrucScrn;

    Toolkit toolkit = Toolkit.getDefaultToolkit();


    public InstructionPanel()
    {
        try
        {
            instrucScrn = toolkit.getImage(getClass().getResource("Instructions.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load Instructions Image");
        }

        MediaTracker instrucTracker = new MediaTracker(this);
        instrucTracker.addImage(instrucScrn, 0);

        try
        {
            instrucTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error loading instruction screen!!!");
            System.exit(0);
        }

        requestFocus();
        addKeyListener(this);
    }


    public boolean isFocusTraversable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(instrucScrn, 1, 1, this);
    }


    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }
    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }
    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}

class CreditPanel extends JPanel implements KeyListener
{
    Image creditScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public CreditPanel()
    {
        this.addKeyListener(this);

        try
        {
            creditScrn = toolkit.getImage(getClass().getResource("Credits.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Can not load story screen!");
        }

        MediaTracker creditTracker = new MediaTracker(this);
        creditTracker.addImage(creditScrn, 0);

        try
        {
            creditTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error loading story screen!!!");
            System.exit(0);
        }

        requestFocus();
        repaint();
    }


    public boolean isFocusTraversable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(creditScrn, 1, 1, this);
    }


    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }


    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}


class SetupPanel extends JPanel implements KeyListener
{
    Font font;
    public SetupPanel()
    {
        this.addKeyListener(this);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        repaint();
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawRect(180,120,270,190);
        g.drawString("Choose your difficulty!" , 200, 160);
        g.drawString("Press:", 200, 190);
        g.drawString("1) Easy", 200, 220);
        g.drawString("2) Medium", 200, 250);
        g.drawString("3) Difficult", 200, 280);
    }

    public void keyTyped(KeyEvent event)
    {
        if (event.getKeyChar() == '1')
        {
            Global.curPanel = Global.GAME_PANEL;
            Global.PC.reset(1);
            setVisible(false);
        }
        else if (event.getKeyChar() == '2')
        {
            Global.curPanel = Global.GAME_PANEL;
            Global.PC.reset(2);
            setVisible(false);
        }
        else if (event.getKeyChar() == '3')
        {
            Global.curPanel = Global.GAME_PANEL;
            Global.PC.reset(3);
            setVisible(false);
        }

    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}


class GamePanel extends JPanel implements KeyListener, ComponentListener
{
    MediaTracker gameStartTracker = new MediaTracker(this);


    Toolkit toolkit = Toolkit.getDefaultToolkit();

    Image worldMap;
    Image mapOnScreen;
    Image worldMapMask;

    Insets insets;
    int pixels [];
    int iscore;
    Player PC = Global.PC;

    JTextArea statBox = null;
    JTextArea msgBox = null;
    BattlePanel battle = null;

    public GamePanel()
    {
        PC.reset(0);
        addKeyListener(this);

        this.setLayout(null);
        insets = this.getInsets();

        // Loads GIF's
        try
        {
            worldMap = toolkit.getImage(getClass().getResource("WorldMap.gif"));
            worldMapMask = toolkit.getImage(getClass().getResource("WorldMask.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load world map or it's mask");
        }

        gameStartTracker.addImage(worldMap, 0);
        gameStartTracker.addImage(worldMapMask, 1);
        try
        {
            gameStartTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Can not process Game Image");
        }

        // Grabs all of the the pixels from the mask
        pixels = new int [worldMapMask.getWidth(this) * worldMapMask.getHeight(this)];
        PixelGrabber pg = new PixelGrabber(worldMapMask, 0, 0, worldMapMask.getWidth(this), worldMapMask.getHeight(this), pixels, 0, worldMapMask.getWidth(this));

        try
        {
            pg.grabPixels();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Internal difficulty");
        }

        worldMap.flush();
        worldMapMask.flush();
        startGame();
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (mapOnScreen != null && PC.mapIcon != null)
        {
            g.drawImage(mapOnScreen, 1, 1, this);
            g.drawImage(PC.mapIcon, 320, 150, this);
        }
    }

    private void runWork()
    {
        if (PC.spirit <= 0)
        {
            Global.curPanel = Global.GAMEOVER_PANEL;
            this.removeAll();
            this.setVisible(false);
        }
        else
        {
            statBox.setText("Stats:\nSpirit: " + PC.spirit + "\nAttack: " + PC.attack + "\nDefence: " + PC.defence + "\nScore: " + PC.score);

            mapOnScreen = createImage(new FilteredImageSource(worldMap.getSource(), new CropImageFilter(PC.x - 320, PC.y - 150, 639, 299)));

            MediaTracker gameRunTracker = new MediaTracker(this);

            gameRunTracker.addImage(mapOnScreen, 0);
            gameRunTracker.addImage(PC.mapIcon, 1);

            try
            {
                gameRunTracker.waitForAll();
            }
            catch (InterruptedException ex)
            {
                System.out.println("Can not process image");
            }
            repaint();
        }
    }

    public void startGame()
    {
        removeAll();

        statBox = new JTextArea();
        msgBox = new JTextArea();
        add(statBox);
        add(msgBox);

        statBox.setBounds(500 + insets.left, 300 + insets.top, 139, 180);
        statBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        statBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        statBox.setEditable(false);

        msgBox.setBounds(1 + insets.left, 300 + insets.top, 499, 180);
        msgBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        msgBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        msgBox.setEditable(false);

        statBox.setVisible(true);
        msgBox.setVisible(true);

        PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));

        msgBox.setText("Welcome, here you have no name, you will be called only Darkenvader.\nBecause you have invaded my domain, but rest assured Darkenvader,\nwithout the light you will fail, and you will accept this.");
        runWork();
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {

        int tx = PC.x;
        int ty = PC.y;

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if (!PC.mapPicURL.equals("PCR0.gif"))
            {
                PC.mapPicURL = "PCR0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            tx = PC.x + PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if (!PC.mapPicURL.equals("PCL0.gif"))
            {
                PC.mapPicURL = "PCL0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            tx = PC.x - PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if (!PC.mapPicURL.equals("PCD0.gif"))
            {
                PC.mapPicURL = "PCD0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            ty = PC.y + PC.speed;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_UP)
        {
            if (!PC.mapPicURL.equals("PCU0.gif"))
            {
                PC.mapPicURL = "PCU0.gif";
                PC.mapIcon = toolkit.getImage(getClass().getResource(PC.mapPicURL));
            }
            ty = PC.y - PC.speed;
        }

        else if (keyEvent.getKeyCode() == KeyEvent.VK_R)
        {
            int pixel = pixels [PC.y * worldMapMask.getWidth(this) + PC.x];
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;

            if (red == 0)
            {
                switch (green)
                {
                    case 32:
                        msgBox.setText("Fly! Fly! The atmosphere is thin, and freedom is a relative term.");
                        break;
                    case 64:
                        msgBox.setText("Welcome to Hell!");
                        break;
                    case 96:
                        msgBox.setText("Hither the lake of lost dreams.");
                        break;
                    default:
                        msgBox.setText("It's too hard to make out." + green);
                        break;
                }
            }
            else
            {
                msgBox.setText("There is nothing to read here.");
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_V)
        {
            if (PC.score >= 100)
            {
                this.removeAll();
                Global.curPanel = Global.VICTORY_PANEL;
                setVisible(false);
            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_Q)
        {
            PC.reset(0);
            Global.curPanel = Global.SPLASH_PANEL;
            setVisible(false);
        }


        int tlPixel = pixels [ty * worldMapMask.getWidth(this) + tx];
        int tlRed = (tlPixel >> 16) & 0xff;
        int tlGreen = (tlPixel >> 8) & 0xff;
        int tlBlue = (tlPixel) & 0xff;

        int trPixel = pixels [ty * worldMapMask.getWidth(this) + tx + PC.mapIcon.getWidth(this)];
        int trRed = (trPixel >> 16) & 0xff;
        int trGreen = (trPixel >> 8) & 0xff;
        int trBlue = (trPixel) & 0xff;

        int blPixel = pixels [(ty + PC.mapIcon.getHeight(this)) * worldMapMask.getWidth(this) + tx];
        int blRed = (blPixel >> 16) & 0xff;
        int blGreen = (blPixel >> 8) & 0xff;
        int blBlue = (blPixel) & 0xff;

        int brPixel = pixels [(ty + PC.mapIcon.getHeight(this)) * worldMapMask.getWidth(this) + (tx + PC.mapIcon.getWidth(this))];
        int brRed = (brPixel >> 16) & 0xff;
        int brGreen = (brPixel >> 8) & 0xff;
        int brBlue = (brPixel) & 0xff;

        if ( !(tlRed == 0 && tlBlue == 0 && tlGreen == 0) && !(trRed == 0 && trBlue == 0 && trGreen == 0) && !(blRed == 0 && blBlue == 0 && blGreen == 0) && !(brRed == 0 && brBlue == 0 && brGreen == 0))
        {
            PC.x = tx;
            PC.y = ty;
        }
        if ( (tlRed == 255 && tlBlue == 255 && tlGreen == 0) || (trRed == 255 && trBlue == 255 && trGreen == 0) || (blRed == 255 && blBlue == 255 && blGreen == 0) || (brRed == 255 && brBlue == 255 && brGreen == 0))
        {
            PC.spirit = PC.spirit - 1;
        }

        Random rand = new Random();

        if (rand.nextInt(10) == 9)
        {
            battle = new BattlePanel(rand.nextInt(2)+1);
            this.add(battle);
            battle.setBounds(1 + insets.left, 1 + insets.top, 639, 479);
            battle.setVisible(true);
            battle.requestFocus();
            battle.addComponentListener(this);
            iscore = PC.score;
        }

        runWork();
    }
    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }


    public void keyTyped(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void componentHidden(java.awt.event.ComponentEvent componentEvent)
    {
        this.remove(battle);
        battle = null;
        this.requestFocus();
        if (iscore == 0 && PC.score > 0)
        {
            msgBox.setText("So, you have brought peace to one of my minons.\nDon't be too proud of yourself Darkenvader.");
        }
        else if (iscore <75 && PC.score >=75)
        {
            msgBox.setText("You're arrogant young one.\nDo you not know that all your power comes from me?\nI think it's time for you to be humbled!");
            PC.defence = 10;
            PC.attack = 10;
            PC.spirit = 25;
        }
        else if (iscore < 100 && PC.score >= 100)
        {
            msgBox.setText("Impressive... I admire your spirit, and your request is granted.\nYou may leave my world at any time with your girl.\nI'm sure that in your world your deeds will be renouned,\nand this is your opportunity to augment them.\nAnytime you'd like to leave simply press 'v'.");
        }
        runWork();
    }

    public void componentMoved(java.awt.event.ComponentEvent componentEvent)
    {
    }

    public void componentResized(java.awt.event.ComponentEvent componentEvent)
    {
    }

    public void componentShown(java.awt.event.ComponentEvent componentEvent)
    {
    }

}

class BattlePanel extends JPanel implements KeyListener
{
    Image monsterPic;
    Monster monster;

    JTextArea plrStatBox;
    JTextArea mtrStatBox;

    Toolkit toolkit = Toolkit.getDefaultToolkit();


    public BattlePanel(int m)
    {
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
        plrStatBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        plrStatBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        plrStatBox.setEditable(false);

        mtrStatBox.setBounds(1 + insets.left, 300 + insets.top, 319, 180);
        mtrStatBox.setBackground(new Color((float)0.2,(float)0.0,(float)0.0));
        mtrStatBox.setForeground(new Color((float)1.0,(float)0.0,(float)0.0));
        mtrStatBox.setEditable(false);

        plrStatBox.setVisible(true);
        mtrStatBox.setVisible(true);

        addKeyListener(this);

    }

    public boolean isFocusTraversable()
    {
        return true;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.drawImage(monsterPic, 1, 1, this);
        plrStatBox.setText("Stats:\nSpirit: " + Global.PC.spirit + "\nAttack: " + Global.PC.attack + "\nDefence: " + Global.PC.defence);
        mtrStatBox.setText(monster.type + "\nSpirit: " + monster.spirit + "\nAttack: " + monster.attack + "\nDefence: " + monster.defence);
    }


    public void keyTyped(KeyEvent event)
    {
        if (event.getKeyChar() == 'v')
        {
            Global.PC.score = Global.PC.score + monster.score;
            this.setVisible(false);
        }
        else if(event.getKeyChar() == 'd')
        {
            Global.PC.spirit = 0;
            this.setVisible(false);
        }
    }
    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }
    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}


class Global
{
    static int curPanel = 0;

    static final int    SPLASH_PANEL = 0,
                        STORY_PANEL = 1,
                        INSTRUCTION_PANEL = 2,
                        HIGHSCORE_PANEL = 3,
                        CREDIT_PANEL = 4,
                        SETUP_PANEL = 9,
                        GAME_PANEL = 10,
                        BATTLE_PANEL = 11,
                        GAMEOVER_PANEL = 12,
                        VICTORY_PANEL = 13;

    static Player PC = new Player();
}

class Actor
{
    int x;
    int y;
    int spirit;
    int score;
    int attack;
    int defence;
    Image mapIcon;
    int xwidth;
    int ywidth;
    int speed;
    String mapPicURL;

    void setupActor(int X, int Y, String ActorType)
    {
        x = X;
        y = Y;
    }
}


class Player extends Actor
{
    int score;
    void reset(int dfclty)
    {
        x = 700;
        y = 700;
        speed = 4;
        score = 0;
        switch (dfclty)
        {
            case 1:
                spirit = 100;
                attack = 20;
                defence = 20;
                break;
            case 2:
                spirit = 50;
                attack = 15;
                defence = 15;
                break;
            case 3:
                spirit = 25;
                attack = 10;
                defence = 10;
                break;
            default:
                spirit = 100;
                attack = 100;
                defence = 100;
                break;
        }
        mapPicURL = "PCD0.gif";
    }
}

class Monster extends Actor
{
    String picFile;
    String type;

    public Monster(int m)
    {
        switch (m)
        {
            case 1:
                Angel();
                break;
            case 2:
                Umbra();
                break;
            default:
                Angel();
                break;
        }
    }


    private void Angel()
    {
        type = "Angel";
        spirit = 30;
        attack = 10;
        defence = 10;
        score = 10;
        picFile = "PAngel.gif";
    }


    private void Umbra()
    {
        type = "Umbra";
        spirit = 10;
        attack = 5;
        defence = 10;
        score = 5;
        picFile = "PUmbra.gif";
    }
}


class GameOverPanel extends JPanel implements KeyListener
{
    ImageIcon gameOverScrn;
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public GameOverPanel()
    {
        this.addKeyListener(this);
        MediaTracker gameOverTracker = new MediaTracker(this);

        try
        {
            gameOverScrn = new ImageIcon(getClass().getResource("GameOver.gif"));
        }
        catch(Exception ex)
        {
            System.err.println("Could not load game over Image");
        }

        JLabel background = new JLabel(gameOverScrn);
        this.add(background);
        requestFocus();
    }


    public boolean isFocusTraversable()
    {
        return true;
    }


    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }
    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }
    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}

class VictoryPanel extends JPanel implements KeyListener
{
    ImageIcon victoryScrn;
    String playerName;
    JTextField plrname;
    boolean doneVictory;

    public VictoryPanel()
    {
        setBackground(Color.black);
    }
    public void initVictory()
    {
        removeAll();
        doneVictory = false;

        JTextArea label = new JTextArea("Congradulations, you have earned a name for yourself...\nWhat is it?");
        this.add(label);
        label.setEditable(false);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label.setBackground(Color.black);
        label.setForeground(Color.red);
        label.setVisible(true);

        plrname = new JTextField();
        this.add(plrname);
        plrname.addKeyListener(this);
        plrname.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        plrname.setColumns(20);
        plrname.setBackground(Color.white);
        plrname.setForeground(Color.black);
        plrname.setVisible(true);
        plrname.requestFocus();

        repaint();
    }

    public void highScore()
    {
        try
        {
            String[] names = new String[10];
            int[] scores = new int[10];

            File highscores = new File("highscores.txt");
            if (!highscores.exists())
            {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(highscores));
                for (int i = 0; i < 10; i ++)
                {
                    out.writeChars("no one");
                    out.writeChar('\t');
                    out.writeInt(0);
                }
                out.close();
            }
            DataInputStream in = new DataInputStream(new FileInputStream(highscores));
            for (int i = 0; i < 10; i++)
            {
                char chr;
                StringBuffer sBuffer = new StringBuffer();

                while ((chr = in.readChar()) != '\t')
                {
                    sBuffer.append(chr);
                }
                names[i] = sBuffer.toString();
                scores[i] = in.readInt();
            }
            in.close();

            if (Global.PC.score > scores[9])
            {
                int i = 8;
                while (Global.PC.score > scores[i] && i > 0)
                {
                    i--;
                    names[i + 1] = names[i];
                    scores[i + 1]= scores[i];
                }
                names[i] = playerName;
                scores[i] = Global.PC.score;
            }

            DataOutputStream out = new DataOutputStream(new FileOutputStream(highscores));
            for (int i = 0; i < 10; i++)
            {
                out.writeChars(names[i]);
                out.writeChar('\t');
                out.writeInt(scores[i]);
            }
            out.close();

            removeAll();

            victoryScrn = new ImageIcon(getClass().getResource("Victory.gif"));
            JLabel background = new JLabel(victoryScrn);

            Insets insets = this.getInsets();

            add(background);
            setLayout(null);
            background.setBounds(1 + insets.left,1 + insets.top, 639, 479);
            background.setVisible(true);

            addKeyListener(this);
            requestFocus();
            doneVictory = true;
            repaint();
        }
        catch(IOException e)
        {
            System.err.print("Error: " + e);
        }
    }

    public boolean isFocusTraversable()
    {
        return true;
    }


    public void keyTyped(KeyEvent event)
    {
    }
    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {

        if (!doneVictory)
        {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER && plrname.getText().length() > 1)
            {
                playerName = plrname.getText();
                highScore();
            }
        }
        else
        {
            Global.curPanel = Global.HIGHSCORE_PANEL;
            setVisible(false);
        }
    }
    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}

class HighScorePanel extends JPanel implements KeyListener
{
    Font font;
    public HighScorePanel()
    {
        this.addKeyListener(this);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        repaint();
    }

    public boolean isFocusTraversable()
    {
        return true;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.black);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString("Noteable Adventurers!", 200, 50);

        try
        {
            File highscores = new File("highscores.txt");
            if (!highscores.exists())
            {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(highscores));
                for (int i = 0; i < 10; i ++)
                {
                    out.writeChars("no one");
                    out.writeChar('\t');
                    out.writeInt(0);
                }
                out.close();
            }
            DataInputStream in = new DataInputStream(new FileInputStream(highscores));
            for (int i = 0; i < 10; i++)
            {
                char chr;
                StringBuffer sBuffer = new StringBuffer();

                while ((chr = in.readChar()) != '\t')
                {
                    sBuffer.append(chr);
                }
                g.drawString(((i + 1) + ") " + sBuffer.toString() + ": " + in.readInt()), 200, 100 + (i * 25));
            }
        }
        catch(IOException e)
        {
        }
    }

    public void keyTyped(KeyEvent event)
    {
        Global.curPanel = Global.SPLASH_PANEL;
        setVisible(false);
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }
}


