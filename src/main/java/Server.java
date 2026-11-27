import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class Server {

    private Socket socket = null;
    private ServerSocket server_socket = null;
    private BufferedReader input = null;
    private HttpRequest http_request = null;
    private DataOutputStream output = null;
    private HttpResponse http_response;
    private HttpSerializer http_serializer;

    public Server(int port, HttpSerializer http_serializer) {

        this.http_request = new HttpRequest();
        this.http_serializer = http_serializer;

        try {

            server_socket = new ServerSocket(port);
            System.out.println("Running the server..!");

            // accept() method waits for a client to connect , it blocks until a client is connected!
            socket = server_socket.accept();
            System.out.println("Client Accepted");

//            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            HttpParser http_parser = new HttpParser(http_request, input);
            System.out.println(http_parser.status_parser());

            System.out.println(http_parser.headers_parser());

            System.out.println(http_parser.body_parser());

//            try {
//
//                System.out.println("Method: " + " -> " + this.http_request.getMethod());
//                System.out.println("Path: " + " -> " + this.http_request.getPath());
//                System.out.println("Version: " + " -> " + this.http_request.getVersion());
//                System.out.println(this.http_request.getHeaders());
//                System.out.println("Body: " + this.http_request.getBody());
//
//                output = new DataOutputStream(socket.getOutputStream());
//
//                http_response = new HttpResponse();
//
//                byte[] response = http_serializer.serializer(http_response);
//
//                System.out.println(new String(response));
//
//                output.write(response);
//
//            } catch (IOException i) {
//
//                System.out.println(i);
//
//            }
//
//            System.out.println("Closing connection");
//
//            try {
//
//                output.flush();
//                input.close();
//                socket.close();
//
//            } catch (SocketTimeoutException e) {
//
//                System.out.println(e);
//
//            }

        } catch (IOException i) {

            System.out.println(i);

        }

    }

    public static void main(String[] args) {

        System.out.println("Server File...");

        HttpSerializer http_serializer = new HttpSerializer();

        Server server = new Server(8000, http_serializer);

    }

}