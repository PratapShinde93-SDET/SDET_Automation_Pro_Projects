package stepdefination;

import CommUtilities.utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourcesData.TestDatapayLoad;
import resourcesData.TestResources;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefination extends utility {

    RequestSpecification ReqTest;
    ResponseSpecification res;
    Response respo;
    TestDatapayLoad PayL= new TestDatapayLoad();

    @Given("Add place API PayLoad {string} {string} {string}")
    public void add_place_api_pay_load(String Address, String Name, String Language) throws IOException {

        res = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
        ReqTest= given().spec(CommonMeTh()).log().all().body(PayL.AddPlace_Payload(Address,Name,Language));

    }
    @When("user calls {string} API {string} https call")
    public void user_calls_api_https_call(String resource, String method) {

        TestResources ResourceAPIEP=TestResources.valueOf(resource);
        System.out.println(ResourceAPIEP.getretunresource());

        if(method.equalsIgnoreCase("POST"))

            respo= ReqTest.when().log().all().post(ResourceAPIEP.getretunresource())
                    .then().spec(res).extract().response();
            else if(method.equalsIgnoreCase("GET"))
            respo= ReqTest.when().log().all().get(ResourceAPIEP.getretunresource())
                    .then().spec(res).extract().response();;


       /* respo= ReqTest.when().log().all().post(ResourceAPIEP.getretunresource())
                .then().spec(res).extract().response();
        System.out.println("Body Response "+ respo);*/
    }
    @Then("Api call got with successful with status code {int}")
    public void api_call_got_with_successful_with_status_code(Integer int1) {
        assertEquals(respo.getStatusCode(),200);
        System.out.println(" Response ststaus COde  " + respo.statusCode());

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String Keyv, String ActV) {


        assertEquals(getresponse(respo,Keyv),ActV);

    }
    @Then("Verfiy place id created maps to {string} using {string}")
    public void verfiy_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
       // get API Call
       // Request Spec
        String PlaceID=getresponse(respo,"place_id");
        System.out.println("Place ID+++ "+PlaceID);
        System.out.println("First Response" + respo);
        //Calling the Get API
        ReqTest= given().spec(CommonMeTh()).queryParam("place_id",PlaceID);
        user_calls_api_https_call(resource,"GET");

        String ActualName=getresponse(respo,"Name");
        System.out.println("Name in Response GET " + ActualName);
        System.out.println("Second Response" + respo);
       // assertEquals(ActualName,expectedname);

        // Adding Comments Commit
    }


}
