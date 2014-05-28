package co.edu.eafit.solver.lib.interpolation.function.spline;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.function.EFunctionInterpolationResult;
import co.edu.eafit.solver.lib.interpolation.function.FunctionPolinomyInterpolation;
import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.GaussianPartialPivotFactorization;

public abstract class Spline extends FunctionPolinomyInterpolation {

	protected double[][] A;
	protected double[] b;

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
		
		return g.getX();
	}
	
	protected abstract void conformSystem();
	
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
