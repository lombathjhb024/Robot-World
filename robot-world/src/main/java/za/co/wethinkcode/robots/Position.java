package za.co.wethinkcode.robots;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public boolean isIn(Position topLeft, Position bottomRight) {
        return (this.x >= topLeft.getX() && this.x <= bottomRight.getX()) &&
                (this.y <= topLeft.getY() && this.y >= bottomRight.getY());
    }

    // Return a new Position with adjusted x and y
    public Position moveBy(int deltaX, int deltaY) {
        return new Position(this.x + deltaX, this.y + deltaY);
    }
}
