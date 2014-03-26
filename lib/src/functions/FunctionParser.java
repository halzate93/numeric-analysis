package functions;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class FunctionParser {
	
	private static Evaluator evalutor;
	
	public static String parse(String function){
		evalutor = new Evaluator();
		try
		{
			String resultInString = evalutor.evaluate(function);
			evalutor.clearFunctions();
			return resultInString;
		}
		catch (EvaluationException e)
		{
			evalutor.clearFunctions();
			return "error en la expresión";
		}
	}
}
