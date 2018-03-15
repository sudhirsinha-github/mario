package io.vertx.mario.helper;

import io.vertx.mario.reactive.MessagingService;

public enum MessageServiceHelper {
	INSTANCE;
	private MessagingService messagingService;

	public MessagingService getMessageService() {
		return messagingService;
	}

	public void setMessageService(MessagingService messagingService) {
		this.messagingService = messagingService;
	}
}