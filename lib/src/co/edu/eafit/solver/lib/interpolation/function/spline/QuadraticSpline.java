package co.edu.eafit.solver.lib.interpolation.function.spline;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class QuadraticSpline extends Spline {

	private int rule = 0;
	private double value = 0;
	private ESplineType type = ESplineType.Natural;
	
	@Override
	protected void conformSystem() throws Exception{
		fillFEquations();
		fillFirstDEquations();
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
	
	protected void fillFEquations() {
		A = new double[3 * (points.length - 1)][3 * (points.length - 1)];
		b = new double[3 * (points.length - 1)];
		
		// First
		for (int j = 0; j < 3; j++) {
			A[0][j] = Math.pow(points[0][0], 2 - j);
		}
		b[0] = points[0][1];
		
		// Connection points
		for (int i = 0; i < points.length - 2; i++) {
			for (int j = 0; j < 3; j++) {
				A[2*i + 1][(i) * 3 + j] = Math.pow(points[i + 1][0], 2 - j);
				A[2*i + 2][(i + 1) * 3 + j] = Math.pow(points[i + 1][0], 2 - j);;
			}
			b[2*i + 1] =points[i+1][1];
			b[2*i + 2] =points[i+1][1];
		}
		
		// Last
		for (int j = 0; j < 3; j++) {
			A[(points.length - 2)*2 + 1][(points.length - 2) * 3 + j] = Math.pow(points[points.length - 1][0], 2 - j);
		}
		b[(points.length - 2)*2 + 1] = points[points.length - 1][1];
	}

	protected void fillFirstDEquations() {
		// Connection points
		for (int i = 0; i < points.length - 2; i++) {
			A[((points.length - 1) * 2) + i][(i) * 3] = 2 * points[i + 1][0];
			A[((points.length - 1) * 2) + i][(i) * 3 + 1] = 1;
			A[((points.length - 1) * 2) + i][(i + 1) * 3] = -2 * points[i + 1][0];
			A[((points.length - 1) * 2) + i][(i + 1) * 3 + 1] = -1;
			b[((points.length - 1) * 2) + i] = 0;
		}
	}
	
	private void naturalFilling(){
		A[A.length - 1][3 * rule] = 1;
		b[b.length - 1] = 0;
	}
	
	private void fixedFilling(){
		A[A.length - 1][3 * rule] = 2 * points[rule][0];
		A[A.length - 1][3 * rule + 1] = 1;
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
			if(parameters.has(EInterpolationParameter.Rule.toString())){
				rule = parameters.getInt(EInterpolationParameter.Rule.toString());
			}
			if(parameters.has(EInterpolationParameter.FixedValue.toString())){
				value = parameters.getDouble(EInterpolationParameter.FixedValue.toString());
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}

	public int getRule() {
		return rule;
	}

	public ESplineType getType() {
		return type;
	}

}
