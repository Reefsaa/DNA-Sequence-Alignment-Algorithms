package Algo;
import java.util.*;

public class BruteForceDNAAlignment {
 static int alpha = 2; // mismatch
 static int beta = 1; // gap

 static int minCost; //Stores the minimum cost found so far
 static String bestAlignment; //Stores the best alignment as one string
 static int alignmentCounter; //Counts total generated alignments

 
 public static void main(String[] args) {
 Scanner input = new Scanner(System.in);
 
 System.out.print("Enter first DNA sequence:");
 String S1 = input.nextLine();
 
 System.out.print("Enter second DNA sequence:");
 String S2 = input.nextLine();
 BruteForceAlignment(S1, S2); }


 public static void BruteForceAlignment(String S1, String S2) {
 minCost = Integer.MAX_VALUE;
 bestAlignment = "";
 alignmentCounter = 0;

 GenerateAlignments(S1, S2, 0, 0, "", "");

 System.out.println("Total Alignments = " + alignmentCounter);
 System.out.println("Minimum Cost = " + minCost);
 System.out.println("Best Alignment = ");
 System.out.println(bestAlignment); }

//Recursive function that generates all possible alignments
public static void GenerateAlignments(String S1, String S2, int i, int j,
String aligned1, String aligned2) {
// Base Case:
// If both sequences are fully processed,compute the cost
if (i == S1.length() && j == S2.length()) {
int cost = ComputeCost(aligned1, aligned2);
alignmentCounter++;
System.out.println("Alignment " + alignmentCounter + ":");
System.out.println(aligned1);
System.out.println(aligned2);
System.out.println("Cost = " + cost);
System.out.println("-------------------------");
// Update minimum cost and best alignment if needed
if (cost < minCost) {
minCost = cost;
bestAlignment = aligned1 + " | " + aligned2; }
return;}
// Case Match / Mismatch
// Align current character of S1 with current character of S2
if (i < S1.length() && j < S2.length()) {
GenerateAlignments(
S1, S2,
i + 1, j + 1,
aligned1 + S1.charAt(i),
aligned2 + S2.charAt(j) ); }
// Case Gap in S1
// Insert'-' in aligned1 and take current character from S2
if (j < S2.length()) {
GenerateAlignments(
S1, S2,
i, j + 1,
aligned1 + "-",
aligned2 + S2.charAt(j) ); }
// Case Gap in S2
// Take current character from S1 and insert'-' in aligned2
if (i < S1.length()) {
GenerateAlignments(
S1, S2,
i + 1, j,
aligned1 + S1.charAt(i),
aligned2 + "-"); }}
// Cost function
public static int ComputeCost(String S1, String S2) {
int totalCost = 0;
for (int i = 0; i < S1.length(); i++) {
if (S1.charAt(i) == S2.charAt(i)) {
totalCost = totalCost + 0; // Match
} else if (S1.charAt(i) == '-' || S2.charAt(i) == '-') {
totalCost = totalCost + beta; // Gap
} else {
totalCost = totalCost + alpha; // Mismatch 
}}
return totalCost; }}

 
 
