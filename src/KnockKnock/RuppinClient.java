package KnockKnock;

import java.io.*;
import java.net.*;

public class RuppinClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 4445;

        try (
            Socket socket = new Socket(serverAddress, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String fromServer;
            String fromUser;

            System.out.println("Connected to RuppinServer at " + serverAddress + ":" + port);

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);

                if (fromServer.equalsIgnoreCase("Thanks")) {
                    System.out.println("Disconnected from server.");
                    socket.close();
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);

                    if (fromUser.equalsIgnoreCase("q")) {
                        System.out.println("Exiting client.");
                        socket.close();
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Error: Unknown host - " + serverAddress);
            System.err.println("Please check if the server address is correct.");
        } catch (IOException e) {
            System.err.println("Error: Could not establish I/O connection to the server at " + serverAddress + ":" + port);
            System.err.println("Make sure the server is running and reachable.");
        }
    }
}
