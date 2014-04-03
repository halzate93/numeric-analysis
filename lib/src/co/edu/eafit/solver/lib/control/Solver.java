package co.edu.eafit.solver.lib.control;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.methods.*;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;

/**
 * This class handles the setup and usage of multiple equation solving methods,
 * it uses a Prototype pattern to build the method templates and then proceeds
 * to fill the missing parameters, the JSONObject returned contains the correct
 * response for each method and is up to the caller to parse it correctly.
 * @author halzate93
 *
 */
public class Solver {
	/**
	 * The current equation solving method.
	 */
	private Method method;
	
	/**
	 * Sets the equation solving method.
	 * @param method The desired equation solving method.
	 * @throws Exception 
	 */
	public void setMethodPrototype(EMethod method) throws Exception{
		this.method = MethodFactory.build(method);
	}
	
	/**
	 * Sets and configures the equation solving method.
	 * @param method The desired equation solving method.
	 * @param params The beginning configuration parameters for the equation
	 * solving method.
	 * @throws Exception if anything went wrong creating the filled up prototype.
	 */
	public void setMethodPrototype(EMethod method, JSONObject params) throws Exception{
		this.method = MethodFactory.build(method, params);
	}
	
	/**
	 * Configures the current equation solving method, it can be used to fill
	 * missing parameters or to replace existing ones.
	 * @param params A JSONObject containing configuration parameters, it can
	 * be all of the required parameters for the current method or a subset of
	 * them.
	 * @throws InvalidParameterException if any parameter contained in the 
	 * parameters JSON has an invalid value.
	 */
	public void setMethodParams(JSONObject params) throws InvalidParameterException{
		method.setup(params);
	}
	
	/**
	 * Solves the current configuration of method and parameters.
	 * @return A JSONObject containing the specific output for this method, it
	 * could contain intervals, floating point numbers, procedure tables or error
	 * messages.
	 * @throws MissingParametersException If at least one of the parameters needed
	 * to execute the solving is missing the method can't continue.
	 * @throws EvaluationException 
	 */
	public JSONObject solve() throws MissingParametersException, EvaluationException{
		return method.run();
	}
	
	/**
	 * Returns the required parameters to run the selected method.
	 * @return the array of parameters.
	 */
	public EParameter[] getRequiredParameters(){
		return method.getRequiredParameters();
	}

	/**
	 * Returns the result of the last execution, throws an exception if none.
	 * @return the result.
	 * @throws Exception if no execution has happened. 
	 */
	public JSONObject getLastResult() throws Exception {
		return method.getLastResult();
	}
}
