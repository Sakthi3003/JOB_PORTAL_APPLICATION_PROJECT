package com.junit;


import static org.junit.jupiter.api.Assertions.assertEquals;  // ✅ JUnit 5


import org.junit.Test;

public class CalcTest {

	@Test
	public void test() {
		Calc calc = new Calc();
		assertEquals(2, calc.divide(10,5),"hi");
	}

}
