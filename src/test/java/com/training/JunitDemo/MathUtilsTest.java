package com.training.JunitDemo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

//@TestInstance(Lifecycle.PER_CLASS)
class MathUtilsTest {

	MathUtils m;
	
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeInit() {
		System.out.println("This needs to run before all !!");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		
		m = new MathUtils();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
	}
	
	@AfterAll
	static void afterEverything() {
		System.out.println("This needs to run after all !!");
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning completed");
	}
	
	@Test
	@DisplayName("Testing numbers product")
	@Tag("math")
	void multiply() {
		assertAll(
				() -> assertEquals(4, m.multiply(2, 2)),
				() -> assertEquals(0, m.multiply(2, 0)),
				() -> assertEquals(-4, m.multiply(2, -2)),
				() -> assertEquals(4, m.multiply(-2, -2))
				);
	}
	
	@Nested
	@DisplayName("Add method")
	@Tag("math")
	class AddTest {
		@Test
		@DisplayName("Testing positive numbers")
		@EnabledOnJre(JRE.JAVA_8)
		void testPositive() {
			int expected = 2;
			int actual = m.add(1, 1);
			assertEquals(expected, actual,() -> "Add method should add two numbers");
		}
		
		@Test
		@DisplayName("Testing negative numbers")
		@EnabledOnJre(JRE.JAVA_8)
		void testNegative() {
			int expected = -2;
			int actual = m.add(-1, -1);
			assertEquals(expected, actual,() -> "Add method should add two numbers");
		}
	}
	
	@Test
	@DisplayName("Testing add numbers")
	@EnabledOnJre(JRE.JAVA_8)
	@Tag("math")
	void testAdd() {
		int expected = 2;
		int actual = m.add(1, 1);
		assertEquals(expected, actual,() -> "Add method should add two numbers");
	}
	
	@Test
	@DisplayName("Testing numbers division")
	@Tag("math")
	void testDivide() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class, () -> m.divide(1, 0), "Divide by zero");
	}
	
	@RepeatedTest(3)
	@DisplayName("Test to compute circle area")
	@Tag("circle")
	void testCircleArea(RepetitionInfo repeatInfo) {
		double expected = 3.141592653589793;
		System.out.println(repeatInfo.getCurrentRepetition()+" out of "+repeatInfo.getTotalRepetitions());
		assertEquals(expected, m.computeCircleArea(1),() -> "should calculate ciecle area");
	}
	
	@Test
	@DisplayName("skip test")
	@Disabled
	void disableTest() {
		fail("This test should not run");
	}
	
	@Test
	@EnabledOnOs(OS.LINUX)
	@DisplayName("Test on Linux Box")
	void testOnlyOnLinuxBox() {
		System.out.println("Linux box test pass");
	}
	

}
