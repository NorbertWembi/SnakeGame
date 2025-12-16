import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCenter extends JPanel implements ActionListener {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 25;
    private static final int DELAY = 190;

    private Snake snake;
    private Apple apple;
    private int applesEaten;
    private boolean running;
    private Timer timer;

    public GameCenter() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.snake = new Snake(800); // Initial snake size
        this.apple = new Apple();
        startGame();
    }

    public void startGame() {
        apple.generate();
        running = true;
        applesEaten = 0;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        paintGame(g);
    }

    private void paintGame(Graphics g) {
        if (running) {
            // Draw the apple
            g.setColor(Color.red);
            g.fillOval(apple.getX(), apple.getY(), UNIT_SIZE, UNIT_SIZE);

            // Draw the snake
            for (int i = 0; i < snake.getBodyParts(); i++) {
                if (i == 0) {
                    g.setColor(Color.blue); // Snake head
                } else {
                    g.setColor(Color.blue); // Snake body
                }
                g.fillRect(snake.getX(i), snake.getY(i), UNIT_SIZE, UNIT_SIZE);
            }

            // Draw score
            g.setColor(Color.magenta);
            g.setFont(new Font("Times new roman", Font.BOLD, 35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Snake Game - Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Snake Game - Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.magenta);
        g.setFont(new Font("Times new roman", Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game over. Thanks for playing", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 3, SCREEN_HEIGHT / 2);
        //System.out.println("");
        g.drawString("Final Score: " + applesEaten, (SCREEN_WIDTH + metrics.stringWidth("Game Over")) / 3, SCREEN_HEIGHT / 2 + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    private void checkApple() {
        if (snake.getX(0) == apple.getX() && snake.getY(0) == apple.getY()) {
            snake.grow();
            applesEaten++;
            apple.generate();
        }
    }

    private void checkCollision() {
        // Check if snake collides with itself
        for (int i = snake.getBodyParts(); i > 0; i--) {
            if (snake.getX(0) == snake.getX(i) && snake.getY(0) == snake.getY(i)) {
                running = false;
            }
        }

        // Check wall collisions
        if (snake.getX(0) < 0 || snake.getX(0) >= SCREEN_WIDTH || snake.getY(0) < 0 || snake.getY(0) >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getX(0) != 'R') snake.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getX(0) != 'L') snake.setDirection('R');
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getX(0) != 'D') snake.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getX(0) != 'U') snake.setDirection('D');
                    break;
            }
        }
    }
}
