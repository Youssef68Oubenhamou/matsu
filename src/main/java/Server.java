import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


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

                // This while loop is for reading request line by line,
                // i used this method to avoid losing the data while transmitting it!
                while ((m = input.readLine()) != null) {

                    // Parsing the HTTP request to seperate Method and Path and HTTP version
                    Pattern pattern = Pattern.compile("^[A-Z]*\\s+/[a-z]*\\s+[A-Z]*/\\d*[.]\\d$");
                    Matcher matcher = pattern.matcher(m);

                    int i = 0;
                    if (matcher.find()) {

                        System.out.println("Found something!");
                        System.out.println("Method: " + matcher.group().split(" ")[0]);
                        System.out.println("Path: " + matcher.group().split(" ")[1]);
                        System.out.println("Version: " + matcher.group().split(" ")[2]);

                    }

                }

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