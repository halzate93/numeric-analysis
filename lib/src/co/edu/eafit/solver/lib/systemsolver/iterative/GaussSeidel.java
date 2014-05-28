package co.edu.eafit.solver.lib.systemsolver.iterative;

public class GaussSeidel extends IterativeSystemMethod {

	@Override
	protected double[] nextStep(double[] oldX) {
		double[] newX = new double[oldX.length];
		for (int j = 0; j < newX.length; j++) {
            double acum = b[j];
            
            int k = 0;
            while(k < j){
                acum += -A[j][k] * newX[k];
                k++;
            }
            
            k++;
            while(k < oldX.length){
                acum += -A[j][k] * oldX[k];
                k++;
            }
            
            newX[j] = lambda*acum/A[j][j] + (1-lambda)*oldX[j];
        }
		return newX;
	}

}
