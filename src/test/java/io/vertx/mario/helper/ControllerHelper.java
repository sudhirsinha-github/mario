package io.vertx.mario.helper;


import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.reactivex.core.http.HttpServerRequest;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.RoutingContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerHelper {
    public RoutingContext routingContext = mock(RoutingContext.class);
    public HttpServerRequest httpServerRequest = mock(HttpServerRequest.class);
    public HttpServerResponse httpServerResponse = mock(HttpServerResponse.class);

    public void mockRoutingContext(){
        when(routingContext.request()).thenReturn(httpServerRequest);
        when(httpServerResponse.putHeader("content-type", "application/json; charset=utf-8")).thenReturn(httpServerResponse);
        when(httpServerResponse.setStatusCode(any(Integer.class))).thenReturn(httpServerResponse);
        when(routingContext.response()).thenReturn(httpServerResponse);
    }

    public Future getResponse(){
        Future response = mock(Future.class);
        when(response.succeeded()).thenReturn(true);
        when(response.setHandler(any(Handler.class))).then(invocation -> {
            Object[] args = invocation.getArguments();
            Handler handler = (Handler) args[0];
            handler.handle(response);
            return null;
        });
        return response;
    }

}
