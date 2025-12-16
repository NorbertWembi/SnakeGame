import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set up the JFrame
        JFrame frame = new JFrame("Snake Game");

        // Create the GameCenter panel (the game itself)
        GameCenter gamePanel = new GameCenter();

        // Add the GameCenter panel to the JFrame
        frame.add(gamePanel);

        // Set JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack(); // Ensure the frame is sized to fit the panel
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true); // Make the window visible

        // Start the game
        gamePanel.startGame();
    }
}
