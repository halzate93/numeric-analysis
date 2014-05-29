package shell;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.derivatives.Derivative;
import co.edu.eafit.solver.lib.integrals.EIntegrationMethod;
import co.edu.eafit.solver.lib.integrals.Integrator;
import co.edu.eafit.solver.lib.interpolation.EInterpolationMethod;
import co.edu.eafit.solver.lib.interpolation.Interpolator;
import co.edu.eafit.solver.lib.rootfinding.NonLinearEquationSolver;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EEquationSolvingMethod;
import co.edu.eafit.solver.lib.systemsolver.EEquationSystemMethod;
import co.edu.eafit.solver.lib.systemsolver.EquationSystemSolver;

public class Shell {
	
	public static void main(String[] args) throws Exception {
		if(args.length != 2){
			throw new Exception("Invalid Parameters");
		}
		int selection = Integer.parseInt(args[0]);
		System.out.println(selection);
		String file = readFile(args[1], Charset.defaultCharset());
		JSONObject input = new JSONObject(file);
		Component component = Component.values()[selection];
		switch (component) {
		case RootFinding:
			rootFinding(input);
			break;
		case SystemSolving:
			systemSolving(input);
			break;
		case Interpolator:
			interpolate(input);
			break;
		case Derivative:
			derivate(input);
			break;
		case Integral:
			integrate(input);
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}
	}

	private static void integrate(JSONObject input) throws JSONException, Exception {
		Integrator integrator = new Integrator();
		integrator.setMethod(EIntegrationMethod.valueOf(
				input.getString("Method")));
		integrator.setParameters(input);
		System.out.println(integrator.integrate().toString(2));
	}

	private static void derivate(JSONObject input) throws JSONException, Exception {
		Derivative derivative = new Derivative();
		derivative.setParameters(input);
		System.out.println(derivative.solve().toString(2));
	}

	private static void interpolate(JSONObject input) throws JSONException, Exception {
		Interpolator interpolator = new Interpolator();
		interpolator.setMethod(EInterpolationMethod.valueOf(
				input.getString("Method")));
		interpolator.setParameters(input);
		System.out.println(interpolator.interpolate().toString(2));
	}

	private static void systemSolving(JSONObject input) throws JSONException, Exception {
		EquationSystemSolver system = new EquationSystemSolver();
		system.setMethod(EEquationSystemMethod.valueOf(
				input.getString("Method")));
		system.setParameters(input);
		System.out.println(system.solve().toString(2));
	}

	private static void rootFinding(JSONObject input) throws Exception {
		NonLinearEquationSolver root = new NonLinearEquationSolver();
		root.setMethodPrototype(EEquationSolvingMethod.valueOf(
				input.getString("Method")));
		root.setMethodParams(input);
		System.out.println(root.solve().toString(2));
	}

	private static String readFile(String path, Charset encoding) 
			  throws IOException  {
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}
	
}
