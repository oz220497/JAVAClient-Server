package KnockKnock;

import java.io.*;
import java.net.*;

public class RuppinHandler implements Runnable {
    private Socket clientSocket;
    private RuppinProtocol protocol;

    public RuppinHandler(Socket clientSocket, RuppinProtocol protocol) {
        this.clientSocket = clientSocket;
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            Client client = null;
            String inputLine;
            int step = 1;

            while (true) {
                switch (step) {
                    case 1:
                        out.println("New user? (y/n)");
                        inputLine = in.readLine();
                        if (inputLine == null || inputLine.equalsIgnoreCase("q")) {
                            out.println("Thanks for connecting. Goodbye!");
                            return;
                        }
                        if (inputLine.equalsIgnoreCase("y")) {
                            client = new Client(null, null, false, false);
                            step = 3;
                        } else if (inputLine.equalsIgnoreCase("n")) {
                            step = 2;
                        } else {
                            out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                        break;

                    case 2:
                        out.println("Username:");
                        inputLine = in.readLine();
                        client = protocol.findClient(inputLine);
                        if (client == null) {
                            out.println("Username not found. Disconnecting.");
                            return;
                        }
                        out.println("Password:");
                        inputLine = in.readLine();
                        if (!client.getPassword().equals(inputLine)) {
                            out.println("Invalid password. Disconnecting.");
                            return;
                        }
                        out.println("Last time you said you are " +
                                (client.isStudent() ? "a student" : "not a student") +
                                " and " + (client.isHappy() ? "happy." : "not happy.") +
                                " Any changes? (y/n)");
                        inputLine = in.readLine();
                        if (inputLine.equalsIgnoreCase("n")) {
                            out.println("Thanks");
                            return;
                        }
                        if (inputLine.equalsIgnoreCase("y")) {
                            out.println("Are you a student at Ruppin? (y/n)");
                            step = 5;
                        } else {
                            out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                        break;

                    case 3:
                        out.println("Choose your username:");
                        inputLine = in.readLine();
                        if (inputLine == null || inputLine.isEmpty() || !protocol.checkUser(inputLine)) {
                            out.println("Name not OK. Username exists or invalid. Choose a different name:");
                        } else {
                            client.setUsername(inputLine);
                            out.println("Name OK. Choose your password:");
                            step = 4;
                        }
                        break;

                    case 4:
                        inputLine = in.readLine();
                        if (inputLine == null || !protocol.checkPassword(inputLine)) {
                            out.println("Password not OK. Must include uppercase, lowercase, digit, and be 9 characters.");
                        } else {
                            client.setPassword(inputLine);
                            out.println("Password OK. Are you a student at Ruppin? (y/n)");
                            step = 5;
                        }
                        break;

                    case 5:
                        inputLine = in.readLine();
                        if (inputLine.equalsIgnoreCase("y") || inputLine.equalsIgnoreCase("n")) {
                            client.setStudent(inputLine.equalsIgnoreCase("y"));
                            out.println("Are you Happy? (y/n)");
                            step = 6;
                        } else {
                            out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                        break;

                    case 6:
                        inputLine = in.readLine();
                        if (inputLine.equalsIgnoreCase("y") || inputLine.equalsIgnoreCase("n")) {
                            client.setHappy(inputLine.equalsIgnoreCase("y"));

                            if (step == 2) {
                                protocol.updateClient(client);
                            } else {
                                protocol.addUser(client.getUsername(), client.getPassword(), client.isStudent(), client.isHappy());
                            }

                            out.println("Thanks");
                            return;
                        } else {
                            out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                        break;

                    default:
                        out.println("Unexpected state. Disconnecting.");
                        return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}


