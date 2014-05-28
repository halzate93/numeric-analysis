package co.edu.eafit.solver.lib.integrals;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.MissingParameterException;
import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;

public class Simpson13 extends Integral {

	@Override
	protected double evaluate() throws Exception {
		return simpson13(0, points.length/2, points.length - 1);
	}
	
	protected double simpson13(int i, int j, int k) throws Exception{
		return ((points[k][0] - points[j][0])/3)*(points[i][1] + 4 * points[j][1] + points[k][1]);
	}
	
	@Override
	protected void checkParameters() throws MissingParameterException, OutOfRangeException{
		super.checkParameters();
		if(points.length < 3 || points.length%2 != 1){
			throw new MissingParameterException(new EInterpolationParameter[]{EInterpolationParameter.Points});
		}
	}

}
