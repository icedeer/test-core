package com.bns.test.health;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bns.test.core.TestParameter;

public class HealthCheckTestCaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDefaultConstruction() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("sample test case").build();
		assertNotNull(testcase);
		assertEquals("sample test case", testcase.getName());
	}
	
	@Test(expected=AssertionError.class)
	public void testDefaultConstruction_error_withEmptyName() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("").build();
	}
	
	@Test(expected=AssertionError.class)
	public void testDefaultConstruction_error_withNullyName() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder(null).build();
	}
	
	@Test
	public void testAddingParameter() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("url", "http://www.test.com").build();
		assertNotNull(testcase);
		assertEquals("http://www.test.com", testcase.getStringParameter("url"));
	}
	
	@Test
	public void testAddingParameter_usingTestParameter() {
		TestParameter parameter = TestParameter.builder().name("url").stringValue(" http://www.test.com  ").build();
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter(parameter).build();
		assertEquals("http://www.test.com", testcase.getStringParameter("url"));
	}
	
	@Test
	public void testCreateTestCaseFromExistingOne() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("url", "http://www.test.com").build();
		HealthCheckTestCase testcase2 = HealthCheckTestCase.from(testcase).build();
		
		assertNotNull(testcase2);
		assertEquals(testcase, testcase2);
		
		HealthCheckTestCase testcase3 = HealthCheckTestCase.from(testcase).withParameter("name", "    value").build();
		assertNotNull(testcase3);
		assertNotEquals(testcase, testcase3);
	}
	
	@Test
	public void testToString() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("url", "http://www.test.com").build();
		assertEquals("case[url]", testcase.toString());
		
		testcase = HealthCheckTestCase.builder("case").withParameter("url", "http://www.test.com").withParameter("name", "tester").build();
		assertEquals("case[url, name]", testcase.toString());
		
		testcase = HealthCheckTestCase.builder("case").build();
		assertEquals("case[]", testcase.toString());
	}
	
	@Test
	public void testEquals() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("url", "http://www.test.com").withParameter("name", "tester").build();
		
		HealthCheckTestCase testcase2 = HealthCheckTestCase.builder("case").withParameter("name", "tester").withParameter("url", "http://www.test.com").build();
		assertEquals(testcase, testcase2);
		
		TestParameter parameter = TestParameter.builder().name("url").stringValue(" http://www.test.com  ").build();
		HealthCheckTestCase testcase3 = HealthCheckTestCase.builder("case").withParameter("name", "tester").withParameter(parameter).build();
		assertEquals(testcase, testcase3);
	}

	@Test
	public void testGetAllParameters() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("name", "tester").withParameter("url", "http://www.test.com").build();
		
		List<TestParameter> allParas = testcase.getAllParameters();
		assertNotNull(allParas);
		assertEquals(2, allParas.size());
		
		allParas.contains(TestParameter.builder().name("name").value("tester").build());
		allParas.contains(TestParameter.builder().name("url").value("http://www.test.com").build());
		
	}
	
	@Test
	public void testAddFeature() {
		HealthCheckTestCase testcase = HealthCheckTestCase.builder("case").withParameter("name", "tester").withParameter("url", "http://www.test.com").build();
		
		List<TestParameter> allParas = testcase.getAllParameters();
		assertNotNull(allParas);
		assertEquals(2, allParas.size());
		
		allParas.contains(TestParameter.builder().name("name").value("tester").build());
		allParas.contains(TestParameter.builder().name("url").value("http://www.test.com").build());
		
	}

}
