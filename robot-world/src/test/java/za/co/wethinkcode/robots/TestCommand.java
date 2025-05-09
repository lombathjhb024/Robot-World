package za.co.wethinkcode.robots;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.robots.server.Command;
import za.co.wethinkcode.robots.client.ForwardCommand;
import za.co.wethinkcode.robots.client.BackwardCommand;
import za.co.wethinkcode.robots.client.LeftCommand;
import za.co.wethinkcode.robots.client.RightCommand;

public class CommandCreateTest {

    @Test
    public void testCreateForwardCommand() {
        Command cmd = Command.create("forward 5");
        assertTrue(cmd instanceof ForwardCommand);
        assertEquals("forward", cmd.getName());
        assertEquals("5", cmd.getArgument());
    }

    @Test
    public void testCreateBackwardCommand() {
        Command cmd = Command.create("back 3");
        assertTrue(cmd instanceof BackwardCommand);
        assertEquals("back", cmd.getName());
        assertEquals("3", cmd.getArgument());
    }

    @Test
    public void testCreateLeftCommand() {
        Command cmd = Command.create("left");
        assertTrue(cmd instanceof LeftCommand);
        assertEquals("left", cmd.getName());
    }

    @Test
    public void testCreateRightCommand() {
        Command cmd = Command.create("right");
        assertTrue(cmd instanceof RightCommand);
        assertEquals("right", cmd.getName());
    }

    @Test
    public void testCreateForwardMissingArgumentThrows() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Command.create("forward");
        });
        assertEquals("Missing steps for forward.", exception.getMessage());
    }

    @Test
    public void testCreateBackMissingArgumentThrows() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Command.create("back");
        });
        assertEquals("Missing steps for back.", exception.getMessage());
    }

    @Test
    public void testUnsupportedCommandThrows() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Command.create("fly");
        });
        assertEquals("Unsupported command: fly", exception.getMessage());
    }
}

