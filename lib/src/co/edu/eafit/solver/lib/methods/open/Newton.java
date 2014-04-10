package co.edu.eafit.solver.lib.methods.open;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.enums.EFailureCauses;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import expr.SyntaxException;

/**
 * Newton's method is a derivative from the Fixed Point method, with a preset
 * g function, g(x) = x-f(x)/f'(x); this function g is a good one since Newton's
 * convergence is quadratic this means that in each iteration Newton's will multiply
 * the amount of correct digits by 2, getting a fast response.
 * The only and biggest drawback is that the user must provide the derivative of the
 * original function, this isn't always an easy task.
 * @author halzate93
 *
 */
public class Newton extends FixedPoint {

	/**
	 * The first derivative of f, the user must calculate it
	 */
	private Function df;
	
	//Execution values
	protected float dfx;
	/**
	 * Uses the function g(x) = x - f(x)/f'(x) to calculate the next approximation.
	 */
	@Override
	protected float getNextApproximation(JSONObject[] info)
			throws SyntaxException{
		
		info[0].put(EResultProcess.Dfx.toString(), dfx);
		return xn - (y/dfx);
	}
	
	@Override
	protected void firstStep() throws SyntaxException{
		super.firstStep();
		dfx = df.evaluate(xn);
	}
	
	@Override
	protected void updateStatus() throws SyntaxException{
		super.updateStatus();
		dfx = df.evaluate(xn);
	}
	
	@Override
	protected boolean checkExit(){
		if(!super.checkExit())
			return false;
		else if(dfx == 0){
			result = new SimpleEntry<EResults, String>(EResults.Failure,
					EFailureCauses.Dfx.toString());
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public EParameter[] checkParameters(){
		ArrayList<EParameter> missing = new ArrayList<EParameter>(
				Arrays.asList(super.checkParameters()));
		if(df == null) missing.add(EParameter.Df);
		//We don't need the G function that's the point of this method
		missing.remove(EParameter.G); 
		return missing.toArray(new EParameter[]{});
	}
	
	@Override
	public void setup(JSONObject params) throws InvalidParameterException{
		super.setup(params);
		if(params.has(EParameter.Df.toString())){
			String value = params.getString(EParameter.Df.toString());
			try {
				df = new Function(value);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SyntaxException e) {
				throw new InvalidParameterException(EParameter.Df, value);
			}
		}
	}
	
	@Override
	public EParameter[] getRequiredParameters(){
		ArrayList<EParameter> required = new ArrayList<EParameter>(
				Arrays.asList(super.getRequiredParameters()));
		required.add(EParameter.Df);
		required.remove(EParameter.G);
		return required.toArray(new EParameter[]{});
	}
	
	@Override
	public EMethod getMethodDescriptor() {
		return EMethod.Newton;
	}

	public Function getDerivative() {
		return df;
	}
}
