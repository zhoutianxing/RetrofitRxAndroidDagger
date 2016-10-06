package com.common.tool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public final class RspChecker {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Need {
		Class<?>[] value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.FIELD })
	public static @interface Check {
	}

	public static final String check(Object object) throws Exception {
		return check(object, object.getClass(), object.getClass().getSimpleName());
	}

	private static final String check(Object object, Class<?> rspClazz, String location) throws Exception {
		Field[] fields = object.getClass().getDeclaredFields();
		Field field;
		int checkFlag;
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			if (!Modifier.isStatic(field.getModifiers())) {
				checkFlag = getCheckFlag(field, rspClazz);
				if (checkFlag >= 0) {
					field.setAccessible(true);
					Object value = field.get(object);
					if (value == null) {
						if (checkFlag == 1) {
							print(location + "->" + field.getName() + " missing");
							return location + "->" + field.getName() + " missing";
						}
						print(location + "->" + field.getName() + " checked[null]");
						continue;
					} else if (value instanceof List<?>) {
						List<?> objList = (List<?>) value;
						if (objList.size() > 0) {
							if (objList.get(0).getClass().getAnnotation(Check.class) != null) {
								for (int j = 0; j < objList.size(); j++) {
									String result = check(objList.get(j), rspClazz, location + "->" + field.getName() + "[" + j + "]");
									if (result != null) {
										return result;
									}
								}
							} else {
								print(location + "->" + field.getName() + " checked[item needn't check]");
							}
						} else {
							print(location + "->" + field.getName() + " checked[empty list]");
						}
					} else if (field.getType().getAnnotation(Check.class) != null) {
						String result = check(value, rspClazz, location + "->" + field.getName());
						if (result != null) {
							return result;
						}
					} else {
						print(location + "->" + field.getName() + " checked");
					}
				}
			}
		}
		return null;
	}

	private static final int getCheckFlag(Field field, Class<?> clazz) {
		Need need = field.getAnnotation(Need.class);
		if (need != null) {
			Class<?>[] clazzs = need.value();
			if (clazzs.length == 0) {
				return 1;
			}
			for (int i = 0; i < clazzs.length; i++) {
				if (clazzs[i] == clazz) {
					return 1;
				}
			}
		} else if (field.getAnnotation(Check.class) != null) {
			return 0;
		}
		return -1;
	}

	private static final void print(String log) {
		// System.out.println(log);
	}
}