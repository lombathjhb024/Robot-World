package za.co.wethinkcode.robots.server;

import za.co.wethinkcode.robots.Position;


public class SquareObstacle implements Obstacle {
    private int x, y, size = 5;
    public SquareObstacle(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int getBottomLeftX() {
        return x;
    }

    @Override
    public int getBottomLeftY() {
        return y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean blocksPosition(Position position) {

        return position.isIn(new Position(x, size), new Position(size, y));
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        if (a.getX() == b.getX()){
            if (a.getX() >= getBottomLeftX() && a.getX() < getBottomLeftX() + getSize() ){
                return b.getY() >= getBottomLeftY() && a.getY() < getBottomLeftY() + getSize();
            }
        } else if (a.getY() == b.getY()) {
            if(a.getY() >= getBottomLeftY() && a.getY() < getBottomLeftY() + getSize()){
                return b.getX() >= getBottomLeftY() && a.getX() < getBottomLeftY() + getSize();
            }

        }

        return false;
    }
}
