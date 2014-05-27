package co.edu.eafit.solver.lib.systemsolver.exception;

public class DivisionByZeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2361096806444759099L;

	public DivisionByZeroException(int phase) {
		super("Division by zero in phase:" + phase);
	}
}
