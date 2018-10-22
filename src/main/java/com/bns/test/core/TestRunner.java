package com.bns.test.core;

@FunctionalInterface
public interface TestRunner {
	public RunProcess runTest(TestCase testcase);
}
