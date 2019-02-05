package com.wol.smart.fridge;

public class SmartFridgeItem {

	private long type;
	private String uuid;
	private String name;
	private Double fillFactor;

	public SmartFridgeItem() {}

	public SmartFridgeItem(long type, String uuid, String name, Double fillFactor) {
		this.type = type;
		this.uuid = uuid;
		this.name = name;
		this.fillFactor = fillFactor;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFillFactor() {
		return fillFactor;
	}

	public void setFillFactor(Double fillFactor) {
		this.fillFactor = fillFactor;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (type ^ (type >>> 32));
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartFridgeItem other = (SmartFridgeItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public String toString() {
		return "SmartFridgeItem [type=" + type + ", uuid=" + uuid + ", name=" + name + ", fillFactor=" + fillFactor
				+ "]";
	}

}
