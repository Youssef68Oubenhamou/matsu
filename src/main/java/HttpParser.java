import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpParser {

    private HttpRequest http_request;
    private BufferedReader input;
    private String[] in;
    private int content_length;
    private String status;

    public HttpParser(HttpRequest http_request, BufferedReader input) {

        this.http_request = http_request;
        this.input = input;
        this.content_length = 0;
        this.status = "";
        this.in = this.input.lines().toArray(String[]::new);

    }

    public String status_parser() {

        this.status = "";

        int i;
        for (i = 0; i < this.in.length; i++) {

            if (i == 0) {

                this.status = this.in[i];

            }

        }

        this.http_request.setMethod(this.status.split(" ")[0]);
        this.http_request.setPath(this.status.split(" ")[1]);
        this.http_request.setVersion(this.status.split(" ")[2]);

        return this.http_request.getMethod() + " " + this.http_request.getPath() + " " + this.http_request.getVersion();

    }

    public String headers_parser() {

        int j;
        for (j = 1; j < this.in.length; j++) {

            if (in[j].isBlank()) {

                break;

            } else {

                this.http_request.setHeaders(this.in[j].split(": ")[0], this.in[j].split(": ")[1]);

            }

            if (this.in[j].split(": ")[0].equals("Content-Length")) {

                this.content_length = Integer.parseInt(in[j].split(": ")[1]);

            }

        }

        return this.http_request.getHeaders();

    }

    public String body_parser() {

        String body = "";

        int z;
        for (z = Arrays.asList(this.in).indexOf("") + 1; z < this.in.length; z++) {

            body += this.in[z];

        }

        this.http_request.setBody(body);

        return this.http_request.getBody();

    }

    public static void main(String[] args) {

        System.out.println("This message came from HTTP Parser !");

    }

}
