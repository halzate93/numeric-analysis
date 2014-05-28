package co.edu.eafit.solver.lib.interpolation;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public abstract class InterpolationMethod {
	
	protected double[][] points;
	
	public JSONObject interpolate() throws Exception {
		checkParameters();
		return run();
	}
	
	protected void checkParameters() throws MissingParameterException {
		if(points == null)
			throw new MissingParameterException(
					new EInterpolationParameter[]{EInterpolationParameter.Points});
	}

	public abstract JSONObject run() throws Exception;
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		try{
			if(parameters.has(EInterpolationParameter.Points.toString())){
				double[][] points = MatrixUtility.json2Matrix(
						parameters.getJSONArray(EInterpolationParameter.Points.toString()));;
				if(points.length == 0 || points[0].length != 2)
					throw new Exception("Invalid set of points");
				this.points = points; 
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}
}
