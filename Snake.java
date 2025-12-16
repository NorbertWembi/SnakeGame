public class Snake {
    private final int[] x;
    private final int[] y;
    private int bodyParts;
    private char direction;

    public Snake(int maxSize) {
        x = new int[maxSize];
        y = new int[maxSize];
        bodyParts = 3;
        direction = 'R';
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] -= GameCenter.UNIT_SIZE;
                break;
            case 'D':
                y[0] += GameCenter.UNIT_SIZE;
                break;
            case 'L':
                x[0] -= GameCenter.UNIT_SIZE;
                break;
            case 'R':
                x[0] += GameCenter.UNIT_SIZE;
                break;
        }
    }

    public void grow() {
        bodyParts++;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void resetPosition() {
        bodyParts = 3;
        direction = 'R';
    }
}
