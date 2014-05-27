package co.edu.eafit.solver.lib.rootfinding.functions;

import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import expr.Variable;

public class Function {
	private Expr expr;
	private String exprStr;
	private Variable x;
	/**
	 * Creates a Function from a String representation.
	 * @param expression in a String format such as sin(x) + x, always
	 * expressed in terms of x.
	 * @throws SyntaxException when the given expression is invalid.
	 */
	public Function(String expression) throws SyntaxException{
		x = Variable.make("x");
		Parser parser = new Parser();
		parser.allow(x);
		
		expr = parser.parseString(expression);
		this.exprStr = expression;
	}
	
	/**
	 * Evaluates this function with a given value.
	 * @param x the value to replace in the function.
	 * @return the float value of the result.
	 */
	public float evaluate(float xv) throws SyntaxException{
		x.setValue(xv);
		return (float) expr.value();
	}

	public String getExpression() {
		return exprStr;
	}
}

