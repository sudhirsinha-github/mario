package io.vertx.mario.bdd;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rx.java.RxHelper;

/**
 * Created by Sudhir on 26/11/16.
 */
public class TestContext {
    private HttpClientResponse httpClientResponse;

    private String httpResponseBody;

    public HttpClientResponse getHttpClientResponse() {
        return httpClientResponse;
    }

    public void setHttpClientResponse(HttpClientResponse httpClientResponse) {
        this.httpClientResponse = httpClientResponse;

        rx.Observable.just(this.httpClientResponse)
                .concatMap(RxHelper::toObservable)
                .reduce(Buffer.buffer(), Buffer::appendBuffer)
                .map(buffer -> buffer.toString("UTF-8"))
                .subscribe(this::setHttpResponseBody);
    }

    public String getHttpRawResponseBody() {
        return httpResponseBody;
    }

    public JsonObject getHttpJsonResponseBody() {

        return new JsonObject(httpResponseBody);
    }

    public JsonArray getHttpJsonArrayResponseBody() {
        //array convert and compare..

        JsonArray arr = new JsonArray();
        arr.add(httpResponseBody);

        return arr;
    }

    public void setHttpResponseBody(String httpResponseBody) {
        this.httpResponseBody = httpResponseBody;
    }
}
