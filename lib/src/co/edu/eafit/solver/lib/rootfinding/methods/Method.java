package co.edu.eafit.solver.lib.rootfinding.methods;

import org.json.*;

import co.edu.eafit.solver.lib.rootfinding.functions.Function;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.MissingParametersException;
import expr.SyntaxException;

/**
 * Serves as a template for any equation solving method.
 * @author halzate93
 *
 */
public abstract class Method {
	
	/**
	 * This is the function to work with while looking for roots. 
	 */
	private Function f;
	
	/**
	 * Holds the result of the last execution, null if none.
	 */
	private JSONObject lastResult;
	
	/**
	 * Verifies the current setup for the method and then runs the specific
	 * implementation of each method.
	 * @return a JSONObject containing the information of this run.
	 * @throws MissingParametersException If any required parameter is missing the 
	 * execution fails.
	 * @throws SyntaxException 
	 */
	public JSONObject run() throws MissingParametersException, SyntaxException{
		EParameter[] missingParams = checkParameters();
		if(missingParams.length > 0)
			throw new MissingParametersException(missingParams, getMethodDescriptor());
		else{
			lastResult = solve();
			return lastResult;
		}
	}
	
	/**
	 * Runs the determined equation solving method.
	 * @return A JSONObject containing the specific output for this run and method.
	 * @throws SyntaxException 
	 */
	protected abstract JSONObject solve() throws SyntaxException;
	
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
			} catch (SyntaxException e) {
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
	
	public Function getFunction(){
		return f;
	}
	
	/**
	 * Returns the descriptor associated with this Method.
	 * @return the descriptor.
	 */
	public abstract EMethod getMethodDescriptor();

	public JSONObject getLastResult() throws Exception {
		if(lastResult == null) throw new Exception("No result has been executed");
		return lastResult;
	}
}
