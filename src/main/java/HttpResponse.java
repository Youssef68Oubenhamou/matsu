import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponse {

    private String[] status;
    private Map<String, String> headers;
    private String body;

    // Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
    public HttpResponse() {

        this.body = "Hello World, This is the response !";
        this.status = new String[] {"HTTP/1.1", "200", "OK"};
        this.headers = new HashMap<>(Map.of(
            "Server", "Apache",
            "Date", "" + ZonedDateTime.now(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)),
            "Content-Type", "text/plain",
            "Content-Length", "" + this.body.getBytes(StandardCharsets.UTF_8).length
        ));

    }

    public String[] getStatus() {

        return this.status;

    }
    public void setStatus(String httpVersion, String statusCode, String reasonPhrase) {

        this.status[0] = httpVersion;
        this.status[1] = statusCode;
        this.status[2] = reasonPhrase;

    }

    public Map<String, String> getHeaders() {

        return this.headers;

    }
    public void setHeaders(String newKey) {

        setHeaders(newKey,this.body.getBytes(StandardCharsets.UTF_8).length + "");

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
