package co.edu.eafit.solver.lib.test.integrals;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.integrals.Simpson38;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class Simpson38Test {

	private double[][] points = {{1, 0.718281828}, {1.33, 1.1210433876},
								 {1.67, 1.9721677972}, {2, 3.3890560989}};
	private double y = 1.706839;
	
	private Simpson38 s38;
	@Before
	public void setUp() throws BadParameterException {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));

		s38 = new Simpson38();
		s38.setParameters(parameters);
	}
	
	@Test
	public void testRun() throws Exception {
		s38.interpolate();
	}
	
	@Test
	public void testSolution() throws Exception {
		s38.interpolate();
		assertEquals(s38.getValue(), y, 0.000001);
	}

}
