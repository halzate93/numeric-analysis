package functions;

public class FunctionParser {
	
	private Evaluator evalutor;
	
	public static Function parse(String function){
		evalutor = new Evaluator();
		String resultInString = evalutor.evaluate(function);
		return resultInString;
	}
}
