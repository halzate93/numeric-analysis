package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.exception.*;

/**
 * A convenient interface to handle any equation system solving method.
 * @author halzate93
 *
 */
public interface LinearSystemMethod {

	/**
	 * Solves the current configuration for the linear equation system
	 * @return the step by step solution
	 * @throws MissingParameterException when any parameter is left unconfigured
	 * and no default value exists.
	 * @throws DivisionByZeroException if a division by zero happens
	 * @throws Exception 
	 */
	public JSONObject solve() throws MissingParameterException, DivisionByZeroException, Exception;
	
	/**
	 * Allows to configure the solving method, the parameters can be set one by one, or
	 * all in one jsonObject.
	 * @param parameters The parameters to be configured
	 * @throws BadParameterException if any parameter is invalid, the correct parameters
	 * are left unset if anything fails.
	 */
	public void setParameters(JSONObject parameters) throws BadParameterException;
}
