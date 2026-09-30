import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;
    private String version;
    private Map<String, String> headers;
    private long body;

    HttpRequest(String method, String path, String version, Map<String, String> headers, long body) {

        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = new HashMap<>();
        this.body = body;

    }

    public static void main(String[] args) {

        System.out.println("Hello from Http Request Class !");

    }

}
