package co.edu.eafit.solver.lib.methods;

import org.json.*;

import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.open.FixedPoint;
import co.edu.eafit.solver.lib.methods.open.Newton;
import co.edu.eafit.solver.lib.methods.preliminary.IncrementalSearch;
/**
 * Creates and configures instances of Method, serves as a place to place similar
 * Method construction logic and also isolates it from other code.
 * @author halzate93
 *
 */
public class MethodFactory {

	/**
	 * Builds a Method instance with all the configuration parameters blank.
	 * @param method The specified method to build.
	 * @return a Method instance with all the configuration parameters blank.
	 * @throws Exception when the specified method is not available.
	 */
	public static Method build(EMethod method) throws Exception {
		Method result = null;
		switch (method) {
		case IncrementalSearch:
			result = new IncrementalSearch();
			break;
		case FixedPoint:
			result = new FixedPoint();
			break;
		case Newton:
			result = new Newton();
			break;
		default:
			break;
		}
		if(result != null){
			return result;
		} else
			throw new Exception("Invalid method descriptor.");
	}
	
	/**
	 * Builds a Method instance and sets the given parameters, they could not be
	 * complete anyway.
	 * @param method The specified method to build.
	 * @param params The starting configuration parameters.
	 * @return A Method instance with it's configuration parameters completely or
	 * partially set.
	 * @throws Exception when the specified method is not available. 
	 */
	public static Method build(EMethod method, JSONObject params) throws Exception{
		Method blank = build(method);
		blank.setup(params);
		return blank;
	}
}
