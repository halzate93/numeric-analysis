package co.edu.eafit.solver.lib.methods.exceptions;

import co.edu.eafit.solver.lib.methods.enums.EParameter;

public class InvalidParameterException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private EParameter parameter;
	private String value;
	
	/**
	 * Gets the invalid parameter descriptor.
	 * @return the invalid parameter descriptor.
	 */
	public EParameter getParameter() {
		return parameter;
	}
	
	/**
	 * Gets the invalid value provided.
	 * @return the invalid value provided.
	 */
	public String getValue() {
		return value;
	}
	
	public InvalidParameterException (EParameter parameter, String value){
		super(createMessage(parameter, value));
		this.parameter = parameter;
	}
	
	private static String createMessage(EParameter parameter, String value){
		return "The " + parameter.toString() + " current value of " + value + " is invalid.";
	}
}
