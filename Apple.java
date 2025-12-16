import java.util.Random;

public class Apple {
    private int x;
    private int y;

    public void generate() {
        Random random = new Random();
        this.x = random.nextInt(GameCenter.SCREEN_WIDTH / GameCenter.UNIT_SIZE) * GameCenter.UNIT_SIZE;
        this.y = random.nextInt(GameCenter.SCREEN_HEIGHT / GameCenter.UNIT_SIZE) * GameCenter.UNIT_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
