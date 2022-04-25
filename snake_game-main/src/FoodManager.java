import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class FoodManager extends GraphicsGroup {

    private Food food;

    private Point location;

    private CanvasWindow canvas;
    
    private int x;
    private int y;

    /**
     * Creates an initial piece of food and adds it to FoodManager's graphics group
     */
    FoodManager(CanvasWindow canvas) {
        this.canvas = canvas;
        food = new Food();
        food.setCenter(100, 400);
        this.add(food);
    }

    /**
     * Creates a new piece of food and adds it to the graphics group
     */
    public void addFood() {
        newLocation();
        this.add(food);
    }

    /** 
     * Sets the piece of food to a new random spot on the canvas where the food stays inbounds
     * and does not intersect with the walls.
     */
    public void newLocation() {
        x = new Random().nextInt(SnakeGame.CANVAS_WIDTH);
        y = new Random().nextInt(SnakeGame.CANVAS_HEIGHT);  
        while (canvas.getElementAt(x + food.getRadius(), y + food.getRadius()) instanceof Wall || 
        canvas.getElementAt(x + food.getRadius(), y - food.getRadius()) instanceof Wall || 
        canvas.getElementAt(x - food.getRadius(), y + food.getRadius()) instanceof Wall || 
        canvas.getElementAt(x - food.getRadius(), y - food.getRadius()) instanceof Wall ||
        x - food.getRadius() < 0 || x + food.getRadius() > SnakeGame.CANVAS_WIDTH ||
        y - food.getRadius() < 0 || y + food.getRadius() > SnakeGame.CANVAS_HEIGHT) {
            x = new Random().nextInt(SnakeGame.CANVAS_WIDTH);
            y = new Random().nextInt(SnakeGame.CANVAS_HEIGHT);  
        }

        location = new Point(x, y);
        food.setCenter(location);
    }
}
 