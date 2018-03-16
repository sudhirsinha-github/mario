package io.vertx.mario.handler;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.mario.auth.SessionUser;
import io.vertx.mario.exception.InvalidTokenException;
import io.vertx.mario.helper.SessionHelper;
import io.vertx.mario.util.DateUtil;
import io.vertx.mario.util.ResponseUtil;

public class AuthHandler extends BaseHandler {
	protected static Logger logger = LoggerFactory.getLogger(AuthHandler.class);
	private final static SessionHelper sessionHelper = SessionHelper.INSTANCE;

	public AuthHandler(Vertx vertx) {
		super(vertx);
	}

	@Override
	public void handle(RoutingContext event) {
		try {
			logger.info("Started executing method AuthHandler.handle");
			ResponseUtil.setCookiesForLogging(event, "class io.vertx.mario.handler.AuthHandler.handle",
					DateUtil.getTimeInMS());
			String authHeader = event.request().headers().get(HttpHeaders.AUTHORIZATION.toString());
			if (null == authHeader)
				throw new InvalidTokenException("Missing auth token");
			sessionHelper.doValidateSession(authHeader.trim()).doOnSuccess(result -> {
				SessionUser sessionUser = new SessionUser(result, SessionHelper.INSTANCE);
				event.getDelegate().setUser(sessionUser);
				event.next();
			}).doOnError(cause -> {
				event.fail(cause);
			}).subscribe();
		} catch (Exception ex) {
			event.fail(ex);
		}
	}
}