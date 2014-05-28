package co.edu.eafit.solver.lib.interpolation.function;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.GaussianPartialPivotFactorization;

public class SystemInterpolation extends FunctionInterpolation {

	private double[][] A;
	private double[] b;
	
	@Override
	protected double evaluate() {
		double fx = 0;
		for (int i = 0; i < p.length; i++) {
			fx += Math.pow(x, p.length - 1 - i) * p[i];
		}
		return fx;
	}

	@Override
	protected double[] calculatePolinomy() throws Exception {
		A = new double[points.length][points.length];
		b = new double[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				A[i][j] = Math.pow(points[i][0], points.length - 1 - j);
			}
			b[i] = points[i][1];
		}
		
		GaussianPartialPivotFactorization g = new GaussianPartialPivotFactorization();
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		g.setParameters(parameters);
		g.solve();
		
		return g.getX();
	}

	@Override
	public JSONObject interpolate() throws Exception{
		JSONObject result = super.interpolate();
		result.put(EFunctionInterpolationResult.A.toString(),
				MatrixUtility.matrix2Json(A));
		
		return result;
	}
	public double[][] getA() {
		return A;
	}

	public double[] getB() {
		return b;
	}
	
}
