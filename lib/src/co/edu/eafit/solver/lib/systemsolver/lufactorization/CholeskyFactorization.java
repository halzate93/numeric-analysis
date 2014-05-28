package co.edu.eafit.solver.lib.systemsolver.lufactorization;

public class CholeskyFactorization extends LUFactorization {

	@Override
	protected void kIteration(int k) {
		double acum = 0;
		for (int p = 0; p < k; p++) {
			acum += L[k][p] * U[p][k];
		}
		double squared = A[k][k] - acum;
		U[k][k] = Math.sqrt(Math.abs(squared));
		L[k][k] = U[k][k];
		if(squared < 0) L[k][k] *= -1;
	}

	@Override
	protected void initialSetup() {
		
	}

}
