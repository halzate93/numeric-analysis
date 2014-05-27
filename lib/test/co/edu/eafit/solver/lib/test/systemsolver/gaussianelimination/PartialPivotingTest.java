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

public class PartialPivotingTest {

	private static final double[][] A = {{-7, 2, -3, 4},
												{5, -1, 14, -1},
												{1, 9, -7, 5},
												{-12, 13, -8, -4}};
	private static final double[] b = {-12, 13, 31, -32};
	private static final double[][] answer = {{-12, 13, -8, -4, -32},
													 {0, 10.08333333, -7.666666667, 4.666666667, 28.33333333},
													 {0, 0, 14.02479339, -4.710743802, -12.74380165},
													 {0, 0, 0, 8.051266942, 20.01237478}};
	private static final double[] xValues = {3.624387031, 1.603454585, -0.073775891, 2.485618093};
	
	private GaussianElimination gaussianElimination;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EGaussianEliminationParameter.A.toString(), 
				MatrixUtility.matrix2Json(A));
		parameters.put(EGaussianEliminationParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		parameters.put(EGaussianEliminationParameter.Strategy.toString(),
				EPivotingStrategy.Partial.toString());
		
		gaussianElimination = new GaussianElimination();
		gaussianElimination.setParameters(parameters);
	}

	@Test
	public void strategyConfigurationTest() throws BadParameterException {
		JSONObject ps = new JSONObject();
		ps.put(EGaussianEliminationParameter.Strategy.toString(), EPivotingStrategy.Partial.toString());
		
		gaussianElimination.setParameters(ps);
		assertEquals(EPivotingStrategy.Partial.toString(), gaussianElimination.getPivotingStrategy().toString());
	}
	
	@Test
	public void partialPivotingTest() throws MissingParameterException, DivisionByZeroException{
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
