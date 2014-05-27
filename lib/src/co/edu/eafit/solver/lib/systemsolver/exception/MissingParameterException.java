package co.edu.eafit.solver.lib.systemsolver.exception;

import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EGaussianEliminationParameter;

public class MissingParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685494622504316957L;

	private EGaussianEliminationParameter[] missingParameters;

	public MissingParameterException(
			EGaussianEliminationParameter[] missingParameters) {
		super("Some obligatory parameters are missing: " + getMissingList(missingParameters));
		this.missingParameters = missingParameters;
	}
	
	private static String getMissingList(EGaussianEliminationParameter[] missingParameters) {
		String message = "";
		for(EGaussianEliminationParameter m : missingParameters){
			message += m.toString() + " ";
		}
		return message;
	}

	public EGaussianEliminationParameter[] getMissingParameters() {
		return missingParameters;
	}		
	
}
