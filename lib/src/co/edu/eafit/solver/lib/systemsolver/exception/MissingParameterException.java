package co.edu.eafit.solver.lib.systemsolver.exception;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;

public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685494622504316957L;

	private ESystemSolvingParameter[] missingParameters;

	public MissingParameterException(
			ESystemSolvingParameter[] missingParameters) {
		super("Some obligatory parameters are missing: " + getMissingList(missingParameters));
		this.missingParameters = missingParameters;
	}
	
	private static String getMissingList(ESystemSolvingParameter[] missingParameters) {
		String message = "";
		for(ESystemSolvingParameter m : missingParameters){
			message += m.toString() + " ";
		}
		return message;
	}

	public ESystemSolvingParameter[] getMissingParameters() {
		return missingParameters;
	}		
	
}
