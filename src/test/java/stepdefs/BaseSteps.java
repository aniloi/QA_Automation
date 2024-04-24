package stepdefs;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.util.JSONPObject;
import container.Container;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class BaseSteps {

  //    public static HashMap requestBody;
  public JSONObject requestBody = new JSONObject();
  JSONPObject requestParams;

  private static Container container;

  public BaseSteps(Container container) {
    BaseSteps.container = container;
  }

  @Then("Verify the response status code is {int}")
  public void verify_the_response_status_code_is(int expectedStatusCode) {

    container.statusCode = container.response.getStatusCode();
    System.out.println("Status Code: " + container.statusCode);
    Assert.assertEquals(expectedStatusCode, container.statusCode);
  }

  @And("Verify the response schema matches {string}")
  public void verify_the_response_schema_matches(String schemaFileName) {

    // Assert response schema matches the given schema
    //        container.response.then().assertThat().body("$", hasSize(greaterThanOrEqualTo(1)))
    //                .body("[0].id", notNullValue())
    //                .body("[0].symbol", notNullValue())
    //                .body("[0].name", notNullValue())
    //                .body("[1].symbol", notNullValue())
    //                .body("[1].name", notNullValue())
    //                .body("[1].id", notNullValue())
    //                .body("[1].instrumentType", notNullValue())
    //                .body("[1].exchange", notNullValue())
    //                .body("[1].status", notNullValue())
    //                .body("[1].ISIN", notNullValue())
    //                .body("[1].settlementDays", notNullValue())
    //                .body("[1].payFrequency", notNullValue())
    //                .body("[1].couponRate", notNullValue())
    //                .body("[1].maturityDate", notNullValue())
    //                .body("[1].spRating", notNullValue())
    //                .body("[1].minimumInvestmentAmount", notNullValue())
    //                .body("[1].incrementalInvestmentAmount", notNullValue())
    //                .body("[1].issueDate", notNullValue())
    //                .body("[1].datedDate", notNullValue())
    //                .body("[1].issueAmount", notNullValue())
    //                .body("[1].issuePrice", notNullValue())
    //                .body("[1].accruedInterest", notNullValue())
    //                .body("[1].bondType", notNullValue())
    //                .body("[1].debtType", notNullValue())
    //                .body("[1].coupon", notNullValue())
    //                .body("[1].issuer", notNullValue());

    container.body = container.response.getBody();
    System.out.println("Response Body: " + container.body.asString());

    container
        .response
        .then()
        .assertThat()
        .body(
            JsonSchemaValidator.matchesJsonSchemaInClasspath(
                "schema/" + schemaFileName.replaceAll("\\s", "") + ".json"));
  }
}
