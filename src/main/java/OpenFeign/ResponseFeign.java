package OpenFeign;

import feign.RequestLine;
import feign.Response;

public interface ResponseFeign {
    @RequestLine("GET ")
    Response connect();

    class connect {
    }
}
