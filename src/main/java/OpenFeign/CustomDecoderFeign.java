package OpenFeign;

import feign.Response;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class CustomDecoderFeign extends GsonDecoder {

    private Map<String, Collection<String>> headers;

    @Override
    public Object decode(Response response, Type type) throws IOException {
        headers = response.headers();
        return super.decode(response, type);
    }

    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }
}
