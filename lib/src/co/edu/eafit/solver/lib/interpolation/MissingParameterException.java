package co.edu.eafit.solver.lib.interpolation;


public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741215210700843699L;
	
	private EInterpolationParameter[] missingParameters;

	public MissingParameterException(
			EInterpolationParameter[] missingParameters) {
		super("Some obligatory parameters are missing: " + getMissingList(missingParameters));
		this.missingParameters = missingParameters;
	}

	private static String getMissingList(EInterpolationParameter[] missingParameters) {
		String message = "";
		for(EInterpolationParameter m : missingParameters){
			message += m.toString() + " ";
		}
		return message;
	}

	public EInterpolationParameter[] getMissingParameters() {
		return missingParameters;
	}		
	

}
