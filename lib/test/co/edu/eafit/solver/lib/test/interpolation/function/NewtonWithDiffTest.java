package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.EFunctionInterpolationResult;
import co.edu.eafit.solver.lib.interpolation.function.NewtonWithDiffInterpolation;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class NewtonWithDiffTest {

	private static double[][] points = {{0.4, 6.219231}, {0.6, 7.271719},
										{0.8, 8.824608}, {1, 11.145858}};
	private static double x = 0.63;
	
	private static double[][] F = {{6.219231, 0, 0, 0},
								   {7.271719, 5.26244, 0, 0},
								   {8.824608, 7.764445, 6.255013, 0},
								   {11.145858, 11.60625, 9.604513, 5.5825}};
	private static double[] p = {6.219231, 5.26244, 6.255013, 5.5825};
	private static double y = 7.466204;
	private NewtonWithDiffInterpolation nw;
	
	@Before
	public void setUp() throws BadParameterException{
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		parameters.put(EInterpolationParameter.X.toString(), x);
		
		nw = new NewtonWithDiffInterpolation();
		nw.setParameters(parameters);
	}
	
	@Test
	public void testRun() throws Exception {
		nw.interpolate();
	}
	
	@Test
	public void testF() throws Exception{
		nw.interpolate();
		assertTrue(MatrixUtility.compareMatrix(nw.getF(), F, 0.000001));
	}
	
	@Test
	public void testP() throws Exception{
		nw.interpolate();
		assertTrue(MatrixUtility.compareVector(nw.getP(), p, 0.000001));
	}
	
	@Test
	public void testSolution() throws Exception{
		nw.interpolate();
		assertEquals(y, nw.getY(), 0.000001);
	}
	
	@Test
	public void testJSON() throws Exception{
		JSONObject result = nw.interpolate();
		assertTrue(result.has(EFunctionInterpolationResult.F.toString()));
	}

}
