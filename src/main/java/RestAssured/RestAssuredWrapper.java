package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class RestAssuredWrapper {

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private Method httpMethod = Method.GET;
    private String baseURL = "";
    private Parser parser = Parser.JSON;

    private RestAssuredResponseData restAssuredResponseData = RestAssuredResponseData.getData();

    public void getResponse(){
        RestAssured.defaultParser = parser;

        RestAssured.baseURI = baseURL;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.cookies(cookies);
        httpRequest.headers(headers);
        httpRequest.params(parameters);
        Response response = httpRequest.request(httpMethod);

        setToResponseData(response);
    }

    /**
     * Set url
     * @param url
     */
    public void setURL(String url){
        baseURL = url;
    }

    /**
     * Set cookies
     * if value is empty (e.g. key1=&key2=), will set empty string
     * @param cookie Example: "key1=value1&key2=value2"
     *
     */
    public void setCookies(String cookie){
        cookies.putAll(Arrays.stream(cookie.split("&")).map(keyValue -> keyValue.split("=")).collect(Collectors.toMap(
                e -> e[0],
                e -> e.length > 1 ? e[1] : ""
        )));
    }

    /**
     * Set headers
     * if value is empty (e.g. key1=&key2=), will set empty string
     * @param header Example: "key1=value1&key2=value2"
     *
     */
    public void setHeaders(String header){
        headers.putAll(Arrays.stream(header.split("&")).map(keyValue -> keyValue.split("=")).collect(Collectors.toMap(
                e -> e[0],
                e -> e.length > 1 ? e[1] : ""
        )));
    }

    /**
     * Set headers
     * if value is empty (e.g. key1=&key2=), will set empty string
     * @param parameter Example: "key1=value1&key2=value2"
     *
     */
    public void setParameters(String parameter){
        parameters.putAll(Arrays.stream(parameter.split("&")).map(keyValue -> keyValue.split("=")).collect(Collectors.toMap(
                e -> e[0],
                e -> e.length > 1 ? e[1] : ""
        )));
    }

    /**
     * Set response to object restAssuredResponseData for use in Validator and LocalParser
     * @param response
     */
    private void setToResponseData(Response response){
        restAssuredResponseData.setResponseBody(response.getBody());
        restAssuredResponseData.setResponseCookies(response.getDetailedCookies());
        restAssuredResponseData.setResponseHeaders(response.getHeaders());
        restAssuredResponseData.setStatusCode(response.getStatusCode());
    }
}
