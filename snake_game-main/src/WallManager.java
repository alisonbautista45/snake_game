
import edu.macalester.graphics.GraphicsGroup;

public class WallManager extends GraphicsGroup {

    private static final double CANVAS_WIDTH = SnakeGame.CANVAS_WIDTH;
    private static final double CANVAS_HEIGHT = SnakeGame.CANVAS_HEIGHT;
    private static final double SMALLER_SIDE = 50;

    public WallManager() {
        this.removeAll();
    }

    /**
     * Creates borders around the canvas.
     */
    public void generateBorders() {
        Wall wall1 = new Wall(0, 0, CANVAS_WIDTH - SMALLER_SIDE, SMALLER_SIDE);
        this.add(wall1);

        Wall wall2 = new Wall(CANVAS_WIDTH - SMALLER_SIDE, 0, SMALLER_SIDE, CANVAS_HEIGHT - SMALLER_SIDE);
        this.add(wall2);

        Wall wall3 = new Wall(SMALLER_SIDE, CANVAS_HEIGHT - SMALLER_SIDE, CANVAS_WIDTH - SMALLER_SIDE, SMALLER_SIDE);
        this.add(wall3);

        Wall wall4 = new Wall(0, SMALLER_SIDE, SMALLER_SIDE, CANVAS_HEIGHT - SMALLER_SIDE);
        this.add(wall4);
    }

    /**
     * Creates two vertical walls at 1/3 and 2/3 of the width of canvas with openings for the snake to
     * go through.
     */
    public void generateDoors() {
        Wall wall1 = new Wall((CANVAS_WIDTH / 3) - SMALLER_SIDE, 0, SMALLER_SIDE, CANVAS_HEIGHT / 3);
        this.add(wall1);

        Wall wall2 = new Wall((CANVAS_WIDTH / 3) - SMALLER_SIDE, (CANVAS_HEIGHT / 3) + SMALLER_SIDE, SMALLER_SIDE, (2 * CANVAS_HEIGHT / 3) - SMALLER_SIDE);
        this.add(wall2);

        Wall wall3 = new Wall((2 * CANVAS_WIDTH / 3) - SMALLER_SIDE, 0, SMALLER_SIDE, 2 * CANVAS_HEIGHT / 3);
        this.add(wall3);

        Wall wall4 = new Wall((2 * CANVAS_WIDTH / 3) - SMALLER_SIDE, (2 * CANVAS_HEIGHT / 3) + SMALLER_SIDE, SMALLER_SIDE, (CANVAS_HEIGHT / 3) - SMALLER_SIDE);
        this.add(wall4);
    }

    /**
     * Creates a simple maze for snake to navigate.
     */
    public void generateSimpleMaze() {
        Wall wall1 = new Wall(0, 0, SMALLER_SIDE, 2 * CANVAS_HEIGHT / 3);
        this.add(wall1);

        Wall wall2 = new Wall(0, (2 * CANVAS_HEIGHT / 3) + SMALLER_SIDE, SMALLER_SIDE, (CANVAS_HEIGHT / 3) - SMALLER_SIDE);
        this.add(wall2);

        Wall wall3 = new Wall(CANVAS_WIDTH / 3, CANVAS_HEIGHT / 3, 2 * CANVAS_WIDTH / 3, SMALLER_SIDE);
        this.add(wall3);

        Wall wall4 = new Wall(CANVAS_WIDTH / 3, (CANVAS_HEIGHT / 3) + SMALLER_SIDE, SMALLER_SIDE, CANVAS_HEIGHT / 3);
        this.add(wall4);

        Wall wall5 = new Wall(CANVAS_WIDTH / 3, (2 * CANVAS_HEIGHT / 3) + SMALLER_SIDE, CANVAS_WIDTH / 3, SMALLER_SIDE);
        this.add(wall5);

        Wall wall6 = new Wall(CANVAS_WIDTH - SMALLER_SIDE, 3 * CANVAS_HEIGHT / 4, SMALLER_SIDE, CANVAS_HEIGHT / 4);
        this.add(wall6);
    }

    /**
     * Creates a herder maze for snake to navigate.
     */
    public void generateHarderMaze() {
        Wall wall1 = new Wall(0, 0, (CANVAS_WIDTH / 2) - SMALLER_SIDE, SMALLER_SIDE);
        this.add(wall1);

        Wall wall2 = new Wall((CANVAS_WIDTH / 2) + SMALLER_SIDE, 0, (CANVAS_WIDTH / 2) - SMALLER_SIDE, SMALLER_SIDE);
        this.add(wall2);

        Wall wall3 = new Wall(0, CANVAS_HEIGHT / 4, CANVAS_WIDTH / 3, SMALLER_SIDE);
        this.add(wall3);

        Wall wall4 = new Wall((CANVAS_WIDTH / 3) - SMALLER_SIDE, (CANVAS_HEIGHT / 4) + SMALLER_SIDE, SMALLER_SIDE, CANVAS_HEIGHT / 3);
        this.add(wall4);

        Wall wall5 = new Wall(2 * CANVAS_WIDTH / 3, (CANVAS_HEIGHT / 4) + SMALLER_SIDE, SMALLER_SIDE, CANVAS_HEIGHT / 3);
        this.add(wall5);

        Wall wall6 = new Wall(2 * CANVAS_WIDTH / 3, (7 * CANVAS_HEIGHT / 12) + SMALLER_SIDE, CANVAS_WIDTH / 3, SMALLER_SIDE);
        this.add(wall6);

        Wall wall7 = new Wall(CANVAS_WIDTH / 4, (3 * CANVAS_HEIGHT / 4) + SMALLER_SIDE, CANVAS_WIDTH / 2, SMALLER_SIDE);
        this.add(wall7);
    }

    /**
     * Creates 2 rows of walls of 25 walls in total that appear in the very top and very bottom of the canvas.
     */
    public void generateFunScreen() {
        double x = 0;
        double y = 20;
        for (int numWalls = 0; numWalls < 25; numWalls++) {
            if (x > CANVAS_WIDTH) {
                x = 10;
                y = CANVAS_HEIGHT - (20 + SMALLER_SIDE);
            }
            Wall wall = new Wall(x, y, SMALLER_SIDE, SMALLER_SIDE);
            this.add(wall);
            x += 20 + SMALLER_SIDE;
        }
    }
}
