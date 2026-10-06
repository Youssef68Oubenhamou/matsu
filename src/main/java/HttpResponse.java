import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponse {

    private String[] status = null;
    private Map<String, String> headers = null;
    private String body = null;

    // Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
    HttpResponse(String body) {

        this.status = new String[3];
        this.headers = new HashMap<>();
        this.body = body;

    }

    public String getStatus() {

        return this.status.toString();

    }
    public void setStatus(String httpVersion, String statusCode, String reasonPhrase) {

        this.status[0] = httpVersion;
        this.status[1] = statusCode;
        this.status[2] = reasonPhrase;

    }

    public String getHeaders() {

        return this.headers.entrySet().stream()
                .map(header -> header.getKey() + ": " + header.getValue())
                .collect(Collectors.joining("\n"));

    }
    public void setHeaders(String newKey, String newValue) {

        this.headers.put(newKey , newValue);

    }

    public String getBody() {

        return this.body;

    }
    public void setBody(String newBody) {

        this.body = newBody;

    }

    public static void main(String[] args) {

        System.out.println("Hello from the HTTP Response Class!");

    }

}
