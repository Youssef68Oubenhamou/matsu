import java.io.*;
import java.net.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;


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

            Map<String, String> http_request = new HashMap<>();

            try {

                // This while loop is for reading request line by line,
                // i used this method to avoid losing the data while transmitting it!
                while ((m = input.readLine()) != null) {

                    // Parsing the HTTP request line to separate Method and Path and HTTP version
                    Pattern first_pattern = Pattern.compile("^[A-Z]*\\s+[/[a-z]]*\\s+[A-Z]*/\\d*[.]\\d$");
                    Matcher first_matcher = first_pattern.matcher(m);

                    if (first_matcher.find()) {

                        http_request.put("Method", first_matcher.group().split(" ")[0]);
                        http_request.put("Path", first_matcher.group().split(" ")[1]);
                        http_request.put("Version", first_matcher.group().split(" ")[2]);

                    }

                    // Parsing the HTTP header!
                    Pattern second_pattern = Pattern.compile("^[A-Za-z]+:\\s[0-9]+[.][0-9]+[.][0-9]+[.][0-9]+$");
                    Matcher second_matcher = second_pattern.matcher(m);

                    if (second_matcher.find()) {

                        http_request.put(second_matcher.group().split(" ")[0].substring(0,
                                second_matcher.group().split(" ")[0].length() - 1),
                                second_matcher.group().split(" ")[1]);

                    }

                    // Parsing User-Agent: RawJavaClient/1.0
                    Pattern third_pattern = Pattern.compile("^[A-Za-z\\-]+[:]\\s[A-Za-z]+\\/[0-9][.][0-9]$");
                    Matcher third_matcher = third_pattern.matcher(m);

                    if (third_matcher.find()) {

                        http_request.put(third_matcher.group().split(" ")[0].substring(0,
                                        third_matcher.group().split(" ")[0].length() - 1),
                                third_matcher.group().split(" ")[1]);

                    }

                    // Parsing Accept: text/html
                    Pattern fourth_pattern = Pattern.compile("^[A-Za-z]+[:]\\s[a-z]+\\/[a-z]+$");
                    Matcher fourth_matcher = fourth_pattern.matcher(m);

                    if (fourth_matcher.find()) {

                        http_request.put(fourth_matcher.group().split(" ")[0].substring(0,
                                        fourth_matcher.group().split(" ")[0].length() - 1),
                                fourth_matcher.group().split(" ")[1]);

                    }

                    // Parsing Connection: status
                    Pattern fifth_pattern = Pattern.compile("^[A-Za-z]+[:]\\s[A-Za-z]+");
                    Matcher fifth_matcher = fifth_pattern.matcher(m);

                    if (fifth_matcher.find()) {

                        http_request.put(fifth_matcher.group().split(" ")[0].substring(0,
                                        fifth_matcher.group().split(" ")[0].length() - 1),
                                fifth_matcher.group().split(" ")[1]);

                    }

                    // Parsing Content-Type: application/json
                    Pattern sixth_pattern = Pattern.compile("^[A-Za-z\\-]+[:]\\s[a-z]+\\/[a-z]+$");
                    Matcher sixth_matcher = sixth_pattern.matcher(m);

                    if (sixth_matcher.find()) {

                        http_request.put(sixth_matcher.group().split(" ")[0].substring(0,
                                        sixth_matcher.group().split(" ")[0].length() - 1),
                                sixth_matcher.group().split(" ")[1]);

                    }

                    // Parsing Content-Length: length
                    Pattern seventh_pattern = Pattern.compile("^[A-Za-z\\-]+[:]\\s[0-9]+$");
                    Matcher seventh_matcher = seventh_pattern.matcher(m);

                    if (seventh_matcher.find()) {

                        http_request.put(seventh_matcher.group().split(" ")[0].substring(0,
                                        seventh_matcher.group().split(" ")[0].length() - 1),
                                seventh_matcher.group().split(" ")[1]);

                    }

                    // Parsing Body: data
                    Pattern eighth_pattern = Pattern.compile("^[A-Za-z\\{\\}\\,\"\\:\\?\\.\\!\\/\\-\\_\\;\\\\\\s]+$");
                    Matcher eighth_matcher = eighth_pattern.matcher(m);

                    if (eighth_matcher.find()) {

                        http_request.put("Body", eighth_matcher.group());

                    }

                }

                for (Map.Entry<String, String> http : http_request.entrySet()) {

                    System.out.println(http.getKey() + " -> " + http.getValue());

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