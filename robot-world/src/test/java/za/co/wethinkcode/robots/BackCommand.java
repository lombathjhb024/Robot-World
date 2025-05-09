package za.co.wethinkcode.robots;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackwardCommandTest {

    @Test
    public void testSuccessfulBackwardMovement() {
        TestRobot robot = new TestRobot(true);
        BackwardCommand command = new BackwardCommand("4");

        boolean result = command.execute(robot);

        assertTrue(result);
        assertEquals("Moved back by 4 steps.", robot.getStatus());
    }

    @Test
    public void testBlockedBackwardMovement() {
        TestRobot robot = new TestRobot(false);
        BackwardCommand command = new BackwardCommand("2");

        boolean result = command.execute(robot);

        assertTrue(result);
        assertEquals("Sorry, I can't move out of my safe zone.", robot.getStatus());
    }

    @Test
    public void testInvalidArgumentThrowsException() {
        BackwardCommand command = new BackwardCommand("oops");

        assertThrows(NumberFormatException.class, () -> {
            command.execute(new TestRobot(true));
        });
    }
}
