import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {

    private String method;
    private String path;
    private String version;
    private Map<String, String> headers;
    private String body;

    HttpRequest() {

        this.method = null;
        this.path = null;
        this.version = null;
        this.headers = new HashMap<>();
        this.body = null;

    }

    HttpRequest(String method, String path, String version, Map<String, String> headers, String body) {

        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.body = body;

    }

    public String getMethod() {

        return this.method;

    }
    public void setMethod(String newMethod) {

        this.method = newMethod;

    }

    public String getPath() {

        return this.path;

    }
    public void setPath(String newPath) {

        this.path = newPath;

    }

    public String getVersion() {

        return this.version;

    }
    public void setVersion(String newVersion) {

        this.version = newVersion;

    }

    public String getHeaders() {

        return this.headers.entrySet().stream()
                .map(header -> header.getKey() + ": " + header.getValue())
                .collect(Collectors.joining("\n"));

    }
    public void setHeaders(String newKey , String newValue) {

        this.headers.put(newKey, newValue);

    }

    public String getBody() {

        return this.body;

    }
    public void setBody(String newBody) {

        this.body = newBody;

    }

    public static void main(String[] args) {

        System.out.println("Hello from the new Class!");

    }

}
