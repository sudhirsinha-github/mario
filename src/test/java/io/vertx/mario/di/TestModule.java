package io.vertx.mario.di;

import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.mario.bdd.TestContext;
import io.vertx.mario.helper.VerticleHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sudhir on 17/9/16.
 */
public class TestModule extends AbstractModule {
    private static final String REST_SERVICE_CONFIGURATION_FILE = "config/rest-verticle.json";
    private static final Integer DEFAULT_TEST_WAIT_TIME = 15;


    @Override
    protected void configure() {
        bind(VerticleHelper.class);
        bind(TestContext.class);
    }


    @Provides
    @Named("TestWaitTime")
    public Integer getRestServiceWaitTime(){
        return DEFAULT_TEST_WAIT_TIME;
    }

    @Provides
    public Map<String, String> getMap(){
       return new HashMap<>();
    }

    @Provides
    @Singleton
    public Vertx getVertx(){
        return Vertx.vertx();
    }

    @Provides
    @Singleton
    public HttpClient getHttpClient(Vertx vertx){
      return vertx.createHttpClient();
    }
}
