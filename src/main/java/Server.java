import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;


public class Server {

    private Socket socket = null;
    private ServerSocket server_socket = null;
    private DataInputStream input = null;
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

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String m = "";

            String body = "";

            try {

                // This while loop is for reading request line by line,
                // i used this method to avoid losing the data while transmitting it!
                while ((m = input.readLine()) != null) {

                    // Parsing the HTTP request line to separate Method and Path and HTTP version
                    Pattern first_pattern = Pattern.compile("^[A-Z]*\\s+[/[a-z]]*\\s+[A-Z]*/\\d*[.]\\d$");
                    Matcher first_matcher = first_pattern.matcher(m);

                    if (first_matcher.find()) {

                        this.http_request.setMethod(first_matcher.group().split(" ")[0]);
                        this.http_request.setPath(first_matcher.group().split(" ")[1]);
                        this.http_request.setVersion(first_matcher.group().split(" ")[2]);
                        m = "";

                    }

                    // Parsing the HTTP Headers!
                    Pattern second_pattern = Pattern.compile("^[A-Za-z\\-]+:\\s([0-9\\.]+|[A-Za-z0-9\\-/.]+)$");
                    Matcher second_matcher = second_pattern.matcher(m);

                    if (second_matcher.find()) {

                        this.http_request.setHeaders(second_matcher.group().split(" ")[0].substring(0,
                                second_matcher.group().split(" ")[0].length() - 1),
                                second_matcher.group().split(" ")[1]);
                        m = "";

                    }

                    // Parsing Body: data
                    Pattern third_pattern = Pattern.compile("^(?s)[\\{\\[A-Za-z0-9\\._\\)\\(\\]\\}\\s/+=*\\&%$|#@`~^?!;:<>\\',\\\"\\r\\n-]+$");
                    Matcher third_matcher = third_pattern.matcher(m);

                    if (third_matcher.find()) {

                        System.out.println(third_matcher.group());
                        body += third_matcher.group();
                        this.http_request.setBody(body);
                        m = "";

                    }

                }

                System.out.println("Method: " + " -> " + this.http_request.getMethod());
                System.out.println("Path: " + " -> " + this.http_request.getPath());
                System.out.println("Version: " + " -> " + this.http_request.getVersion());
                System.out.println(this.http_request.getHeaders());
                System.out.println("Body: " + this.http_request.getBody());

                output = new DataOutputStream(socket.getOutputStream());

                http_response = new HttpResponse();

                byte[] response = http_serializer.processor(http_response);

                System.out.println(new String(response));

                output.write(response);

            } catch (IOException i) {

                System.out.println(i);

            }

            System.out.println("Closing connection");

            try {

                output.flush();
                input.close();
                socket.close();

            } catch (SocketTimeoutException e) {

                System.out.println(e);

            }

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