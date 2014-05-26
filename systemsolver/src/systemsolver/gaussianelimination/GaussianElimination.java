package systemsolver.gaussianelimination;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.json.JSONObject;

import systemsolver.LinearSystemMethod;
import systemsolver.MatrixUtility;
import systemsolver.exception.DivisionByZeroException;
import systemsolver.exception.MissingParameterException;

public class GaussianElimination implements LinearSystemMethod {

	private double[][] A;
	private double[] b;
	
	private JSONObject result;

	public JSONObject solve() throws MissingParameterException, DivisionByZeroException {
		checkParameters();
		result = new JSONObject();
		
		double[][] Ab = MatrixUtility.augmentedMatrix(A, b);
		
		for (int i = 0; i < A.length - 1; i++) {
			if(A[i][i] == 0) throw new DivisionByZeroException(i);
			for (int j = i + 1; j < Ab.length; j++) {
				double m = Ab[j][i]/Ab[i][i];
				for (int k = i; k < Ab[j].length; k++) {
					Ab[j][k] -= Ab[i][k] * m;
				}
			}
			result.accumulate("Steps", MatrixUtility.matrix2Json(Ab.clone()));
		}
		
		result.put("Solution", MatrixUtility.vector2Json(regresiveSustitution(Ab)));
		
		return result;
	}
	
	private void checkParameters() throws MissingParameterException{
		ArrayList<EGaussianEliminationParameter> missing = 
				new ArrayList<EGaussianEliminationParameter>(3);
		if(A == null) missing.add(EGaussianEliminationParameter.A);
		if(b == null) missing.add(EGaussianEliminationParameter.b);
		if(missing.size() > 0){
			EGaussianEliminationParameter[] missingArray=
					new EGaussianEliminationParameter[0];
			throw new MissingParameterException(missing.toArray(missingArray));
		}
	}
	
	public void setParameters(JSONObject parameters)
			throws InvalidParameterException {
		if(parameters.has(EGaussianEliminationParameter.A.toString())){
			A = MatrixUtility.json2Matrix(parameters.getJSONArray(
					EGaussianEliminationParameter.A.toString()));
		}
		if(parameters.has(EGaussianEliminationParameter.b.toString())){
			b = MatrixUtility.json2Vector(parameters.getJSONArray(
					EGaussianEliminationParameter.b.toString()));
		}
	}
	
	private double[] regresiveSustitution(double[][] Ab){
		double[] values = new double[Ab.length];
		for(int i = Ab.length -1; i >= 0; i--){
			values[i] = Ab[i][Ab[i].length - 1];
			for (int j = i + 1; j < values.length; j++) {
				values[i] -= Ab[i][j] * values[j];
			}
			values[i] /= Ab[i][i];
		}
		return values;
	}

	
	public double[][] getA() {
		return A;
	}

	public double[] getB() {
		return b;
	}
	
	public JSONObject getResult(){
		return result;
	}
	
}
