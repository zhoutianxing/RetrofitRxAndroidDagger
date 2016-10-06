package com.common.utils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtils {

	private static final String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	private static Gson gson = null;

	private static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().setDateFormat(FORMAT_STRING).registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter()).registerTypeAdapter(java.sql.Date.class, new SQLDateTypeAdapter()).create();
		}
		return gson;
	}

	private static class TimestampTypeAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
		private final DateFormat format = new SimpleDateFormat(FORMAT_STRING, Locale.getDefault());

		@Override
		public JsonElement serialize(Timestamp src, Type arg1, JsonSerializationContext arg2) {
			String dateFormatAsString = format.format(new Date(src.getTime()));
			return new JsonPrimitive(dateFormatAsString);
		}

		@Override
		public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (!(json instanceof JsonPrimitive)) {
				throw new JsonParseException("The date should be a string value");
			}
			try {
				Date date = format.parse(json.getAsString());
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				throw new JsonParseException(e);
			}
		}
	}

	private static class SQLDateTypeAdapter implements JsonSerializer<java.sql.Date> {
		private final DateFormat format = new SimpleDateFormat(FORMAT_STRING, Locale.getDefault());

		@Override
		public JsonElement serialize(java.sql.Date src, Type arg1, JsonSerializationContext arg2) {
			String dateFormatAsString = format.format(new java.sql.Date(src.getTime()));
			return new JsonPrimitive(dateFormatAsString);
		}
	}

	public static String toJson(Object src) {
		return getGson().toJson(src);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return getGson().fromJson(json, classOfT);
	}

	public static <T> T fromJson(String json, Type type) {
		return getGson().fromJson(json, type);
	}

}