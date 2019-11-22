package OpenFeign;

import feign.Feign;
import feign.Response;
import feign.gson.GsonDecoder;


public class FeignWrapper {
    private FeignResponseData feignResponseData = FeignResponseData.getData();

    private String baseURL = "";

    public void getResponse(){
        Response responseFeign = Feign.builder()
                .decoder(new GsonDecoder())
                .target(ResponseFeign.class, baseURL).response();

        setToResponseData(responseFeign);

    }

    private void setToResponseData(Response response){
        feignResponseData.setRequest(response.request());
        feignResponseData.setStatus(response.status());
        feignResponseData.setReason(response.reason());
        feignResponseData.setBody(response.body());
        feignResponseData.setHeaders(response.headers());
    }

    /**
     * Set url
     * @param url
     */
    public void setURL(String url){
        baseURL = url;
    }
}
