package stepdefs;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.util.JSONPObject;
import container.Container;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class InstrumentSteps {

  //    public static HashMap requestBody;
  public JSONObject requestBody = new JSONObject();
  JSONPObject requestParams;

  private static Container container;

  public InstrumentSteps(Container container) {
    InstrumentSteps.container = container;
  }

  @When("I GET a list of all the available Instruments offered on the DriveWealth platform")
  public void i_get_a_list_of_all_the_available_instruments_offered_on_the_drive_wealth_platform(
      Map<String, String> queryParams) {

    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "instruments";

    container.response =
        given()
            .log()
            .all()
            .header("Accept", "application/json")
            .header("dw-auth-token", container.authToken)
            .header("dw-client-app-key", "942176f1-9f6f-427c-b9a4-bd99f74d2914")
            .queryParams(queryParams)
            .get(RestAssured.baseURI + RestAssured.basePath);

    // Store response body in a variable
    container.body = container.response.getBody();
    //        System.out.println("Response Body: " + container.body.asString());

  }

  @When("I GET a list of Options chain based on query parameters for {string}")
  public void i_get_a_list_of_options_chain_based_on_query_parameters(
      String symbolOrInstrumentID, Map<String, String> queryParams) {
    // Write code here that turns the phrase above into concrete actions
    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "instruments/" + symbolOrInstrumentID + "/options";

    container.response =
        given()
            .log()
            .all()
            .accept(ContentType.JSON)
            .header("dw-auth-token", container.authToken)
            .header("dw-client-app-key", "942176f1-9f6f-427c-b9a4-bd99f74d2914")
            .queryParams(queryParams)
            .get(RestAssured.baseURI + RestAssured.basePath);

    // Store response body in a variable
    container.body = container.response.getBody();
    System.out.println("Response Body: " + container.body.asString());
  }

  @When("I GET option expiration dates for {string}")
  public void i_get_option_expiration_dates_for_an_underlying_security(
      String symbolOrInstrumentID) {

    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "instruments/" + symbolOrInstrumentID + "/options/expiration-dates";

    container.response =
        given()
            .log()
            .all()
            .accept(ContentType.JSON)
            .header("dw-auth-token", container.authToken)
            .header("dw-client-app-key", "942176f1-9f6f-427c-b9a4-bd99f74d2914")
            .get(RestAssured.baseURI + RestAssured.basePath);

    container.body = container.response.getBody();
    System.out.println("Response Body: " + container.body.asString());
  }

  @When("I POST a filter criteria to return a list of matching instruments")
  public void i_post_a_filter_criteria_to_return_a_a_list_of_matching_instruments() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("Verify the response contains the correct symbol for {string}")
  public void verify_the_response_contains_the_correct_symbol_for(String symbolOrInstrumentID) {

    String symbol = container.response.then().extract().jsonPath().get("symbol");
    System.out.println("Symbol: " + symbol);

    Assert.assertEquals(
        container.response.then().extract().jsonPath().get("symbol"), symbolOrInstrumentID);
  }

  @When("I GET the details of {string}")
  public void i_get_the_details_of_a_specific_instrument(String symbolOrInstrumentID) {
    RestAssured.baseURI = "https://bo-api.drivewealth.io/back-office/";
    RestAssured.basePath = "instruments/" + symbolOrInstrumentID;

    container.response =
        given()
            .accept(ContentType.JSON)
            .header("dw-auth-token", container.authToken)
            .header("dw-client-app-key", "9bc97551-c1fc-468d-8ef5-46e1b1469974")
            .get(RestAssured.baseURI + RestAssured.basePath);

    // Store response body in a variable
    container.body = container.response.getBody();

    // Log
    System.out.println("Status Code: " + container.response.statusCode());
    System.out.println("Response Body: " + container.body.asString());
    System.out.println("Auth Token: " + container.authToken);
  }
}
