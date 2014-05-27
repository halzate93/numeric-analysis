package co.edu.eafit.solver.lib.systemsolver.lufactorization;

public class CroutFactorization extends LUFactorization {

	@Override
	protected void kIteration(int k) {
		double acum = 0;
		for (int p = 0; p < k; p++) {
			acum += L[k][p] * U[p][k];
		}
		L[k][k] = A[k][k] - acum;
	}

	@Override
	protected void initialSetup() {
		for (int i = 0; i < U.length; i++) {
			U[i][i] = 1;
		}
	}

}
