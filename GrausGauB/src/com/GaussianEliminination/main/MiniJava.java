package com.GaussianEliminination.main;

import java.util.Scanner;

public class MiniJava {
	
	public static Scanner sc;
	
	//read matrix from input size 3x4
	public static int[][] readMatrix()
	{
		int [][] arr = new int[3][4];
		sc = new Scanner(System.in);
		
		for (int i = 0; i < 3; i++) {
			int count = 0;
			System.out.print("Please enter coefficient of matrix : ");
			String line = sc.nextLine();
			String[] coeffiecient = line.split(" ");
			
			for (String string : coeffiecient) {
				arr[i][count++] = new Integer(string);
			}
		}
		
		
		
		return arr;
		
	}
	
	//print given matrix
	public static void printMatrix(int[][] matrix)
	{
		for (int[] row : matrix) {
			for (int coef : row) {
				System.out.print(coef +" ");
			}
			System.out.println("");
		}
	}
	
	public static int[] get(int[][] matrix, int line,int column)
	{
		int[] arr = new int[4];
		for (int i = 0; i < 4; i++) {
			arr[i] = matrix[line][i];
		}
		return arr;
	}
	
	public static void set(int[][] matrix, int line, int column,int value)
	{
		
		
	}
	
	//Multiply row with factor
	public static void multLine(int[][] matrix, int line,int factor)
	{
		int[] row = matrix[line];
		for (int i = 0; i < row.length; i++) {
			matrix[line][i] = row[i] * factor;
		}
	}
	
	//swap two rows
	public static void swap(int[][] matrix, int lineA, int lineB)
	{
		int tmpRow[] = matrix[lineA];
		matrix[lineA] = matrix[lineB];
		matrix[lineB] = tmpRow;
	}
	
	//search for swap
	public static void searchSwap(int[][] matrix, int fromLine)
	{
		int index= 0,prevCoef = 0;
		for (int i = fromLine; i < 3; i++) {
			prevCoef = matrix[i][index];
			if(prevCoef == 0 )
			{
				for (int j = i+1; j < 3; j++) {
					if(matrix[j][0] != 0 && matrix[i][matrix.length-1] != 0)
					{
						swap(matrix,i,j);
						break;
					}
					
				}
			}
			
		}
		printMatrix(matrix);
	}
	
	//get KGV of given numbers
	public static int kgv(int a, int b)
	{
		int max = Math.max(a, b); //(a > b) ? a : b;
		int min = Math.min(a, b); //(a < b) ? a : b;
		int returnStatus = 0;
		for (int i = 1;; i++) 
		{
			int temp = max * i;
			if((max * i)% min == 0)
				{
					returnStatus = max * i;
					break;
				}
		}
		return returnStatus;
	}
	
	// solve each row
	public static int rowEchelonToResult(int[][] matrix,int row,int[] res)
	{
		int sum = 0;
		for (int j = row+1; j <= matrix.length-1; j++) {
			sum += matrix[row][j] * res[j];
		}
		return (matrix[row][matrix.length] - sum)/matrix[row][row];
	}
	
	//Main function to solve matrix
	public static void solveSystem(int[][] matrix)
	{
		
		for (int j = 0; j < matrix.length-1; j++) {
			for (int i = j; i < matrix.length; i++) {
				if(matrix[i][j] != 0)
						{
							searchSwap(matrix, 0);
							ModifyMatrix(matrix,j);
						}
			}
		}
		printMatrix(matrix);
		
		int[] result = new int[matrix.length];
		result[matrix.length-1] = matrix[matrix.length-1][matrix.length]/matrix[matrix.length-1][matrix.length-1];
		
		for(int i = 1; i >=0 ; i--)
		{
			result[i] = rowEchelonToResult(matrix,i,result);
		}
		
		System.out.println("Results :  x = "+result[0]+"  ,y = "+result[1]+"  ,z = "+result[2]);
	}
	
	//Change matrix to get upper triangular matrix
	public static void ModifyMatrix(int[][] matrix,int col)
	{
		int temp = 0;
		for (int i = col; i < matrix.length; i++) {
			temp = matrix[i][col];
			if(temp != 0)
			{
				for (int j = i+1; j < matrix.length; j++) {
					if(matrix[j][col] != 0)
					{
						int kgv = kgv(temp,matrix[j][col]);
						multLine(matrix, j, kgv/matrix[j][col]);
						if(temp < kgv && temp != kgv)
							multLine(matrix, i, kgv/matrix[i][col]);
						subLine(matrix,j,i);
						break;
					}
					
				}
			}
		}
	}
	
	
	
	//Subtract rows
	public static void subLine(int[][] matrix, int j, int i) {
		int[] rowA = matrix[j];
		int[] rowB = matrix[i];
		
		for (int k = 0; k < rowA.length; k++) {
			matrix[j][k] = rowA[k] - rowB[k];
		}
	}
	
}
