package Algo;

import java.util.*;

public class Greedy {

    // ============================================================
    // Global Cost Parameters
    // alpha = mismatch cost
    // beta  = gap cost
    // ============================================================

    static int alpha = 2;
    static int beta = 1;

    // ============================================================
    // Scoring Experiment
    // Tests the greedy algorithm using different alpha and beta values.
    // ============================================================

    static void runScoringExperiment(String S1, String S2) {

        int[][] cases = {
                {1,1},
                {1,2},
                {1,3},
                {1,4},
                {2,1},
                {3,1},
                {4,1}
        };

        for (int[] pair : cases) {

            alpha = pair[0];
            beta = pair[1];

            System.out.println("\n=================================");
            System.out.println("alpha = " + alpha + " , beta = " + beta);
            System.out.println("=================================");

            executeGreedyAlignment(S1, S2);
        }
    }

    // ============================================================
    // Greedy Alignment Algorithm
    // Builds the alignment by choosing the lowest immediate cost.
    // ============================================================

    static void executeGreedyAlignment(String S1, String S2) {

        // Initialize pointers for both strings
        int i = 0;
        int j = 0;

        // Initialize aligned output strings
        String aligned1 = "";
        String aligned2 = "";

        // Continue until both strings are fully processed
        while (i < S1.length() || j < S2.length()) {

            // Possible costs for each greedy move
            int costDiag = Integer.MAX_VALUE;
            int costGapS1 = Integer.MAX_VALUE;
            int costGapS2 = Integer.MAX_VALUE;

            // Case 1: Align current characters from both strings
            if (i < S1.length() && j < S2.length()) {

                if (S1.charAt(i) == S2.charAt(j))
                    costDiag = 0;
                else
                    costDiag = alpha;
            }

            // Case 2: Insert a gap in S1
            if (j < S2.length())
                costGapS1 = beta;

            // Case 3: Insert a gap in S2
            if (i < S1.length())
                costGapS2 = beta;

            // Choose the move with the minimum immediate cost
            if (costDiag <= costGapS1 && costDiag <= costGapS2) {

                // Move diagonally: match or mismatch
                aligned1 += S1.charAt(i);
                aligned2 += S2.charAt(j);

                i++;
                j++;
            }

            else if (costGapS1 < costGapS2) {

                // Insert gap in S1 and move in S2
                aligned1 += "-";
                aligned2 += S2.charAt(j);

                j++;
            }

            else {

                // Insert gap in S2 and move in S1
                aligned1 += S1.charAt(i);
                aligned2 += "-";

                i++;
            }
        }

        // Calculate and display the final cost
        int cost = computeCost(aligned1, aligned2);

        System.out.println("\nGreedy Alignment:");
        System.out.println("S1: " + aligned1);
        System.out.println("S2: " + aligned2);
        System.out.println("Cost = " + cost);
    }

    // ============================================================
    // Cost Function
    // Calculates the total alignment cost based on alpha and beta.
    // ============================================================

    static int computeCost(String S1, String S2) {

        int total = 0;

        for (int i = 0; i < S1.length(); i++) {

            char c1 = S1.charAt(i);
            char c2 = S2.charAt(i);

            if (c1 == c2)
                total += 0;

            else if (c1 == '-' || c2 == '-')
                total += beta;

            else
                total += alpha;
        }

        return total;
    }

    // ============================================================
    // Main Method
    // Allows the user to choose a dataset and runs the experiment.
    // ============================================================

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("\n=========== DATASET MENU ===========");

System.out.println("1. Small Dataset      -> (AB , A)");
System.out.println("2. Small Dataset      -> (ACG , A)");
System.out.println("3. Normal Dataset     -> (ACGT , ACT)");
System.out.println("4. Normal Dataset     -> (GATT , GTT)");
System.out.println("5. Medium Dataset     -> (ABSTC , AB)");
System.out.println("6. Medium Dataset     -> (ACGTAC , ACTAC)");
System.out.println("7. Medium-Large Data  -> (GATTACAGAT , GATACAGT)");
System.out.println("8. Large Dataset      -> Long DNA Sequences");
System.out.println("9. Custom Input");

System.out.println("====================================");

System.out.print("Enter your choice: ");

        int choice = input.nextInt();
        input.nextLine();

        String S1 = "";
        String S2 = "";

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
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nSelected Strings:");
        System.out.println("S1 = " + S1);
        System.out.println("S2 = " + S2);

        runScoringExperiment(S1, S2);
    }
}