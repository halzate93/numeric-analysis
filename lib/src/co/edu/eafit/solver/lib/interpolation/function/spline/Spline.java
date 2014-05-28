package co.edu.eafit.solver.lib.interpolation.function.spline;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;
import co.edu.eafit.solver.lib.interpolation.function.EFunctionInterpolationResult;
import co.edu.eafit.solver.lib.interpolation.function.FunctionPolinomyInterpolation;
import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.GaussianPartialPivotFactorization;

public abstract class Spline extends FunctionPolinomyInterpolation {

	protected double[][] A;
	protected double[] b;
	
	protected double[][] c;
	protected int n;
	
	private GaussianPartialPivotFactorization g;

	@Override
	protected double[] calculatePolinomy() throws Exception {
		conformSystem();

		g = new GaussianPartialPivotFactorization();
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		g.setParameters(parameters);
		g.solve();
		
		double[] p = g.getX();
		
		fillC(p);
		return p;
	}
	
	@Override
	protected double evaluate() throws OutOfRangeException {
		for (int i = 0; i < points.length - 1; i++) {
			if(x <= points[i + 1][0]){
				double acum = 0;
				for (int j = 0; j < c.length; j++) {
					acum += c[j][i] * Math.pow(x, c.length - 1 - j );
				}
				return acum;
			}
		}
		throw new OutOfRangeException(x, points[0][0], points[points.length - 1][0]);
	}
	
	protected abstract void conformSystem() throws Exception;
	
	private void fillC(double[] p){
		c = new double[points.length - 1][n];
		for (int j = 0; j < p.length; j++) {
			c[j / n][j % n] = p[j];
		}
	}
	
	@Override
	public JSONObject interpolate() throws Exception{
		JSONObject result = super.interpolate();
		result.put(EFunctionInterpolationResult.A.toString(),
				MatrixUtility.matrix2Json(A));
		result.put(EFunctionInterpolationResult.C.toString(),
				MatrixUtility.matrix2Json(c));
		return result;
	}
	
	public double[][] getA() {
		return A;
	}

	public double[] getB() {
		return b;
	}

	public double[][] getC() {
		return c;
	}

}
