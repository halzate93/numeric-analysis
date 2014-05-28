package co.edu.eafit.solver.lib.interpolation.function;


public class LagrangeInterpolation extends FunctionInterpolation {
	
	@Override
	protected double evaluate() {
		double acum = 0;
		for (int i = 0; i < points.length; i++) {
			acum += points[i][1] * p[i];
		}
		return acum;
	}

	@Override
	protected double[] calculatePolinomy() throws Exception {
		double[] l = new double[points.length];
		for (int i = 0; i < l.length; i++) {
			l[i] = 1;
			for (int p = 0; p < points.length; p++) {
				if(p != i){
					l[i] *= (x - points[p][0])/(points[i][0] - points[p][0]);
				}
			}
		}
		return l;
	}

}
