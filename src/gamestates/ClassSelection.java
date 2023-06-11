package gamestates;

import main.Game;
import utilities.Load;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;



public class ClassSelection extends State implements Statemethods {

    private BufferedImage backgroundImage;
    private int comNum = 0;
    private Font arial_80B;

    private Graphics g;
    public ClassSelection(Game game) {
        super(game);
        backgroundImage = Load.GetSpriteImg(Load.BACKGROUND_IMG);
        arial_80B = new Font ("Arial", Font.BOLD, 80);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        this.g = g;

        g.drawImage(backgroundImage, 0,0, Game.screenWidth, Game.screenHeight, null);
        g.setColor(Color.white);
        g.setFont(arial_80B);
        g.setFont(g.getFont().deriveFont(50F));

        String text = "Select your class!";
        int x = getCenterTextX(text);
        int y = (int) (Game.screenHeight/2);
        g.drawString(text, x, y);

        text = "FIGHTER";
        x -= Game.tileSize;
        y+= Game.tileSize;
        drawText(x, y, text);
        if (comNum == 0)
            drawPointer(x, y, text);

        text = "ARCHER";
        x = getCenterTextX(text);
        drawText(x, y, text);
        if (comNum == 1)
            drawPointer(x, y, text);

        text = "BARD";
        x += Game.tileSize*2;
        drawText(x, y, text);
        if (comNum == 2)
            drawPointer(x, y, text);

        g.setFont(g.getFont().deriveFont(Font.ITALIC));

        text = "Return";
        x = getCenterTextX(text);
        y+= Game.tileSize*1.5;
        g.drawString(text, x, y);
        if (comNum == 3)
            drawPointer(x, y, text);
    }

    public void drawText(int x, int y, String text) {
        g.setFont(g.getFont().deriveFont(Font.BOLD, 42F));
        g.setColor(Color.BLACK);
        g.fillRoundRect(x-Game.tileSize/4,  y-Game.tileSize/3,
                (int) (Game.tileSize*1.5), Game.tileSize/2, 35, 35);
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

    public void drawPointer(int x, int y, String text) {
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        g.setFont(g.getFont().deriveFont(Font.PLAIN));
        g.drawString("v", (int) (x+length/2.5), y-Game.tileSize/3);
    }

    public int getCenterTextX(String text) {
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = Game.screenWidth/2 - length/2;
        return x;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private void resetButtons() {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (comNum == 3)
                comNum = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (comNum != 3)
                comNum = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (comNum == 0)
                comNum = 2;
            else if (comNum == 3)
                comNum = 0;
            else
                comNum--;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (comNum == 2)
                comNum = 0;
            else if (comNum == 3)
                comNum = 2;
            else
                comNum++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (comNum) {
                case 0 -> {
                    System.out.println("Fighter selected");
                    setGameState(Gamestate.PLAYING);
                }
                case 1 -> {
                    System.out.println("Archer selected");
                    setGameState(Gamestate.PLAYING);
                }
                case 2 -> {
                    System.out.println("Bard selected");
                    setGameState(Gamestate.PLAYING);
                }
                case 3 -> {
                    setGameState(Gamestate.MENU);
                }
            }
        }
    }
}