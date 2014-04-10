package co.edu.eafit.solver.lib.test.functions;

import static org.junit.Assert.*;
import net.sourceforge.jeval.EvaluationException;

import org.junit.Test;

import co.edu.eafit.solver.lib.functions.Function;

public class FunctionTest {

	private static final double MAXERROR = 0.00001;

	@Test
	public void logTest() throws EvaluationException {
		Function log = new Function("log(x)");
		assertEquals(0, log.evaluate(1), MAXERROR);
	}
	
	@Test
	public void sinTest() throws EvaluationException {
		Function sin = new Function("sin(x)");
		assertEquals(0, sin.evaluate(0), MAXERROR);
	}
	
	@Test
	public void pow2Test() throws EvaluationException {
		Function pow2 = new Function("pow(x, 2)");
		assertEquals(4, pow2.evaluate(2), MAXERROR);
	}
	
	@Test
	public void composedTest() throws EvaluationException {
		Function composed = new Function("log(cos(x))");
		assertEquals(0, composed.evaluate(0), MAXERROR);
	}
	
	@Test
	public void polinomyTest() throws EvaluationException {
		Function polinomy = new Function("pow(sin(x), 2) + pow(cos(x), 2)");
		assertEquals(1, polinomy.evaluate(5), MAXERROR);
	}
	
	@Test
	public void expTest() throws EvaluationException {
		Function exp = new Function("exp(x)");
		assertEquals(2.718281, exp.evaluate(1), MAXERROR);
	}
	
	@Test
	public void tightTest() throws EvaluationException {
		Function tight = new Function("-1+pow(x,2)*x+1");
		assertEquals(1, tight.evaluate(1), MAXERROR);
	}
	
	@Test
	public void startTest() throws EvaluationException {
		Function start = new Function("x-1");
		assertEquals(-1, start.evaluate(0), MAXERROR);
	}
	
	@Test
	public void aloneTest() throws EvaluationException {
		Function alone = new Function("x");
		assertEquals(0, alone.evaluate(0), MAXERROR);
	}
	
	@Test
	public void endTest() throws EvaluationException {
		Function end = new Function("1+x");
		assertEquals(1, end.evaluate(0), MAXERROR);
	}
	
	@Test
	public void minusTogetherTest() throws EvaluationException{
		Function complex = new Function("-exp(-x) - 2*x*cos(2*x - 4) + 2*pow(x, 2)*sin(2*x - 4) + 6");
		assertEquals(5.7608897f, complex.evaluate(-1f), MAXERROR);
	}
	
	@Test
	public void minusTogetherCorrectTest() throws EvaluationException{
		Function complex = new Function("-exp(-1*x) - 2*x*cos(2*x - 4) + 2*pow(x, 2)*sin(2*x - 4) + 6");
		assertEquals(5.7608897f, complex.evaluate(-1f), MAXERROR);
	}
	
	@Test
	public void binaryMissingTest() throws EvaluationException{
		new Function("x - + ");
	}
	
	@Test(expected = StringIndexOutOfBoundsException.class)
	public void missingTest() throws EvaluationException{
		new Function("");
	}
	
	@Test
	public void smallNumber() throws EvaluationException{
		Function f = new Function("x * exp(x) - exp(x) + 1");
		assertEquals(0f, f.evaluate(-2.71201133854352E-6f), MAXERROR);
	}
}
