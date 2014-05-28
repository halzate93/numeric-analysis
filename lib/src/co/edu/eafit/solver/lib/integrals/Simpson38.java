package co.edu.eafit.solver.lib.integrals;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.MissingParameterException;
import co.edu.eafit.solver.lib.interpolation.OutOfRangeException;

public class Simpson38 extends Integral{

	@Override
	protected double evaluate() throws Exception {
		return simpson38(0, points.length/3, 2*points.length/3, points.length - 1);
	}
	
	protected double simpson38(int i, int j, int k, int l) throws Exception{
		return (3*(points[k][0] - points[j][0])/8)*(points[i][1] + 3 * points[j][1] + 3 * points[k][1] + points[l][1]);
	}
	
	@Override
	protected void checkParameters() throws MissingParameterException, OutOfRangeException{
		super.checkParameters();
		if(points.length < 4 || points.length%2 != 0){
			throw new MissingParameterException(new EInterpolationParameter[]{EInterpolationParameter.Points});
		}
	}
}
