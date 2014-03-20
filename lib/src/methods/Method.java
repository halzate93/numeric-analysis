package methods;

import org.json.*;

import functions.Function;

/**
 * Serves as a template for any equation solving method.
 * @author halzate93
 *
 */
public abstract class Method {
	
	/**
	 * The max error tolerance that this run will allow.
	 */
	private float tolerance;
	
	/**
	 * This is the function to work with while looking for roots. 
	 */
	private Function function;
	
	public float getTolerance(){
		return tolerance;
	}
	
	public void setTolerance(float tolerance){
		this.tolerance = tolerance;	
	}
	
	public Function getFunction(){
		return function;
	}
	
	public void setFunction(Function function){
		this.function = function;
	}
	
	/**
	 * Checks if all the required parameters are set and then runs the
	 * determined equation solving method.
	 * @return A JSONObject containing the specific output for this run and method.
	 * @throws MissingParametersException If any required parameter is missing the 
	 * execution fails.
	 */
	public abstract JSONObject solve() throws MissingParametersException;
}
