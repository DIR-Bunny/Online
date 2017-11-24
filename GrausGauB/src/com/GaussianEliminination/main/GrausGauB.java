package com.GaussianEliminination.main;

public class GrausGauB extends MiniJava {

	private static int lines = 0;
	public static void main(String[] args) {
		
		/* please give input in line pattern like below 
		*  Please enter coefficient of matrix : 6 2 3 2
		*  Please enter coefficient of matrix : 4 2 2 4
		*  Please enter coefficient of matrix : 3 3 1 0*/
		
		int[][] matrix = readMatrix();
		//printMatrix(matrix);
		//solveSystem(matrix);
		searchSwap(matrix, 0);
	}
	

}
