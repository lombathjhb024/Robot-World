package za.co.wethinkcode.robots.server;
import za.co.wethinkcode.flow.Recorder;
import java.io.*;
import java.net.*;

/**
 * The Server class is responsible for setting up the server socket,
 * accepting client connections, and launching a new ClientManager thread
 * for each client. It also initializes the shared world state.
 */

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintStream out;
    private BufferedReader in;

    // REQUIRED for `flow` monitoring - DO NOT REMOVE
    static {
        new Recorder().logRun();
    }

    /**
     * Starts the server on the specified port and listens for incoming client connections.
     * Each client connection is handled in a separate thread via the ClientManager class.
     *
     * @param port the port number the server should listen on
     */

    public void start(int port) {
        try {
            MyWorld  MyWorld = new MyWorld();
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            clientSocket = new Socket();


            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");
                ClientManager handler = new ClientManager(clientSocket, MyWorld);
                new Thread(handler).start();
            }


        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    /**
     * Stops the server by closing open resources.
     */
    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Entry point of the server application.
     *
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }
}
