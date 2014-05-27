package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.exception.*;

public interface LinearSystemMethod {

	/**
	 * Solves the current configuration for the linear equation system
	 * @return the step by step solution
	 * @throws MissingParameterException when any parameter is left unconfigured
	 * @throws DivisionByZeroException if a division by zero happens
	 * and no default value exists.
	 */
	public JSONObject solve() throws MissingParameterException, DivisionByZeroException;
	public void setParameters(JSONObject parameters) throws InvalidParameterException;
}
