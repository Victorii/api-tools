package OpenFeign;

import feign.Feign;
import feign.Response;
import feign.gson.GsonDecoder;


public class FeignWrapper {
    private String baseURL = "";

    public void getResponse(){
        ResponseFeign responseFeign = Feign.builder()
                .decoder(new GsonDecoder())
                .target(ResponseFeign.class, baseURL);

        Response response = responseFeign.response();
    }

    /**
     * Set url
     * @param url
     */
    public void setURL(String url){
        baseURL = url;
    }
}
