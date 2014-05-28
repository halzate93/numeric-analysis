package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;
import co.edu.eafit.solver.lib.systemsolver.iterative.GaussSeidel;
import co.edu.eafit.solver.lib.systemsolver.iterative.Jacobi;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.CholeskyFactorization;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.CroutFactorization;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.DolittleFactorization;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.GaussianPartialPivotFactorization;


public class EquationSystemSolver {
	private LinearSystemMethod method;
	
	public void setMethod(EEquationSystemMethod methodEnum) {
		switch (methodEnum) {
		case GaussianElimination:
			method = new GaussianElimination();
			break;
		case GaussianFactorization:
			method = new GaussianPartialPivotFactorization();
			break;
		case CholeskyFactorization:
			method = new CholeskyFactorization();
			break;
		case CroutFactorization:
			method = new CroutFactorization();
			break;
		case DolittleFactorization:
			method = new DolittleFactorization();
			break;
		case GaussSeidel:
			method = new GaussSeidel();
			break;
		case Jacobi:
			method = new Jacobi();
			break;
		default:
			break;
		}
	}
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		method.setParameters(parameters);
	}
	
	public JSONObject solve() throws Exception{
		return method.solve();
	}
}
