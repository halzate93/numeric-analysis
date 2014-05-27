package co.edu.eafit.solver.lib.systemsolver.lufactorization;

public class CholeskyFactorization extends LUFactorization {

	@Override
	protected void kIteration(int k) {
		double acum = 0;
		for (int p = 0; p < k; p++) {
			acum += L[k][p] * U[p][k];
		}
		U[k][k] = Math.sqrt(A[k][k] - acum);
		L[k][k] = U[k][k];
		System.out.println(acum);
	}

	@Override
	protected void initialSetup() {
		
	}

}
