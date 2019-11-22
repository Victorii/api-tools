package RestAssured;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;

public class RestAssuredResponseData {
    /**
     * private constructor to avoid multiple object instances.
     * access only from public getData() method
     */
    private RestAssuredResponseData() {
        responseBody = null; //TODO: NPE?
        responseHeaders = new Headers();
        responseCookies = new Cookies();
        statusCode = 0;
    }

    /**
     * @return instance of RestAssuredResponseData object due to single object existence
     */
    public static RestAssuredResponseData getData(){
        return ResponseDataHolder.instance;
    }

    /**
     * private inner class intended to store constant of instance
     * it provides possibility to work with only single object of RestAssuredResponseData
     */
    private static class ResponseDataHolder {
        private static final RestAssuredResponseData instance = new RestAssuredResponseData();
    }

    private ResponseBody responseBody;
    private Headers responseHeaders;
    private Cookies responseCookies;
    private int statusCode;

    /**
     * @return response cookie
     */
    public Cookies getResponseCookies() {
        return responseCookies;
    }

    /**
     * @return response headers
     */
    public Headers getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * @return response body
     */
    public ResponseBody getResponseBody() {
        return responseBody;
    }

    /**
     * @return status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * set value to responseBody for single object
     * @param responseBody
     */
    protected void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    /**
     * set value to responseCookies for single object
     * @param responseCookies
     */
    protected void setResponseCookies(Cookies responseCookies) {
        this.responseCookies = responseCookies;
    }

    /**
     * set value to responseHeaders for single object
     * @param responseHeaders
     */
    protected void setResponseHeaders(Headers responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * set value to statusCode for single object
     * @param statusCode
     */
    protected void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
