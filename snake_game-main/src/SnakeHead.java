import edu.macalester.graphics.*;

import java.awt.Color;

public class SnakeHead {

    /**
     * An iv that sets the scale for the snake's head, a scale of 1 having bounds of 150 x 150.
     */
    private static double scale = 0.1;

    /**
     * A constructor that takes the created snake's head, and allows for it to be pointed in whichever direction,
     * dictated by degreesRotate.
     * @param degreesRotate
     */
    public SnakeHead(double degreesRotate) {
        createSnakeHead(degreesRotate);
    }

    /**
     * Creates the snake's head, with the ability to point it in whatever desired direction.
     * @param degreesRotate
     */
    public static GraphicsGroup createSnakeHead(double degreesRotate) {
        GraphicsGroup group = new GraphicsGroup();
        group.add(createJawRight());
        group.add(createJawLeft());
        group.add(createMid());
        group.add(createSnout());
        group.add(createRightEye());
        group.add(createLeftEye());
        group.add(createLeftPupil());
        group.add(createRightPupil());
        group.add(createNoseOutline());
        group.add(createMidCover());

        group.setScale(scale);
        group.setCenter(scale * group.getCenter().getX(),
            scale * group.getCenter().getY());
        group.rotateBy(degreesRotate);

        return group;
    }

    /**
     * Creates the snakes left pupil
     * @return
     */
    private static Ellipse createLeftPupil() {
        Ellipse leftPupil = new Ellipse(0, 0, 20, 20);
        leftPupil.setCenter(40, 48);
        leftPupil.setFilled(true);
        leftPupil.setStrokeColor(Color.BLACK);
        leftPupil.setFillColor(Color.BLACK);
        return leftPupil;
    }

    /**
     * Creates the snake's right pupil
     * @return
     */
    private static Ellipse createRightPupil() {
        Ellipse rightPupil = new Ellipse(0, 0, 20, 20);
        rightPupil.setCenter(110, 48);
        rightPupil.setFilled(true);
        rightPupil.setStrokeColor(Color.BLACK);
        rightPupil.setFillColor(Color.BLACK);
        return rightPupil;
    }

    /**
     * Creates the snake's left eye
     * @return
     */
    private static Ellipse createLeftEye() {
        Ellipse leftEye = new Ellipse(0, 0, 60, 60);
        leftEye.setCenter(40, 66);
        leftEye.setFilled(true);
        leftEye.setStrokeColor(Color.BLACK);
        leftEye.setFillColor(Color.WHITE);
        leftEye.setStrokeWidth(4);
        return leftEye;
    }

    /**
     * Creates the snake's right eye
     * @return
     */
    private static Ellipse createRightEye() {
        Ellipse rightEye = new Ellipse(0, 0, 60, 60);
        rightEye.setCenter(110, 66);
        rightEye.setFilled(true);
        rightEye.setStrokeColor(Color.BLACK);
        rightEye.setFillColor(Color.WHITE);
        rightEye.setStrokeWidth(4);
        return rightEye;
    }

    /**
     * Creates the back of the snake's head.
     * @return
     */
    private static Path createMid() {
        Point point1 = new Point(5, 127);
        Point point2 = new Point(16, 150);
        Point point3 = new Point(134, 150);
        Point point4 = new Point(145, 127);
        Path faceMid = new Path(point1, point2, point3,
            point4, point1);
        faceMid.setFilled(true);
        faceMid.setFillColor(SnakeGame.DARK_GREEN);
        faceMid.setStrokeColor(Color.BLACK);
        faceMid.setStrokeWidth(4);
        return faceMid;
    }

    /**
     * Creates a cover the same color as the rest of the snakes head to hide any unwanted edges of the 
     * back of the snake's head from showing up.
     * @return
     */
    private static Path createMidCover() {
        Point point1 = new Point(8, 124);
        Point point2 = new Point(11, 130);
        Point point3 = new Point(139, 130);
        Point point4 = new Point(142, 124);
        Path faceMid = new Path(point1, point2, point3,
            point4, point1);
        faceMid.setFilled(true);
        faceMid.setFillColor(SnakeGame.DARK_GREEN);
        faceMid.setStrokeColor(SnakeGame.DARK_GREEN);
        faceMid.setStrokeWidth(4);
        return faceMid;
    }

    /**
     * Creates the border on the snake's snout
     * @return
     */
    private static Arc createNoseOutline() {
        Arc noseOutline = new Arc(0, 0, 100, 148, 34, 112);
        noseOutline.setCenter(75, 18);
        noseOutline.setStrokeColor(Color.BLACK);
        noseOutline.setStrokeWidth(4);
        return noseOutline;
    }

    /**
     * Creates the left side of the snake's face
     * @return
     */
    private static Ellipse createJawLeft() {
        Ellipse jawLeft = new Ellipse(0, 0, 70, 85);
        jawLeft.setCenter(35, 105);
        jawLeft.setFilled(true);
        jawLeft.setFillColor(SnakeGame.DARK_GREEN);
        jawLeft.setStrokeColor(Color.BLACK);
        jawLeft.setStrokeWidth(4);
        return jawLeft;
    }

    /**
     * Creates the right side of the snake's face
     * @return
     */
    private static Ellipse createJawRight() {
        Ellipse jawRight = new Ellipse(0, 0, 70, 85);
        jawRight.setCenter(115, 105);
        jawRight.setFilled(true);
        jawRight.setFillColor(SnakeGame.DARK_GREEN);
        jawRight.setStrokeColor(Color.BLACK);
        jawRight.setStrokeWidth(4);
        return jawRight;
    }

    /**
     * Creates the snake's nose
     * @return
     */
    private static Ellipse createSnout() {
        Ellipse snout = new Ellipse(0, 0, 100, 148);
        snout.setCenter(75, 74);
        snout.setFilled(true);
        snout.setFillColor(SnakeGame.DARK_GREEN);
        snout.setStrokeColor(SnakeGame.DARK_GREEN);
        return snout;
    }

}
