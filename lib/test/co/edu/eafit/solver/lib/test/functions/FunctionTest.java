package co.edu.eafit.solver.lib.test.functions;

import static org.junit.Assert.*;

import org.junit.Test;

import co.edu.eafit.solver.lib.functions.Function;
import expr.SyntaxException;

public class FunctionTest {

	private static final double MAXERROR = 0.00001;

	@Test
	public void logTest() throws SyntaxException  {
		Function log = new Function("log(x)");
		assertEquals(0, log.evaluate(1), MAXERROR);
	}
	
	@Test
	public void sinTest() throws SyntaxException {
		Function sin = new Function("sin(x)");
		assertEquals(0, sin.evaluate(0), MAXERROR);
	}
	
	@Test
	public void pow2Test() throws SyntaxException {
		Function pow2 = new Function("x^2");
		assertEquals(4, pow2.evaluate(2), MAXERROR);
	}
	
	@Test
	public void composedTest() throws SyntaxException {
		Function composed = new Function("log(cos(x))");
		assertEquals(0, composed.evaluate(0), MAXERROR);
	}
	
	@Test
	public void polinomyTest() throws SyntaxException {
		Function polinomy = new Function("sin(x)^2 + cos(x)^2");
		assertEquals(1, polinomy.evaluate(5), MAXERROR);
	}
	
	@Test
	public void expTest() throws SyntaxException {
		Function exp = new Function("exp(x)");
		assertEquals(2.718281, exp.evaluate(1), MAXERROR);
	}
	
	@Test
	public void tightTest() throws SyntaxException {
		Function tight = new Function("-1+(x^2)*x+1");
		assertEquals(1, tight.evaluate(1), MAXERROR);
	}
	
	@Test
	public void startTest() throws SyntaxException {
		Function start = new Function("x-1");
		assertEquals(-1, start.evaluate(0), MAXERROR);
	}
	
	@Test
	public void aloneTest() throws SyntaxException {
		Function alone = new Function("x");
		assertEquals(0, alone.evaluate(0), MAXERROR);
	}
	
	@Test
	public void endTest() throws SyntaxException {
		Function end = new Function("1+x");
		assertEquals(1, end.evaluate(0), MAXERROR);
	}
	
	@Test
	public void minusTogetherTest() throws SyntaxException {
		Function complex = new Function("-exp(-x) - 2*x*cos(2*x - 4) + 2*(x^2)*sin(2*x - 4) + 6");
		assertEquals(5.7608897f, complex.evaluate(-1f), MAXERROR);
	}
	
	@Test
	public void minusTogetherCorrectTest() throws SyntaxException {
		Function complex = new Function("-exp(-1*x) - 2*x*cos(2*x - 4) + 2*(x^2)*sin(2*x - 4) + 6");
		assertEquals(5.7608897f, complex.evaluate(-1f), MAXERROR);
	}
	
	@Test(expected = SyntaxException.class)
	public void binaryMissingTest() throws SyntaxException {
		new Function("x - + ");
	}
	
	@Test(expected = SyntaxException.class)
	public void missingTest() throws SyntaxException {
		new Function("");
	}
	
	@Test
	public void smallNumber() throws SyntaxException {
		Function f = new Function("x * exp(x) - exp(x) + 1");
		assertEquals(0f, f.evaluate(-2.71201133854352E-6f), MAXERROR);
	}
}
