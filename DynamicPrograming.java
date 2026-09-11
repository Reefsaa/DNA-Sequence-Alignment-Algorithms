package Algo;
import java.util.*;
public class DynamicPrograming {
    static int alpha = 2;
    static int beta = 1;
    static void runScoringExperiment(String S1, String S2) {
        int[][] testCases = {{1, 1}, {1, 2},  {1, 3},  {1, 4},  {2, 1},  {3, 1},   {4, 1}};
        for (int[] values : testCases) {
            alpha = values[0];
            beta = values[1];
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("Scoring Case: alpha = " + alpha + ", beta = " + beta);
            System.out.println("--------------------------------------------------");
            executeDP(S1, S2);} }
    static void executeDP(String S1, String S2) {
        int n = S1.length();          int m = S2.length();
        int[][] DP = new int[n + 1][m + 1];
        // Initializes the first cell, where both sequences are empty.
        DP[0][0] = 0;
        // Initializes the first column by aligning S1 characters with gaps.
        for (int i = 1; i <= n; i++) {
            DP[i][0] = i * beta;   }
        // Initializes the first row by aligning S2 characters with gaps.
        for (int j = 1; j <= m; j++) {
            DP[0][j] = j * beta;  }
        // Fills the DP table using the minimum cost among three choices.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost;
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = alpha;         }
                int diagonal = DP[i - 1][j - 1] + cost;
                int up = DP[i - 1][j] + beta;
                int left = DP[i][j - 1] + beta;
                DP[i][j] = Math.min(diagonal, Math.min(up, left));       }       }
        printDPTable(DP, S1, S2);
        reconstructAlignment(DP, S1, S2); }
    static void printDPTable(int[][] DP, String S1, String S2) {
        System.out.println();
        System.out.println("DP Table:");
        System.out.print("     ");
        for (int j = 0; j < S2.length(); j++) {
         System.out.print(S2.charAt(j) + "   ");}
        System.out.println();
        for (int i = 0; i <= S1.length(); i++) {
            if (i == 0) {
        System.out.print("  ");
            } else {
         System.out.print(S1.charAt(i - 1) + " ");}
            for (int j = 0; j <= S2.length(); j++) {
                System.out.print(DP[i][j] + "   ");        }
            System.out.println();        }}
    static void reconstructAlignment(int[][] DP, String S1, String S2) {
        int i = S1.length();
        int j = S2.length();
        String aligned1 = "";
        String aligned2 = "";
        // Backtracks from the last cell to reconstruct the optimal alignment.
        while (i > 0 || j > 0) {
            // Checks whether the current value came from a diagonal move.
            if (i > 0 && j > 0) {
            int cost = (S1.charAt(i - 1) == S2.charAt(j - 1)) ? 0 : alpha;
            if (DP[i][j] == DP[i - 1][j - 1] + cost) {
            aligned1 = S1.charAt(i - 1) + aligned1;
            aligned2 = S2.charAt(j - 1) + aligned2;
                    i--;         j--;
                    continue;   }           }
            // Checks whether the current value came from moving up.
            if (i > 0 && DP[i][j] == DP[i - 1][j] + beta) {
                aligned1 = S1.charAt(i - 1) + aligned1;
                aligned2 = "-" + aligned2;
                i--;
            } else {
                // Otherwise, the current value came from moving left.
                aligned1 = "-" + aligned1;
                aligned2 = S2.charAt(j - 1) + aligned2;
                j--;        } }
        System.out.println();
        System.out.println("Optimal Alignment:");
        System.out.println("S1: " + aligned1);
        System.out.println("S2: " + aligned2);
        System.out.println("Minimum Cost = " + DP[S1.length()][S2.length()]);  }

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("          DYNAMIC PROGRAMMING ALIGNMENT           ");
        System.out.println("==================================================");
        System.out.println("Choose one of the following datasets:");
        System.out.println("1. Dataset 1  -> S1 = AB, S2 = A");
        System.out.println("2. Dataset 2  -> S1 = ACG, S2 = A");
        System.out.println("3. Dataset 3  -> S1 = ACGT, S2 = ACT");
        System.out.println("4. Dataset 4  -> S1 = GATT, S2 = GTT");
        System.out.println("5. Dataset 5  -> S1 = ABSTC, S2 = AB");
        System.out.println("6. Dataset 6  -> S1 = ACGTAC, S2 = ACTAC");
        System.out.println("7. Dataset 7  -> Medium dataset");
        System.out.println("8. Dataset 8  -> Large dataset");
        System.out.println("9. Enter custom sequences");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();    input.nextLine();   String S1 = "";     String S2 = "";

        switch (choice) {
            case 1:
                S1 = "AB";
                S2 = "A";
                break;
            case 2:
                S1 = "ACG";
                S2 = "A";
                break;
            case 3:
                S1 = "ACGT";
                S2 = "ACT";
                break;
            case 4:
                S1 = "GATT";
                S2 = "GTT";
               break;
            case 5:
                S1 = "ABSTC";
                S2 = "AB";
                break;
            case 6:
                S1 = "ACGTAC";
                S2 = "ACTAC";
                break;
            case 7:
                S1 = "GATTACAGAT";
                S2 = "GATACAGT";
                break;
            case 8:
                S1 = "ACGTACGTAGCTAGCTAGCTA";
                S2 = "ACGTAGCTAGCTAGT";
                break;
            case 9:
                System.out.print("Enter S1: ");
                S1 = input.nextLine();
                System.out.print("Enter S2: ");
                S2 = input.nextLine();
                break;
            default:
                System.out.println("Invalid choice. Program terminated.");
                return;    }
        System.out.println();
        System.out.println("Selected Input:");
        System.out.println("S1 = " + S1);
        System.out.println("S2 = " + S2);
        runScoringExperiment(S1, S2);}}