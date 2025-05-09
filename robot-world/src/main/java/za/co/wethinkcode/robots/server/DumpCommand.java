package za.co.wethinkcode.robots.server;
// implements the Dump command that outputs state of the world and all in it
import java.io.PrintWriter;

/**
 * The DumpCommand class handles the 'dump' request from the client.
 * It outputs the current state of the world, including its size and all obstacles.
 */
public class DumpCommand implements Command {
    private PrintWriter out;
    private World world;


    /**
     * Constructs a DumpCommand with the given output stream and world reference.
     *
     * @param out the PrintWriter used to send data to the client
     * @param world the current world object containing the game state
     */

    public DumpCommand(PrintWriter out, World world) {
        this.out = out;
        this.world = world;
    }


    /**
     * Executes the dump command by sending the world's dimensions
     * and a list of all obstacles' coordinates to the client.
     */

    @Override
    public void execute() {
        out.print("World Size: " + world.getWidth() + "x" + world.getHeight()+ ", Obstacles: ");
//        out.print("Obstacles: ");
        for (Obstacle obstacle : world.getObstacles()) {
            out.println("Obstacle at (" + obstacle.getBottomLeftX() + ", " + obstacle.getBottomLeftY() + ")");
        }
    }
}
