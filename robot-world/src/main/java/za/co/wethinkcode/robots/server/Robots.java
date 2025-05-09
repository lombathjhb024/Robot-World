package za.co.wethinkcode.robots.server;

import za.co.wethinkcode.robots.Direction;
import za.co.wethinkcode.robots.Position;



public class Robots {
    private final String name;
    private int x;
    private int y;
    private Direction direction;
    private Position position;
    private Position TOP_LEFT;
    private Position BOTTOM_RIGHT;
    private Direction currentDirection;

    public Robots(String name, int x, int y, Direction direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = Direction.NORTH;

    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public void setPosition(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }
    public boolean updatePosition(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY += nrSteps;
        } else if (Direction.WEST.equals(this.currentDirection)) {
            newX += nrSteps;
        }else {
            newX -= nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return String.format("Robot{name='%s', position=(%d,%d), direction=%s}",
                name, x, y, direction);
    }
}

