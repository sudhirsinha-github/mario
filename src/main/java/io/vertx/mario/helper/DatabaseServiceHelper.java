package io.vertx.mario.helper;

import io.vertx.mario.reactive.DatabaseService;

public enum DatabaseServiceHelper {
	INSTANCE;
	private DatabaseService dbService;

	public DatabaseService getDbService() {
		return dbService;
	}

	public void setDbService(DatabaseService dbService) {
		this.dbService = dbService;
	}
}