package model;

import java.util.Map;

public interface Validation {

	public Map<String, Object> visit(Application app);
}
