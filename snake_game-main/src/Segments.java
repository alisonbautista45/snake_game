import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class Segments extends Rectangle {

    private List<Point> path;
    private int distance;
    private static GraphicsGroup segmentsGroup = new GraphicsGroup();

    /**
     * A contructor that makes a Segments object, taking and input of a list of points (path), and 
     * an integer (distance). A segement object is a rectangle (extends retangle), with the below designated 
     * color and stroke width.
     * 
     * @param path
     * @param distance
     */

    public Segments(List<Point> path, int distance) {
        super(0, 0, 10, 10);
        this.setFilled(true);
        this.setFillColor(SnakeGame.DARK_GREEN);
        this.setStrokeColor(Color.BLACK);
        this.setStrokeWidth(0.4);
        this.path = path;
        this.distance = distance;
    }

    /**
     * Sets the position of any new segment at a designated point in the list of points path, with distance
     * dictating how many points in the list away from the specified point in the path (x, y) the segment should
     * be set. 
     * 
     */

    public void follow() {
        this.setPosition(path.get(this.path.size() - distance * 6).getX() + 2.5,
            path.get(this.path.size() - distance * 6).getY() + 2.5);
    }

    /**
     * Adds every new segment to the segments graphcis group at a point in the path.
     */

    public void addToGroup() {
        this.setCenter(path.get(0).getX(), path.get(0).getY());
        segmentsGroup.add(this);
    }

    /**
     * Returns the graphics group containing all of the segment objects.
     * @return
     */

    public GraphicsGroup getSegmentsGroup() {
        return segmentsGroup;
    }

    /**
     * Removes all segments from the group of segments grpahics objects.
     */

    public void removeAllSegments() {
        if(Segments.segmentsGroup != null) {
            segmentsGroup.removeAll();
        }
    }
}
