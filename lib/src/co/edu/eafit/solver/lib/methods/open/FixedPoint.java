package co.edu.eafit.solver.lib.methods.open;

import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.Method;
import co.edu.eafit.solver.lib.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;

/**
 * This is the first one of the open methods, here we don't have
 * an interval where we will look further, instead we have just an
 * starting approximation that could or could not be near a root.
 * 
 * The trick is having a function called g(x), where we will replace
 * our last approximation and get a better one, hoping to move closer
 * to a root.
 * 
 * The method will keep refining the approximation until the error is
 * less than a given tolerance or a max count of iterations is reached.
 * 
 * The better our g(x) function the faster will this method be.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Fixed-Point
 */
public class FixedPoint extends Method {

	/**
	 * The initial approximation.
	 */
	private float x0;
	/**
	 * The function that generates the next x approximation.
	 */
	private Function g;
	/**
	 * The tolerance allowed by the method when looking for a root.
	 */
	private float tolerance;
	/**
	 * The max iterations the method will perform 
	 */
	private int n;
	/**
	 * Error type used when calculating root, it can be absolute or relative
	 */
	private EErrorType errorType;
	
	@Override
	protected JSONObject solve() throws EvaluationException {
		JSONObject result = new JSONObject();
		JSONArray process = new JSONArray();
		
		JSONObject iteration = new JSONObject();
		//Preliminary setup and first iteration
		float xa = x0;
		float ya = getNextApproximation(xa, new JSONObject[]{iteration});
		
		float error = tolerance + 1, xn, yn;
		int i = 0;
		
		iteration.put(EResultProcess.I.toString(), i);
		iteration.put(EResultProcess.X.toString(), xa);
		iteration.put(EResultProcess.Fx.toString(), ya);
		
		/* Exits if we find a root, get to a good approximation or
		 * max iteration count is reached. */
		while(ya != 0f && error > tolerance && i < n){
			iteration = new JSONObject();
			
			xn = g.evaluate(xa);
			yn = getNextApproximation(xn, new JSONObject[]{iteration});
			
			error = calculateError(xa, xn);
			xa = xn;
			ya = yn;
			i++;
			
			iteration.put(EResultProcess.I.toString(), i);
			iteration.put(EResultProcess.X.toString(), xn);
			iteration.put(EResultProcess.Fx.toString(), yn);
			iteration.put(EResultProcess.Error.toString(), Float.toString(error));
			process.put(iteration);
		}
		
		if(ya == 0){
			result.put(EResults.Root.toString(), xa);
			result.put(EResultInfo.Error.toString(), "0");
		} else if( error <= tolerance) {
			result.put(EResults.Root.toString(), xa);
			result.put(EResultInfo.Error.toString(), Float.toString(error));
		} else {
			result.put(EResults.Failure.toString(), EResultInfo.IterationCount.toString());
		}
		result.put(EResultInfo.IterationCount.toString(), i);
		result.put(EResultInfo.Proccess.toString(), process);
		return result;
	}

	/**
	 * Evaluates the g function and gets the next approximation, any derivative of this
	 * method only needs to override this method.
	 * @param xn the actual value
	 * @param info An array holding a single JSONObject to save information about that
	 * step (Emulates the out parameter keyword from other languages).
	 * @return the new approximation to xv (xa).
	 * @throws EvaluationException if the evaluated function fails.
	 */
	protected float getNextApproximation(float xn, JSONObject[] info) throws EvaluationException {
		return getFunction().evaluate(xn);
	}

	/**
	 * Calculates the error depending on which is the current errorType set in this
	 * moment for the method.
	 * @param xi the last approximation.
	 * @param xf the new approximation.
	 * @return the error.
	 */
	private float calculateError(float xi, float xf) {
		if(errorType == EErrorType.Absolute)
			return Math.abs(xf - xi);
		else
			return Math.abs((xf - xi)/xf);
	}
	
	@Override
	public EParameter[] checkParameters() {
		ArrayList<EParameter> missing = new ArrayList<EParameter>();
		if(g == null) missing.add(EParameter.G);
		if(tolerance <= 0) missing.add(EParameter.Tolerance);
		if(n == 0) missing.add(EParameter.N);
		missing.addAll(Arrays.asList(super.checkParameters()));
		return missing.toArray(new EParameter[]{});
	}

	@Override
	public EParameter[] getRequiredParameters(){
		EParameter[] required = new EParameter[]{EParameter.G, EParameter.Tolerance,
				EParameter.X0, EParameter.N};
		ArrayList<EParameter> complete = new ArrayList<EParameter>(Arrays.asList(required));
		complete.addAll(Arrays.asList(super.getRequiredParameters()));
		return complete.toArray(required);
	}
	
	@Override
	public void setup(JSONObject parameters) throws InvalidParameterException{
		super.setup(parameters);
		if(parameters.has(EParameter.X0.toString())){
			try{
				x0 = (float) parameters.getDouble(EParameter.X0.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.X0, 
						parameters.get(EParameter.X0.toString()).toString());
			}
		}
		if(parameters.has(EParameter.Tolerance.toString())){
			try{
				tolerance = (float) parameters.getDouble(EParameter.Tolerance.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.Tolerance, 
						parameters.get(EParameter.Tolerance.toString()).toString());
			}
		}
		if(parameters.has(EParameter.G.toString())){
			String value = parameters.getString(EParameter.G.toString());
			try {
				g = new Function(value);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (EvaluationException e) {
				throw new InvalidParameterException(EParameter.G, value);
			}
		}
		if(parameters.has(EParameter.N.toString())){
			try{
				n = parameters.getInt(EParameter.N.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.N, 
						parameters.get(EParameter.N.toString()).toString());
			}
		}
		if(parameters.has(EParameter.ErrorType.toString())){
			try{
				errorType = EErrorType.valueOf(parameters.getString(
						EParameter.ErrorType.toString()));
			}catch(Exception e){
				throw new InvalidParameterException(EParameter.ErrorType, 
						parameters.get(EParameter.ErrorType.toString()).toString());
			}
		}
	}
	
	public float getX0() {
		return x0;
	}

	public Function getG() {
		return g;
	}

	public float getTolerance() {
		return tolerance;
	}

	
	public int getN() {
		return n;
	}
	
	public EErrorType getErrorType() {
		return errorType;
	}
	
	@Override
	public EMethod getMethodDescriptor() {
		return EMethod.FixedPoint;
	}

	
}
