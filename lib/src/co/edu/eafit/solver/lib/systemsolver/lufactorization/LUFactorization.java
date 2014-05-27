package co.edu.eafit.solver.lib.systemsolver.lufactorization;

import java.util.ArrayList;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.LinearSystemMethod;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;

public abstract class LUFactorization implements LinearSystemMethod {

	protected double[][] A, L, U;
	protected double[] b, x, z;
	
	@Override
	public JSONObject solve() throws MissingParameterException,
			DivisionByZeroException, Exception {
		checkParameters();
		L = new double[A.length][A.length];
		U = new double[A.length][A.length];
		initialSetup();
		
		JSONObject result = new JSONObject();
		
		for (int k = 0; k < A.length; k++) {
			kIteration(k);
			fillKLColumn(k);
			fillKURow(k);	
			
			JSONObject step = new JSONObject();
			step.put(ELUResults.L.toString(),
					MatrixUtility.matrix2Json(L));
			step.put(ELUResults.U.toString(),
					MatrixUtility.matrix2Json(U));
			
			result.accumulate(ELUResults.Steps.toString(), step);
		}
		
		z = MatrixUtility.progresiveSustitution(L, b); 
		result.put(ELUResults.Z.toString(), MatrixUtility.vector2Json(z));
		
		x = MatrixUtility.regresiveSustitution(U, z);
		result.put(ELUResults.X.toString(), MatrixUtility.vector2Json(x));
		
		return result;
	}
	
	private void checkParameters() throws MissingParameterException{
		ArrayList<ESystemSolvingParameter> missing = 
				new ArrayList<ESystemSolvingParameter>(3);
		if(A == null) missing.add(ESystemSolvingParameter.A);
		if(b == null) missing.add(ESystemSolvingParameter.b);
		if(missing.size() > 0){
			ESystemSolvingParameter[] missingArray=
					new ESystemSolvingParameter[0];
			throw new MissingParameterException(missing.toArray(missingArray));
		}
	}

	@Override
	public void setParameters(JSONObject parameters)
			throws BadParameterException {
		try{
			if(parameters.has(ESystemSolvingParameter.A.toString())){
				A = MatrixUtility.json2Matrix(parameters.getJSONArray(
						ESystemSolvingParameter.A.toString()));
			}
			if(parameters.has(ESystemSolvingParameter.b.toString())){
				b = MatrixUtility.json2Vector(parameters.getJSONArray(
						ESystemSolvingParameter.b.toString()));
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}
	
	protected abstract void kIteration(int k);
	protected abstract void initialSetup();
	
	protected void fillKURow(int k){
		for (int j = k + 1; j < A.length; j++) {
			double acum = 0;
			for (int p = 0; p < k; p++) {
				acum += L[k][p]*U[p][j];
			}
			U[k][j] = (A[k][j] - acum)/L[k][k];
		}
	}
	
	protected void fillKLColumn(int k){
		for (int i = k + 1; i < A.length; i++) {
			double acum = 0;
			for (int p = 0; p < k; p++) {
				acum += L[i][p]*U[p][k];
			}
			L[i][k] = (A[i][k] - acum)/U[k][k];
		}
	}

	public double[][] getA() {
		return A;
	}

	public double[][] getL() {
		return L;
	}

	public double[][] getU() {
		return U;
	}

	public double[] getB() {
		return b;
	}

	public double[] getX() {
		return x;
	}

	public double[] getZ() {
		return z;
	}

}
