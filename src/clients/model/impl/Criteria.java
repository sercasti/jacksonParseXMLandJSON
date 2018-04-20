package clients.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Criteria {

	private final Map<String, String> parameters = new HashMap<String, String>();

	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

}
