package io.vertx.mario.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.impl.client.CloseableHttpClient;
import wiremock.org.apache.http.impl.client.HttpClients;

/**
 * Created by sudhir on 19/11/16.
 */
@ScenarioScoped
public class TestStepDef extends Steps {
    TestWireMock mock = new TestWireMock();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpResponse response = null;


    @Before
    public void beforeTest() {
        System.out.println("Called Before Test ");
        mock.startService();

        //Set Environemnt variable
        // utils.setEnv("ENV", "DEV", true);
    }

    @Before("@Service")
    public void startService() {
        System.out.println("Starting REST Verticle API");
        startRestServiceVerticle();
    }

    @After
    public void afterTest() {
        System.out.println("After Test Stopping verticle Service...");
        // cleanup
        stopRestServiceVerticle();
    }

    @After("@Service")
    public void stopService() {
        System.out.println("Stopping Mock Service");

        //startRestServiceVerticle();
        mock.stopService(true);
        //utils.setEnv("ENV", "dev", false);
    }

  /*  @When("^.*call[ing]* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void i_call_get_on_v_cec_addresses_find(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       // System.out.println(HOST_PORT + url + "\n\n");
        callService(HOST_PORT + url, HttpMethod.GET);
    }*/

    @When("^.*call[ing]* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void i_call_post_on_v_cec_register(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Get Customer Sample Request Json Body
        String body = "{\n" +
                "   \"title\": \"Mr\",\n" +
                "   \"firstName\": \"TEST\",\n" +
                "   \"lastName\": \"USER\",\n" +
                "   \"emailid\": \"NOM@gmail.com\",\n" +
                "   \"postcode\": \"EN2 8SF\",\n" +
                "   \"addressLines\": [\n" +
                "      \"Flat 3\",\n" +
                "      \"Stockbridge House\",\n" +
                "      \"44 The Ridgeway\",\n" +
                "      \"ENFIELD\"\n" +
                "   ],\n" +
                "   \"phoneNumber\": \"0983333211\",\n" +
                "   \"marketingPreferences\": false,\n" +
                "   \"terms\": true\n," +
                "  \"correlationId\": \"2fc013be-b3ac-4cbb-87bf-40fdcc5915d4\"" +
                "}";

        callServiceWithBody(HOST_PORT + url, HttpMethod.POST, body);
    }

    @When("^.*run[ing]* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void i_call_again_post_on_v_cec_register(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Get Customer Sample Request Json Body
        String body = "{\n" +
                "   \"title\": \"Mr\",\n" +
                "   \"firstName\": \"TIM\",\n" +
                "   \"lastName\": \"USER\",\n" +
                "   \"emailid\": \"NOM@gmail.com\",\n" +
                "   \"postcode\": \"EN2 8SF\",\n" +
                "   \"addressLines\": [\n" +
                "      \"Flat 3\",\n" +
                "      \"Stockbridge House\",\n" +
                "      \"44 The Ridgeway\",\n" +
                "      \"ENFIELD\"\n" +
                "   ],\n" +
                "   \"phoneNumber\": \"0983333211\",\n" +
                "   \"marketingPreferences\": false,\n" +
                "   \"terms\": true ,\n" +
                "  \"correlationId\": \"2fc013be-b3ac-4cbb-87bf-40fdcc5915d5\",\n" +
                " \"status\": {\n" +
                "\"identityRegisterCustomer\": \"Passed\",\n" +
                "         \"identityViewCustomer\": \"Passed\"," +
                " \"identity_uuid\": \"trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac\"," +
                "\"identityCustomerToken\": \"c33fb616-eb5b-439e-81ed-97697930d836\",\n" +
                "         \"identityCustomerTokendate\": 1491637113204,\n" +
                "         \"profile\": \"Passed\"," +
                "\"contactSaveMP\": \"Failed\",\n" +
                "         \"contactSaveEmail\": \"Failed\",\n" +
                "         \"contactSavePhone\": \"Failed\",\n" +
                "         \"contactSaveWK\": \"Failed\",\n" +
                "         \"addressSaveManual\": \"Failed\",\n" +
                "         \"identitySetPwd\": \"Failed\",\n" +
                "         \"identitySetChannel\": \"Failed\"" +
                " }" +
                "}";

        callServiceWithBody(HOST_PORT + url, HttpMethod.POST, body);
    }

    @When("^.*ran[ing]* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void i_ran_post_on_v_cec_register(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Get Customer Sample Request Json Body
        String body = "{\n" +
                "   \"title\": \"Mr\",\n" +
                "   \"firstName\": \"TIM\",\n" +
                "   \"lastName\": \"USER\",\n" +
                "   \"emailid\": \"NOM@gmail.com\",\n" +
                "   \"postcode\": \"EN2 8SF\",\n" +
                "   \"addressLines\": [\n" +
                "      \"Flat 3\",\n" +
                "      \"Stockbridge House\",\n" +
                "      \"44 The Ridgeway\",\n" +
                "      \"ENFIELD\"\n" +
                "   ],\n" +
                "\"addressLookupId\": \"trn:tesco:address:address:uuid:c30b6b24-871d-4f01-ba41-a9a51eb080ad\"," +
                "   \"phoneNumber\": \"0983333211\",\n" +
                "   \"marketingPreferences\": false,\n" +
                "   \"terms\": true ,\n" +
                "  \"correlationId\": \"2fc013be-b3ac-4cbb-87bf-40fdcc5915d6\",\n" +
                " \"status\": {\n" +
                "\"identityRegisterCustomer\": \"Passed\",\n" +
                "         \"identityViewCustomer\": \"Passed\"," +
                " \"identity_uuid\": \"trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac\"," +
                "\"identityCustomerToken\": \"c33fb616-eb5b-439e-81ed-97697930d836\",\n" +
                // This token is invalid , so PRofile will fail
                // and exit the flow
                "         \"identityCustomerTokendate\": 1491637113204,\n" +
                "         \"profile\": \"Failed\"," +
                "\"contactSaveMP\": \"Passed\",\n" +
                "         \"contactSaveEmail\": \"Passed\",\n" +
                "         \"contactSavePhone\": \"Failed\",\n" +
                "         \"contactSaveWK\": \"Passed\",\n" +
                "         \"addressSaveManual\": \"Passed\",\n" +
                "         \"identitySetPwd\": \"Passed\",\n" +
                "         \"identitySetChannel\": \"Passed\"" +
                " }" +
                "}";

        callServiceWithBody(HOST_PORT + url, HttpMethod.POST, body);
    }

    @When("^.*execute* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void iExecutePostOnVCecCustomerRegister(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Write code here that turns the phrase above into concrete actions
        // Get Customer Sample Request Json Body
        String body = "{\n" +
                "   \"title\": \"Mr\",\n" +
                "   \"firstName\": \"TIM\",\n" +
                "   \"lastName\": \"USER\",\n" +
                "   \"emailid\": \"NOM@gmail.com\",\n" +
                "   \"postcode\": \"EN2 8SF\",\n" +
                "   \"addressLines\": [\n" +
                "      \"Flat 3\",\n" +
                "      \"Stockbridge House\",\n" +
                "      \"44 The Ridgeway\",\n" +
                "      \"ENFIELD\"\n" +
                "   ],\n" +
                "\"addressLookupId\": \"trn:tesco:address:address:uuid:c30b6b24-871d-4f01-ba41-a9a51eb080ad\"," +
                "   \"phoneNumber\": \"0983333211\",\n" +
                "   \"marketingPreferences\": false,\n" +
                "   \"terms\": true ,\n" +
                "  \"correlationId\": \"2fc013be-b3ac-4cbb-87bf-40fdcc5915d6\",\n" +
                " \"status\": {\n" +
                "\"identityRegisterCustomer\": \"Passed\",\n" +
                "         \"identityViewCustomer\": \"Passed\"," +
                " \"identity_uuid\": \"trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac\"," +
                "\"identityCustomerToken\": \"06387ba2-d413-43d9-8df3-e7745d771b16\",\n" +
                "         \"identityCustomerTokendate\": 1491637113204,\n" +
                "         \"profile\": \"Failed\"," +
                "\"contactSaveMP\": \"Passed\",\n" +
                "         \"contactSaveEmail\": \"Passed\",\n" +
                "         \"contactSavePhone\": \"Failed\",\n" +
                "         \"contactSaveWK\": \"Passed\",\n" +
                "         \"addressSaveManual\": \"Passed\",\n" +
                "         \"identitySetPwd\": \"Passed\",\n" +
                "         \"identitySetChannel\": \"Passed\"" +
                " }" +
                "}";

        callServiceWithBody(HOST_PORT + url, HttpMethod.POST, body);
    }


    @When("^I do* " + BRACE_VARIABLE + " on " + BRACE_VARIABLE + "$")
    public void i_do_get_request_on_v_cec_routes(String method, String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        callServiceWithBody(HOST_PORT + url, HttpMethod.GET, null);
    }

    @Then("^the response should be \"([^\"]*)\" and contain$")
    public void the_response_should_be_and_contain(String status, JsonObject response) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // response validate

        validateServicePartResponse(status, response);
    }

    @Then("^the response should be \"([^\"]*)\" and should contain$")
    public void the_response_should_be_and_should_contain(String status, JsonObject response) throws Throwable {
        // Write code here that turns the phrase above into concrete actions    throw new PendingException();

        validateServicePartResponse(status, response);
    }

    @When("^I (?:credit|debit) clubcard" + " on " + BRACE_VARIABLE + "$")
    public void iCreditClubcardOnVCecClubcardCredit(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        MultiMap multiMap = MultiMap.caseInsensitiveMultiMap();
        JsonObject body = new JsonObject();
        body.put("clubcard", "634004025044624046");
        body.put("points", "12");
        body.put("description", "From Test case");
        callService(HOST_PORT + url, multiMap, HttpMethod.POST, body.toString());
    }

    @When("^I wrong (?:credit|debit) clubcard" + " on " + BRACE_VARIABLE + "$")
    public void iCreditClubcardOnVCecClubcardCreditInvalid(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        MultiMap multiMap = MultiMap.caseInsensitiveMultiMap();
        JsonObject body = new JsonObject();
        body.put("clubcard", "634004025044624046");
        body.put("points", "12");
        body.put("description", "From Test case");
        callService(HOST_PORT + url, multiMap, HttpMethod.POST, body.toString());
    }

    @When("^I voidCredit clubcard" + " on " + BRACE_VARIABLE + "$")
    public void voidCredit(String url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        MultiMap multiMap = MultiMap.caseInsensitiveMultiMap();
        callService(HOST_PORT + url, multiMap, HttpMethod.DELETE, null);
    }

    @When("^I want to debit points on a clubcard but has no (.*) in request body on url (.*)")
    public void i_try_calling_debit_without_clubcard(String value, String url) {
        TransactClubcard(value, url);
    }

    @When("^I try to debit CC with more than (.*)")
    public void i_try_debitting_more_value_than_available(String points) {
        String url = "/orchestrator/v1/cec/clubcard/debit";
        MultiMap multiMap = MultiMap.caseInsensitiveMultiMap();
        JsonObject body = new JsonObject();
        body.put("clubcard", "634004025044624046");
        body.put("points", Integer.parseInt(points));
        body.put("description", "From Test case");
        callService(HOST_PORT + url, multiMap, HttpMethod.POST, body.toString());

    }

    private void TransactClubcard(String value, String url) {
        MultiMap multimap = MultiMap.caseInsensitiveMultiMap();
        switch (value) {
            case "clubcard": {
                JsonObject body = new JsonObject();
                body.put("points", "12");
                body.put("description", "From Test case");
                callService(HOST_PORT + url, multimap, HttpMethod.POST, body.toString());
                break;
            }
            case "points": {
                JsonObject body = new JsonObject();
                body.put("points", "0");
                body.put("clubcard", "634004025044624046");
                body.put("description", "From Test case");
                callService(HOST_PORT + url, multimap, HttpMethod.POST, body.toString());
                break;
            }
            case "description": {
                JsonObject body = new JsonObject();
                body.put("clubcard", "634004025044624046");
                body.put("points", "12");
                callService(HOST_PORT + url, multimap, HttpMethod.POST, body.toString());
                break;
            }
        }
    }

    @When("^I gdpr_Pin post (.*)")
    public void iGdpr_PinPostOnOrchestratorVCecGdprPin(String url){
        JsonObject object = new JsonObject();
        object.put("emailAddress", "test@gmail.com");
        object.put("companyName", "TESCO");
        object.put("tempCustomerToken", "99f768cb-75ae-4bf7-8f3a-613af63f6bac");

        MultiMap header = MultiMap.caseInsensitiveMultiMap();
        System.out.printf(url);
        callService(HOST_PORT + url, header, HttpMethod.POST, object.toString());

    }
}