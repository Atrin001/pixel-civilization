package mygame;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to send a message to all clients
    public void broadcast(String message, ClientHandler excludeUser) {
        for (ClientHandler aClient : clients) {
            if (aClient != excludeUser) {
                aClient.sendMessage(message);
            }
        }
    }

    // Method to remove a client
    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Client disconnected: " + clientHandler.getClientSocket().getInetAddress().getHostAddress());
    }

    // Start the server
    public static void main(String[] args) {
        Server server = new Server(5000);
        server.start();
    }
}
