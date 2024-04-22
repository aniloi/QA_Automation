package stepdefs;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.assertEquals;
import io.cucumber.datatable.DataTable;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationSteps {

    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public String authToken;
    public static HashMap requestBody;
    JSONPObject requestParams;

    @Given("I log in with the following credentials")
    public void i_log_in_with_the_following_credentials(Map<String,String> table) {

        RestAssured.baseURI = "https://bo-api.drivewealth.xyz/back-office/";
        RestAssured.basePath = "auth/";

        requestBody.put("username", table.get("username"));
        requestBody.put("password", table.get("password"));
        requestBody.put("appTypeID", table.get("appTypeID"));

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("dw-client-app-key", table.get("dwClientAppKey"))
                .body(requestBody)
                .post(RestAssured.baseURI + RestAssured.basePath);

        assertEquals(response.statusCode(), 200);


    }
    @Then("the scenario passes")
    public void the_scenario_passes() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("I am logged in with a valid user account")
    public void i_am_logged_in_with_a_valid_user_account() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "https://bo-api.drivewealth.xyz/back-office/";
        RestAssured.basePath = "auth/";

        requestBody.put("username", "bo.kruger.apiauto.jenkinsnewmantests");
        requestBody.put("password", "passw0rd");
        requestBody.put("appTypeID", 4);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("dw-client-app-key", "9bc97551-c1fc-468d-8ef5-46e1b1469974")
                .body(requestBody)
                .post(RestAssured.baseURI + RestAssured.basePath);

        assertEquals(response.statusCode(), 200);

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        JsonPath jsonPath = response.jsonPath();
        authToken = jsonPath.getString("authToken");

        System.out.println("Auth Token: " + authToken);


    }


}
