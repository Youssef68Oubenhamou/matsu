import java.io.*;
import java.net.*;

public class Server {

    private static Socket socket = null;
    private static ServerSocket server_socket = null;
    private static DataInputStream input = null;

    public Server(int port) {

        try {

            server_socket = new ServerSocket(port);
            System.out.println("Running the server..!");

            // accept() method waits for a client to connect , it blocks until a client is connected!
            socket = server_socket.accept();
            System.out.println("Client Accepted");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String m = "";

            try {

                m = input.readUTF();
                System.out.println(m);

            } catch (IOException i) {

                System.out.println(i);

            }

            System.out.println("Closing connection");

            socket.close();
            input.close();

        } catch (IOException i) {

            System.out.println(i);

        }

    }

    public static void main(String[] args) {

        System.out.println("Server File...");

        Server server = new Server(5000);

    }

}