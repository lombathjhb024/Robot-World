package za.co.wethinkcode.robots.server;
// implements the quit command that closes the connection to the client
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * The QuitCommand class handles the 'quit' request from the client.
 * It gracefully sends a goodbye message and closes the socket connection.
 */
public class QuitCommand implements Command {
    private PrintWriter out;
    private Socket clientSocket;


    /**
     * Constructs a QuitCommand with the given output stream and client socket.
     *
     * @param out the PrintWriter used to send a message to the client
     * @param clientSocket the client's socket that will be closed
     */

    public QuitCommand(PrintWriter out, Socket clientSocket) {
        this.out = out;
        this.clientSocket = clientSocket;
    }

    /**
     * Executes the quit command by notifying the client and
     * closing their socket connection.
     */

    @Override
    public void execute() {
        out.println("Goodbye! Closing connection.");
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
