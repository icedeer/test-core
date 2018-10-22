package com.bns.test.health;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bns.test.core.TestCase;
import com.bns.test.core.TestParameter;
import com.bns.test.core.annotation.Feature;

@Feature(name="cache_test_result")
public class HealthCheckTestCase implements TestCase {

	private final String name;
	
	private final Map<String, TestParameter> paraMap;
	
	private HealthCheckTestCase(Builder builder) {
		this.name = builder.caseName;
		this.paraMap = Collections.unmodifiableMap(builder.paraMap);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<TestParameter> getAllParameters() {
		return new ArrayList<>(paraMap.values());
	}
	
	public String getStringParameter(String paraName) {
		TestParameter testPara = paraMap.get(paraName);
		if(testPara != null) {
			return testPara.getStringValue();
		}
		return null;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj != null && obj instanceof HealthCheckTestCase) {
			HealthCheckTestCase other = (HealthCheckTestCase) obj;
			return this.name.equals(other.name) && this.paraMap.equals(other.paraMap);   // TBT double check map equals
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode() + paraMap.hashCode();
	}

	@Override
	public String toString() {
		return getName() + paraMap.keySet();
	}
	
	
	public static Builder builder(String caseName) {
		assert StringUtils.isNotBlank(caseName);
		return new Builder(caseName);
	}
	
	public static Builder from(HealthCheckTestCase anotherCase) {
		assert anotherCase != null;
		return new Builder(anotherCase);
	}

	public static class Builder {
		private String caseName;
		private Map<String, TestParameter> paraMap;
		
		public Builder(String caseName) {
			this.caseName = caseName;
			this.paraMap = new LinkedHashMap<>();
		}
		
		public Builder(HealthCheckTestCase anotherCase) {
			this.caseName = anotherCase.getName();
			this.paraMap = new LinkedHashMap<>(anotherCase.paraMap);
		}

		public HealthCheckTestCase build() {
			return new HealthCheckTestCase(this);
		}

		public Builder withParameter(String paraName, String paraValue) {
			assert StringUtils.isNotBlank(paraName);
			paraMap.put(paraName, TestParameter.builder().name(paraName).value(paraValue).build());
			return this;
		}

		public Builder withParameter(TestParameter parameter) {
			assert parameter != null;
			paraMap.put(parameter.getParameterName(), parameter);
			return this;
		}
		
	}

	
}
