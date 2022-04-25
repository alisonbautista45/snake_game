import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;


public class Snake extends GraphicsGroup {

    private int i = 0;
    private double speed = 2;

    private double dx = 0;
    private double dy = 0;

    private static double width = 15;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;

    private GraphicsObject snakeHeadLeft = SnakeHead.createSnakeHead(270);
    private GraphicsObject snakeHeadRight = SnakeHead.createSnakeHead(90);
    private GraphicsObject snakeHeadUp = SnakeHead.createSnakeHead(0);
    private GraphicsObject snakeHeadDown = SnakeHead.createSnakeHead(180);
    private GraphicsObject snakeHead;

    /**
     * Sets the current snake head (snakeHead) to snakeHeadUp, initializes all of the movement booleans to false,
     * and sets the starting position of the snake.
     */
    public Snake() {
        snakeHead = snakeHeadUp;
        this.add(snakeHead);
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        movingDown = false;
        this.startPosition();
    }

    /**
     * Moves the snake's head left, turns on the movement boolean for the snake moving left, and calls the method to 
     * turn the snake's head appropriately (turnSnakeHead();)
     */
    public void moveLeft() {
        dx = -speed;
        dy = 0;
        if (this.getX() + dx <= 0) {
            this.setX(SnakeGame.CANVAS_WIDTH);
        }
        this.updatePosition();
        movingLeft = true;
        movingRight = false;
        movingUp = false;
        movingDown = false;
        turnSnakeHead();
    }

    /**
     * Moves the snake's head right, turns on the movement boolean for the snake moving right, and calls the method to 
     * turn the snake's head appropriately (turnSnakeHead();)
     */
    public void moveRight() {
        dx = speed;
        dy = 0;
        if (this.getX() + width + dx >= SnakeGame.CANVAS_WIDTH) {
            this.setX(0);
        }
        this.updatePosition();
        movingLeft = false;
        movingRight = true;
        movingUp = false;
        movingDown = false;
        turnSnakeHead();
    }

    /**
     * Moves the snake's head up, turns on the movement boolean for the snake moving up, and calls the method to 
     * turn the snake's head appropriately (turnSnakeHead();)
     */
    public void moveUp() {
        dx = 0;
        dy = -speed;
        if (this.getY() + dy <= 0) {
            this.setY(600);
        }
        this.updatePosition();
        movingLeft = false;
        movingRight = false;
        movingUp = true;
        movingDown = false;
        turnSnakeHead();
    }

    /**
     * Moves the snake's head down, turns on the movement boolean for the snake moving down, and calls the method to 
     * turn the snake's head appropriately (turnSnakeHead();)
     */
    public void moveDown() {
        dx = 0;
        dy = speed;
        if (this.getY() + width + dy >= SnakeGame.CANVAS_HEIGHT) {
            this.setY(0);
        }
        this.updatePosition();
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        movingDown = true;
        turnSnakeHead();
    }

    /**
     * Updates the postion of the snake's head, given the dx and dy specified by the direction the snake is moving.
     */
    public void updatePosition() {
        this.setPosition(this.getX() + dx, this.getY() + dy);
    }

    /**
     * Sets the start position of the snake's head
     */
    private void startPosition() {
        this.setPosition(SnakeGame.CANVAS_WIDTH * 0.5, SnakeGame.CANVAS_HEIGHT * 0.5);
    }

    /**
     * Creates a list of points (path) 2.5 units behind the snake's head (in the diretion it is currently going), 
     * with adjusting the distance of the points away from a line directly behind the snake on intervals of four.
     * This way, every four points is allowed to be a new distance away from the line directly behind the snake's
     * head, allowing for an object following along said path (consitently 
     * setting its position to the next point on the list) to appear that far away from the said line. 
     * This is adjusted so that the path appears in a curved pattern, simulating a snake slithering.
     */

    public void addToPath(List<Point> path) {
        Point point = new Point(this.getX(), this.getY());
        if (movingLeft) {
            if ((i % 32) < 4) {
                point = new Point(this.getX() + 2.5, this.getY());
            } else if ((i % 32) >= 4 && (i % 32) < 8) {
                point = new Point(this.getX() + 2.5, this.getY() + 1.25);
            } else if ((i % 32) >= 8 && (i % 32) < 12) {
                point = new Point(this.getX() + 2.5, this.getY() + 2.5);
            } else if ((i % 32) >= 12 && (i % 32) < 16) {
                point = new Point(this.getX() + 2.5, this.getY() + 1.25);
            } else if ((i % 32) >= 16 && (i % 32) < 20) {
                point = new Point(this.getX() + 2.5, this.getY());
            } else if ((i % 32) >= 20 && (i % 32) < 24) {
                point = new Point(this.getX() + 2.5, this.getY() - 1.25);
            } else if ((i % 32) >= 24 && (i % 32) < 28) {
                point = new Point(this.getX() + 2.5, this.getY() - 2.5);
            } else if ((i % 32) >= 28 && (i % 32) < 32) {
                point = new Point(this.getX() + 2.5, this.getY() - 1.25);
            }
        } else if (movingRight) {
            if ((i % 32) < 4) {
                point = new Point(this.getX() - 2.5, this.getY());
            } else if ((i % 32) >= 4 && (i % 32) < 8) {
                point = new Point(this.getX() - 2.5, this.getY() + 1.25);
            } else if ((i % 32) >= 8 && (i % 32) < 12) {
                point = new Point(this.getX() - 2.5, this.getY() + 2.5);
            } else if ((i % 32) >= 12 && (i % 32) < 16) {
                point = new Point(this.getX() - 2.5, this.getY() + 1.25);
            } else if ((i % 32) >= 16 && (i % 32) < 20) {
                point = new Point(this.getX() - 2.5, this.getY());
            } else if ((i % 32) >= 20 && (i % 32) < 24) {
                point = new Point(this.getX() - 2.5, this.getY() - 1.25);
            } else if ((i % 32) >= 24 && (i % 32) < 28) {
                point = new Point(this.getX() - 2.5, this.getY() - 2.5);
            } else if ((i % 32) >= 28 && (i % 32) < 32) {
                point = new Point(this.getX() - 2.5, this.getY() - 1.25);
            }
        } else if (movingUp) {
            if ((i % 32) < 4) {
                point = new Point(this.getX(), this.getY() + 2.5);
            } else if ((i % 32) >= 4 && (i % 32) < 8) {
                point = new Point(this.getX() + 1.25, this.getY() + 2.5);
            } else if ((i % 32) >= 8 && (i % 32) < 12) {
                point = new Point(this.getX() + 2.5, this.getY() + 2.5);
            } else if ((i % 32) >= 12 && (i % 32) < 16) {
                point = new Point(this.getX() + 1.25, this.getY() + 2.5);
            } else if ((i % 32) >= 16 && (i % 32) < 20) {
                point = new Point(this.getX(), this.getY() + 2.5);
            } else if ((i % 32) >= 20 && (i % 32) < 24) {
                point = new Point(this.getX() - 1.25, this.getY() + 2.5);
            } else if ((i % 32) >= 24 && (i % 32) < 28) {
                point = new Point(this.getX() - 2.5, this.getY() + 2.5);
            } else if ((i % 32) >= 28 && (i % 32) < 32) {
                point = new Point(this.getX() - 1.25, this.getY() + 2.5);
            }
        } else if (movingDown) {
            if ((i % 32) < 4) {
                point = new Point(this.getX(), this.getY() - 2.5);
            } else if ((i % 32) >= 4 && (i % 32) < 8) {
                point = new Point(this.getX() + 1.25, this.getY() - 2.5);
            } else if ((i % 32) >= 8 && (i % 32) < 12) {
                point = new Point(this.getX() + 2.5, this.getY() - 2.5);
            } else if ((i % 32) >= 12 && (i % 32) < 16) {
                point = new Point(this.getX() + 1.25, this.getY() - 2.5);
            } else if ((i % 32) >= 16 && (i % 32) < 20) {
                point = new Point(this.getX(), this.getY() - 2.5);
            } else if ((i % 32) >= 20 && (i % 32) < 24) {
                point = new Point(this.getX() - 1.25, this.getY() - 2.5);
            } else if ((i % 32) >= 24 && (i % 32) < 28) {
                point = new Point(this.getX() - 2.5, this.getY() - 2.5);
            } else if ((i % 32) >= 28 && (i % 32) < 32) {
                point = new Point(this.getX() - 1.25, this.getY() - 2.5);
            }
        }
        i++;
        path.add(point);
    }

    /**
     * Gets the points that represent where the snake's eyes are (the corners of the bounds in the direction the 
     * snake is facing), and adds them to a list. Only the two current points representing th snake's eyes are on 
     * the list at any one time, with the left eye being snakeEyes.get(0) and the right eye being snakeEyes.get(1).
     */

    public List<Point> snakeEyes() {
        List<Point> eyes = new ArrayList<>();
        Point leftEye = new Point(this.getCenter().getX() + 7.5, this.getCenter().getY() - 7.55);
        Point rightEye = new Point(this.getCenter().getX() - 7.5, this.getCenter().getY() - 7.55);
        if (movingLeft) {
            leftEye = new Point(this.getCenter().getX() - 7.55, this.getCenter().getY() + 7.5);
            rightEye = new Point(this.getCenter().getX() - 7.55, this.getCenter().getY() - 7.5);
        }
        if (movingRight) {
            leftEye = new Point(this.getCenter().getX() + 7.55, this.getCenter().getY() - 7.5);
            rightEye = new Point(this.getCenter().getX() + 7.55, this.getCenter().getY() + 7.5);
        }
        if (movingUp) {
            leftEye = new Point(this.getCenter().getX() + 7.5, this.getCenter().getY() - 7.55);
            rightEye = new Point(this.getCenter().getX() - 7.5, this.getCenter().getY() - 7.55);
        }
        if (movingDown) {
            leftEye = new Point(this.getCenter().getX() + 7.5, this.getCenter().getY() + 7.55);
            rightEye = new Point(this.getCenter().getX() - 7.5, this.getCenter().getY() + 7.55);
        }
        eyes.clear();
        eyes.add(leftEye);
        eyes.add(rightEye);
        return eyes;
    }

    /**
     * Removes the snakeHead from this graphics group,
     * then baed on the direction the snake is moving, indicated by the below booleans, sets the snakeHead equal
     * to one facing the appropriate direction, and readds the snakeHEad to the graphicsGroup.
     */

    public void turnSnakeHead() {
        this.remove(snakeHead);
        if (movingLeft) {
            snakeHead = snakeHeadLeft;
            this.add(snakeHead);
        } else if (movingRight) {
            snakeHead = snakeHeadRight;
            this.add(snakeHead);
        } else if (movingDown) {
            snakeHead = snakeHeadDown;
            this.add(snakeHead);
        } else if (movingUp) {
            snakeHead = snakeHeadUp;
            this.add(snakeHead);
        }
    }

}
