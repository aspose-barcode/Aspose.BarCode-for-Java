package com.books.aspose;

import java.util.HashMap;
import java.util.Map;

/*
 * @author: Adeel Ilyas
 * Company: Aspose Pty Ltd.
 */

public class ScopedContext {
	public static final String SCOPE_NAME_PAGE = "page";
	public static final String SCOPE_NAME_REQUEST = "request";
	public static final String SCOPE_NAME_SESSION = "session";
	public static final String SCOPE_NAME_APPLICATION = "application";
	private static final Map BY_NAME = new HashMap();
	private static final Map BY_VALUE = new HashMap();
	public static final ScopedContext PAGE = new ScopedContext("page", 1);
	public static final ScopedContext REQUEST = new ScopedContext("request", 2);
	public static final ScopedContext SESSION = new ScopedContext("session", 3);
	public static final ScopedContext APPLICATION = new ScopedContext(
			"application", 4);
	private String name;
	private int value;

	private ScopedContext(String name, int value) {
		this.name = name;
		this.value = value;
		BY_NAME.put(this.name, this);
		BY_VALUE.put(new Integer(this.value), this);
	}

	public String getName() {
		return this.name;
	}

	public int getValue() {
		return this.value;
	}

	public static ScopedContext getInstance(String name) {
		return (ScopedContext) BY_NAME.get(name);
	}

	public static ScopedContext getInstance(int value) {
		return (ScopedContext) BY_VALUE.get(new Integer(value));
	}
}
