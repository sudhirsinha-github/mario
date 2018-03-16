package io.vertx.mario.helper;

import com.google.inject.Inject;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Map;

/**
 * Created by Sudhir on 17/9/16.
 */
public class VerticleHelper {
    @Inject
    private Vertx vertx;

    @Inject
    private Map<String,String> verticles ;

    public void deployVerticle(String verticleName, JsonObject verticleConfig, Future<Boolean> deploymentStatus) {
        DeploymentOptions deploymentOptions = new DeploymentOptions()
                .setConfig(verticleConfig);

        vertx
                .deployVerticle(verticleName
                        , deploymentOptions
                        , event ->{
                            if(event.succeeded()) {
                                System.out.print("Verticles are deployed !!");
                                verticles
                                        .put(verticleName, event.result());

                                deploymentStatus.complete(event.succeeded());
                            }
                            else{
                                System.out.print("Verticles are not deployed! Make sure config for test is same " + event.cause());
                            }
                        });

    }

    public void unDeployVerticle(String verticleName, Future<Boolean> unDeploymentStatus){
        vertx.undeploy(verticles.get(verticleName)
                , event -> unDeploymentStatus.complete(event.succeeded()));
    }
}
