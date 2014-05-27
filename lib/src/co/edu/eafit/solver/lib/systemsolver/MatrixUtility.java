package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONArray;

public class MatrixUtility {
	public static double[][] json2Matrix(JSONArray jsonMatrix) {
		double[][] matrix = new double[jsonMatrix.length()][jsonMatrix.getJSONArray(0).length()];
		for (int i = 0; i < jsonMatrix.length(); i++) {
			JSONArray row = jsonMatrix.getJSONArray(i);
			for (int j = 0; j < row.length(); j++) {
				matrix[i][j] = row.getDouble(j);
			}
		}
		return matrix;
	}

	public static double[] json2Vector(JSONArray jsonArray) {
		double[] vector = new double[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			vector[i] = jsonArray.getDouble(i);
		}
		return vector;
	}

	public static double[][] augmentedMatrix(double[][] A, double[] b) {
		double[][] Ab = new double[A.length][A[0].length +1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				Ab[i][j] = A[i][j];
			}
			Ab[i][A[0].length] = b[i];
		}
		return Ab;
	}

	public static JSONArray matrix2Json(double[][] matrix) {
		JSONArray jsonMatrix = new JSONArray();
		for(double[] row : matrix){
			jsonMatrix.put(vector2Json(row));
		}
		return jsonMatrix;
	}

	public static JSONArray vector2Json(double[] vector) {
		JSONArray jsonArray = new JSONArray();
		for(double v : vector){
			jsonArray.put(v);
		}
		return jsonArray;
	}
	
	public static boolean compareMatrix(double[][] m, double[][] n, double tolerance){
		boolean equals = m.length == n.length;
		for (int i = 0; equals && i < n.length; i++) {
			equals = compareVector(m[i], n[i], tolerance);
		}
		return equals;
	}
	
	public static boolean compareVector(double[] m, double[] n, double tolerance){
		boolean equals = m.length == n.length;
		for (int i = 0; equals && i < n.length; i++) {
			equals = Math.abs(m[i]-n[i]) < tolerance;
		}
		return equals;
	}

	public static void printVector(double[] vector){
		for (double d : vector) {
			System.out.print(d + " ");
		}
		System.out.println();
	}
	
	public static void printMatrix(double[][] matrix){
		for (double[] ds : matrix) {
			printVector(ds);
		}
	}
}
