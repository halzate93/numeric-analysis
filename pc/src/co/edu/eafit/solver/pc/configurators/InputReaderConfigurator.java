package co.edu.eafit.solver.pc.configurators;

import java.util.Scanner;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.methods.enums.EParameter;

/**
 * Generic method configurator, receives a list of all the required parameters
 * and reads them from input, however, it is not guaranteed that the provided
 * parameters are valid nor correct. 
 * @author halzate93
 *
 */
public class InputReaderConfigurator implements MethodConfigurator {

	@Override
	public JSONObject getConfiguration(EParameter[] required) {
		JSONObject parameters = new JSONObject();
		
		Scanner sc = new Scanner(System.in);
		
		for(EParameter p : required){
			System.out.println("Input value for " + p.toString() + ":");
			parameters.put(p.toString(), sc.nextLine());
		}
		
		return parameters;
	}
}
