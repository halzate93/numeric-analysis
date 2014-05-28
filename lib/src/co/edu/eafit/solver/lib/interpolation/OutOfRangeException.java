package co.edu.eafit.solver.lib.interpolation;

public class OutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8213642930933720504L;

	private double x, min, max;
	
	public OutOfRangeException(double x, double min, double max){
		super("The specified value of: " + x + "is not in the interval [" + min + ", " + max + "].");
		this.min = min;
		this.max = max;
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
}
