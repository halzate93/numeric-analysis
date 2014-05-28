package co.edu.eafit.solver.lib.interpolation.function;

public class NewtonWithDiff extends FunctionInterpolation {

	private double[][] f;
	private boolean[][] done;
	
	@Override
	protected double evaluate() {
		double acum = 0;
		for (int i = 0; i < p.length; i++) {
			double prod = p[i];
			for (int j = 0; j < i; j++) {
				prod *= (x - points[j][0]);
			}
			acum += prod;
		}
		return acum;
	}

	@Override
	protected double[] calculatePolinomy() throws Exception {
		f = new double[points.length][points.length];
		done = new boolean[points.length][points.length];
		double[] p = new double[points.length];
		
		for (int k = 0; k < f.length; k++) {
			p[k] = f(0, k);
		}
		return p;
	}
	
	private double f(int i, int j){
		if(!done[j][j - i]){
			if(i == j){
				f[j][0] = points[j][1];
			}else{
				f[j][j - i] = (f(i + 1, j) - f(i, j - 1)) / (points[j][0] - points[i][0]); 
			}
			done[j][j - i] = true;
		}
		return f[j][j - i];
	}

	public double[][] getF() {
		return f;
	}
	
}
