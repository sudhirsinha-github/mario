package io.vertx.mario.bdd;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Created by sudhir on 25/11/16.
 */
public class TestWireMock {
    WireMockServer wireMockServer; //No-args constructor will start on port 8989, no HTTPS



    public static String getJsonSampleData(String fileName){
        try {
            return new String(Files
                    .readAllBytes(Paths.get(Resources.getResource(fileName).getFile()))
            );
        }
        catch (IOException ex)
        {
            System.out.println("Error while reading json file -- " + fileName);
            return "";
        }
    }

    public void startService() {

        wireMockServer = new WireMockServer(8081, 8082);
        wireMockServer.start();

        System.out.println("Start mocking Service");

        WireMockRule wireMockRule = new WireMockRule(wireMockConfig().httpsPort(8082));

        WireMock.configureFor(8081);

        //Get customer UUID mocking
        WireMock.stubFor(get(urlEqualTo("/v3/api/username:test@gmail.com")
        ).willReturn(aResponse()
                        .withStatus(422)
                        .withHeader("Content-Type", "application/json")));
        WireMock.stubFor(get(urlEqualTo("/v3/api/username:NOM@gmail.com")
        ).willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"userId\":\"trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac\"}")));

        WireMock.stubFor(put(urlEqualTo("/v1/Consent/trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac/urn:contact:topicCategory:(UK)Marketing"))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", "application/json")));

        WireMock.stubFor(post(urlEqualTo("/v1/addressbook/emailaddresses/trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")));

        WireMock.stubFor(post(urlEqualTo("/address/v4/postcodes/EN28SF/addresses"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                .withBody("{\"addressId\":\"trn:tesco:address:address:uuid:c30b6b24-871d-4f01-ba41-a9a51eb080ad\"}")));;

        WireMock.stubFor(put(urlEqualTo("/v1/addressbook/address-sets/trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac/main"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")));

        WireMock.stubFor(post(urlEqualTo("/v1/Conversation/RegistrationConfirmation/trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));

        WireMock.stubFor(post(urlEqualTo("/v1/Conversation/LoyaltyWelcomePack/trn:tesco:uid:uuid:c4bf79f3-ad89-46ee-bf2f-21e1453e6dac"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));

        WireMock.stubFor(put(urlEqualTo("/v1/pii/trn:tesco:uid:uuid:32f02757-4318-4249-aed8-04f35a355a8f/name"))
                .willReturn(aResponse()
                        .withStatus(403)
                        .withHeader("Content-Type", "application/json")));
    }

    public void stopService(boolean stop) {
        if (stop == true) {
            wireMockServer.stop();
            System.out.println("Stopping the mock Service");
        } else {

            System.out.println("Not able to stop service..");
        }
    }

    public static  void main(String[] args){
        new TestWireMock().startService();
    }
}
