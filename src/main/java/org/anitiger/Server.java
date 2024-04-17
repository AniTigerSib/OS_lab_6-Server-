package org.anitiger;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private ServerSocket server = null;
    private ArrayList<Client> clients = null;
    private int port = -1;
    private int maxConnections = 0;

    public Server(int port, int maxConnections) {
        try {
            this.port = port;
            this.maxConnections = maxConnections;
            this.clients = new ArrayList<Client>();

            server = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.out.println("Could not start server on port " + port);
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void Listen() {
        System.out.println("Waiting for clients...");
        while (true) {
            try {
                Socket clientSocket = server.accept();
                System.out.println("Client connected");

                Client client = new Client(clientSocket, clients.size());
                clients.add(client);

                if (clients.size() >= maxConnections) {
                    System.out.println("Maximum number of connections reached");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Could not accept client");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Server server = new Server(8080, 10);
        server.Listen();
    }
}