package co.edu.eafit.solver.lib.interpolation.function;

import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class NevilleInterpolation extends FunctionInterpolation {

	private double[][] n;
	private boolean[][] done;
	
	@Override
	protected double evaluate() {
		return n(0, points.length - 1);
	}

	@Override
	protected double[] calculatePolinomy() throws Exception {
		n = new double[points.length][points.length];
		done = new boolean[points.length][points.length];
		
		n(0, points.length - 1);
		return MatrixUtility.getColumn(n, 0);
	}

	private double n(int i, int j){
		if(!done[j][j - i]){
			if(i == j){
				n[j][j - i] = points[i][1];
			}else{
				n[j][j - i] = ((x - points[i][0]) * n(i + 1, j) - (x - points[j][0]) * n(i, j - 1))
						/ (points[j][0] - points[i][0]);
			}
			done[j][j - i] = true;
		}
		return n[j][j - i];
	}

	public double[][] getN() {
		return n;
	}
	
}
