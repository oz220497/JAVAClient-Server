package KnockKnock;

import java.io.*;
import java.net.*;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server is listening on port 4444...");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new KnockKnockHandler(clientSocket));
                clientThread.start();
            } catch (IOException e) {
                System.err.println("Accept failed: " + e.getMessage());
            }
        }
    }
}
