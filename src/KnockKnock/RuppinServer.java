package KnockKnock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RuppinServer {
    private static final int PORT = 4445;
    private static final RuppinProtocol protocol = new RuppinProtocol();

    public static void main(String[] args) {
        System.out.println("RuppinServer is listening on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) { // Loop to accept new clients
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new Thread(new RuppinHandler(clientSocket, protocol)).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }
}