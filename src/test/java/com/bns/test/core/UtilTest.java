package com.bns.test.core;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private String[][] testdata = new String[][] {
		{"test", "test"},
		{"veryLongStringInOnePhaseButWhoCare", "veryLongStringInOnePhaseButWhoCare"},
		{"a long test's thing", "a.l.t.t"},
		{"two word", "two.word"},
		{"very.long.sentence.with.lot.of.words", "v.l.s.w.l.o.w"}
	};

	@Test
	public void testGetShortName() {
		Arrays.stream(testdata).forEach(list-> assertEquals(list[1], Util.getShortName(list[0])));
	}

}
