// Norbert wembi
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.font.FontRenderContext;
import java.util.Random;

public class GameItem extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 110;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    int bodyParts = 3;
    int applesEaten;
    int appleX;
    int appleY;
    static char direction = 'R';
    boolean running =false;
    Timer timer;
    Random random;

    GameItem(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();;
    }
    public void startGame(){
        newApple();
        running=true;
        timer= new Timer(DELAY, this);
        timer.start();

    }
    public void paint(Graphics g){
        super.paintComponent(g);
        paintGame(g);
    }
    public void paintGame(Graphics g){
        if(running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.blue);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.blue);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Times new roman", Font.BOLD, 35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("DAL -Snake by Medard - Score: "+applesEaten, (600 - metrics.stringWidth("DAL -Snake - Score: "+applesEaten))/2,g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }
    public void newApple(){
        appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public enum Direction {
        LEFT, RIGHT, DOWN, UP;
    }

    public void move(){
        for (int i=bodyParts; i>0; i--){
            x[i]=x[i-1];
            y[i]= y[i-1];
        }
        switch (direction){
            case 'U':
                y[0]=y[0]-UNIT_SIZE;
                break;
            case 'D':
                y[0]=y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0]=x[0]-UNIT_SIZE;
                break;
            case 'R':
                x[0]=x[0]+ UNIT_SIZE;
                break;
        }

    }
    public void checkApple(){
        if ((x[0]==appleX) && (y[0]==appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollision(){
        // Checks if head collide with body
        for (int i=bodyParts; i>0;i--){
            if ((x[0]==x[i]) && (y[0]==y[i])){
                running=false;
            }
        }
        // Checks if head touches left border
        if (x[0]<0){
            running=false;
        }
        // Checks if head touches right border
        if (x[0]>SCREEN_WIDTH){
            running=false;
        }
        // Checks if head touches top border
        if (y[0]<0){
            running=false;
        }
        // Checks if head touches bottom border
        if (y[0]>SCREEN_HEIGHT ){
            running=false;
        }

        if (!running){
            timer.stop();
        }


    }
    public void gameOver(Graphics g){
        // Text
        g.setColor(Color.red);
        g.setFont(new Font("Times new roman", Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over. Thanks for playing", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);

        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("DAL Snake by Medard - Score: "+applesEaten, (400 - metrics.stringWidth("DAL Snake - Score: "+applesEaten))/2,g.getFont().getSize());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction!='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction!='D'){
                        direction='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction!='U'){
                        direction='D';
                    }
                    break;
            }
        }
        public static void handleInput(){
        }
    }
    class Brick{

    }
    class Point{

    }
}
