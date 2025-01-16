package KnockKnock;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RuppinProtocol {
    private List<Client> clientState = new ArrayList<>();

    public boolean checkUser(String username) {
        return clientState.stream().noneMatch(client -> client.getUsername().equals(username));
    }

    public boolean checkPassword(String password) {
        return Client.isValidPassword(password);
    }

    public synchronized void addUser(String username, String password, boolean isStudent, boolean isHappy) {
        Client newClient = new Client(username, password, isStudent, isHappy);
        clientState.add(newClient);
        if (clientState.size() % 3 == 0) {
            createBackup();
        }
    }

    public synchronized Client findClient(String username) {
        return clientState.stream()
                .filter(client -> client.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    public synchronized void updateClient(Client updatedClient) {
        for (int i = 0; i < clientState.size(); i++) {
            if (clientState.get(i).getUsername().equals(updatedClient.getUsername())) {
                clientState.set(i, updatedClient);
                createBackup();
                return;
            }
        }
        System.out.println("Client not found for update: " + updatedClient.getUsername());
    }

    private void createBackup() {
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "backup_" + date + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Client client : clientState) {
                writer.write(client.toString());
                writer.newLine();
            }
            System.out.println("Backup created: " + fileName);
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }

    
}

