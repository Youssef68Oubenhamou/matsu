import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String addr, int port) {

        try {

            socket = new Socket(addr , port);
            System.out.println("Connected");

            input = new DataInputStream(socket.getInputStream());
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

        String request = "POST /users HTTP/1.1\r\n" +
                "Host: " + addr + "\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: 33\r\n" +
                "\r\n" +
                "{\r\n" +
                "  \"id\": 1042,\r\n" +
                "  \"active\": true,\r\n" +
                "  \"tags\": [\"admin\", \"dev-team\"],\r\n" +
                "  \"price\": -19.99\r\n" +
                "}";

//        String request = "POST /login HTTP/1.1\r\n" +
//                "Host: example.com\r\n" +
//                "Content-Type: application/x-www-form-urlencoded\r\n" +
//                "\r\n" +
//                "user=john_doe%40mail.com&password=P@ssw0rd!#&remember=1";

//        String request = "POST /api/xml HTTP/1.1\r\n" +
//                "Host: example.com\r\n" +
//                "Content-Type: application/xml\r\n" +
//                "Content-Length: 48\r\n" +
//                "\r\n" +
//                "<user id=\"1\"><name>Alice &amp; Bob</name></user>";

//        String request = "POST /users HTTP/1.1\r\n" +
//                "Host: " + addr + "\r\n" +
//                "Content-Type: application/json\r\n" +
//                "Content-Length: 33\r\n" +
//                "\r\n" +
//                "{\r\n" +
//                "\"payload\": \"Hello Friend !\"\r\n"+
//                "}";

        try {

            output.writeBytes(request);

        }
        catch (IOException i) {

            System.out.println(i);

        }

        // Closing the connection!
        try {

            output.flush();
            socket.shutdownOutput();

        } catch (IOException i) {

            System.out.println(i);

        }

        try {

            byte[] response = input.readAllBytes();
            String res_str = new String(response, StandardCharsets.UTF_8);
            System.out.println(res_str);
            socket.close();

        } catch (IOException e) {

            System.out.println(e);

        }

    }

    public static void main(String[] args) {

        System.out.println("Hello and Matsu!\n");

        System.out.println("Let's get started hacking!\n");

        Client client = new Client("127.0.0.1" , 8000);

    }
}
