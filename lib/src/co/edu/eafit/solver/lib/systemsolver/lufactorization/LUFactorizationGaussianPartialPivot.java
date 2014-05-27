package co.edu.eafit.solver.lib.systemsolver.lufactorization;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EPivotingStrategy;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;

/**
 * Follows the same process as Gaussian Elimination with Partial Pivoting strategy,
 * but creates L and U matrixes in the process to allow the user to solve many LUx = b
 * systems easily.
 * 
 * Transforms the problem Ax = b in LUx = b where L and U are triangular matrixes
 * product from the factorization of the A matrix.
 * 
 * @author halzate93
 *
 */
public class LUFactorizationGaussianPartialPivot extends GaussianElimination {

	private double[][] L, U, P;
	private double[] x, z;
	private JSONObject result;
	
	public LUFactorizationGaussianPartialPivot(){
		super.ps = EPivotingStrategy.Partial;
		result = new JSONObject();
	}
	
	@Override
	public void setParameters(JSONObject parameters)
			throws BadParameterException {
		try{
			if(parameters.has(ESystemSolvingParameter.Strategy.toString())){
				parameters.remove(ESystemSolvingParameter.Strategy.toString());
			}
			super.setParameters(parameters);
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}
	
	@Override
	public JSONObject solve() throws Exception {
		super.checkParameters();
		L = new double[A.length][A.length];
		U = new double[A.length][A.length];
		P = new double[A.length][A.length];
		
		for (int k = 0; k < P.length; k++) {
			P[k][k] = 1;
		}
		
		super.solve();
		
		for (int k = 0; k < L.length; k++) {
			L[k][k] = 1;
		}
		
		double[][] bt = MatrixUtility.transpose(new double[][]{b});
		double[][] Pb = MatrixUtility.matrixProduct(P, bt);
		
		z = MatrixUtility.progresiveSustitution(L, MatrixUtility.getColumn(Pb, 0)); 
		result.put(ELUResults.Z.toString(), MatrixUtility.vector2Json(z));
		
		x = MatrixUtility.regresiveSustitution(U, z);
		result.put(ELUResults.X.toString(), MatrixUtility.vector2Json(x));
		
		return this.result;
	}
	
	@Override
	protected double[][] kIteration(double[][] Ab, int k)
			throws DivisionByZeroException {
		Ab = super.kIteration(Ab, k);
		U = MatrixUtility.decomposeMatrix2A(Ab);
		JSONObject step = new JSONObject();
		step.put(ELUResults.U.toString(), 
				MatrixUtility.matrix2Json(U));
		
		step.put(ELUResults.L.toString(),
				MatrixUtility.matrix2Json(L));
		
		step.put(ELUResults.P.toString(),
				MatrixUtility.matrix2Json(P));
		
		result.accumulate(ELUResults.Steps.toString(), step);
		return Ab;
	}
	
	@Override
	protected double calculateMik(double[][] Ab, int i, int k){
		L[k][i] = super.calculateMik(Ab, i, k);
		return L[k][i];
	}
	
	@Override
	protected void swapRows(double[][] Ab, int k, int i) {
		super.swapRows(Ab, k, i);
		MatrixUtility.swapRows(P, k, i);
		MatrixUtility.swapRows(L, k, i);
	}

	public double[][] getL() {
		return L;
	}

	public double[][] getU() {
		return U;
	}

	public double[][] getP() {
		return P;
	}

	public double[] getX() {
		return x;
	}

	public double[] getZ() {
		return z;
	}

}
