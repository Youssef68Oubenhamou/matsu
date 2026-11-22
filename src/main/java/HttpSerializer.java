import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HttpSerializer {

    public String processor(HttpResponse http_response) {

//        String status = http_response.getStatus();
//        String headers = http_response.getHeaders();

        http_response.setStatus("HTTP/1.1", "200", "OK");
        http_response.setHeaders("Content-Type", "text/plain");
        http_response.setHeaders("Content-Length");

        byte[] prcsd_status = http_response.getStatus().getBytes(StandardCharsets.UTF_8);
        byte[] prcsd_headers = http_response.getHeaders().getBytes(StandardCharsets.UTF_8);
        byte[] prcsd_body = http_response.getBody().getBytes(StandardCharsets.UTF_8);

        return new String(prcsd_status, StandardCharsets.UTF_8)
                + new String(prcsd_headers, StandardCharsets.UTF_8)
                + new String(prcsd_body, StandardCharsets.UTF_8);

    }

    public static void main(String[] args) {

        System.out.println("Hello from the Http Serializer Class!");

    }

}
