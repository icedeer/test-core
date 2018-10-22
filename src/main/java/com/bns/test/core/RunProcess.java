package com.bns.test.core;

import java.time.Instant;

public interface RunProcess {
	public RunStatus getRunStatus();
	
	public String getProcessId();
	
	public TestCase getTestCase();
	
	public Instant getStartTime();
	
	public Instant getEndTime();
	
	public RunResult getRunResult();
}
