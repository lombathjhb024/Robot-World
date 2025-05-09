package za.co.wethinkcode.robots.client;

import za.co.wethinkcode.robot.Command;
import za.co.wethinkcode.robot.Direction;
import za.co.wethinkcode.robot.Robot;

public class LeftCommand extends Command {
    public LeftCommand(){
        super("left");
    }
    @Override
    public boolean execute(Robot robot){
        robot.setStatus("Turned left.");
        robot.setCurrentDirection(Direction.EAST);
        return true;
    }

}



