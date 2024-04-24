package stepdefs;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import com.fasterxml.jackson.databind.util.JSONPObject;
import container.Container;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.json.simple.JSONObject;

public class AuthenticationSteps {

  public RequestSpecification httpRequest;
  //    public static HashMap requestBody;
  public JSONObject requestBody = new JSONObject();
  JSONPObject requestParams;

  private static Container container;

  public AuthenticationSteps(Container container) {
    AuthenticationSteps.container = container;
  }

  @Given("I log in with the following credentials")
  public void i_log_in_with_the_following_credentials(Map<String, String> table) {

    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "auth";

    requestBody.put("username", table.get("username"));
    requestBody.put("password", table.get("password"));
    requestBody.put("appTypeID", table.get("appTypeID"));

    container.response =
        RestAssured.given()
            .log()
            .all()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("dw-client-app-key", table.get("dwClientAppKey"))
            .body(requestBody)
            .post(RestAssured.baseURI + RestAssured.basePath);

    System.out.println(container.response.statusCode());

    assertEquals(container.response.statusCode(), 200);

    System.out.println("Response Status Code: " + container.response.getStatusCode());
    System.out.println("Response Body: " + container.response.getBody().asString());

    JsonPath responseJSONPath = container.response.jsonPath();

    container.authToken = responseJSONPath.getString("authToken");

    System.out.println("Auth Token: " + container.authToken);
  }

  @Then("the scenario passes")
  public void the_scenario_passes() {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("I am logged in with a valid user account")
  public void i_am_logged_in_with_a_valid_user_account() {

    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "auth";

    requestBody.put("username", "bo.niloi.api");
    requestBody.put("password", "passw0rd");
    requestBody.put("appTypeID", "4");

    System.out.println(requestBody);

    container.response =
        RestAssured.given()
            .log()
            .all()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("dw-client-app-key", "942176f1-9f6f-427c-b9a4-bd99f74d2914")
            .body(requestBody)
            .post(RestAssured.baseURI + RestAssured.basePath);

    System.out.println("Response Status Code: " + container.response.getStatusCode());
    System.out.println("Response Body: " + container.response.getBody().asString());

    JsonPath jsonPath = container.response.jsonPath();
    container.authToken = jsonPath.getString("authToken");

    System.out.println("Auth Token: " + container.authToken);

    assertEquals(container.response.statusCode(), 200);
  }

  @Then("the response contains an auth token")
  public void the_response_contains_an_auth_token() {
    // Write code here that turns the phrase above into concrete actions
    container.response.then().body("$", hasKey("authToken"));
    container.response.then().body("authToken", notNullValue());
    //        assertEquals(container.response.getBody().jsonPath(), hasKey("authToken"));
  }
}
