package za.co.wethinkcode.robots.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The ClientManager class handles communication between the server
 * and a single connected client. It listens for commands from the client
 * and executes them using the Command pattern.
 */
public class ClientManager extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private World world;

    /**
     * Constructor for ClientManager.
     *
     * @param clientSocket the socket associated with the connected client
     * @param world the shared world object used across all clients
     */
    public ClientManager(Socket clientSocket, World world) {
        this.clientSocket = clientSocket;
        this.world = world;
    }

    /**
     * Listens for client input and handles valid commands.
     * Supported commands include: quit, robots, dump.
     */

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Greet the client
//            String greeting = in.readLine();
//            if (greeting.toLowerCase() == "hello server") {
//                out.println("hello client");
//            } else {
//                out.println("unrecognized greeting");
//            }

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(clientSocket.getInetAddress().getHostName() + ": " + message );
                Command command = null;

                // Handle commands
                if ("quit".equalsIgnoreCase(message)) {
                    command = new QuitCommand(out, clientSocket);
                    break;
                } else if ("robots".equalsIgnoreCase(message)) {
                    command = new RobotsCommand(out, world.getRobots());
                } else if ("dump".equalsIgnoreCase(message)) {
                    command = new DumpCommand(out, world);
                } else {
                    out.println("Unrecognized command: " + message);
                    continue;
                }

                // Execute the command
                command.execute();
            }

        } catch (IOException e) {
            e.printStackTrace(); // Consider logging or handling more robustly
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace(); // Consider logging or handling more robustly
            }
        }
    }
}
