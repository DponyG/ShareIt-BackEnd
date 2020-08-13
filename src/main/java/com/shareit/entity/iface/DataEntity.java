package com.shareit.entity.iface;

import java.util.Map;

public interface DataEntity {

	public enum PersistenceState {
		NEW, MODIFIED, COMMITTED, DELETED
	}

	public Long getId();

	public void setId(Long id);

	public String getSequenceName();

	/**
	 * @return the object values as a Map
	 */
	public Map<String, Object> getAsMap();

}
