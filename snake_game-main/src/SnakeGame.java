import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;


import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class SnakeGame extends GraphicsGroup {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;

    public static final Color YELLOW = new Color(238, 186, 76);
    public static final Color RED = new Color(227, 73, 59);
    public static final Color AQUA = new Color(35, 181, 175);
    public static final Color LIGHT_AQUA = new Color(169, 221, 217);
    public static final Color DARK_GRAY = new Color(58, 58, 60);
    public static final Color DARK_GREEN = new Color(0, 82, 33);

    private Snake snake;

    private FoodManager food;

    private WallManager wallManager;

    private Collision collide;

    private Segments segments;
    private List<Segments> allSegments = new ArrayList<>();
    private int numSegs;
    private List<Point> path;
    
    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    private GraphicsText score;
    private GraphicsText gameOver;

    private CanvasWindow canvas;

    private SnakeGameWindow window;

    private GraphicsGroup menuLayer = new GraphicsGroup();


    public static void main(String[] args) {
        SnakeGameWindow snakeGameWindow = new SnakeGameWindow();
        snakeGameWindow.newGame().homeScreen();
    }

    /**
     * creates SnakeGame and adds all it simportant elements.
     * @param canvas canvas window that hosts the game.
     * @param window creates the canvas and implements event handlers.
     */
    public SnakeGame(CanvasWindow canvas, SnakeGameWindow window) {

        this.canvas = canvas;
        this.window = window;

        food = new FoodManager(canvas);
        snake = new Snake();

        numSegs = 0;
        path = new ArrayList<>();

        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;

        wallManager = new WallManager();
        this.add(wallManager);

        collide = new Collision(snake, food, wallManager, this);

        score = new GraphicsText("Score: " + numSegs);
        score.setCenter(CANVAS_WIDTH * 0.1, CANVAS_HEIGHT * 0.1);
    }

    /**
     * adds the food, snake, and score board to the game.
     */
    private void run() {
        this.add(food);
        this.add(snake);
        menuLayer.add(score);
    }

    /**
     * Creates a new Segments object, which takes in the list of points path. Score is updated,
     * since a segment added indicates an increase in score. Each segment object is added to its graphics group
     * made in segments, which is then added to the snake graphics group.
     */

    private void addingSegments(List<Point> path) {
        numSegs++;
        segments = new Segments(path, numSegs);
        segments.addToGroup();
        allSegments.add(segments);
        updateScore();
        this.add(segments.getSegmentsGroup());
    }

    /**
     * Sets the segments to follow along their input path, and sets the newest segment to have the smallest size, 
     * increasing until the fourth from newest has the standard size of a 10x10 rectangle. 
     */

    private void following() {
        for (Segments segs : allSegments) {
            if (allSegments.get(numSegs - 1) == segs) {
                segs.setScale(0.4);
                segs.setStrokeWidth(0.4);
            } else if (allSegments.get(numSegs - 2) == segs) {
                segs.setScale(0.6);
                segs.setStrokeWidth(0.4);
            } else if (allSegments.get(numSegs - 3) == segs) {
                segs.setScale(0.8);
                segs.setStrokeWidth(0.4);
            } else {
                segs.setScale(1);
            }
            segs.follow();
        }
    }

    /**
     * checks if the snake has collided with the walls or itself, ends the game if there has been a collision.
     */
    private void checkForCollision() {
        if (collide.wallCollision()) {
            this.removeAll();
            gameOverScreen();
        }
        if (collide.snakeCollision()) {
            this.removeAll();
            gameOverScreen();
        }
    }

    /**
     * turns the snake to face the direction of the key pressed.
     * @param event key pressed
     */
    public void onKeyDown(KeyboardEvent event) {
        if ((event.getKey() == Key.LEFT_ARROW && moveRight != true) ||
            (event.getKey() == Key.LEFT_ARROW && numSegs == 0)) {
            moveRight = false;
            moveUp = false;
            moveDown = false;
            moveLeft = true;
        }
        if ((event.getKey() == Key.RIGHT_ARROW && moveLeft != true) ||
            (event.getKey() == Key.RIGHT_ARROW && numSegs == 0)) {
            moveLeft = false;
            moveUp = false;
            moveDown = false;
            moveRight = true;
        }
        if ((event.getKey() == Key.UP_ARROW && moveDown != true) ||
            (event.getKey() == Key.UP_ARROW && numSegs == 0)) {
            moveLeft = false;
            moveRight = false;
            moveDown = false;
            moveUp = true;
        }
        if ((event.getKey() == Key.DOWN_ARROW && moveUp != true) ||
            (event.getKey() == Key.DOWN_ARROW && numSegs == 0)) {
            moveLeft = false;
            moveRight = false;
            moveUp = false;
            moveDown = true;
        }
    }

    /**
     * moves the snake and its segments in the direction of the key press, and adds food to the canvas if the snake intersects it.
     */
    public void animate() {
        checkForCollision();
        if (collide.eatsFood()) {
            addingSegments(path);
            food.addFood();
        }
        snake.addToPath(path);
        if (moveLeft) {
            snake.moveLeft();
        }
        if (moveRight) {
            snake.moveRight();
        }
        if (moveUp) {
            snake.moveUp();
        }
        if (moveDown) {
            snake.moveDown();
        }
        following();
    }

    /**
     * adds the game to the canvas and creates the homescreen.
     */
    private void homeScreen() {
        canvas.add(this);
        canvas.add(menuLayer);
        levelButtons();
        welcomeText();
        startButton();
    }

    /**
     * Adds the game's title and "choose level" text to the menu GraphicsGroup.
     */
    private void welcomeText() {
        GraphicsText title = new GraphicsText();
        title.setText("SNAKE GAME");
        title.setFillColor(DARK_GRAY);
        title.setCenter(CANVAS_WIDTH / 5, CANVAS_HEIGHT / 3);
        title.setFont(FontStyle.BOLD, 80);
        menuLayer.add(title);

        GraphicsText chooseLevel = new GraphicsText();
        chooseLevel.setText("Choose your level");
        chooseLevel.setFillColor(DARK_GRAY);
        chooseLevel.setCenter(CANVAS_WIDTH / 5, 2 * CANVAS_HEIGHT / 3);
        chooseLevel.setFont(FontStyle.BOLD, 60);
        menuLayer.add(chooseLevel);
    }

    /**
     * Adds a start button to the menu GraphicsGroup.
     */
    private void startButton() {
        Button start = new Button("Start!");
        start.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        menuLayer.add(start);
        start.onClick(() -> {
            menuLayer.removeAll();
            run();
        });
    }

    /**
     * Adds 5 buttons to the nemu GraphicsGroup that enable use to choose levels.
     */
    private void levelButtons() {
        Button basic = new Button("No Obstacles");
        basic.setPosition(50, 3 * CANVAS_HEIGHT / 4);
        menuLayer.add(basic);
        basic.onClick(() -> wallManager.removeAll());

        Button borders = new Button("Borders");
        borders.setPosition(200, 3 * CANVAS_HEIGHT / 4);
        menuLayer.add(borders);
        borders.onClick(() -> {
            wallManager.removeAll();
            wallManager.generateBorders();
        });

        Button doors = new Button("Doors");
        doors.setPosition(300, 3 * CANVAS_HEIGHT / 4);
        menuLayer.add(doors);
        doors.onClick(() -> {
            wallManager.removeAll();
            wallManager.generateDoors();
        });

        Button simpleMaze = new Button("Simple Maze");
        simpleMaze.setPosition(400, 3 * CANVAS_HEIGHT / 4);
        menuLayer.add(simpleMaze);
        simpleMaze.onClick(() -> {
            wallManager.removeAll();
            wallManager.generateSimpleMaze();
        });

        Button harderMaze = new Button("Not So Simple Maze");
        harderMaze.setPosition(550, 3 * CANVAS_HEIGHT / 4);
        menuLayer.add(harderMaze);
        harderMaze.onClick(() -> {
            wallManager.removeAll();
            wallManager.generateHarderMaze();
        });
    }

    
    /**
     * updates the score to match the number of segments the snake has/the number of food items it's eaten.
     */
    private void updateScore() {
        menuLayer.remove(score);
        score = new GraphicsText("Score: " + numSegs);
        score.setCenter(CANVAS_WIDTH * 0.1, CANVAS_HEIGHT * 0.1);
        menuLayer.add(score);
    }

    /**
     * removes all current GraphicsObjects from the game, repositions the score board, prints "Game Over",
     * and adds a replay button and 2 rows of colorful walls.
     */
    private void gameOverScreen() {
        gameOver = new GraphicsText();
        gameOver.setFont(FontStyle.ITALIC, 65);
        gameOver.setText("Game Over");
        gameOver.setFillColor(RED);
        Point center = new Point(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        gameOver.setCenter(center);
        menuLayer.add(gameOver);

        score.setCenter(CANVAS_WIDTH / 2 - score.getWidth(), CANVAS_HEIGHT / 3);
        score.setFont(FontStyle.BOLD, 40);

        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
        
        snake.setCenter(center);
        for (Segments segs : allSegments) {
            segs.removeAllSegments();
        }

        wallManager.removeAll();
        wallManager.generateFunScreen();

        this.removeAll();
        this.add(wallManager);
        
        replayGame();
    }

    /**
     * creates button that allows user to replay by creating a whole new game and running it.
     */
    private void replayGame() {
        Button replay = new Button("Replay");
        replay.setCenter(CANVAS_WIDTH / 2, 2 * CANVAS_HEIGHT / 3);
        this.add(replay);
        replay.onClick(() -> {
            this.removeAll();
            menuLayer.removeAll();
            window.newGame().homeScreen();
        });
    }
}
