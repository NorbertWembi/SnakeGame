// Norbert wembi
import javax.swing.*;

public class Snake extends JFrame {
    GenerateSnake(){
        this.add(new GameItem());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // I have stopped at 04:23
    }
}
