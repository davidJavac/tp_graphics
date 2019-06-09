package model;

import java.util.Map;

public interface Visitable {

	public Map<String, Object> accept(Validation parseable);
}
