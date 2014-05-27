package co.edu.eafit.solver.lib.test.systemsolver.gaussianelimination;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EGaussianEliminationParameter;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EPivotingStrategy;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;

public class TotalPivotingTest {

	private static final double[][] A = {{-7, 2, -3, 4},
										 {5, -1, 14, -1},
										 {1, 9, -7, 13},
										 {-12, 13, -8, -4}};
	private static final double[] b = {-12, 13, 31, -32};
	private static final double[][] answer = {{14, -1, -1, 5, 13},
				 							  {0, 12.5, 8.5, 3.5, 37.5},
				 							  {0, 0, 15.53714286, -7.862857143, -10.85714286},
				 							  {0, 0, 0, -7.387642516, -21.1224715}};
	private static final double[] xValues = {2.859162643, 0.748145567, 0.081644845, 1.690695475};
	
	private GaussianElimination gaussianElimination;
	
	@Before
	public void setUp() throws Exception {
	JSONObject parameters = new JSONObject();
		parameters.put(EGaussianEliminationParameter.A.toString(), 
				MatrixUtility.matrix2Json(A));
		parameters.put(EGaussianEliminationParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		parameters.put(EGaussianEliminationParameter.Strategy.toString(),
				EPivotingStrategy.Total.toString());
		
		gaussianElimination = new GaussianElimination();
		gaussianElimination.setParameters(parameters);
	}
	
	@Test
	public void strategyConfigurationTest() throws BadParameterException {
		JSONObject ps = new JSONObject();
		ps.put(EGaussianEliminationParameter.Strategy.toString(), EPivotingStrategy.Total.toString());
		
		gaussianElimination.setParameters(ps);
		assertEquals(EPivotingStrategy.Total.toString(), gaussianElimination.getPivotingStrategy().toString());
	}
	
	@Test
	public void totalPivotingTest() throws MissingParameterException, DivisionByZeroException{
		gaussianElimination.solve();
		
		double[][] solution = MatrixUtility.json2Matrix(
				gaussianElimination.getResult().getJSONArray("Steps")
				.getJSONArray(A.length - 2));
		
		assertTrue(MatrixUtility.compareMatrix(solution, answer, 0.00000001));
	}
	
	@Test
	public void regresiveSustitutionTest() throws MissingParameterException, DivisionByZeroException{
		gaussianElimination.solve();
		
		double[] solution = MatrixUtility.json2Vector(
				gaussianElimination.getResult().getJSONArray("Solution"));
		
		assertTrue(MatrixUtility.compareVector(solution, xValues, 0.000000001));
	}

}
