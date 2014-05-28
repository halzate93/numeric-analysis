package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.LagrangeInterpolation;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class LagrangeTest {

	private static double[][] points = {{0.4, 6.219231}, {0.6, 7.271719},
										{0.8, 8.824608}, {1, 11.145858}};
	private static double x = 0.63;
	
	private static double[] p = {-0.039313, 0.904188, 0.159563, -0.024438};
	private static double y = 7.466204;
	
	private LagrangeInterpolation lg;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		parameters.put(EInterpolationParameter.X.toString(), x);
		
		lg = new LagrangeInterpolation();
		lg.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		lg.interpolate();
	}
	
	@Test
	public void testP() throws Exception{
		lg.interpolate();
		assertTrue(MatrixUtility.compareVector(p, lg.getP(), 0.000001));
	}
	
	@Test
	public void testSolution() throws Exception{
		lg.interpolate();
		assertEquals(y, lg.getY(), 0.000001);
	}

}
