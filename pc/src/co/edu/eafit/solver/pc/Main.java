package co.edu.eafit.solver.pc;

import java.util.Scanner;

import co.edu.eafit.solver.lib.methods.EMethod;
import co.edu.eafit.solver.pc.interpreters.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int method = 0;
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
			run(sc, methodEnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void run(Scanner sc, EMethod method) throws Exception{
		Interpreter i = null;
		switch (method) {
		case IncrementalSearch:
			i = new IncrementalSearchInterpreter(sc, EMethod.IncrementalSearch);
			break;

		default:
			break;
		}
		if(i != null) i.run();
	}

}
