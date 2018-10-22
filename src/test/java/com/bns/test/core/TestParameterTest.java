package com.bns.test.core;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestParameterTest{
	TestParameter parameter;

	@Before
	public void setUp() throws Exception {
		parameter = TestParameter.builder().name("name").value("tester").build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		Set<TestParameter> theSet = new HashSet<>();
		theSet.add(parameter);
		theSet.add(TestParameter.builder().name("name").value("tester").build());
		assertEquals(1, theSet.size());
	}
	
	@Test(expected=AssertionError.class)
	public void testEmptyTestParameter(){
		TestParameter.builder().build();
	}

	@Test
	public void testGetParameterName() {
		assertEquals("name", parameter.getParameterName());
	}

	@Test
	public void testGetParameterValue() {
		assertEquals("tester", parameter.getParameterValue());
		
		TestParameter nullValue = TestParameter.builder().name("age").build();
		assertNull(nullValue.getParameterValue());
	}

	@Test
	public void testGetParameterValueOrDefault() {
		TestParameter nullValue = TestParameter.builder().name("age").build();
		assertNull(nullValue.getParameterValueOrDefault());
		
		
		TestParameter noDefaultValue = TestParameter.builder().name("age").value(23).build();
		assertEquals(23, noDefaultValue.getParameterValueOrDefault());
		
		TestParameter hasDefaultValue = TestParameter.builder().name("age").defaultValue(100).build();
		assertEquals(100, hasDefaultValue.getParameterValueOrDefault());
	}

	@Test
	public void testGetParameterDefaultValue() {
		assertNull(parameter.getParameterDefaultValue());
		
		TestParameter hasDefaultValue = TestParameter.builder().name("age").defaultValue(100).build();
		assertEquals(100, hasDefaultValue.getParameterDefaultValue());
	}

	@Test
	public void testGetStringValue() {
		assertEquals("tester", parameter.getStringValue());
		
		TestParameter hasIntValue = TestParameter.builder().name("age").value(20).build();
		assertEquals("20", hasIntValue.getStringValue());
		
		TestParameter hasBooleanValue = TestParameter.builder().name("married").value(Boolean.TRUE).build();
		assertEquals("true", hasBooleanValue.getStringValue());
	}

	@Test
	public void testGetStringValueOrDefault() {
		assertEquals("tester", parameter.getStringValueOrDefault());
		
		TestParameter hasIntValue = TestParameter.builder().name("age").value(20).build();
		assertEquals("20", hasIntValue.getStringValueOrDefault());
		
		TestParameter hasDefaultValue = TestParameter.builder().name("age").defaultValue(100).build();
		assertEquals("100", hasDefaultValue.getStringValueOrDefault());
		
		TestParameter noValue =  TestParameter.builder().name("age").build();
		assertNull(noValue.getStringValueOrDefault());
	}

	@Test
	public void testGetBooleanValue() {
		TestParameter hasBooleanValue = TestParameter.builder().name("married").value(Boolean.TRUE).build();
		assertEquals(Boolean.TRUE, hasBooleanValue.getBooleanValue());
		
		hasBooleanValue = TestParameter.builder().name("married").value("true").build();
		assertEquals(Boolean.TRUE, hasBooleanValue.getBooleanValue());
		
		hasBooleanValue = TestParameter.builder().name("married").value("yes").build();
		assertEquals(Boolean.TRUE, hasBooleanValue.getBooleanValue());
		
		hasBooleanValue = TestParameter.builder().name("married").value("not agreed").build();
		assertEquals(Boolean.FALSE, hasBooleanValue.getBooleanValue());
		
		TestParameter noValue =  TestParameter.builder().name("age").build();
		assertNull(noValue.getBooleanValue());
		
		
	}

	@Test
	public void testEqualsObject() {
		TestParameter hasBooleanValue = TestParameter.builder().name("married").value(Boolean.TRUE).build();
		assertEquals(Boolean.TRUE, hasBooleanValue.getBooleanValue());
		
		assertFalse(parameter.equals(hasBooleanValue));
		
		assertFalse(parameter.equals("tester"));
		
		assertTrue(parameter.equals(TestParameter.builder().name("name  ").value("tester").build()));
	}
	

	@Test
	public void testToString() {
		assertEquals("name=tester", parameter.toString());
		
		TestParameter noValue =  TestParameter.builder().name("age").build();
		assertEquals("age=null", noValue.toString());
		
		TestParameter sameValue =  TestParameter.builder().name("age").value(23).defaultValue(23).build();
		assertEquals("age=23", sameValue.toString());
		
		TestParameter diffValue =  TestParameter.builder().name("age").value(23).defaultValue(100).build();
		assertEquals("age=23[100]", diffValue.toString());
		
		TestParameter hasDefaultValue =  TestParameter.builder().name("age").defaultValue(100).build();
		assertEquals("age=null[100]", hasDefaultValue.toString());
		
	}

}
