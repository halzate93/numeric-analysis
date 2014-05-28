package co.edu.eafit.solver.lib.derivatives;


public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741215210700843699L;
	
	private EDerivativeParameter[] missingParameters;

	public MissingParameterException(
			EDerivativeParameter[] missingParameters) {
		super("Some obligatory parameters are missing: " + getMissingList(missingParameters));
		this.missingParameters = missingParameters;
	}

	private static String getMissingList(EDerivativeParameter[] missingParameters) {
		String message = "";
		for(EDerivativeParameter m : missingParameters){
			message += m.toString() + " ";
		}
		return message;
	}

	public EDerivativeParameter[] getMissingParameters() {
		return missingParameters;
	}		
	

}
