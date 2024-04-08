package mygame;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private Server server;
    private PrintWriter out;

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                server.broadcast(clientMessage, this);
            } while (!clientMessage.equals("exit"));

            server.removeClient(this);
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Error in ClientHandler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
