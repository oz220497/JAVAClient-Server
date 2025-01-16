package KnockKnock;

import java.io.*;
import java.net.*;

public class KnockKnockHandler implements Runnable {
    private Socket clientSocket;

    public KnockKnockHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();

            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("q")) break;
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
            }
        } catch (IOException e) {
            System.err.println("Exception in client handler: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }
}
