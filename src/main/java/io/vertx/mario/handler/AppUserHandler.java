package io.vertx.mario.handler;

import static io.vertx.mario.util.JsonUtil.decode;

import java.util.Date;

import org.bson.types.ObjectId;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.mario.annotation.Protected;
import io.vertx.mario.annotation.RequestMapping;
import io.vertx.mario.auth.SessionUser;
import io.vertx.mario.constant.MediaType;
import io.vertx.mario.dto.UserSessionDTO;
import io.vertx.mario.exception.RestException;
import io.vertx.mario.helper.AppUserHelper;
import io.vertx.mario.helper.ConfigHelper;
import io.vertx.mario.helper.TemporaryLinkHelper;
import io.vertx.mario.model.AppUser;
import io.vertx.mario.model.TemporaryLink;
import io.vertx.mario.util.MapperUtil;
import io.vertx.mario.util.MessageUtil;
import io.vertx.mario.util.ResponseUtil;

@RequestMapping(path = "/user")
public class AppUserHandler extends BaseHandler {
	private AppUserHelper appUserHelper = AppUserHelper.INSTANCE;
	private TemporaryLinkHelper temporaryLinkHelper = TemporaryLinkHelper.INSTANCE;

	public AppUserHandler(Vertx vertx) {
		super(vertx);
	}

	@Override
	@Protected
	@RequestMapping(method = HttpMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void handle(RoutingContext ctx) {
		try {
			Date date = new Date();
			ObjectId userId = ((SessionUser) ctx.getDelegate().user()).getCurrentSession().getAppUserId();
			AppUser appUserRequest = decode(ctx.getBodyAsString(), AppUser.class);
			appUserHelper.doValidate(appUserRequest, false).flatMap(user -> {
				user.setCreatedBy(userId);
				user.setUpdatedBy(userId);
				return appUserHelper.doCreate(user.toJson());
			}).doOnSuccess(jsonObject -> {
				ResponseUtil.toResponse(MapperUtil.map(new AppUser(jsonObject), UserSessionDTO.class), ctx, date);
			}).doOnError(cause -> {
				ctx.fail(cause);
			}).subscribe();
		} catch (DecodeException dx) {
			ctx.fail(new RestException("Bad Request", HttpResponseStatus.BAD_REQUEST.code()));
		} catch (Exception ex) {
			ctx.fail(ex);
		}
	}

	@RequestMapping(method = HttpMethod.POST, path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void signUp(RoutingContext ctx) {
		try {
			Date date = new Date();
			AppUser appUserRequest = decode(ctx.getBodyAsString(), AppUser.class);
			appUserHelper.doValidate(appUserRequest, true).flatMap(appUser -> {
				return appUserHelper.doCreate(appUser.toJson());
			}).flatMap(jsonObject -> {
				AppUser _user = new AppUser(jsonObject);
				_user.set_id(new ObjectId(jsonObject.getJsonObject("_id").getValue("$oid").toString()));
				TemporaryLink link = TemporaryLink.toNewTemporaryLink(_user);
				return temporaryLinkHelper.doCreate(link.toJson());
			}).doOnSuccess(jsonObject -> {
				TemporaryLink link = new TemporaryLink(jsonObject);
				String tempLink = String.join("/", ConfigHelper.getServerContextPath(), "link", link.getLink());
				MessageUtil.doSendWelcomeMail(appUserRequest, tempLink, this.vertx);
				JsonObject response = new JsonObject().put("link", tempLink);
				ResponseUtil.toResponse(response, ctx, date);
			}).doOnError(cause -> {
				ctx.fail(cause);
			}).subscribe();
		} catch (DecodeException dx) {
			ctx.fail(new RestException("Bad Request", HttpResponseStatus.BAD_REQUEST.code()));
		} catch (Exception ex) {
			ctx.fail(ex);
		}
	}
}