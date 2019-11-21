package OpenFeign;

import feign.Client;
import feign.Request;
import feign.Response;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;

public class FeignResponseDelegate extends Client.Default {


    /**
     * Null parameters imply platform defaults.
     *
     * @param sslContextFactory
     * @param hostnameVerifier
     */
    public FeignResponseDelegate(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
        super(sslContextFactory, hostnameVerifier);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Response response = super.execute(request, options);


        response.body().asInputStream()


        System.out.println("Request URL {}" + request.url());
        System.out.println("Request Request body {}" + (requestBody.length() > 0 ? requestBody.asString() : null));
        System.out.println("Response Body {}", IOUtils.toString(responseBodyInputStream));

        // logger.debug("Path Variables: {}".....
        // how to log the path variables?


        return response.toBuilder()
                .body(bytes)
                .build();
    }
}
