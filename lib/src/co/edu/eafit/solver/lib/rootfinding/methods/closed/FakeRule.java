package co.edu.eafit.solver.lib.rootfinding.methods.closed;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.rootfinding.functions.Function;
import co.edu.eafit.solver.lib.rootfinding.methods.Method;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResults;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.InvalidParameterException;
import expr.SyntaxException;

/**
 * Similar to the Bisection method.
 * The middle point is calculated with the given function: xm= a-((fa*(b-a))/(fb-fa))
 * @author prestrepoh
 * of the found middle point evaluated in the function, we decide an interval to continue evaluating
 * https://github.com/halzate93/solver/wiki/Incremental-Search
 */
public class FakeRule extends Method {

	/**
	 * The initial point of the given interval.
	 */
	private float xI;
	
	/**
	 * The final point of the given interval
	 */
	private float xS;
	
	/**
	 * Maximum possible error
	 */
	private float tolerance;
	
	/**
	 * Absolute or relative error
	 */
	private String errorType;
	
	/**
	 * The max iteration count to stop the program if no root is found.
	 */
	private int n;
		

	public float getxI() {
		return xI;
	}

	public void setxI(float xI) {
		this.xI = xI;
	}

	public float getxS() {
		return xS;
	}

	public void setxS(float xS) {
		this.xS = xS;
	}

	public float getTolerance() {
		return tolerance;
	}

	public void setTolerance(float tolerance) {
		this.tolerance = tolerance;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}
	
	@Override
	protected JSONObject solve() throws SyntaxException{
		JSONObject result = new JSONObject();
		JSONArray process = new JSONArray();
		Function f = getFunction();
		
		float fxi = f.evaluate(xI);
		float fxs = f.evaluate(xS);
		float fxm;
		float xm;
		int counter = 1;
		float error;
		float xaux;
		
		JSONObject iteration = new JSONObject();
		
		if(fxi == 0){
			result.put(EResults.Root.toString(), xI);
		} else if(fxs == 0){
			result.put(EResults.Root.toString(), xS);
		} else if((fxi * fxs) < 0 ){
			xm = xI-((fxi*(xS-xI))/(fxs-fxi));
			fxm = f.evaluate(xm);
			error = tolerance + 1;
			
			while(error > tolerance && fxm != 0 && counter < n){
				if(fxi * fxm < 0){
					xS = xm;
					fxs = fxm;
				}else{
					xI = xm;
					fxi = fxm;
				}
				xaux = xm;
				xm = xI-((fxi*(xS-xI))/(fxs-fxi));
				fxm = f.evaluate(xm);
				error = Math.abs(xm - xaux);
				counter = counter + 1;
				
				//Get iteration info and append it to the results table
				iteration.put(EResultProcess.I.toString(), Float.toString(counter));
				iteration.put(EResultProcess.Xinf.toString(), Float.toString(xI));
				iteration.put(EResultProcess.Xsup.toString(), Float.toString(xS));
				iteration.put(EResultProcess.Xmi.toString(), Float.toString(xm));
				iteration.put(EResultProcess.Fxmi.toString(), Float.toString(fxm));
				iteration.put(EResultProcess.Error.toString(), Float.toString(error));
				process.put(iteration);
			}
			if(xm == 0){
				result.put(EResults.Root.toString(), xm);
			} else if(error < tolerance){
				result.put(EResults.Approximation.toString(), xm);
			}else{
				result.put(EResults.Failure.toString(), xm);
			}
		}else{
			result.put(EResults.IncorrectInterval.toString(), "");
		}
		result.put(EResultInfo.Proccess.toString(), process);
		result.put(EResultInfo.IterationCount.toString(), counter);
		return result;
	}
	
	@Override
	public EParameter[] checkParameters() {
		ArrayList<EParameter> missing = new ArrayList<EParameter>();
		if(xI == 0) missing.add(EParameter.Xi);
		if(xS == 0) missing.add(EParameter.Xs);
		if(tolerance <= 0) missing.add(EParameter.Tolerance);
		if(n == 0) missing.add(EParameter.N);
		missing.addAll(Arrays.asList(super.checkParameters()));
		return missing.toArray(new EParameter[]{});
	}
	
	@Override
	public EParameter[] getRequiredParameters(){
		EParameter[] required = new EParameter[]{EParameter.Xi, EParameter.Xs,
				EParameter.Tolerance, EParameter.N};
		ArrayList<EParameter> complete = new ArrayList<EParameter>(Arrays.asList(required));
		complete.addAll(Arrays.asList(super.getRequiredParameters()));
		return complete.toArray(required);
	}
	
	@Override
	public void setup(JSONObject parameters) throws InvalidParameterException{
		super.setup(parameters);
		if(parameters.has(EParameter.Xi.toString())){
			try{
				xI = (float) parameters.getDouble(EParameter.Xi.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.Xi, 
						parameters.get(EParameter.Xi.toString()).toString());
			}
		}
		if(parameters.has(EParameter.Xs.toString())){
			try{
				xS = (float) parameters.getDouble(EParameter.Xs.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.Xs, 
						parameters.get(EParameter.Xs.toString()).toString());
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
		if(parameters.has(EParameter.N.toString())){
			try{
				n = parameters.getInt(EParameter.N.toString());
			}catch(JSONException e){
				throw new InvalidParameterException(EParameter.N, 
						parameters.get(EParameter.N.toString()).toString());
			}
		}
	}

	@Override
	public EMethod getMethodDescriptor() {
		return EMethod.FakeRule;
	}
}

