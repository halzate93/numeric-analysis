package co.edu.eafit.solver.lib.systemsolver.iterative;

public class Jacobi extends IterativeSystemMethod {

	@Override
	protected double[] nextStep(double[] oldX) {
		double [] newX = new double[oldX.length];
		for (int j = 0; j < newX.length; j++) {
            double acum = b[j];
            
            for(int k = 0; k < j; k++){
                acum += -A[j][k] * oldX[k];
            }
            for(int k = j + 1; k < oldX.length; k++){
                acum += -A[j][k] * oldX[k];
            }
            
            newX[j] = lambda*acum/A[j][j] + (1-lambda)*oldX[j];
        }
		return newX;
	}

}
