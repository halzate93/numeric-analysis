package co.edu.eafit.solver.lib.functions;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class Function {
	private Evaluator evaluator;
	private String expression;
	
	/**
	 * Creates a Function from a String representation.
	 * @param expression in a String format such as sin(x) + x, always
	 * expressed in terms of x.
	 * @throws EvaluationException if the expression passed was invalid.
	 */
	public Function(String expression) throws EvaluationException{
		evaluator = new Evaluator();
		//The library uses this format for variables.
		evaluator.parse(expression.replace("x", "#{x}"));
		this.expression = expression;
	}
	
	/**
	 * Evaluates this function with a given value.
	 * @param x the value to replace in the function.
	 * @return the float value of the result.
	 * @throws EvaluationException if the parsed expression was wrong or
	 * if the provided value is invalid.
	 */
	public float evaluate(float x) throws EvaluationException{
		evaluator.putVariable("x", x+"");
		String resultString = evaluator.evaluate();
		return Float.parseFloat(resultString);
	}

	public String getExpression() {
		return expression;
	}
}

