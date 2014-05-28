package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.NevilleInterpolation;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class NevilleTest {

	private static double[][] points = {{1, 0.674732}, {1.2, 0.849196},
										{1.4, 1.121408}, {1.6, 1.492136},
										{1.8, 1.960736}, {2, 2.525897}};
	private static double x = 1.45;
	
	private static double[][] n = {{0.674732, 0, 0, 0, 0, 0},
								   {0.849196, 1.067276, 0, 0, 0, 0},
								   {1.121408, 1.189460, 1.204734, 0, 0, 0},
								   {1.492136, 1.214090, 1.204854, 1.204824, 0, 0},
								   {1.960736, 1.140686, 1.204914, 1.204879, 1.204855, 0},
								   {2.525897, 0.971703, 1.204055, 1.204843, 1.204868, 1.204861}};
	
	private static double y = 1.2048606287;
	private NevilleInterpolation nvl;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		parameters.put(EInterpolationParameter.X.toString(), x);
		
		nvl = new NevilleInterpolation();
		nvl.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		nvl.interpolate();
	}

	@Test
	public void testN() throws Exception{
		nvl.interpolate();
		assertTrue(MatrixUtility.compareMatrix(n, nvl.getN(), 0.00001));
	}
	
	@Test
	public void testSolution() throws Exception{
		nvl.interpolate();
		assertEquals(y, nvl.getY(), 0.000001);
	}
}
