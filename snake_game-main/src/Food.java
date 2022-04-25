import java.awt.Color;

import edu.macalester.graphics.*;


public class Food extends Ellipse{

    //The radius of our food ellipse
    private static int radius = 8;

    /**
     * Creates a piece of food
     */
    public Food() {
        super(0 - radius, 0 - radius, 
        radius * 2, radius * 2);
        this.setFilled(true);
        this.setFillColor(SnakeGame.RED);
        this.setStrokeColor(Color.BLACK);
    }

    /**
     * Returns the radius of the food ellipse
     */
    public int getRadius() {
        return radius;
    }
}