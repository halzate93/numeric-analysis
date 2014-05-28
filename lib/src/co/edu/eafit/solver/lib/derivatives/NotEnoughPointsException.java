package co.edu.eafit.solver.lib.derivatives;

public class NotEnoughPointsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3249868627629661314L;

	public NotEnoughPointsException(int index, int points, EDerivationRule rule, Throwable cause){
		super("You can't use "+ rule + " with index" + index + ", only " + points + " points", cause);
	}
}
