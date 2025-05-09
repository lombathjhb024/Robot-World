package za.co.wethinkcode.robots;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.wethinkcode.robots.client.LeftCommand;
import za.co.wethinkcode.robots.Direction;
import za.co.wethinkcode.robots.server.Robots;

import java.awt.*;

public class LeftCommandTest {

    @Test
    public void testTurnLeftFromNorth() {
        Robots robot = new Robots("jack", 0, 0, Direction.NORTH);
        LeftCommand command = new LeftCommand();

        command.execute(robot);

        assertEquals(Direction.WEST, robot.getDirection());
        assertEquals("Turned left.", robot.getStatus());
    }

    @Test
    public void testTurnLeftFromWest() {
        TestRobot robot = new TestRobot(Direction.WEST);
        LeftCommand command = new LeftCommand();

        command.execute(robot);

        assertEquals(Direction.SOUTH, robot.getCurrentDirection());
    }

    @Test
    public void testTurnLeftFromSouth() {
        TestRobot robot = new TestRobot(Direction.SOUTH);
        LeftCommand command = new LeftCommand();

        command.execute(robot);

        assertEquals(Direction.EAST, robot.getCurrentDirection());
    }

    @Test
    public void testTurnLeftFromEast() {
        TestRobot robot = new TestRobot(Direction.EAST);
        LeftCommand command = new LeftCommand();

        command.execute(robot);

        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }
}

