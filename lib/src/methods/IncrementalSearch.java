package methods;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONObject;

import functions.Function;

import java.util.ArrayList;

/**
 * Starting with a given continuous function f(x) we will find and interval where it 
 * crosses the x axis, this method doesn't always finds a solution, however it is the
 * most easy and understandable of all the equation solving methods.

 * We must ensure that the function is continuous because it gives us the ability to
 * assume that when f(xi) * f(xf) < 0 we have a sign change in f(x), therefore we
 * crossed the x axis.

 * Eventhough f(x) can cross the x axis many times, we will point only the first one
 * found in this iteration, if you want to find other roots, you can run the program
 * again starting after the already found root.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Incremental-Search
 */
public class IncrementalSearch extends Method {

	/**
	 * The beginning x value to start searching for roots.
	 */
	private float x0;
	
	/**
	 * The change in x for each step, also know as step.
	 */
	private float dx;
	
	/**
	 * The max iteration count to stop the program if no root is found.
	 */
	private int n;
		
	public float getX0() {
		return x0;
	}

	public float getDx() {
		return dx;
	}

	public int getN() {
		return n;
	}
	
	@Override
	protected JSONObject solve() throws EvaluationException{
		JSONObject result = new JSONObject();
		boolean root = false;
		Function f = getFunction();
		
		float xi = x0;
		float yi = f.evaluate(xi);
		
		if(yi == 0){
			root = true;
			result.put(EResults.Root.toString(), x0);
		}
		
		int i = 0;
		float xf, yf;
		while(i < n && root){
			xf = xi + dx;
			yf = f.evaluate(xf);
			
			if(yf == 0){
				root = true;
				result.put(EResults.Root.toString(), xf);
			}else if(yf * yi < 0){
				root = true;
				result.append(EResults.Interval.toString(), xi);
				result.append(EResults.Interval.toString(), xf);
			}
				
			xi = xf;
			yi = yf;
			i++;
		}
		
		if(!root){
			result.put(EResults.Error.toString(), EResultInfo.IterationCount.toString());
		}
		
		result.put(EResultInfo.MaxAbsoluteError.toString(), dx);
		result.put(EResultInfo.IterationCount.toString(), i);
		return result;
	}
	
	@Override
	public void setup(JSONObject parameters) throws InvalidParameterException{
		super.setup(parameters);
		if(parameters.has(EParameter.Dx.toString()))
			dx = (float) parameters.getDouble(EParameter.Dx.toString());
		if(parameters.has(EParameter.X0.toString()))
			x0 = (float) parameters.getDouble(EParameter.X0.toString());
		if(parameters.has(EParameter.N.toString()))
			n = parameters.getInt(EParameter.N.toString());
	}

	/**
	 * Only dx and n are absolutely necessary for the execution, it's
	 * ok if x0's value is 0.
	 */
	@Override
	public EParameter[] checkParameters() {
		ArrayList<EParameter> parameters = new ArrayList<EParameter>(2);
		if(dx == 0) parameters.add(EParameter.Dx);
		if(n == 0) parameters.add(EParameter.N);
		return (EParameter[]) parameters.toArray();
	}
}
