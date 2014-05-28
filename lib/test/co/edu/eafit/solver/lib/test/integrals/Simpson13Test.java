package co.edu.eafit.solver.lib.test.integrals;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.integrals.Simpson13;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class Simpson13Test {

	private double[][] points = {{1, 0.718281828}, {1.5, 1.48168907}, {2, 3.389056099}};
	private double y = 1.6723490345;
	
	private Simpson13 s13;
	@Before
	public void setUp() throws BadParameterException {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));

		s13 = new Simpson13();
		s13.setParameters(parameters);
	}
	
	@Test
	public void testRun() throws Exception {
		s13.interpolate();
	}
	
	@Test
	public void testSolution() throws Exception {
		s13.interpolate();
		assertEquals(s13.getValue(), y, 0.000001);
	}

}
