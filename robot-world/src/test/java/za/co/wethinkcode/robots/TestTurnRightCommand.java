package za.co.wethinkcode.robots;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.wethinkcode.robots.client.RightCommand;
import za.co.wethinkcode.robots.Direction;

public class RightCommandTest {

    @Test
    public void testTurnRightFromNorth() {
        TestRobot robot = new TestRobot(Direction.NORTH);
        RightCommand command = new RightCommand();

        command.execute(robot);

        assertEquals(Direction.EAST, robot.getCurrentDirection());
        assertEquals("Turned right.", robot.getStatus());
    }

    @Test
    public void testTurnRightFromEast() {
        TestRobot robot = new TestRobot(Direction.EAST);
        RightCommand command = new RightCommand();

        command.execute(robot);

        assertEquals(Direction.SOUTH, robot.getCurrentDirection());
    }

    @Test
    public void testTurnRightFromSouth() {
        TestRobot robot = new TestRobot(Direction.SOUTH);
        RightCommand command = new RightCommand();

        command.execute(robot);

        assertEquals(Direction.WEST, robot.getCurrentDirection());
    }

    @Test
    public void testTurnRightFromWest() {
        TestRobot robot = new TestRobot(Direction.WEST);
        RightCommand command = new RightCommand();

        command.execute(robot);

        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }
}
