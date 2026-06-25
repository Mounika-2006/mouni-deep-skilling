import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPcLIENT_SERVERchat {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java TCPcLIENT_SERVERchat server|client");
            return;
        }

        if ("server".equalsIgnoreCase(args[0])) {
            startServer();
        } else if ("client".equalsIgnoreCase(args[0])) {
            startClient();
        } else {
            System.out.println("Usage: java TCPcLIENT_SERVERchat server|client");
        }
    }

    private static void startServer() throws IOException {
        int port = 5000;
        System.out.println("Starting server on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner console = new Scanner(System.in)) {

            System.out.println("Client connected: " + socket.getRemoteSocketAddress());
            Thread receiver = new Thread(() -> {
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Client: " + line);
                    }
                } catch (IOException ignored) {
                }
            });
            receiver.setDaemon(true);
            receiver.start();

            System.out.println("Type messages and press enter to send.");
            String msg;
            while ((msg = console.nextLine()) != null) {
                writer.println(msg);
                if ("bye".equalsIgnoreCase(msg)) {
                    break;
                }
            }
        }
    }

    private static void startClient() throws IOException {
        String host = "localhost";
        int port = 5000;
        System.out.println("Connecting to server " + host + ":" + port);

        try (Socket socket = new Socket(host, port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner console = new Scanner(System.in)) {

            System.out.println("Connected to server " + host + ":" + port);
            Thread receiver = new Thread(() -> {
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Server: " + line);
                    }
                } catch (IOException ignored) {
                }
            });
            receiver.setDaemon(true);
            receiver.start();

            System.out.println("Type messages and press enter to send.");
            String msg;
            while ((msg = console.nextLine()) != null) {
                writer.println(msg);
                if ("bye".equalsIgnoreCase(msg)) {
                    break;
                }
            }
        }
    }
}
