# JUnit5-Basics-Demo
This project comprises of JUnit5 basics like architecture, different annotations used, realtime testcases etc,.

JUnit 5 (Jupiter)
-----------------

Junit5 dependencies :

	1. Junit-jupiter-api     - API for writing tests using JUnit Jupiter
	2. Junit-jupiter-engine  - Implementation of the test engine API for JUnit Jupiter
	3. Junit-vintage-engine  - A thin layer on top of JUnit4 to allow running vintage tests

-> Add Maven surefire plugin dependency to run testcases through Maven build process. This will help in non-dev environments.

Annotations:

@Test
	• Marks a method as a testcase
	• Informs Junit engine what methods needs to run

Assertions Methods:

	-> assertEquals(expected, actual)
		  Compares whether expected & actual are equal
	-> assertArrayEquals(expectedArray, actualArray)
      Verifies each item in the arrays are equal with right position 
	-> assertIterableEquals(expectedArray, actualArray)
       Verifies each item in the iterable are equal with right position
    -> assertAll() is used to perform bunch of asserts in a group. 
     Example :
        assertAll(
        () -> assertEquals(4, m.multiply(2, 2)),
        () -> assertEquals(0, m.multiply(2, 0)),
        () -> assertEquals(-4, m.multiply(2, -2)),
        () -> assertEquals(4, m.multiply(-2, -2))
        );
   
    For all assertion method, https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html

    -> assertThrows(expectedType, Executable)
		Used to catch the exceptions for code
	  Example Code :
	  assertThrows(ArithmeticException.class, () -> m.divide(1, 0), "Divide by zero")
	

@Nested - this is used to perform aggregated test operations in single set.
Example :
    
    @Nested
    @DisplayName("Add method")
        class AddTest {
                @Test
                @DisplayName("Testing positive numbers")
                void testPositive() { …………………..; }
                @Test
                @DisplayName("Testing negative numbers")
        void testNegative() { …………………..; }
        }
        

Test Life cycle Hooks :
Junit5 creates a new instance for every test method run.

	-> @BeforeAll  - Initializes before anything in the class runs
	-> @AfterAll   - Tear down after all methods are done
	-> @BeforeEach - Initializes before each method
	-> @AfterEach  - Tear down after each method

@BeforeAll @AfterAll hooks cannot be annotated to methods in test classes since these will execute even before an instance is created. Hence, use static methods for such hooks.

@TestInstance - it is used to control the instance creation in test life cycle. There are usually 2 types of instance creations :

	1. PER_METHOD - This is default. it creates new instance for each test method.
           @TestInstance(Lifecycle.PER_METHOD)
	1. PER_CLASS - this creates only one instance for per test class. This is not recommended unless you require to control no. of instances created.
	@TestInstance(Lifecycle.PER_CLASS)
	
@DisplayName - this is used to provide a name to test case
Example code :

    @DisplayName("Testing add numbers")
    void testAdd() {
      int expected = 2;
      int actual = m.add(1, 1);
      assertEquals(expected, actual, "Add method should add two numbers");
    }
    
@Disabled - this is used when a test case needs to be skipped in build process.

Conditional Execution :

@EnabledOnOs(OS.LINUX)
@EnabledOnJre(JRE.JAVA_11)
@EnabledIf
@EnabledIfSystemProperty
@EnabledIfEnvironmentVariable

Assumptions:

@AssumeTrue - validates assumption given. If it is true, then proceeds. Else aborts the test with TestAbortedException.

@RepeatedTest(n) : 
It is used to repeat the test method 'n' times. It also provides an object called RepetitionInfo  which contains total  current repetition number & total repetition count. This object can be accepted as an argument of test method.

@Tag is used to segregate test methods based on tags. This can be used to run specific set of tests by passing relevant tag at time of build(Either in Eclipse or Maven)

TestInfo & TestReporter two interfaces which can be used to log any event at the time  of test run. 
