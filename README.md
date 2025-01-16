# Client-Server Communication System

## Overview
This project implements a multi-protocol client-server communication system in Java. It includes two distinct protocols, `KnockKnockProtocol` and `RuppinProtocol`, each with unique functionality. The system is built to handle multiple clients simultaneously and offers features such as user management, password validation, and dynamic responses based on user input.

## Features
- **KnockKnockProtocol**: A fun joke-telling server-client interaction.
- **RuppinProtocol**: 
  - Handles new and existing user registration and updates.
  - Validates usernames and passwords.
  - Creates backups for client data every three new users.
- Multi-threaded server to support multiple simultaneous clients.
- Password validation ensuring security standards:
  - Minimum 9 characters.
  - At least one uppercase, one lowercase, and one digit.

## Technologies Used
- **Language**: Java
- **Networking**: Sockets for communication between server and clients.
- **Multi-threading**: Efficiently handles multiple clients using threads.
- **File I/O**: Manages backups of client data in CSV format.

## Project Structure
- **KnockKnock Protocol**:
  - `KnockKnockClient.java`: Client application for interacting with the `KnockKnockServer`.
  - `KnockKnockServer.java`: Server application for handling multiple `KnockKnockClient` connections.
  - `KnockKnockHandler.java`: Processes client requests in the context of the `KnockKnockProtocol`.
  - `KnockKnockProtocol.java`: Implements the logic for the Knock-Knock jokes.
  
- **Ruppin Protocol**:
  - `RuppinClient.java`: Client application for interacting with the `RuppinServer`.
  - `RuppinServer.java`: Server application for handling multiple `RuppinClient` connections.
  - `RuppinHandler.java`: Processes client requests in the context of the `RuppinProtocol`.
  - `RuppinProtocol.java`: Implements user registration, updates, and backup logic.
  - `Client.java`: Represents user data, including validation for usernames and passwords.

- **Main Program**:
  - `MainClass.java`: Entry point for starting either the `KnockKnockServer` or `RuppinServer`.
