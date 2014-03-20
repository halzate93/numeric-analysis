package methods;

import org.json.JSONObject;

/**
 * Starting with a given continuous function f(x) we will find and interval where it 
 * crosses the x axis, this method doesn't always finds a solution, however it is the
 * most easy and understandable of all the equation solving methods.

 * We must ensure that the function is continuous because it gives us the ability to
 * assume that when f(xi) * f(xf) < 0 we have a sign change in f(x), therefore we
 * crossed the x axis.

 * Eventhough f(x) can cross the x axis many times, we will point only the first one
 * found in this iteration, if you want to find other roots, you can run the program
 * again starting after the already found root.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Incremental-Search
 */
public class IncrementalSearch extends Method {
	
	/**
	 * The beginning x value to start searching for roots.
	 */
	private float x0;
	
	/**
	 * The change in x for each step, also know as step.
	 */
	private float dx;
	
	/**
	 * The max iteration count to stop the program if no root is found.
	 */
	private int n;
		
	public float getX0() {
		return x0;
	}

	public void setX0(float x0) {
		this.x0 = x0;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public JSONObject solve() throws MissingParametersException {
		// TODO Auto-generated method stub
		return null;
	}
}
