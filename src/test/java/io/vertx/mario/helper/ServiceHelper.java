package io.vertx.mario.helper;

import com.google.inject.Inject;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.rx.java.RxHelper;
import rx.Emitter;
import rx.Observable;

/**
 * Created by sudhir on 23/11/16.
 */
public class ServiceHelper {
    @Inject
    private HttpClient httpClient;

    public Observable<HttpClientResponse> call(String uri, MultiMap headers, HttpMethod httpMethod, String requestBody) {
        HttpClientRequest httpClientRequest =
                httpClient
                        .requestAbs(httpMethod, uri);

        if (headers != null)
            httpClientRequest.headers().addAll(headers);

        return Observable.create(subscriber -> {
            RxHelper
                    .toObservable(httpClientRequest)
                    .subscribe(subscriber);

            if(requestBody != null)
                httpClientRequest.end(requestBody);
            else
                httpClientRequest.end();
        }, Emitter.BackpressureMode.NONE);
    }
}
