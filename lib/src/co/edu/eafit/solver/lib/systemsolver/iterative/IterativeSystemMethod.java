package co.edu.eafit.solver.lib.systemsolver.iterative;

import java.util.ArrayList;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.LinearSystemMethod;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;

public abstract class IterativeSystemMethod implements LinearSystemMethod {

	protected double[][] A;
	protected double[] b;
	private double[] initial;
	private double tolerance;
	private int iterations;
	private EVectorErrorType errorType = EVectorErrorType.Absolute;
	private ENorm norm = ENorm.Euclidean;
	protected double lambda = 1;
	
	private double[] x;
	private int i;
	private double error;
	
	@Override
	public JSONObject solve() throws MissingParameterException,
			DivisionByZeroException, Exception {
		checkParameters();
		
		x = initial;
		error = tolerance + 1;
		i = 0;
		double[] nx;
		
		JSONObject result = new JSONObject();
		
		while(error > tolerance && i < iterations){
			nx = nextStep(x);
			error = calculateError(nx, x);
			x = nx;
			i++;
			
			JSONObject step = new JSONObject();
			step.put(EIterativeResult.X.toString(),
					MatrixUtility.vector2Json(x));
			step.put(EIterativeResult.Error.toString(), error);
			step.put(EIterativeResult.I.toString(), i);
			
			result.accumulate(EIterativeResult.Steps.toString(), step);
		}
		
		result.put(EIterativeResult.X.toString(), MatrixUtility.vector2Json(x));
		result.put(EIterativeResult.I.toString(), i);
		result.put(EIterativeResult.Error.toString(), error);
		
		return result;
	}

	private double calculateError(double[] newX, double[] oldX) throws Exception {
		switch (errorType) {
		case Absolute:
			return Math.abs(measure(newX) - measure(oldX));
		case Relative:
			return Math.abs((measure(newX) - measure(oldX)) / measure(newX));
		case Max:
			return maxError(newX, oldX);
		case Euclidean:
			return euclideanError(newX, oldX);
		default:
			throw new Exception();
		}
	}
	
	private double maxError(double[] newX, double[] oldX) throws Exception{
		if(newX.length != oldX.length)
			throw new Exception("Can't calculate error because vectors don't match.");
		
		double max = Math.abs(newX[0] - oldX[0]);
		double err;
		
		for (int i = 0; i < oldX.length; i++) {
			err = Math.abs(newX[i] - oldX[i]);
			if(err > max) max = err;
		}
		return max;
	} 
	
	private double euclideanError(double[] newX, double[] oldX) throws Exception{
		if(newX.length != oldX.length)
			throw new Exception("Can't calculate error because vectors don't match.");
		double[] diff = new double[newX.length];
		for (int i = 0; i < diff.length; i++) {
			diff[i] = newX[i] - oldX[i];
		}
		return euclideanNorm(diff);
	}
	
	private double measure(double[] x) throws Exception{
		switch (norm) {
		case Euclidean:
			return euclideanNorm(x);
		case Infinite:
			return infiniteNorm(x);
		case One:
			return oneNorm(x);
		default:
			throw new Exception();
		}
	}
	
	private double euclideanNorm(double[] x){
		double acum = 0;
		for (int i = 0; i < x.length; i++) {
			acum += Math.pow(x[i], 2);
		}
		return Math.sqrt(acum);
	}
	
	private double infiniteNorm(double[] x){
		double max = x[0];
		for (int i = 1; i < x.length; i++) {
			if(max < x[i]){
				max = x[i];
			}
		}
		return max;
	}
	
	private double oneNorm(double[] x){
		double acum = 0;
		for (int i = 0; i < x.length; i++) {
			acum += Math.abs(x[i]);
		}
		return acum;
	}
	
	protected abstract double[] nextStep(double[] x);

	private void checkParameters() throws MissingParameterException {
		ArrayList<ESystemSolvingParameter> missing = 
				new ArrayList<ESystemSolvingParameter>(3);
		if(A == null) missing.add(ESystemSolvingParameter.A);
		if(b == null) missing.add(ESystemSolvingParameter.b);
		if(initial == null) missing.add(ESystemSolvingParameter.Initial);
		if(tolerance == 0) missing.add(ESystemSolvingParameter.Tolerance);
		if(iterations == 0) missing.add(ESystemSolvingParameter.Iterations);
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
			if(parameters.has(ESystemSolvingParameter.Tolerance.toString())){
				tolerance = parameters.getDouble(ESystemSolvingParameter.Tolerance.toString());
			}
			if(parameters.has(ESystemSolvingParameter.Tolerance.toString())){
				iterations = parameters.getInt(ESystemSolvingParameter.Iterations.toString());
			}
			if(parameters.has(ESystemSolvingParameter.Initial.toString())){
				initial = MatrixUtility.json2Vector(
						parameters.getJSONArray(ESystemSolvingParameter.Initial.toString()));
			}
			if(parameters.has(ESystemSolvingParameter.Error.toString())){
				errorType = EVectorErrorType.valueOf(
						parameters.getString(ESystemSolvingParameter.Error.toString()));
			}
			if(parameters.has(ESystemSolvingParameter.Norm.toString())){
				norm = ENorm.valueOf(
						parameters.getString(ESystemSolvingParameter.Norm.toString()));
			}
			if(parameters.has(ESystemSolvingParameter.Lambda.toString())){
				lambda = parameters.getDouble(ESystemSolvingParameter.Lambda.toString());
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}

	}

	public double[][] getA() {
		return A;
	}

	public double[] getB() {
		return b;
	}

	public double[] getInitial() {
		return initial;
	}

	public double getTolerance() {
		return tolerance;
	}

	public double[] getX() {
		return x;
	}

	public int getIterations() {
		return iterations;
	}

	
	public int getI() {
		return i;
	}
	

	public double getError() {
		return error;
	}

}
