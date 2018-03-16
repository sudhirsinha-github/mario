package io.vertx.mario.bdd;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jayway.awaitility.core.ConditionTimeoutException;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.mario.helper.ServiceHelper;
import io.vertx.mario.helper.VerticleHelper;
import org.junit.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

/**
 * Created by Sudhir on 15/9/16.
 */
public class Steps {
    private  static  final Logger logger = LoggerFactory.getLogger(Steps.class);
    protected static final String BRACE_VARIABLE = "\\[([^\\]]*)\\]";
    protected static final String SPACE_VARIABLE = "([^ ]*)";
    protected static final String QUOTED_VARIABLE = "\"([^\"]*)\"";
    protected static final String HOST_PORT = "http://localhost:9090";
    protected static final String STRING_BRACE_VARIABLE ="\\[\"([^\"]*)\"\\]";

    @Inject
    private TestContext testContext;

    @Inject
    private VerticleHelper verticleHelper;

    @Inject
    private ServiceHelper serviceHelper;

    @Inject
    @Named("TestWaitTime")
    private Integer waitTime;

    private Boolean doContain(JsonObject data, JsonObject containedData) {
        for (String key : containedData.getMap().keySet()) {
            if (!(data.containsKey(key)
                    && isNotNull(data.getValue(key))
                    && isNotNull(containedData.getValue(key))
                    && data.getValue(key).equals(containedData.getValue(key))))
                return false;
        }

        return true;
    }


    protected void startRestServiceVerticle(){
        try {
            Future<Boolean> deploymentStatus = Future.future();

            verticleHelper
                    .deployVerticle("" //todo
                            , new JsonObject().put("", "") //todo
                            , deploymentStatus);

            waitFor("Starting the Service", ()-> deploymentStatus.succeeded());

        }catch (Exception exception){
            logger.error("error :" +exception.getMessage());
        }
    }

    protected void stopRestServiceVerticle(){
        try {
            Future<Boolean> unDeploymentStatusStatus = Future.future();

            verticleHelper
                    .unDeployVerticle(""
                            , unDeploymentStatusStatus);

            waitFor("Stopping the services", ()-> unDeploymentStatusStatus.succeeded());

        }catch (Exception exception){
            logger.error(exception.getMessage());
        }
    }

    protected void callService(String uri, MultiMap headers, HttpMethod httpMethod, String requestBody){
        serviceHelper
                .call(uri, headers, httpMethod, requestBody)
                .subscribe(httpClientResponse -> testContext.setHttpClientResponse(httpClientResponse)
                        ,throwable -> logger.error(throwable.getMessage()));
    }

    protected void callServiceWithBody(String uri, HttpMethod httpMethod, String requestBody){
        callService(uri, null, httpMethod, requestBody);
    }

    /**
     * Method to validate Service Response for its JSON and status
     * @param expectedStatus
     * @param expectedResponse
     */
    protected void validateServiceResponse(String expectedStatus, JsonArray expectedResponse) {


        waitFor("VALIDATE_SERVICE_FULL_RESPONSE",
                () -> {
                   return
                           isNotNull(testContext.getHttpClientResponse())
                            && testContext.getHttpClientResponse().statusMessage().equals(expectedStatus)
                            && isNotNull(testContext.getHttpRawResponseBody())
                            && testContext.getHttpJsonArrayResponseBody().equals(expectedResponse);
                });

    }

    /**
     * Method to validate Service Response for its JSON partially and status
     * @param expectedStatus
     * @param expectedPartResponse
     */
    protected void validateServicePartResponse(String expectedStatus, JsonObject expectedPartResponse) {
        try {
            waitFor("VALIDATE_SERVICE_PART_RESPONSE",
                    () -> {
            /*if(isNotNull(testContext.getHttpClientResponse())&& testContext.getHttpClientResponse().statusMessage().equals(expectedStatus))
            {
                System.out.println("@@@" +testContext.getHttpJsonResponseBody());
            }*/
                        return isNotNull(testContext.getHttpClientResponse())
                                && testContext.getHttpClientResponse().statusMessage().equals(expectedStatus)
                                && isNotNull(testContext.getHttpRawResponseBody())
                                && doContain(testContext.getHttpJsonResponseBody(), expectedPartResponse);

                    });
        } catch (ConditionTimeoutException ex) {
            Assert.assertNotNull(testContext.getHttpClientResponse());
            Assert.assertEquals(expectedPartResponse, testContext.getHttpJsonResponseBody());
        }
    }

    protected void validateServiceStatus(String expectedStatus){
       try {
           waitFor("VALIDATE_SERVICE_RESPONSE_STATUS",
                   () -> isNotNull(testContext.getHttpClientResponse())
                           && Integer.toString(testContext.getHttpClientResponse().statusCode())
                           .equals(expectedStatus));
       }
       catch (ConditionTimeoutException ex) {
           Assert.assertNotNull(testContext.getHttpClientResponse());
           Assert.assertEquals(expectedStatus, testContext.getHttpClientResponse().statusCode());
       }
    }

    /***
     *
     * @param step
     * @param callable
     * @param timeoutSeconds
     */
    private void waitFor(String step, Callable<Boolean> callable, int timeoutSeconds) {
        await(step)
                .atMost(timeoutSeconds, TimeUnit.SECONDS)
                .until(callable);
    }

    private void waitFor(String step, Callable<Boolean> callable) {
        waitFor(step, callable, waitTime);
    }

    private Boolean isNotNull(Object object){
        return object != null;
    }
}
