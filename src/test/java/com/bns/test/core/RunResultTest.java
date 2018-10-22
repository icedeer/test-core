package com.bns.test.core;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunResultTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateDefaultRunResult() {
		RunResult result = RunResult.builder().build();
		
		assertNotNull(result);
		assertEquals(RunResult.Status.UNKNOWN, result.getStatus());
		assertNotNull(result.getEndTime());
		assertFalse(Instant.now().isBefore(result.getEndTime()));
	}
	
	@Test
	public void testCreateSuccessRunResult() {
		RunResult result = RunResult.success().build();
		
		assertNotNull(result);
		assertEquals(RunResult.Status.SUCCESS, result.getStatus());
	}
	
	@Test
	public void testCreateFailureRunResult() {
		RunResult result = RunResult.failure().build();
		
		assertNotNull(result);
		assertEquals(RunResult.Status.FAILURE, result.getStatus());
	}
	
	@Test
	public void testCreateSkippedRunResult() {
		RunResult result = RunResult.skipped().build();
		
		assertNotNull(result);
		assertEquals(RunResult.Status.SKIPPED, result.getStatus());
	}
	
	@Test
	public void testCreateRunResultWithCustomizedEndTime() {
		Instant endTime = Instant.now();
		RunResult result = RunResult.success().endAt(endTime).build();
		
		assertNotNull(result);
		assertEquals(endTime, result.getEndTime());
		
	}
	
	@Test(expected=AssertionError.class)
	public void testCreateRunResultWithCustomizedEndTime_Error_IfEndTimeIsInTheFuture() {
		Instant endTime = Instant.now().plusSeconds(20);
		RunResult result = RunResult.success().endAt(endTime).build();
	}
	
	@Test
	public void testCreateRunResultWithCustomizedStartTime() {
		Instant startTime = Instant.now().minusSeconds(2);
		RunResult result = RunResult.success().startAt(startTime).build();
		
		assertNotNull(result);
		assertEquals(startTime, result.getStartTime());
		
	}
	
	@Test(expected=AssertionError.class)
	public void testCreateRunResultWithCustomizedStartTime_Error_IfStartTimeIsInTheFuture() {
		Instant startTime = Instant.now().plusSeconds(20);
		RunResult result = RunResult.success().startAt(startTime).build();
	}
	
	@Test(expected=AssertionError.class)
	public void testCreateRunResultWithCustomizedTime_Error_IfStartTimeIsAfterEndTime() {
		Instant startTime = Instant.now().minusSeconds(1);
		Instant endTime = Instant.now().minusSeconds(3);
		RunResult result = RunResult.success().startAt(startTime).endAt(endTime).build();
	}
	
	@Test
	public void testCreateRunResultWithDetails() {
		RunResult result = RunResult.success().addDetail("response_code", "404").build();
		
		assertNotNull(result);
		assertEquals("404", result.getDetailOf("response_code"));
	}
	
	@Test
	public void testEqualsAndHash() {
		RunResult result = RunResult.success().build();
		RunResult result2 = RunResult.success().build();
		
		assertTrue(result.equals(result2));
		Set<RunResult> set = new HashSet<>();
		set.add(result);
		set.add(result2);
		assertEquals(1, set.size());
	}
	
	@Test
	public void testNotEqual() {
		RunResult result = RunResult.failure().build();
		RunResult result2 = RunResult.success().build();
		
		assertFalse(result.equals(result2));
		
		RunResult result3 = null;
		assertFalse(result.equals(result3));
	}
	
	@Test
	public void testEqualWithDetails() {
		RunResult result = RunResult.failure().addDetail("response_code", "404").build();
		RunResult result2 = RunResult.failure().addDetail("response_code", "404").build();
		
		assertTrue(result.equals(result2));
	}

}
