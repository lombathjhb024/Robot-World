package za.co.wethinkcode.robots.server;

import za.co.wethinkcode.robots.Position;
import za.co.wethinkcode.robots.Direction;
import java.util.ArrayList;
import java.util.List;


/**
 * MyWorld represents a simple robot world with fixed width and height.
 * It contains robots, obstacles, and logic for movement and boundary checking.
 */

public class MyWorld implements World {
    private final int width = 10;   // World size
    private final int height = 10;
    private List<Robots> robots;
    private List<Obstacle> obstacles;

    // CENTRE
    public static final Position CENTRE = new Position(0, 0);


    /**
     * Constructs a new MyWorld with one hardcoded obstacle and no robots initially.
     */

    public MyWorld() {
        robots = new ArrayList<>();
        obstacles = new ArrayList<>();
        // Create one hardcoded obstacle
        obstacles.add(new SquareObstacle(4, 4));  //obstacle at position (4,4)
    }

    //TODO:
    public boolean hasRobot(int x, int y){
        //method to check if the world has a robot at specific coordinates
        return true;
    }

    //TODO:
    public boolean hasObstacle(int x, int y){
        //method to check if a worls has an obstacle at a specific coordinate
        return true;
    }

    //TODO:
    public boolean isInsideBounds(int x, int y){
        //method to check if robot is inside the bounds of the world
        return true;
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        if (robots.isEmpty()) {
            return UpdateResponse.FAILED_OBSTRUCTED; // no robots
        }

        Robots robot = robots.get(0);
        Position currentPosition = robot.getPosition();

        int newX = currentPosition.getX();
        int newY = currentPosition.getY();

        switch (robot.getDirection()) {
//            case UP:
            case NORTH: // If UP and NORTH are treated the same, use either one
                newY += nrSteps;
                break;
//            case DOWN:
            case SOUTH:
                newY -= nrSteps;
                break;
//            case LEFT:
            case WEST:
                newX -= nrSteps;
                break;
//            case RIGHT:
            case EAST:
                newX += nrSteps;
                break;
        }

        Position newPosition = new Position(newX, newY);

        // Check if the new position is within the world
        if (isNewPositionAllowed(newPosition)) {
            robot.set.Position(newPosition);
            return UpdateResponse.SUCCESS;
        } else {
            return UpdateResponse.FAILED_OBSTRUCTED;  // Or FAILED_OUTSIDE_WORLD if position is invalid
        }
    }

    @Override
    public void updateDirection(boolean turnRight) {
        Direction currentDirection = getCurrentDirection();
        Direction newDirection;
        if (turnRight) {
            newDirection = Direction.values()[(currentDirection.ordinal() + 1) % Direction.values().length];
        } else {
            newDirection = Direction.values()[(currentDirection.ordinal() - 1 + Direction.values().length) % Direction.values().length];
        }

        robots.get(0).setDirection(newDirection);
    }


    @Override
    public Position getPosition() {
        if (robots.isEmpty()) {
            // Handle the case where no robots exist
            throw new IllegalStateException("No robots exist in the world.");
        }
        return robots.get(0).getPosition();
    }

    @Override
    public Direction getCurrentDirection() {
        if (robots.isEmpty()) {
            // Handle the case where no robots exist
            throw new IllegalStateException("No robots exist in the world.");
        }
        return robots.get(0).getDirection();
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        // Check if position is within the world limits
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }

        // Check if position is blocked by any obstacles
        for (Obstacle obstacle : obstacles) {
            if (obstacle.blocksPosition(position)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isAtEdge() {
        if (robots.isEmpty()) {
            return false;  // Or handle the case for no robots as appropriate
        }
        Position position = getPosition();
        return position.getX() == 0 || position.getX() == width - 1 || position.getY() == 0 || position.getY() == height - 1;
    }

    @Override
    public void reset() {
        if (robots.isEmpty()) {
            return;  // Handle no robots scenario or log an appropriate message
        }
        // Reset robot position to center and direction to UP
        robots.get(0).setPosition(CENTRE);
        robots.get(0).setDirection(Direction.NORTH);
    }

    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public void showObstacles() {
        // Print out obstacle details (for debugging)
        for (Obstacle obstacle : obstacles) {
            System.out.println("Obstacle at: (" + obstacle.getBottomLeftX() + ", " + obstacle.getBottomLeftY() + ")");
        }
    }

    // add a robot to the world
    public void addRobot(Robots robot) {
        robots.add(robot);
    }

    // get all robots in the world
    public List<Robots> getRobots() {
        return robots;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
