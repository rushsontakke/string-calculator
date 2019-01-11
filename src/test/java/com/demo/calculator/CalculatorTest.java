package com.demo.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * @author Rushikesh
 * Test Class for Calculator 
 */
public class CalculatorTest {
	@Test
	public void calculateSum() {
		assertEquals(new Calculator().processInput("2 + 3"), "5.0");
	}

	@Test
	public void calculateComplex() {
		assertEquals(new Calculator().processInput("2 + 3 * 6 - 4"), "16.0");
	}

	@Test
	public void calculateSimpleParanthesis() {
		assertEquals(new Calculator().processInput("2 + 3 * ( 6 - 4 )"), "8.0");
	}

	@Test
	public void calculateComplexParanthesis() {
		assertEquals(new Calculator().processInput("2 ^ 3 - ( 6 - 4 ) * ( ( 2 + 3 ) * ( 10 / 5 ) )"), "-12.0");
	}

	@Test
	public void invalidInput() {
		assertEquals(new Calculator().processInput("2^3-(6-4)*((2+3)*(10/5))"), "Invalid Expression");
	}
	
}
