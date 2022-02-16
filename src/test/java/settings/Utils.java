package settings;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {

    public static RequestSpecification req;

    public static RequestSpecification requestSpecification() throws FileNotFoundException {
        String baseUri = new PropertiesFile().getProperty("baseURI");
        if(req==null){
            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(baseUri)
                    .addQueryParam("key","AIzaSyDXbWvOa95j9u9TK8PzsG1vR4RvbOvVkR4")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
        return req;
    }

    public static String getResponseValue(Response response, String key){
        JsonPath js = new JsonPath(response.asString());
        return js.get(key);
    }
}
