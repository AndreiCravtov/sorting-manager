# Sorting manager project
## Implementation
The project consists of a `Sorter` interface, which is the abstraction for sorting arrays.
```java
public interface Sorter {
    int[] sortArray(int[] arrayToSort);
}
```

At the moment, the sorting manager only has one sorting algorithm implemented: the bubble sort algorithm. The class`BubbleSort` implements `Sorter`, meaning `BubbleSort` can now be used as the concretion for the `Sorter` abstraction, like this:
```java
int[] sortMe = {3, 2, 1, 19, -1};
Sorter sort = new BubbleSort();
int[] sorted = sort.sortArray(sortMe);
```

The bubble sort algorithm is the following
```java
00 public int[] sortArray(int[] arrayToSort) {
01     int[] returnArr = Arrays.copyOf(arrayToSort, arrayToSort.length);
02     boolean swapped;
03     for (int i=returnArr.length-1; i>0; i--) {
04         swapped = false;
05         for (int j=0; j<i; j++)
06             if (returnArr[j] > returnArr[j+1]) {
07                 returnArr[j] = returnArr[j]^returnArr[j+1]^(returnArr[j+1]=returnArr[j]);
08                 swapped = true;
09            }
10         if (!swapped) break;
11     }
12     return returnArr;
13 }
```

Line 01 duplicates the array, so that the original remains unmodified.
Line 02 creates a swapped flag.
At the beginning of each iteration, it is set to `false`, as seen on line 04.
If a swap occurs, it is set to `true`, as seen on line 08.
If the bubble sort goes through an iteration and no swaps occur, the algorithm terminates, as seen on line 10.
Each iteration, the number of elements in order on the right hand side increase by at least 1.
Hence, I only sort the remaining leftmost elements in the following iteration, as seen in line 03.
When two adjacent elements are compared and found to be in the wrong order, as seen in line 06, then they are swapped, as seen in line 07.

To swap the elements I use a special XOR swapping method that only works when swapping two integers. The general format of this XOR swap is as follows:
```java
int a = 1;
int b = 2;
a = a ^ b ^ (b = a);
```

## Tests
To test the various algorithms implemented for the `Sorter` interface, I have come up with 6 unit tests which make sure the behaviour of the algorithm is acceptable.

### Test One:
```java
@Test
@DisplayName("Test sort with only positive numbers")
void testOnlyPositive() {
    Assertions.assertArrayEquals(
            sorter.sortArray(new int[]{5, 5, 7, 8, 2, 4, 1}),
            new int[]{1, 2, 4, 5, 5, 7, 8}
    );
}
```
Description goes here

### Test Two:
```java
@Test
@DisplayName("Test sort with only negative numbers")
void testOnlyNegative() {
    Assertions.assertArrayEquals(
            sorter.sortArray(new int[]{-1, -3, -5, -7, -9, -5}),
            new int[]{-9, -7, -5, -5, -3, -1}
    );
}
```
Description goes here

### Test Three:
```java
@Test
@DisplayName("Test sort with positive and negative numbers")
void testPositiveNegative() {
    Assertions.assertArrayEquals(
            sorter.sortArray(new int[]{0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1}),
            new int[]{-6, -5, -4, 0, 1, 2, 4, 5, 5, 7, 8}
    );
}
```
Description goes here

### Test Four:
```java
@Test
@DisplayName("Test sort with the same number")
void testSameNumber() {
    Assertions.assertArrayEquals(
            sorter.sortArray(new int[]{1, 1, 1, 1}),
            new int[]{1, 1, 1, 1}
    );
}
```
Description goes here

### Test Five:
```java
@Test
@DisplayName("Test sort with an empty list")
void testEmptyList() {
    Assertions.assertArrayEquals(
            sorter.sortArray(new int[]{}),
            new int[]{}
    );
}
```
Description goes here

### Test Six:
```java
@Test
@DisplayName("Test that the sort doesn't change the original array")
void testNonModification() {
    int[] array = {0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1};
    sorter.sortArray(array);
    Assertions.assertArrayEquals(
            array,
            new int[] {0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1}
    );
}
```
Description goes here