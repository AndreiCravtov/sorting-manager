# Sorting manager project

## Description
This program allows the user to sort a randomised array. The general flow of the application is:
- The user is presented with a number of sort algorithms to choose from (Bubble Sort, Merge Sort, Tree Sort).
- They can choose more than one algorithm in order to compare their performance.
- The program will then ask for the length of an array, and the lower and upper bounds of the range for the numbers in the array.
- The program should then output:
    - The sort algorithm to be used and the time taken
    - The unsorted randomly generated array
    - The sorted array after the algorithm has been executed

## Implementation

### Sorters

#### Sorter Interface
The `sorters` package has a public `Sorter` interface, which is the abstraction for sorting arrays.
```java
public interface Sorter {
    int[] sortArray(int[] arrayToSort);
}
```

#### Bubble Sort
The class `BubbleSort` implements `Sorter`, meaning `BubbleSort` can now be used as the concretion for the `Sorter` abstraction.

My bubble sort algorithm implementation is the following:
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

#### Merge Sort
The class `MergeSort` implements `Sorter`, meaning `MergeSort` can now be used as the concretion for the `Sorter` abstraction.

My merge sort algorithm implementation is the following:
```java
00 @Override
01 public int[] sortArray(int[] arrayToSort) {
02     int[] returnVal = Arrays.copyOf(arrayToSort, arrayToSort.length);
03     mergeSort(returnVal);
04     return returnVal;
05 }
06 
07 private void mergeSort(int[] arrayToSort) {
08     if (arrayToSort.length <= 1) return;
09 
10     int mid = arrayToSort.length / 2;
11     int[] left = Arrays.copyOfRange(arrayToSort, 0, mid);
12     int[] right = Arrays.copyOfRange(arrayToSort, mid, arrayToSort.length);
13 
14     mergeSort(left);
15     mergeSort(right);
16 
17     int i = 0, j = 0, k = 0;
18     while (i < left.length && j < right.length)
19         if (left[i] <= right[j])
20             arrayToSort[k++] = left[i++];
21         else
22             arrayToSort[k++] = right[j++];
23     while (i < left.length)
24         arrayToSort[k++] = left[i++];
25     while (j < right.length)
26         arrayToSort[k++] = right[j++];
27 }
```
It consists of two main parts: copying the array - as to avoid modifying the original, and the actual sort.
The actual sort also consists of two parts: subdividing, and merging.
When the length of the array is less than or equal to 1, then it is considered sorted and the function terminates.
The function then recursively splits up and merges the left and right halves of the array.
It then merges the two sorted array halves.

#### Tree Sort
The class `TreeSort` implements `Sorter`, meaning `TreeSort` can now be used as the concretion for the `Sorter` abstraction.

My tree sort algorithm implementation consists of two parts.
The first part is a `BinarySearchTree` data structure that is capable of adding values to the tree, and in-order traversing the existing tree.
The second part is the actual sort function which utilises `BinarySearchTree` to sort an incoming array.

##### Sort function
The code for the sort function is the following:
```java
@Override
public int[] sortArray(int[] arrayToSort) {
    BinarySearchTree tree = new BinarySearchTree();
    for (int number: arrayToSort)
        tree.add(number);
    return tree.inOrderTraversal();
}
```
The incoming array is iterated over and each element is added to the tree. An in-order traversal is then returned as the sorted array.

##### Binary Search Tree
The `BinarySearchTree` data structure class has a `Node` inner class which represents a node on the tree.
```java
class Node {
    int value;
    int count;
    Node left, right;

    Node(int value) {
        this.value = value;
        count = 1;
        left = right = null;
    }
}
```
The `BinarySearchTree` data structure class has a private field `root` which represents the root node.
It remains undefined upon instantiation.
The class has the ability to add values, the algorithm for that is iterative rather than recursive.
The class also has the ability to produce an in-order traversal array, which is done recursively rather than iteratively.

#### Sorter Factory
All three of the sorting algorithms implemented (bubble sort, merge sort and tree sort) have constructors which have the `default` access level.
This means that only other classes in the package can instantiate those sorts.
The `SorterFactory` is within the `sorters` package and is able to instantiate these sorts.
This means that other classes outside that package can use it to get instances of these sort algorithms.

The `SorterFactory` has an inner `enum` within itself:
```java
public enum SorterType {
    BUBBLE_SORT("Bubble Sort"),
    MERGE_SORT("Merge Sort"),
    TREE_SORT("Tree Sort");

    private final String name;

    SorterType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```
This `enum` contains the three types of sorts within the package, as well as their `String` name.
The class also has a `getNewSorter()` function, which takes a SorterType parameter as an argument and uses a simple switch-case statement to return the correct new sort instance. 

### Main app
The main app uses the Model-View-Controller pattern in order to orchestrate the user input, display, business logic and control flow efficiently and flexibly.

#### Model
The model is responsible for all business logic. It contains the logical operations and the those logical operations operate on.

##### Random Model
The random model is responsible for generating a random array.
It has two functions, `setArray()` and `getArray()`, which are responsible for generating a random array given some parameters, and returning said random array, respectively.

```java
public void setArray(int length, int lower, int upper) throws LengthIsNegative, InvalidRange {
    if (length < 0) throw new LengthIsNegative();
    if (lower > upper) throw new InvalidRange();

    array = new int[length];
    for (int i=0; i<length; i++)
        array[i] = random.nextInt(lower, upper + 1);
}
```
The `setArray()` function checks that the parameters are appropriate, and if they aren't, custom exceptions, such as `LengthIsNegative` and `InvalidRange`, are thrown.

##### Sort Model
The sort model is responsible for sorting an array.
It has two functions to set and get the unsorted array: `setUnsorted()`, `getUnsorted()`.
There is also a function to get the sorted array: `getSorted()`.
It also has a function to set the sorter: `setSorter()`.
Finally, there is a `sort()` function that sorts the contents of the unsorted array and writes it to the sorted array variable.
```java
public long sort() throws SorterIsNull {
    if (sorter == null) throw new SorterIsNull();

    long startTime = System.nanoTime();
    sorted = sorter.sortArray(unsorted);
    long endTime =System.nanoTime();

    return endTime - startTime;
}
```
The `sort()` function throws the `SorterIsNull` exception if it is called without a sorter being selected first.

#### View
The class responsible for the view is the `DisplayManager`.
It is responsible for displaying information to the user, and capturing input from the user.

The view welcomes the user with the `welcome()` function.
It then collects the user input about the random array parameters and sorting functions using the `getUserOptions()`, returning a `UserOptions` data communication object.
Then, the name of sort and time to sort (in nanoseconds) is displayed using `displayStats()`, and after that, the unsorted and sorted arrays are displayed with `displayArrays()`.
At the end of the program, the `goodbye()` function is called to display the ending characters of the program.

#### Controller
The controller is the connector between the Model and the View.
The `SortManager` is the controller for this project.
It holds instances of all models and views previously discussed.
It holds only one function, `start()`, and its whole purpose is to facilitate the communication between the models and the view.
```java
public void start() {
    displayManager.welcome();
    DisplayManager.UserOptions options = displayManager.getUserOptions();

    Logging.logger.info("User chose a length of random array to be %s".formatted(options.length));
    Logging.logger.info("User chose a the lower bound of numbers in the array to be %s".formatted(options.lower));
    Logging.logger.info("User chose a the upper bound of numbers in the array to be %s".formatted(options.upper));
    Logging.logger.info("User chose the following arrays to sort with: %s".formatted(options.types.stream().map(i -> i.getName()+". ").collect(Collectors.joining())));

    try {
        randomModel.setArray(options.length, options.lower, options.upper);
    } catch (LengthIsNegative | InvalidRange e) {
        Logging.logger.error(e);
        throw new RuntimeException(e);
    }
    sortModel.setUnsorted(randomModel.getArray());

    for (SorterFactory.SorterType type: options.types) {
        sortModel.setSorter(type);
        long timeTaken = 0;
        try {
            timeTaken = sortModel.sort();
        } catch (SorterIsNull e) {
            Logging.logger.error(e);
            throw new RuntimeException(e);
        }
        Logging.logger.info("Sorting the array with %s took %s ns".formatted(type.getName(), timeTaken));
        displayManager.displayStats(type, timeTaken);
    }
    displayManager.displayArrays(sortModel.getUnsorted(), sortModel.getSorted());

    displayManager.goodbye();
}
```
It gets user options from the display manager, uses them to operate the random and sorter models accordingly, and then feeds the results back to the view to end the program.
The `start()` function handles any exceptions that arise.
They are logged, and the program terminates.
Furthermore, there is passive info logging in this function.
The logging is performed using `log4j`.

## Tests
To test the various algorithms implemented for the `Sorter` interface, I have come up with 7 unit tests which make sure the behaviour of the algorithm is acceptable.
All these tests will be applied to all the algorithms implemented: Bubble Sort, Merge Sort and Tree Sort.

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
This test checks if the sorting algorithm is able to sort an array correctly when the array only consists of positive numbers.

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
This test checks if the sorting algorithm is able to sort an array correctly when the array only consists of negative numbers.

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
This test checks if the sorting algorithm is able to sort an array correctly when the array consists of both positive and negative numbers.

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
This test checks if the sorting algorithm is able to sort an array correctly when the array only consists of the same number.

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
This test checks if the sorting algorithm is able to sort an array correctly when the array is empty.

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
This test checks if the sorting algorithm does not modify the original array when sorting it.

### Test Seven:
```java
@Test
@DisplayName("Test the performance of the sort with 2^m=n elements")
void testLargeArray() throws IOException {
    int [] unsorted = new int [n], sorted = new int [n];

    InputStream fileUnsorted = Thread.currentThread().getContextClassLoader().getResourceAsStream("polynomial_test_array_unsorted");
    InputStream fileSorted = Thread.currentThread().getContextClassLoader().getResourceAsStream("polynomial_test_array_sorted");
    for (int i=0; i<n; i++){
        unsorted[i] = fileUnsorted.read();
        sorted[i] = fileSorted.read();
    }
    fileUnsorted.close();
    fileSorted.close();

    Assertions.assertArrayEquals(
            sorter.sortArray(unsorted),
            sorted
    );
}
```
This test checks the performance of each of the implemented algorithms.
For algorithms of time complexity *O( n<sup>2</sup> )*, such as Bubble Sort, `m`=15 and `n`=32768.
For algorithms of time complexity *O( nlogn )*, such as Merge Sort and Tree Sort, `m`=23 and `n`=8388608.
A pre-defined list of numbers (of size `n`), and it's sorted counterpart, are read into memory, and they are used to test the performance  of each of the algorithms.