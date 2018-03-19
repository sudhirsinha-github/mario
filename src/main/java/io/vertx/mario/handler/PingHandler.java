package io.vertx.mario.handler;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.mario.annotation.RequestMapping;
import io.vertx.mario.constant.Constants;
import io.vertx.mario.constant.MediaType;
import io.vertx.mario.exception.RestException;
import io.vertx.mario.model.ApiResponse;
import io.vertx.mario.util.JsonUtil;
import io.vertx.mario.util.ResponseUtil;

@RequestMapping(path = "/ping")
public class PingHandler extends BaseHandler {

	public PingHandler(Vertx vertx) {
		super(vertx);
	}

	@Override
	@RequestMapping(method = HttpMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public void handle(RoutingContext ctx) {
		ApiResponse<JsonObject> response;
		JsonObject result;
		try {

			result = new JsonObject().put("status", "OK");
			response = ResponseUtil.toApiResponse(200, Constants.RESPONSE_SUCCESS, result, false);
			ctx.response().end(JsonUtil.encode(response));
		} catch (Exception ex) {
			ctx.fail(new RestException(Constants.RESPONSE_FAILED, ex, 500));
		}
	}
}