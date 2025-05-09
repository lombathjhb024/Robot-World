package za.co.wethinkcode.robots.client;

public class RightCommand extends Command {
    public RightCommand() {
        super("right");
    }
    @Override
    public boolean execute(Robot robot) {
        robot.setStatus("Turned right.");
        robot.setCurrentDirection(Direction.WEST);
        return true;
    }
}

