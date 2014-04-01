package test.functions;

import static org.junit.Assert.*;

import net.sourceforge.jeval.EvaluationException;

import org.junit.Before;
import org.junit.Test;

import functions.Function;

public class FunctionTest {

	private Function log, sin, pow2;
	private static final double MAXERROR = 0.00001;
	
	@Before
	public void setUp() throws Exception {
		log = new Function("log(x)");
		sin = new Function("sin(x)");
		pow2 = new Function("pow(x, 2)");
	}

	@Test
	public void logTest() {
		try {
			assertEquals(0, sin.evaluate(0), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void sinTest() {
		try {
			assertEquals(0, log.evaluate(1), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void pow2Test() {
		try {
			assertEquals(4, pow2.evaluate(2), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
}
