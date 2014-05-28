package co.edu.eafit.solver.lib.interpolation.function;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.InterpolationMethod;
import co.edu.eafit.solver.lib.interpolation.MissingParameterException;
import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public abstract class FunctionPolinomyInterpolation extends InterpolationMethod {
	
	protected double x, y;
	protected double[] p;
	
	@Override
	public JSONObject run() throws Exception {
		p = calculatePolinomy();
		y = evaluate();
		
		JSONObject result = new JSONObject();
		result.put(EFunctionInterpolationResult.P.toString(),
				MatrixUtility.vector2Json(p));
		result.put(EFunctionInterpolationResult.Y.toString(), y);
	
		return result;
	}
	protected abstract double evaluate() throws OutOfRangeException;
	protected abstract double[] calculatePolinomy() throws Exception;

	@Override
	public void setParameters(JSONObject parameters) throws BadParameterException{
		super.setParameters(parameters);
		try{
			if(parameters.has(EInterpolationParameter.X.toString())){
				x = parameters.getDouble(EInterpolationParameter.X.toString());
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}
	
	@Override
	protected void checkParameters() throws MissingParameterException, OutOfRangeException{
		super.checkParameters();
		if(points[0][0] > x || points[points.length - 1][0] < x){
			throw new OutOfRangeException(x, points[0][0], points[points.length - 1][0]);
		}
	}

	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double[] getP() {
		return p;
	}

	
}
