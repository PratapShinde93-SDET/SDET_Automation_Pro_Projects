package CommUtilities;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class utility {

    RequestSpecification req;
    public RequestSpecification CommonMeTh() throws IOException {

        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getValuesProp("baseURL")).
                    addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        } return req;
    }
    // Method to read Property File for Urls -- Refer lecture 81
    public static String  getValuesProp(String Key) throws IOException {

        Properties pro= new Properties();
        FileInputStream FIS= new FileInputStream("C:\\Users\\USER\\IdeaProjects\\API_FrameWork_BDD_CC\\src\\test\\java\\CommUtilities\\Global.properties");
        pro.load(FIS);
        return pro.getProperty(Key);

    }
    public String getresponse(Response respo, String key){


        String ResponseAct=respo.asString();
        JsonPath js = new JsonPath(ResponseAct);
        return  js.get(key).toString();

    }
}
