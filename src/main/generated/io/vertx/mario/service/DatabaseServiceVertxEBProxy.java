/*
* Copyright 2014 Red Hat, Inc.
*
* Red Hat licenses this file to you under the Apache License, version 2.0
* (the "License"); you may not use this file except in compliance with the
* License. You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations
* under the License.
*/

package io.vertx.mario.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

/*
  Generated Proxy code - DO NOT EDIT
  @author Roger the Robot
*/
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DatabaseServiceVertxEBProxy implements DatabaseService {

	private Vertx _vertx;
	private String _address;
	private DeliveryOptions _options;
	private boolean closed;

	public DatabaseServiceVertxEBProxy(Vertx vertx, String address) {
		this(vertx, address, null);
	}

	public DatabaseServiceVertxEBProxy(Vertx vertx, String address, DeliveryOptions options) {
		this._vertx = vertx;
		this._address = address;
		this._options = options;
		try {
			this._vertx.eventBus().registerDefaultCodec(ServiceException.class, new ServiceExceptionMessageCodec());
		} catch (IllegalStateException ex) {
		}
	}

	public DatabaseService findOne(String collection, JsonObject query, JsonObject fields,
			Handler<AsyncResult<JsonObject>> resultHandler) {
		if (closed) {
			resultHandler.handle(Future.failedFuture(new IllegalStateException("Proxy is closed")));
			return this;
		}
		JsonObject _json = new JsonObject();
		_json.put("collection", collection);
		_json.put("query", query);
		_json.put("fields", fields);
		DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
		_deliveryOptions.addHeader("action", "findOne");
		_vertx.eventBus().<JsonObject>send(_address, _json, _deliveryOptions, res -> {
			if (res.failed()) {
				resultHandler.handle(Future.failedFuture(res.cause()));
			} else {
				resultHandler.handle(Future.succeededFuture(res.result().body()));
			}
		});
		return this;
	}

	public DatabaseService insertOne(String collection, JsonObject document,
			Handler<AsyncResult<String>> resultHandler) {
		if (closed) {
			resultHandler.handle(Future.failedFuture(new IllegalStateException("Proxy is closed")));
			return this;
		}
		JsonObject _json = new JsonObject();
		_json.put("collection", collection);
		_json.put("document", document);
		DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
		_deliveryOptions.addHeader("action", "insertOne");
		_vertx.eventBus().<String>send(_address, _json, _deliveryOptions, res -> {
			if (res.failed()) {
				resultHandler.handle(Future.failedFuture(res.cause()));
			} else {
				resultHandler.handle(Future.succeededFuture(res.result().body()));
			}
		});
		return this;
	}

	public DatabaseService findOneAndUpdate(String collection, JsonObject query, JsonObject toUpdate,
			Handler<AsyncResult<JsonObject>> resultHandler) {
		if (closed) {
			resultHandler.handle(Future.failedFuture(new IllegalStateException("Proxy is closed")));
			return this;
		}
		JsonObject _json = new JsonObject();
		_json.put("collection", collection);
		_json.put("query", query);
		_json.put("toUpdate", toUpdate);
		DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
		_deliveryOptions.addHeader("action", "findOneAndUpdate");
		_vertx.eventBus().<JsonObject>send(_address, _json, _deliveryOptions, res -> {
			if (res.failed()) {
				resultHandler.handle(Future.failedFuture(res.cause()));
			} else {
				resultHandler.handle(Future.succeededFuture(res.result().body()));
			}
		});
		return this;
	}

	public void close() {
		if (closed) {
			throw new IllegalStateException("Proxy is closed");
		}
		closed = true;
		JsonObject _json = new JsonObject();
		DeliveryOptions _deliveryOptions = (_options != null) ? new DeliveryOptions(_options) : new DeliveryOptions();
		_deliveryOptions.addHeader("action", "close");
		_vertx.eventBus().send(_address, _json, _deliveryOptions);
	}

	private List<Character> convertToListChar(JsonArray arr) {
		List<Character> list = new ArrayList<>();
		for (Object obj : arr) {
			Integer jobj = (Integer) obj;
			list.add((char) (int) jobj);
		}
		return list;
	}

	private Set<Character> convertToSetChar(JsonArray arr) {
		Set<Character> set = new HashSet<>();
		for (Object obj : arr) {
			Integer jobj = (Integer) obj;
			set.add((char) (int) jobj);
		}
		return set;
	}

	private <T> Map<String, T> convertMap(Map map) {
		if (map.isEmpty()) {
			return (Map<String, T>) map;
		}

		Object elem = map.values().stream().findFirst().get();
		if (!(elem instanceof Map) && !(elem instanceof List)) {
			return (Map<String, T>) map;
		} else {
			Function<Object, T> converter;
			if (elem instanceof List) {
				converter = object -> (T) new JsonArray((List) object);
			} else {
				converter = object -> (T) new JsonObject((Map) object);
			}
			return ((Map<String, T>) map).entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, converter::apply));
		}
	}

	private <T> List<T> convertList(List list) {
		if (list.isEmpty()) {
			return (List<T>) list;
		}

		Object elem = list.get(0);
		if (!(elem instanceof Map) && !(elem instanceof List)) {
			return (List<T>) list;
		} else {
			Function<Object, T> converter;
			if (elem instanceof List) {
				converter = object -> (T) new JsonArray((List) object);
			} else {
				converter = object -> (T) new JsonObject((Map) object);
			}
			return (List<T>) list.stream().map(converter).collect(Collectors.toList());
		}
	}

	private <T> Set<T> convertSet(List list) {
		return new HashSet<T>(convertList(list));
	}
}