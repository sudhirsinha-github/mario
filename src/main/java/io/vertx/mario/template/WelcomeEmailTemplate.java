package io.vertx.mario.template;

import java.io.IOException;

import io.vertx.mario.annotation.EmailTemplate;
import io.vertx.mario.constant.Constants;
import io.vertx.mario.dto.WelcomeTemplateDTO;

@EmailTemplate(name = Constants.WELCOME_TEMPLATE_NAME)
public class WelcomeEmailTemplate extends AbstractEmailTemplate<WelcomeTemplateDTO> {
	private static final String WELCOME_TEMPLATE_PATH = "../vertx-realworld-app/src/main/resources/welcome_template.html";

	public WelcomeEmailTemplate() {
		super(WELCOME_TEMPLATE_PATH);
	}

	@Override
	public String generateTemplate(WelcomeTemplateDTO data) throws IOException {
		String content = readTemplate().replace("{full_name}", data.getFullName());
		content = content.replace("{activate_url}", data.getActivationLink());
		return content;
	}
}