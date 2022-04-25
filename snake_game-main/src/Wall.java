import edu.macalester.graphics.Rectangle;

import java.util.List;
import java.util.Random;

import java.awt.Color;

public class Wall extends Rectangle {

    private Color YELLOW = SnakeGame.YELLOW;
    private Color RED = SnakeGame.RED;
    private Color AQUA = SnakeGame.AQUA;
    private Color LIGHT_AQUA = SnakeGame.LIGHT_AQUA;
    private Color DARK_GRAY = SnakeGame.DARK_GRAY;

    /**
     * creates a randomly colored wall.
     * @param x 
     * @param y 
     * @param width
     * @param height
     */
    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);

        Random random = new Random();
        List<Color> colors = List.of(YELLOW, RED, AQUA, LIGHT_AQUA);
        this.setFilled(true);
        this.setFillColor(colors.get(random.nextInt(colors.size())));
        this.setStroked(true);
        this.setStrokeColor(DARK_GRAY);
    }

}