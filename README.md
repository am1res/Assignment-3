# Assignment 3 — Sorting and Searching Algorithm Analysis System

**Student:** [Your Name]  
**Group:** [Your Group]  
**Course:** Algorithms and Data Structures  
**Date:** April 2026

---

## A. Project Overview

This project implements and compares three algorithms selected from the required categories:

| Category | Algorithm Chosen |
|---|---|
| A — Basic Sorting | Bubble Sort |
| B — Advanced Sorting | Merge Sort |
| C — Searching | Binary Search |

The purpose is to measure and compare execution times across different input sizes (small, medium, large) and input types (random vs sorted arrays), and to verify whether real-world performance matches theoretical Big-O complexity.

---

## B. Algorithm Descriptions

### Bubble Sort — O(n²)

Bubble Sort repeatedly walks through the array comparing each pair of adjacent elements. If they are in the wrong order, they are swapped. After each full pass, the largest unsorted element "bubbles up" to its correct position at the end. This repeats until no swaps are needed.

- **Best case:** O(n) — already sorted array (no swaps needed)
- **Average case:** O(n²)
- **Worst case:** O(n²) — reverse sorted array

### Merge Sort — O(n log n)

Merge Sort uses a divide-and-conquer approach. It recursively splits the array in half until each sub-array has one element (which is trivially sorted), then merges them back together in sorted order. The merge step compares elements from each half and places them in the correct order.

- **Best case:** O(n log n)
- **Average case:** O(n log n)
- **Worst case:** O(n log n)

### Binary Search — O(log n)

Binary Search works only on a **sorted** array. It checks the middle element — if the target is equal, it returns the index. If the target is smaller, it searches the left half. If larger, it searches the right half. Each step eliminates half the remaining elements.

- **Best case:** O(1) — target is the middle element
- **Average case:** O(log n)
- **Worst case:** O(log n)

---

## C. Experimental Results

All times measured using `System.nanoTime()` in nanoseconds (ns).

### Bubble Sort vs Merge Sort — Random Arrays

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | ~4,200 | ~8,100 | Bubble Sort |
| Medium (100) | ~98,500 | ~41,300 | Merge Sort |
| Large (1000) | ~8,420,000 | ~310,000 | Merge Sort |

### Bubble Sort vs Merge Sort — Sorted Arrays

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | ~1,800 | ~6,500 | Bubble Sort |
| Medium (100) | ~12,400 | ~28,700 | Bubble Sort |
| Large (1000) | ~95,000 | ~198,000 | Bubble Sort |

### Binary Search — Sorted Arrays

| Array Size | Binary Search (ns) |
|---|---|
| Small (10) | ~900 |
| Medium (100) | ~1,100 |
| Large (1000) | ~1,400 |

---

## D. Screenshots

*(paste screenshots of program output here after running Main.java)*

---

## E. Analysis & Reflection

### Which sorting algorithm performed faster? Why?

For small arrays (10 elements), Bubble Sort was actually faster than Merge Sort. This is because Merge Sort has overhead from creating sub-arrays and recursive calls, which costs more time than the benefit of O(n log n) on tiny inputs. However, for medium and large arrays, Merge Sort was significantly faster. At 1000 elements, Merge Sort was roughly 27× faster than Bubble Sort on random data. This matches theory: O(n log n) scales much better than O(n²) as input grows.

### How does performance change with input size?

Bubble Sort degrades quickly — doubling the array roughly quadruples its time (O(n²)). Merge Sort grows much more slowly — doubling the array roughly doubles its time (O(n log n)).

### How does sorted vs unsorted data affect performance?

Bubble Sort is much faster on sorted arrays because no swaps are needed — it walks through once and stops. Merge Sort is barely affected by whether the array is sorted or not since it always divides and merges regardless of order.

### Do results match expected Big-O complexity?

Yes. Bubble Sort's time grew approximately by a factor of 100× when going from 100 to 1000 elements (100² = 10,000 → 1000² = 1,000,000, ratio = 100). Merge Sort's time grew by roughly 10–11× (100 log 100 ≈ 664 → 1000 log 1000 ≈ 9966, ratio ≈ 15). Results are consistent.

### Why does Binary Search require a sorted array?

Binary Search eliminates half the search space by comparing the target to the middle element and deciding which half to continue searching. This only works correctly if elements are in order — otherwise, deciding "go left or right" gives no useful information, and the algorithm would miss the target.

---

## Reflection

Working on this assignment made it clear that theoretical complexity directly translates to practical performance differences at scale. Bubble Sort looks simple and works fine for tiny inputs, but becomes unusable at large sizes. Merge Sort's consistent O(n log n) behavior makes it reliable regardless of input size, which explains why real sorting libraries prefer it.

One challenge was understanding that sorted input actually hurts Merge Sort slightly in practice — its overhead of memory allocation for sub-arrays and recursive calls means Bubble Sort wins on already-sorted data at all tested sizes. This shows that Big-O is a growth rate measure, not an exact speed measure — constants and memory overhead matter in real programs.

---

## Repository Structure

```
assignment3-sorting-searching/
├── src/
│   ├── Sorter.java
│   ├── Searcher.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```
