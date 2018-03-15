/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.mario.model;

import io.vertx.core.json.JsonObject;

/**
 * Converter for {@link io.vertx.mario.model.AppUser}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.mario.model.AppUser} original class using Vert.x codegen.
 */
public class AppUserConverter {

  public static void fromJson(JsonObject json, AppUser obj) {
    if (json.getValue("createdDate") instanceof String) {
      obj.setCreatedDate((String)json.getValue("createdDate"));
    }
    if (json.getValue("email") instanceof String) {
      obj.setEmail((String)json.getValue("email"));
    }
    if (json.getValue("firstName") instanceof String) {
      obj.setFirstName((String)json.getValue("firstName"));
    }
    if (json.getValue("isActive") instanceof Boolean) {
      obj.setIsActive((Boolean)json.getValue("isActive"));
    }
    if (json.getValue("lastName") instanceof String) {
      obj.setLastName((String)json.getValue("lastName"));
    }
    if (json.getValue("password") instanceof String) {
      obj.setPassword((String)json.getValue("password"));
    }
    if (json.getValue("salt") instanceof String) {
      obj.setSalt((String)json.getValue("salt"));
    }
    if (json.getValue("updatedDate") instanceof String) {
      obj.setUpdatedDate((String)json.getValue("updatedDate"));
    }
    if (json.getValue("phoneNumber") instanceof String) {
      obj.setPhoneNumber((String)json.getValue("phoneNumber"));
    }
    if (json.getValue("marketingPreferences") instanceof Boolean) {
      obj.setMarketingPreferences((Boolean) json.getBoolean("marketingPreferences"));
    }
    if (json.getValue("address") instanceof String) {
      obj.setAddress((String)json.getValue("address"));
    }
  }

  public static void toJson(AppUser obj, JsonObject json) {
    if (obj.getCreatedDate() != null) {
      json.put("createdDate", obj.getCreatedDate());
    }
    if (obj.getEmail() != null) {
      json.put("email", obj.getEmail());
    }
    if (obj.getFirstName() != null) {
      json.put("firstName", obj.getFirstName());
    }
    if (obj.getIsActive() != null) {
      json.put("isActive", obj.getIsActive());
    }
    if (obj.getLastName() != null) {
      json.put("lastName", obj.getLastName());
    }
    if (obj.getPassword() != null) {
      json.put("password", obj.getPassword());
    }
    if (obj.getSalt() != null) {
      json.put("salt", obj.getSalt());
    }
    if (obj.getUpdatedDate() != null) {
      json.put("updatedDate", obj.getUpdatedDate());
    }
    if (obj.getPhoneNumber() != null) {
      json.put("phoneNumber", obj.getPhoneNumber());
    }
    if (obj.getAddress() != null) {
      json.put("address", obj.getAddress());
    }
    if (obj.getMarketingPreferences() != null) {
      json.put("marketingPreferences", obj.getMarketingPreferences());
    }
  }
}