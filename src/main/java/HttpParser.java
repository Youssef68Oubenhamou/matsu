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

//        Map<String, String> headers = new HashMap<>();

        System.out.println(this.in.length);

        int j;
        for (j = 1; j < this.in.length; j++) {

            if (in[j].isBlank()) {

                break;

            } else {

//                System.out.println(this.in[j].split(": ")[0] + "====" + this.in[j].split(": ")[1]);
                this.http_request.setHeaders(this.in[j].split(": ")[0], this.in[j].split(": ")[1]);

            }

            if (this.in[j].split(": ")[0].equals("Content-Length")) {

                this.content_length = Integer.parseInt(in[j].split(": ")[1]);

            }

        }

        return this.http_request.getHeaders();

    }

//        while ((m = input.readLine()) != null) {
//
//            Pattern first_pattern = Pattern.compile("^[A-Z]*\\s+[/[a-z]]*\\s+[A-Z]*/\\d*[.]\\d$");
//            Matcher first_matcher = first_pattern.matcher(m);
//
//            if (first_matcher.find()) {
//
//                this.http_request.setMethod(first_matcher.group().split(" ")[0]);
//                this.http_request.setPath(first_matcher.group().split(" ")[1]);
//                this.http_request.setVersion(first_matcher.group().split(" ")[2]);
//                m = "";
//
//            }
//
//            Pattern second_pattern = Pattern.compile("^[A-Za-z\\-]+:\\s([0-9\\.]+|[A-Za-z0-9\\-/.]+)$");
//            Matcher second_matcher = second_pattern.matcher(m);
//
//            if (second_matcher.find()) {
//
//                this.http_request.setHeaders(second_matcher.group().split(" ")[0].substring(0,
//                                second_matcher.group().split(" ")[0].length() - 1),
//                        second_matcher.group().split(" ")[1]);
//                m = "";
//
//            }
//
//            Pattern third_pattern = Pattern.compile("^(?s)[\\{\\[A-Za-z0-9\\._\\)\\(\\]\\}\\s/+=*\\&%$|#@`~^?!;:<>\\',\\\"\\r\\n-]+$");
//            Matcher third_matcher = third_pattern.matcher(m);
//
//            if (third_matcher.find()) {
//
//                System.out.println(third_matcher.group());
//                body += third_matcher.group();
//                this.http_request.setBody(body);
//                m = "";
//
//            }
//
//        }


    public static void main(String[] args) {

        System.out.println("This message came from HTTP Parser !");

    }

}
