package co.edu.eafit.solver.lib.interpolation.function.spline;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;
import co.edu.eafit.solver.lib.interpolation.function.EFunctionInterpolationResult;
import co.edu.eafit.solver.lib.interpolation.function.FunctionPolinomyInterpolation;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class LinearSpline extends FunctionPolinomyInterpolation {

	private double[] m;
	private double[] b;
	
	@Override
	protected double evaluate() throws OutOfRangeException {
		for (int i = 0; i < points.length - 1; i++) {
			if(x <= points[i + 1][0]) return m[i] * x + b[i];
		}
		throw new OutOfRangeException(x, points[0][0], points[points.length - 1][0]);
	}
	
	@Override
	protected double[] calculatePolinomy() throws Exception {
		m = new double[points.length - 1];
		b = new double[points.length - 1];
		double[] p = new double[m.length * 2];
		
		for (int i = 0; i < points.length - 1; i++) {
			m[i] = calculateSlope(points[i], points[i + 1]);
			b[i] = solveIntercept(m[i], points[i]);
			p[i] = m[i];
			p[m.length + i] = b[i];
		}
		
		return p;
	}

	private double calculateSlope(double[] p1, double[] p2) {
		return (p2[1] - p1[1])/(p2[0] - p1[0]);
	}

	private double solveIntercept(double m, double[] p) {
		return p[1] - m * p[0];
	}
	
	@Override
	public JSONObject interpolate() throws Exception{
		JSONObject result = super.interpolate();
		result.put(EFunctionInterpolationResult.m.toString(),
				MatrixUtility.vector2Json(m));
		result.put(EFunctionInterpolationResult.b.toString(),
				MatrixUtility.vector2Json(b));
		return result;
	}

	public double[] getM() {
		return m;
	}

	public double[] getB() {
		return b;
	}

}
