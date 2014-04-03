package co.edu.eafit.solver.lib.test.functions;

import static org.junit.Assert.*;
import net.sourceforge.jeval.EvaluationException;

import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.functions.Function;

public class FunctionTest {

	private Function log, sin, pow2, composed, polinomy, exp, tight,
		start, alone, end;
	private static final double MAXERROR = 0.00001;
	
	@Before
	public void setUp() throws Exception {
		log = new Function("log(x)");
		sin = new Function("sin(x)");
		pow2 = new Function("pow(x, 2)");
		exp = new Function("exp(x)");
		composed = new Function("log(cos(x))");
		polinomy = new Function("pow(sin(x), 2) + pow(cos(x), 2)");
		tight = new Function("-1+pow(x,2)*x+1");
		start = new Function("x-1");
		alone = new Function("x");
		end = new Function("1+x");
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
	
	@Test
	public void composedTest() {
		try {
			assertEquals(0, composed.evaluate(0), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void polinomyTest() {
		try {
			assertEquals(1, polinomy.evaluate(5), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void expTest() {
		try {
			assertEquals(2.718281, exp.evaluate(1), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void tightTest() {
		try {
			assertEquals(1, tight.evaluate(1), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void startTest() {
		try {
			assertEquals(-1, start.evaluate(0), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void aloneTest() {
		try {
			assertEquals(0, alone.evaluate(0), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void endTest() {
		try {
			assertEquals(1, end.evaluate(0), MAXERROR);
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
}
