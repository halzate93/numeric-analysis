package co.edu.eafit.solver.lib.systemsolver.gaussianelimination;

import java.util.ArrayList;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.LinearSystemMethod;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;

public class GaussianElimination implements LinearSystemMethod {

	private double[][] A;
	private double[] b;
	private EPivotingStrategy ps = EPivotingStrategy.None;
	private int[] marks;
	
	private JSONObject result;

	public JSONObject solve() throws MissingParameterException, DivisionByZeroException {
		checkParameters();
		result = new JSONObject();
		
		double[][] Ab = MatrixUtility.augmentedMatrix(A, b);
		
		if(ps == EPivotingStrategy.Total){
			marks = new int[A.length];
			for (int i = 0; i < marks.length; i++) {
				marks[i] = i;
			}
		}
		
		for (int k = 0; k < A.length - 1; k++) {
			switch (ps) {
			case Partial:
				Ab = partialPivoting(Ab, k);
				break;
			case Total:
				Ab = totalPivoting(Ab, k);
				break;
			default:
				break;
			}
			
			if(A[k][k] == 0) throw new DivisionByZeroException(k);
			for (int i = k + 1; i < Ab.length; i++) {
				double m = Ab[i][k]/Ab[k][k];
				for (int j = k; j < Ab[i].length; j++) {
					Ab[i][j] -= Ab[k][j] * m;
				}
			}
			result.accumulate("Steps", MatrixUtility.matrix2Json(Ab.clone()));
		}
		
		result.put("Solution", MatrixUtility.vector2Json(regresiveSustitution(Ab)));
		
		return result;
	}
	
	private double[][] partialPivoting(double[][] Ab, int k) {
		int i = k;
		double champ = Math.abs(Ab[k][k]);
		for (int s = k+1; s < Ab.length; s++) {
			if(Math.abs(Ab[s][k]) > champ){
				i = s;
				champ = Math.abs(Ab[s][k]);
			}
		}
		
		if(i != k){
			double[] aux = Ab[i];
			Ab[i] = Ab[k];
			Ab[k] = aux;
		}
		
		return Ab;
	}
	
	private double[][] totalPivoting(double[][] Ab, int k) {
		int i = k, j = k;
		double champ = Math.abs(Ab[k][k]);
		for (int s = k; s < Ab.length; s++) {
			for (int l = k; l < Ab[s].length - 1; l++) {
				if(Math.abs(Ab[s][l]) > champ){
					i = s;
					j = l;
					champ = Math.abs(Ab[s][l]);
				}
			}
		}
		
		if(i != k){
			double[] aux = Ab[i];
			Ab[i] = Ab[k];
			Ab[k] = aux;
		}
		
		if(j != k){
			double aux;
			for (int s = 0; s < Ab.length; s++) {
				aux = Ab[s][j];
				Ab[s][j] = Ab[s][k];
				Ab[s][k] = aux;
			}
			
			int aux2 = marks[j];
			marks[j] = marks[k];
			marks[k] = aux2;
		}
		
		return Ab;
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
			throws BadParameterException {
		try{
			if(parameters.has(EGaussianEliminationParameter.A.toString())){
				A = MatrixUtility.json2Matrix(parameters.getJSONArray(
						EGaussianEliminationParameter.A.toString()));
			}
			if(parameters.has(EGaussianEliminationParameter.b.toString())){
				b = MatrixUtility.json2Vector(parameters.getJSONArray(
						EGaussianEliminationParameter.b.toString()));
			}
			if(parameters.has(EGaussianEliminationParameter.Strategy.toString())){
				ps = EPivotingStrategy.valueOf(parameters.getString(
						EGaussianEliminationParameter.Strategy.toString()));
			}
		}catch(Exception e){
			throw new BadParameterException(e);
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
		
		if(ps == EPivotingStrategy.Total){
			double[] aux = values.clone();
			for (int i = 0; i < aux.length; i++) {
				values[marks[i]] = aux[i];
			}
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

	public EPivotingStrategy getPivotingStrategy() {
		return ps;
	}
	
}
