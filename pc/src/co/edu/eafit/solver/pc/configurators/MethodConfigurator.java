package co.edu.eafit.solver.pc.configurators;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.methods.enums.EParameter;

public interface MethodConfigurator {
	
	/**
	 * Sets the required parameters to execute the solving method
	 * @param inputScanner
	 * @return the parsed configuration for this method.
	 */
	JSONObject getConfiguration(EParameter[] required);
}
