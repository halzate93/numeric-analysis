package co.edu.eafit.solver.lib.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		evaluator.parse(parseExpression(expression));
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
	
	/**
	 * Parses an expression in terms of x to a jeval compliant one
	 * @param expression in terms of x
	 * @return a jeval compliant expression
	 */
	private static String parseExpression(String expression){
		// The library uses this format for variables. #{x}
		
		// We put spaces around any x that doesn't have any letter or
		// number exactly at his left, this is to avoid replacing function
		// names, e.g exp.
		int i = 0;
		
		// As we are checking for signs to the left, we must make an exception
		// for the first char.
		if(expression.charAt(0)=='x'){
			i = 3;
			expression = expression.replaceFirst("x", " x ");
		}
		
		Pattern p = Pattern.compile("\\Wx");
		Matcher m = p.matcher(expression);
		//System.out.println("------------------------");
		while(i < expression.length() && m.find(i)){
			//System.out.println(expression + " " + i + " " + m.start() + " " + m.end() + " " + m.group());
			expression = expression.substring(0, m.start())+
					m.group().replace("x", " x ")+
					expression.substring(m.end());
			i = m.end()+2; //Because of the added spaces
			m = p.matcher(expression);
		}
		
		expression = expression.replaceAll("\\sx\\s", "#{x}");
		//System.out.println(expression);
		return expression;
	}
}

