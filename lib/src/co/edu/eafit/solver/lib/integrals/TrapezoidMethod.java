package co.edu.eafit.solver.lib.integrals;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.MissingParameterException;
import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;

public class TrapezoidMethod extends Integral {

	@Override
	protected double evaluate() {
		return trapezoidArea(0, points.length - 1); 
	}

	protected double trapezoidArea(int a, int b) {
		return ((points[b][0] - points[a][0])/2)*(points[b][1] + points[a][1]);
	}
	
	@Override
	protected void checkParameters() throws MissingParameterException, OutOfRangeException{
		super.checkParameters();
		if(points.length < 2){
			throw new MissingParameterException(new EInterpolationParameter[]{EInterpolationParameter.Points});
		}
	}

}
