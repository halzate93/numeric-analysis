package co.edu.eafit.solver.pc;

import java.util.Scanner;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.rootfinding.control.Solver;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.pc.configurators.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int method = 0;
		Scanner sc = new Scanner(System.in);
		if(args.length == 0){
			method = -1;
			while(method == -1){
				System.out.println("Which method do you want to use?");
				for(EMethod m : EMethod.values()){
					System.out.println(m.ordinal() + ": " + m.toString());
				}
				method = sc.nextInt();
			}
		}else{
			method = Integer.parseInt(args[0]);
		}
		EMethod methodEnum = EMethod.values()[method];
		
		try {
			run(methodEnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}
	
	private static void run(EMethod method) throws Exception {
		
		Solver s = new Solver();
		s.setMethodPrototype(method);
		
		MethodConfigurator cf = new InputReaderConfigurator();
		
		JSONObject params = cf.getConfiguration(s.getRequiredParameters());
		
		boolean ready = false;
		while(!ready){
			try{
				s.setMethodParams(params);
				ready = true;
			} catch(InvalidParameterException e){
				e.printStackTrace();
			}
		}
		
		s.solve();
		System.out.println(s.getLastResult().toString(2));
	}

}
