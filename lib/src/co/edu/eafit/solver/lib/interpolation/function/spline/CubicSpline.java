package co.edu.eafit.solver.lib.interpolation.function.spline;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class CubicSpline extends Spline {

	private double value = 0;
	private ESplineType type = ESplineType.Natural;
	
	public CubicSpline(){
		n = 4;
	}
	
	@Override
	protected void conformSystem() throws Exception{
		fillFEquations();
		fillFirstDEquations();
		fillSecondDEquations();
		switch (type) {
		case FixedBorder:
			fixedFilling();
			break;
		case Natural:
			naturalFilling();
			break;
		default:
			throw new Exception();
		}
	}
	
	private void fillFEquations() {
		A = new double[4 * (points.length - 1)][4 * (points.length - 1)];
		b = new double[4 * (points.length - 1)];
		
		// First
		for (int j = 0; j < 4; j++) {
			A[0][j] = Math.pow(points[0][0], 3 - j);
		}
		b[0] = points[0][1];
		
		// Connection points
		for (int i = 0; i < points.length - 2; i++) {
			for (int j = 0; j < 4; j++) {
				A[2*i + 1][(i) * 4 + j] = Math.pow(points[i + 1][0], 3 - j);
				A[2*i + 2][(i + 1) * 4 + j] = Math.pow(points[i + 1][0], 3 - j);;
			}
			b[2*i + 1] =points[i+1][1];
			b[2*i + 2] =points[i+1][1];
		}
		
		// Last
		for (int j = 0; j < 4; j++) {
			A[(points.length - 2)*2 + 1][(points.length - 2) * 4 + j] = Math.pow(points[points.length - 1][0], 3 - j);
		}
		b[(points.length - 2)*2 + 1] = points[points.length - 1][1];
		
	}

	private void fillFirstDEquations() {
		// Connection points
		for (int i = 0; i < points.length - 2; i++) {
			for (int j = 0; j < 3; j++) {
				A[((points.length - 1) * 2) + i][(i) * 4 + j] = (3 - j) * Math.pow(points[i + 1][0], 2 - j);
				A[((points.length - 1) * 2) + i][(i + 1) * 4 + j] = -(3 - j) * Math.pow(points[i + 1][0], 2 - j);
			}
			b[((points.length - 1) * 2) + i] = 0;
		}
	}
	
	private void fillSecondDEquations() {
		// Connection points
		for (int i = 0; i < points.length - 2; i++) {
			
			A[(points.length * 2) + i][(i) * 4] = 6 * points[i + 1][0];
			A[(points.length * 2) + i][(i) * 4 + 1] = 2;
			
			A[(points.length * 2) + i][(i + 1) * 4] = -6 * points[i + 1][0];
			A[(points.length * 2) + i][(i + 1) * 4 + 1] = -2;
			
			b[(points.length * 2) + i] = 0;
		}
	}
	
	private void naturalFilling(){
		A[A.length - 2][0] = 1;
		b[b.length - 2] = 0;
		A[A.length - 1][A.length - 4] = 1;
		b[b.length - 1] = 0;
	}
	
	private void fixedFilling(){
		for (int j = 0; j < 3; j++) {
			A[A.length - 2][j] = (3 - j) * Math.pow(points[0][0], 2 - j);
			A[A.length - 1][A.length - 4 + j] = (3 - j) * Math.pow(points[points.length - 1][0], 2 - j);
		}
		
		b[b.length - 2] = value;
		b[b.length - 1] = value;
		
	}

	@Override
	public void setParameters(JSONObject parameters)
			throws BadParameterException {
		super.setParameters(parameters);
		try{ 
			if(parameters.has(EInterpolationParameter.SplineType.toString())){
				type = ESplineType.valueOf(
						parameters.getString(EInterpolationParameter.SplineType.toString()));
			}
			if(parameters.has(EInterpolationParameter.FixedValue.toString())){
				value = parameters.getDouble(EInterpolationParameter.FixedValue.toString());
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}

	public ESplineType getType() {
		return type;
	}

}
