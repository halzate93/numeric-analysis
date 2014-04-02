package co.edu.eafit.solver.lib.methods.closed;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.Method;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;

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
		JSONArray process = new JSONArray();
		boolean root = false;
		Function f = getFunction();
		
		float xi = x0;
		float yi = f.evaluate(xi);
		
		if(yi == 0){ //Check for root on start
			root = true;
			result.put(EResults.Root.toString(), x0);
		}
		
		JSONObject iteration = new JSONObject(); //Append first iteration
		iteration.put(EResultProcess.X.toString(), Float.toString(xi));
		
		iteration.put(EResultProcess.Fx.toString(), Float.toString(yi));
		
		int i = 0;
		iteration.put(EResultProcess.I.toString(), i+1);
		process.put(iteration);
		
		float xf, yf;
		while(!root && i < n){ //Ends if a root is found or max count
			xf = xi + dx;
			yf = f.evaluate(xf);
			
			float change = yf * yi; 
			if(yf == 0){ //We found a root
				root = true;
				result.put(EResults.Root.toString(), Float.toString(xf));
			}else if(change < 0){ //We crossed the x axis
				root = true;
				result.append(EResults.Interval.toString(), Float.toString(xi));
				result.append(EResults.Interval.toString(), Float.toString(xf));
			}
				
			xi = xf; //Update variables
			yi = yf;
			i++;
			
			iteration = new JSONObject(); //Append this iteration
			iteration.put(EResultProcess.I.toString(), i+1);
			iteration.put(EResultProcess.X.toString(), Float.toString(xi));
			iteration.put(EResultProcess.Fx.toString(), Float.toString(yi));
			iteration.put(EResultProcess.Change.toString(), Float.toString(change));
			process.put(iteration);
		}
		
		if(!root){ //If no root was found, max count was reached
			result.put(EResults.Failure.toString(), EResultInfo.IterationCount.toString());
		}
		
		result.put(EResultInfo.Proccess.toString(), process);
		result.put(EResultInfo.MaxAbsoluteError.toString(), dx);
		result.put(EResultInfo.IterationCount.toString(), i);
		return result;
	}
	
	@Override
	public void setup(JSONObject parameters) throws InvalidParameterException{
		super.setup(parameters);
		
		if(parameters.has(EParameter.Dx.toString())){
			try{
				dx = (float) parameters.getDouble(EParameter.Dx.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.Dx, 
						parameters.getString(EParameter.Dx.toString()));
			}
		}
		if(parameters.has(EParameter.X0.toString())){
			try{
				x0 = (float) parameters.getDouble(EParameter.X0.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.X0, 
						parameters.get(EParameter.X0.toString()).toString());
			}
		}
		if(parameters.has(EParameter.N.toString())){
			try{
				n = parameters.getInt(EParameter.N.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.N, 
						parameters.getString(EParameter.N.toString()));
			}
		}
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
		EParameter[] returnArray = new EParameter[parameters.size()];
		return parameters.toArray(returnArray);
	}
}
