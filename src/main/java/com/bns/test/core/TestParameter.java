package com.bns.test.core;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class TestParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6948974065840531300L;
	private final String name;
	private final Serializable value;
	private final Serializable defaultValue;
	
	private TestParameter(Builder builder) {
		this.name = builder.paraName;
		this.value = builder.paraValue;
		this.defaultValue = builder.paraDefaultValue;
	}
	
	
	public String getParameterName() {
		return name;
	}
	
	public Serializable getParameterValue() {
		return value;
	}
	
	public Serializable getParameterValueOrDefault() {
		if(value != null) {
			return value;
		}
		return defaultValue;
	}
	
	public Serializable getParameterDefaultValue() {
		return defaultValue;
	}
	
	public String getStringValue() {
		return getString(getParameterValue());
	}

	public String getStringValueOrDefault() {
		return getString(getParameterValueOrDefault());
	}
	
	public Boolean getBooleanValue() {
		return getBoolean(getParameterValue());
	}
	
	private String getString(Serializable theValue) {
		return theValue == null ? null : theValue.toString();
	}
	
	private Boolean getBoolean(Serializable theValue) {
		if(theValue == null) {
			return null;
		}
		String avalue = theValue.toString().toLowerCase();
		if("true".equals(avalue) || "yes".equals(avalue)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null && obj instanceof TestParameter) {
			TestParameter other = (TestParameter) obj;
			return this.name.equals(other.name) && this.value.equals(other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = this.name.hashCode();
		return hashCode + this.value.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb =  new StringBuilder(getParameterName()).append("=");
		sb.append(getStringValue());
		if(defaultValue != null && !defaultValue.equals(value)) {
			sb.append("[").append(defaultValue).append("]");
		}
		return sb.toString();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	public static class Builder{
		private String paraName;
		private Serializable paraValue;
		private Serializable paraDefaultValue;
		
		public Builder() {
			
		}
		
		public Builder name(String paraName) {
			assert StringUtils.isNotBlank(paraName);
			this.paraName = paraName.trim();
			return this;
		}
		
		public Builder value(Serializable value) {
			this.paraValue = value;
			return this;
		}
		
		public Builder defaultValue(Serializable defaultValue) {
			this.paraDefaultValue = defaultValue;
			return this;
		}
		
		public Builder stringValue(String value) {
			this.paraValue = (value == null ? value : value.trim());
			return this;
		}
		
		public TestParameter build() {
			assert StringUtils.isNotEmpty(paraName);
			return new TestParameter(this);
		}
	}
}
