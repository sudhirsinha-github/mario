package io.vertx.mario.util;

import java.io.IOException;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mail.MailMessage;
import io.vertx.reactivex.core.Vertx;
import io.vertx.mario.constant.Constants;
import io.vertx.mario.dto.WelcomeTemplateDTO;
import io.vertx.mario.helper.ConfigHelper;
import io.vertx.mario.helper.MessageServiceHelper;
import io.vertx.mario.messaging.EmailMessage;
import io.vertx.mario.messaging.EmailMessageConverter;
import io.vertx.mario.model.AppUser;
import io.vertx.mario.reactive.MessagingService;
import io.vertx.mario.template.AbstractEmailTemplate;
import io.vertx.mario.template.TemplateFactory;

public class MessageUtil {
	private static final MessagingService messagingService = MessageServiceHelper.INSTANCE.getMessageService();
	protected static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

	@SuppressWarnings("unchecked")
	public static void doSendWelcomeMail(final AppUser user, final String tempLink, final Vertx vertx)
			throws ClassNotFoundException, IOException {
		WelcomeTemplateDTO data = new WelcomeTemplateDTO(user.getFullName(), tempLink);
		AbstractEmailTemplate<WelcomeTemplateDTO> emailTemplate = TemplateFactory
				.getTemplate(Constants.WELCOME_TEMPLATE_NAME);
		String content = emailTemplate.generateTemplate(data);

		EmailMessage emailMsg = new EmailMessage.Builder().setTo(ResponseUtil.toList(user.getEmail()))
				.setContent(content).setFrom(ConfigHelper.getFromAddress()).setIsContentHTML(true)
				.setSubject("Welcome to the family of Greyseals").build();
		doSendMail(EmailMessageConverter.toMailMessage(emailMsg, vertx));

	}

	public static void doSendMail(final MailMessage mailMessage) {
		messagingService.rxSendMail(mailMessage).subscribe(result -> {
			logger.info("Successfully executed MessageUtil.doSendMail. {}", result.toString());
		}, cause -> {
			logger.error("There was an error while sending email/s: {}", cause);
		});
	}
}