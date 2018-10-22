package com.bns.test.core;

import java.util.List;

public interface TestCase {
	public String getName();
	
	public List<TestParameter> getAllParameters();
	
//	public List<TestParameter> getAllParametersForFeature(TestFeature feature);
//	
//	public List<TestFeature> getAllFeatures();
//	
//	public boolean containFeature(TestFeature feature);
	
}
