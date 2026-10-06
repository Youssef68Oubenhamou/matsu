import java.nio.charset.StandardCharsets;

public class HttpSerializer {

    private HttpResponse http_response = null;

    HttpSerializer(HttpResponse http_response) {

        this.http_response = http_response;

    }

    public String processor() {

        byte[] processed_data = http_response.getStatus().getBytes(StandardCharsets.UTF_8);
        System.out.println(processed_data.toString());
        return processed_data.toString();

    }

    public static void main(String[] args) {

        System.out.println("Hello from the Http Serializer Class!");

    }

}
