package za.co.wethinkcode.robots.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;


/**
 * The Client class connects to the server at localhost:6666 and allows the user
 * to send text-based commands through the console. It listens for responses
 * from the server and prints them out.
 */
public class Client {
    public static void main(String[] args){
        try(
                Socket socket = new Socket("localhost",6666);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
        ){
            while (true){
                System.out.print(": ");
                if (!scanner.hasNextLine()){
                    System.out.println("No input detected. Exiting...");
                    break;
                }

                String messageToServer = scanner.nextLine();
                out.println(messageToServer);
                out.flush();

                String messageFromServer = in.readLine();
                if (messageFromServer == null){
                    System.out.println("Server closed the connection.");
                    break;
                }
                System.out.println("Response:" + messageFromServer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
