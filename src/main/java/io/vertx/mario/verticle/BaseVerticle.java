package io.vertx.mario.verticle;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.mario.config.AppConfig;
import io.vertx.mario.constant.Constants;

public class BaseVerticle extends AbstractVerticle {
	public static String CONTEXT_PATH = AppConfig.INSTANCE.getConfig().getString(Constants.CONTEXT_PATH);
}