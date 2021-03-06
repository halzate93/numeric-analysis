package co.edu.eafit.solver.lib.systemsolver.lufactorization;

public class DolittleFactorization extends LUFactorization {

	@Override
	protected void kIteration(int k) {
		double acum = 0;
		for (int p = 0; p < k; p++) {
			acum += L[k][p] * U[p][k];
		}
		U[k][k] = A[k][k] - acum;
	}

	@Override
	protected void initialSetup() {
		for (int i = 0; i < L.length; i++) {
			L[i][i] = 1;
		}
	}

}
