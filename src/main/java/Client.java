import java.io.*;
import java.net.*;

public class Client {

    private static Socket socket = null;
    private static DataInputStream input = null;
    private static DataOutputStream output = null;

    public Client(String addr, int port) {

        try {

            socket = new Socket(addr , port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());

        }
        catch (UnknownHostException u) {

            System.out.println(u);
            return;

        }
        catch (IOException i) {

            System.out.println(i);
            return;

        }

        String request = "GET /hello HTTP/1.1\r\n" +
                "Host: " + addr + "\r\n" +
                "User-Agent: RawJavaClient/1.0\r\n" +
                "Accept: text/html\r\n" +
                "Connection: close\r\n" + // Tells the server to close the socket after responding
                "\r\n";

        try {

            output.writeBytes(request);

        }
        catch (IOException i) {

            System.out.println(i);

        }

        // Closing the connection!
        try {

            input.close();
            output.close();
            socket.close();

        } catch (IOException i) {

            System.out.println(i);

        }

    }

    public static void main(String[] args) {

        System.out.println("Hello and Matsu!\n");

        System.out.println("Let's get started hacking!\n");

        Client client = new Client("127.0.0.1" , 5000);

    }
}
