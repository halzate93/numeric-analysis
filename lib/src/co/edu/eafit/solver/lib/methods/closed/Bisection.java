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
import java.util.Arrays;

/**
 * This method consist in to divide the given interval that contains a root, in two
 * subintervals of the same size.
 * with the sign of the function evaluated in the limits of the interval, and the sign
 * of the found middle point evaluated in the function, we decide an interval to continue evaluating
 * @author prestrepoh
 * https://github.com/halzate93/solver/wiki/Incremental-Search
 */
public class Bisection extends Method {

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
		if(parameters.has(EParameter.N.toString())){
			try{
				n = parameters.getInt(EParameter.N.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.N, 
						parameters.getString(EParameter.N.toString()));
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
	}

	/**
	 * Only dx and n are absolutely necessary for the execution, it's
	 * ok if x0's value is 0.
	 */
	@Override
	public EParameter[] checkParameters() {
		ArrayList<EParameter> parameters = new ArrayList<EParameter>(2);
		parameters.addAll(Arrays.asList(super.checkParameters()));
		if(dx == 0) parameters.add(EParameter.Dx);
		if(n == 0) parameters.add(EParameter.N);
		EParameter[] returnArray = new EParameter[parameters.size()];
		return parameters.toArray(returnArray);
	}

	@Override
	public EParameter[] getRequiredParameters() {
		EParameter[] required = {EParameter.N, EParameter.Dx, EParameter.X0};
		ArrayList<EParameter> complete = new ArrayList<EParameter>(Arrays.asList(required));
		complete.addAll(Arrays.asList(super.getRequiredParameters()));
		return complete.toArray(required);
	}
}
