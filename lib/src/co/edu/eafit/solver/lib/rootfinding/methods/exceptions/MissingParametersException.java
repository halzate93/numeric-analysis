package co.edu.eafit.solver.lib.rootfinding.methods.exceptions;

import co.edu.eafit.solver.lib.rootfinding.methods.enums.EEquationSolvingMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;

/**
 * It is thrown if any configuration parameter is missing.
 * @author halzate93
 *
 */
public class MissingParametersException extends Exception {

	private static final long serialVersionUID = 588219344875840310L;
	private EParameter[] missingParams;
	private EEquationSolvingMethod method;
	
	/**
	 * Gets the missing parameter names that caused this exception.
	 * @return The missing parameter names.
	 */
	public EParameter[] getMissingParams(){
		return missingParams;
	}
	
	/**
	 * Gets the method that caused the error.
	 * @return the method that caused the error.
	 */
	public EEquationSolvingMethod getMethod() {
		return method;
	}

	
	public MissingParametersException(EParameter[] missingParams, EEquationSolvingMethod method){
		super(createMessage(missingParams, method));
		this.missingParams = missingParams;
		this.method = method;
	}
	
	private static String createMessage(EParameter[] missingParams, EEquationSolvingMethod method){
		String message = "The following parameters are needed to use the " + method + " method: ";
		for(EParameter param : missingParams){
			message += param.toString() + ", ";
		}
		message = message.substring(0, message.length()-1);
		return message;
	}
}
