package com.java;


import java.util.Scanner;
import java.util.Stack;

public class MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] heights = new int[rows][cols];

        // Convert the matrix to an array of heights
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = i > 0 ? heights[i - 1][j] + 1 : 1;
                }
            }
        }

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            maxArea = Math.max(maxArea, largestRectangleArea(heights[i]));
        }

        return maxArea;
    }

    private static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty() && (i == heights.length || heights[i] < heights[stack.peek()])) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the binary matrix as a single string (e.g., '101001101'):");
        String input = scanner.nextLine();

        System.out.println("Enter the number of columns:");
        int cols = scanner.nextInt();

        int rows = input.length() / cols;

        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = input.charAt(i * cols + j);
            }
        }

        System.out.println("Largest Rectangle Area: " + maximalRectangle(matrix));

        scanner.close();
    }
}
// given input was -> 10100101111111110010 columns were 5