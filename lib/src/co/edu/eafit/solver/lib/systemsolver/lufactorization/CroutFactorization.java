package co.edu.eafit.solver.lib.systemsolver.lufactorization;

public class CroutFactorization extends LUFactorization {

	@Override
	protected void kIteration(int k) {
		double acum1 = 0;
		for (int p = 0; p < k; p++) {
			acum1 += L[k][p] * U[p][k];
		}
		L[k][k] = A[k][k] - acum1;
		
		
		for (int i = k + 1; i < A.length; i++) {
			double acum2 = 0;
			for (int p = 0; p < k; p++) {
				acum2 += L[i][p]*U[p][k];
			}
			L[i][k] = (A[i][k] - acum2)/U[k][k];
		}
		
		for (int j = k + 1; j < A.length; j++) {
			double acum3 = 0;
			for (int p = 0; p < k; p++) {
				acum3 += L[k][p]*U[p][j];
			}
			U[k][j] = (A[k][j] - acum3)/L[k][k];
		}	
	}

	@Override
	protected void initialSetup() {
		for (int i = 0; i < U.length; i++) {
			U[i][i] = 1;
		}
	}

}
