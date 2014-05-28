package co.edu.eafit.solver.lib.integrals;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.InterpolationMethod;

public abstract class Integral extends InterpolationMethod {
	private double value;
	@Override
	public JSONObject run() throws Exception {
		JSONObject result = new JSONObject();
		value = evaluate();
		result.put(EIntegralResult.Integral.toString(), value);
		
		return result;
	}
	
	protected abstract double evaluate() throws Exception;

	public double getValue() {
		return value;
	}
}
