package com.example.algo_pro1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
//HelloApplication
//        setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 15));
//        Pane ledsPane = new Pane();

public class MaxLEDLighting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of LEDs
        System.out.print("Enter the number of LEDs: ");
        int n = scanner.nextInt();

        // Generate a random permutation for LED ordering
        ArrayList<Integer> ledsOrder = generateRandomPermutation(n);

        // Declare and initialize the dp array
        int[][] dp = new int[n + 1][n + 1];

        // Find the best pairs to connect using LCS
        PairResult result = findBestPairs(n, ledsOrder, dp);

        // Print the generated LED ordering
        System.out.println("Generated LED ordering: " + ledsOrder);

        // Print the result
        System.out.print("Best pairs to connect: ");
        for (MaxLEDLighting.Pair pair : result.getBestPairs()) {
            System.out.print(pair + " ");
        }

        // Print the DP table
        String dpTableString = printDPTable(dp, n, ledsOrder);
        System.out.println("\nDP Table:\n" + dpTableString);
    }

    static class Pair {
        int ledIndex;
        int sourceIndex;

        Pair(int ledIndex, int sourceIndex) {
            this.ledIndex = ledIndex;
            this.sourceIndex = sourceIndex;
        }

        @Override
        public String toString() {
            return "(S" + ledIndex + ",L" + sourceIndex + ")";
        }
    }

    static class PairResult {
        ArrayList<Pair> bestPairs;

        PairResult(ArrayList<Pair> bestPairs) {
            this.bestPairs = bestPairs;
        }

        ArrayList<Pair> getBestPairs() {
            return bestPairs;
        }
    }

    static ArrayList<Integer> generateRandomPermutation(int n) {
        ArrayList<Integer> permutation = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            permutation.add(i);
        }
        Collections.shuffle(permutation);
        return permutation;
    }
//2 6 3 5 4 1
//    static ArrayList<Integer> generateRandomPermutation(int n) {
//        ArrayList<Integer> permutation = new ArrayList<>();
//        permutation.add(2);
//        permutation.add(6);
//        permutation.add(3);
//        permutation.add(5);
//        permutation.add(4);
//        permutation.add(1);
//
//        HashSet<Integer> set = new HashSet<>();
//        for (int num : permutation) {
//            if (!set.add(num)) {
//                // Repetition found
//                System.out.println("Repetition");
//                System.exit(1);
//
//                return null;
//            }
//        }
//
//        // Check if the permutation size is equal to n
//        if (permutation.size() != n) {
//            System.out.println("not equl");
//            System.exit(1);
//
//            return null;
//        }
//
//        return permutation;
//    }

    static PairResult findBestPairs(int n, ArrayList<Integer> ledsOrder, int[][] dp) {


        // Build the dp table using dynamic programming
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (ledsOrder.get(i - 1).equals(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to find the selected pairs
        ArrayList<Pair> bestPairs = new ArrayList<>();
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (ledsOrder.get(i - 1).equals(j)) {
                bestPairs.add(new Pair(ledsOrder.get(i - 1), i));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        Collections.reverse(bestPairs);

        return new PairResult(bestPairs);
    }

    static String printDPTable(int[][] dp, int n, ArrayList<Integer> ledsOrder) {
        StringBuilder result = new StringBuilder();
        result.append("       ");
        for (int j = 1; j <= n; j++) {
            result.append(String.format("%-4d", j));
        }
        result.append("\n");
        for (int i = 1; i <= n; i++) {
            result.append(String.format("%-4d | ", i));
            for (int j = 1; j <= n; j++) {
                result.append(String.format("%-4d", dp[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }
}
