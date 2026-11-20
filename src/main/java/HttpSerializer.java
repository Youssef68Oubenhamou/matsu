import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HttpSerializer {

    private HttpResponse http_response = null;

    HttpSerializer(HttpResponse http_response) {

        this.http_response = http_response;

    }

    public String processor() {

        http_response.getStatus();
        byte[] processed_body = http_response.getBody().getBytes(StandardCharsets.UTF_8);
        return Arrays.toString(processed_body);

    }

    public static void main(String[] args) {

        System.out.println("Hello from the Http Serializer Class!");

    }

}
