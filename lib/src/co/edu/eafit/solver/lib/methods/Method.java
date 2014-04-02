package co.edu.eafit.solver.lib.methods;

import net.sourceforge.jeval.EvaluationException;

import org.json.*;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;

/**
 * Serves as a template for any equation solving method.
 * @author halzate93
 *
 */
public abstract class Method {
	
	/**
	 * Is the unique EMethod value associated with an implementation
	 * of this abstract class. 
	 */
	private EMethod methodDescriptor;
	
	/**
	 * This is the function to work with while looking for roots. 
	 */
	private Function f;
	
	public Function getFunction(){
		return f;
	}
	
	public EMethod getMethodDescriptor(){
		return methodDescriptor;
	}
	
	public void setMethodDescriptor(EMethod descriptor){
		this.methodDescriptor = descriptor;
	}
	
	/**
	 * Verifies the current setup for the method and then runs the specific
	 * implementation of each method.
	 * @return a JSONObject containing the information of this run.
	 * @throws MissingParametersException If any required parameter is missing the 
	 * execution fails.
	 * @throws EvaluationException 
	 */
	public JSONObject run() throws MissingParametersException, EvaluationException{
		EParameter[] missingParams = checkParameters();
		if(missingParams.length > 0)
			throw new MissingParametersException(missingParams, getMethodDescriptor());
		else
			return solve();
	}
	
	/**
	 * Runs the determined equation solving method.
	 * @return A JSONObject containing the specific output for this run and method.
	 * @throws EvaluationException 
	 */
	protected abstract JSONObject solve() throws EvaluationException;
	
	/**
	 * Checks if all the required parameters are set.
	 * @return all the missing parameters.
	 */
	public EParameter[] checkParameters(){
		EParameter[] missing = (f == null)? new EParameter[]{EParameter.F}:
			new EParameter[]{};
		return missing;
	}
	
	/**
	 * Configures the method according to the JSON description sent, only changes according
	 * to the values contained in the JSON, if a parameter is not included, the previous
	 * value will be kept.
	 * This method is meant to be override by every implementation.
	 * @param parameters the JSONObject that contains the configuration.
	 */
	public void setup(JSONObject parameters) throws InvalidParameterException{
		if(parameters.has(EParameter.F.toString())){
			String value = parameters.getString(EParameter.F.toString());
			try {
				f = new Function(value);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (EvaluationException e) {
				throw new InvalidParameterException(EParameter.F, value);
			}
		}
	}
	
	/**
	 * Returns a complete list of any parameter required for the method execution,
	 * each method should inherit it's parent's list and add the missing ones.
	 * @return list of required parameters 
	 */
	public EParameter[] getRequiredParameters(){
		return new EParameter[]{EParameter.F};
	}
}
