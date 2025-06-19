package com.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcTest1 {

	@Test
	void test() {
		Calc calc = new Calc();
		assertEquals(2, calc.divide(10,5),"hi");
	}
	
	@Test
	void testAssertNotEquals() {
		Calc calc = new Calc();
		assertNotEquals(2, calc.divide(10,0), () -> "The test will fail and cause error");
	}

	@Test
	void testAsserTrue() {
		assertTrue(true);
	}
}
