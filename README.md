# DNA Sequence Alignment Algorithms

A Java-based project that implements and compares three different algorithms for solving the DNA Sequence Alignment problem:

- Brute Force
- Greedy Algorithm
- Dynamic Programming

The project was developed as part of the Design and Analysis of Algorithms (CSC 311) course at King Saud University.

## Project Overview

DNA sequence alignment is the process of arranging two DNA sequences to identify matches, mismatches, and gaps between them.

This project uses a scoring function to calculate the cost of an alignment:

- Match = 0
- Mismatch = α
- Gap = β

The algorithms are evaluated using different DNA sequence datasets and different scoring parameters.

## Algorithms

### 1. Brute Force

The Brute Force algorithm generates all possible alignments between the two DNA sequences and calculates the cost of each alignment.

It guarantees the optimal alignment but has exponential time and space complexity, making it inefficient for large sequences.

### 2. Greedy Algorithm

The Greedy algorithm builds the alignment step by step by selecting the option with the minimum immediate cost.

At each step, it considers:

- Match / Mismatch
- Insert a gap in the first sequence
- Insert a gap in the second sequence

The algorithm is faster and more memory-efficient than Brute Force, but it does not always guarantee the globally optimal alignment.

### 3. Dynamic Programming

The Dynamic Programming algorithm divides the alignment problem into smaller overlapping subproblems and stores their solutions in a DP table.

It evaluates the possible alignment choices and selects the minimum cost for each subproblem.

Unlike the Greedy approach, Dynamic Programming guarantees an optimal alignment while achieving significantly better efficiency than Brute Force.

## Comparison

| Algorithm | Strategy | Time Complexity | Space Complexity | Optimal |
|-----------|----------|-----------------|------------------|----------|
| Brute Force | Generates all possible alignments | Exponential | Exponential | Yes |
| Greedy | Local optimal choice | O(n + m) | O(n + m) | No |
| Dynamic Programming | DP table + backtracking | O(n × m) | O(n × m) | Yes |

Where:

- `n` = length of the first DNA sequence
- `m` = length of the second DNA sequence

## Features

- DNA sequence alignment
- Configurable mismatch and gap costs
- Multiple predefined datasets
- Custom DNA sequence input
- Alignment cost calculation
- Greedy alignment
- Brute Force alignment
- Dynamic Programming alignment
- Optimal alignment reconstruction using backtracking
- Complexity analysis
- Algorithm performance comparison

## Example

Example input:

```text
S1 = ACGT
S2 = ACT
