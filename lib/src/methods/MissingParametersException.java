package methods;

/**
 * It is thrown if any configuration parameter is missing.
 * @author halzate93
 *
 */
public class MissingParametersException extends Exception {

	private static final long serialVersionUID = 588219344875840310L;
	private String[] missingParams;
	private String message;
	
	/**
	 * Gets the missing parameter names that caused this exception.
	 * @return The missing parameter names.
	 */
	public String[] getMissingParams(){
		return missingParams;
	}
	
	public MissingParametersException(String[] missingParams, EMethod method){
		super("The following parameters are needed to use the " + method + " method");
		
		this.missingParams = missingParams;
		message = "The following parameters are needed to use the " + method + " method: ";
		for(String param : missingParams){
			message += param + ", ";
		}
		message = message.substring(0, message.length()-1);
	}
	
	public String toString(){
		return message;
	}
}
