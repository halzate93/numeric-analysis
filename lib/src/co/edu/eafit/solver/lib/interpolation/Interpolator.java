package co.edu.eafit.solver.lib.interpolation;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.interpolation.function.LagrangeInterpolation;
import co.edu.eafit.solver.lib.interpolation.function.NevilleInterpolation;
import co.edu.eafit.solver.lib.interpolation.function.NewtonWithDiffInterpolation;
import co.edu.eafit.solver.lib.interpolation.function.SystemInterpolation;
import co.edu.eafit.solver.lib.interpolation.function.spline.CubicSpline;
import co.edu.eafit.solver.lib.interpolation.function.spline.LinearSpline;
import co.edu.eafit.solver.lib.interpolation.function.spline.QuadraticSpline;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class Interpolator {
	private InterpolationMethod method;
	
	public void setMethod(EInterpolationMethod m) throws Exception{
		switch (m) {
		case CubicSpline:
			method = new CubicSpline();
			break;
		case Lagrange:
			method = new LagrangeInterpolation();
			break;
		case LinealSpline: 
			method = new LinearSpline();
			break;
		case Neville:
			method = new NevilleInterpolation();
			break;
		case Newton:
			method = new NewtonWithDiffInterpolation();
			break;
		case QuadraticSpline:
			method = new QuadraticSpline();
			break;
		case SystemEquation:
			method = new SystemInterpolation();
			break;
		default:
			throw new Exception("Invalid method");
		}
	}
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		method.setParameters(parameters);
	}
	
	public JSONObject interpolate() throws Exception{
		return method.interpolate();
	}
}
