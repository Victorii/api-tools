package OpenFeign;

import RestAssured.RestAssuredResponseData;
import feign.Request;
import feign.Response.Body;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;

import java.util.Collection;
import java.util.Map;


public class FeignResponseData {

    /**
     * private constructor to avoid multiple object instances.
     * access only from public getData() method
     */
    private FeignResponseData() {
        body = null; //TODO: NPE?
        request = null; //TODO: NPE?
        reason = null; //TODO: NPE?
        status = 0;
    }

    /**
     * @return instance of RestAssuredResponseData object due to single object existence
     */
    public static FeignResponseData getData(){
        return FeignResponseData.ResponseDataHolder.instance;
    }

    /**
     * private inner class intended to store constant of instance
     * it provides possibility to work with only single object of RestAssuredResponseData
     */
    private static class ResponseDataHolder {
        private static final FeignResponseData instance = new FeignResponseData();
    }

    private Map<String, Collection<String>> headers;
    private Body body;
    private int status;
    private Request request;
    private String reason;



    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }

    public Body getBody() {
        return body;
    }

    public int getStatus() {
        return status;
    }

    public Request getRequest() {
        return request;
    }

    public String getReason() {
        return reason;
    }

    protected void setBody(Body body) {
        this.body = body;
    }

    protected void setReason(String reason) {
        this.reason = reason;
    }

    protected void setHeaders(Map<String, Collection<String>> headers) {
        this.headers = headers;
    }

    protected void setRequest(Request request) {
        this.request = request;
    }

    protected void setStatus(int status) {
        this.status = status;
    }
}
