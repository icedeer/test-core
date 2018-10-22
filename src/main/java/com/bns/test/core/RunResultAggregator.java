package com.bns.test.core;

import java.util.Map;

public interface RunResultAggregator {
	public RunResult aggregate(Map<String, RunResult> resultMap);
}
