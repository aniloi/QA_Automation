package container;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Container {

  public String baseURL;
  public String dwClientAppKey;
  public String username;
  public String password;
  public int appTypeID;
  public String authToken;
  public int statusCode;
  public Response response;
  public ResponseBody body;
  //    public AuthenticationTokenResponse authenticationTokenResponse;

}
