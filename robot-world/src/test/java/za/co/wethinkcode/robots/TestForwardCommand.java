package za.co.wethinkcode.robots;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForwardCommandTest {

    @Test
    public void testSuccessfulForwardMovement() {
        TestRobot robot = new TestRobot(true);
        ForwardCommand command = new ForwardCommand("3");

        boolean result = command.execute(robot);

        assertTrue(result);
        assertEquals("Moved forward by 3 steps.", robot.getStatus());
    }

    @Test
    public void testBlockedForwardMovement() {
        TestRobot robot = new TestRobot(false);
        ForwardCommand command = new ForwardCommand("5");

        boolean result = command.execute(robot);

        assertTrue(result);
        assertEquals("Sorry, I can't move out of  my safe zone.", robot.getStatus());
    }

    @Test
    public void testInvalidArgumentThrowsNumberFormatException() {
        ForwardCommand command = new ForwardCommand("invalid");

        assertThrows(NumberFormatException.class, () -> {
            command.execute(new TestRobot(true));
        });
    }
}

