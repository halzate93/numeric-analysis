package co.edu.eafit.solver.lib.test.systemsolver.gaussianelimination;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EGaussianEliminationResult;
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
		parameters.put(ESystemSolvingParameter.A.toString(), 
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		parameters.put(ESystemSolvingParameter.Strategy.toString(),
				EPivotingStrategy.Total.toString());
		
		gaussianElimination = new GaussianElimination();
		gaussianElimination.setParameters(parameters);
	}
	
	@Test
	public void strategyConfigurationTest() throws BadParameterException {
		JSONObject ps = new JSONObject();
		ps.put(ESystemSolvingParameter.Strategy.toString(), EPivotingStrategy.Total.toString());
		
		gaussianElimination.setParameters(ps);
		assertEquals(EPivotingStrategy.Total.toString(), gaussianElimination.getPivotingStrategy().toString());
	}
	
	@Test
	public void totalPivotingTest() throws Exception{
		JSONObject result = gaussianElimination.solve();
		
		double[][] solution = MatrixUtility.json2Matrix(
				result.getJSONArray(EGaussianEliminationResult.Steps.toString())
				.getJSONArray(A.length - 2));
		
		assertTrue(MatrixUtility.compareMatrix(solution, answer, 0.00000001));
	}
	
	@Test
	public void regresiveSustitutionTest() throws Exception{
		JSONObject result = gaussianElimination.solve();
		
		double[] solution = MatrixUtility.json2Vector(
				result.getJSONArray(EGaussianEliminationResult.X.toString()));
		
		assertTrue(MatrixUtility.compareVector(solution, xValues, 0.000000001));
	}
	
	@Test
	public void test() throws MissingParameterException, DivisionByZeroException, Exception{
		double [][] matrix = {{2, -4, 1},
				{3, 2, 5},
				{4, 3, 5}};
		double[] b = {-1, -2, 4};
		
		JSONObject params = new JSONObject();
		params.put(ESystemSolvingParameter.A.toString(), MatrixUtility.matrix2Json(matrix));
		params.put(ESystemSolvingParameter.b.toString(), MatrixUtility.vector2Json(b));
		gaussianElimination.setParameters(params);
		
		gaussianElimination.solve();
	}

}
