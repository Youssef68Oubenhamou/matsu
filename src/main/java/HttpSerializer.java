import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HttpSerializer {

    public byte[] serializer(HttpResponse http_response) {

        byte[] processed_response = new String(
            Arrays.stream(http_response.getStatus())
                .collect(Collectors.joining(" ")) + "\r\n"
            + http_response.getHeaders().entrySet().stream()
                    .map(header -> header.getKey() +": " + header.getValue())
                    .collect(Collectors.joining("\r\n")) + "\r\n"
            + "\r\n"
            + http_response.getBody()
        ).getBytes(StandardCharsets.UTF_8);

        return processed_response;

    }

    public static void main(String[] args) {

        System.out.println("Hello from the Http Serializer Class!");

    }

}
