/**
 * Serves mildly amusing computer one-liners.
 *
 *
 * @author stevelyall, with portions adapted from:
 * http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class OneLinerSocketServer {

    static int portNumber;
    OneLinerServerProtocol sp = new OneLinerServerProtocol();

    /**
     * Sets up the sockets, listens for connections, and serves phrases
     *
     * Precondition: None
     * Postcondition: The server application has stopped.
     * Complexity: Undefined
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java OneLinerSocketServer <port number>");
            System.exit(1);
        }

        try {
            portNumber = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            System.out.println(args[1] + " is not a valid port number.");
            return;
        }

        System.out.println("Starting server on port " + portNumber + "...");

        OneLinerServerProtocol sp = new OneLinerServerProtocol();

        while (true) {

            System.out.println("Listening for connections...");

            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String outputLine;

                while (in.readLine() != null) {
                    outputLine = sp.getPhrase();
                    out.println(outputLine);
                    System.out.println("Sent to " + clientSocket.getInetAddress() + ": " + outputLine);
                }

            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                e.printStackTrace();
                return;
            }
        }
    }

}
