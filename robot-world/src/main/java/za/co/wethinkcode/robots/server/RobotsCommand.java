package za.co.wethinkcode.robots.server;
// implements the robots command to send a list of robot names (without their full state)

import za.co.wethinkcode.robots.server.Robots;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;



/**
 * The RobotsCommand class handles the 'robots' request from the client.
 * It sends back a list of all robot names currently in the world.
 * Note: Only the robot names are shown, not their full state.
 */
public class RobotsCommand implements Command {
    private PrintWriter out;
//    private PrintStream out;
    private List<Robots> robots;

    /**
     * Constructs a RobotsCommand with the given output stream and list of robots.
     *
     * @param out the PrintWriter used to send data to the client
     * @param robots a list of robot objects whose names will be displayed
     */

    public RobotsCommand(PrintWriter out, List<Robots> robots) {
        this.out = out;
        this.robots = robots;
    }


    /**
     * Executes the robots command by printing the names of all robots to the client.
     */

    @Override
    public void execute() {

        out.println("List of robots:");
        for (Robots robot : robots) {
            out.println(robot.getName());  // Only output robot name
        }
    }
}
