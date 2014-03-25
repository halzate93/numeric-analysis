package functions;

public class FunctionParser {
	
	private Evaluator evalutor;
	
	public static Function parse(String function){
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
