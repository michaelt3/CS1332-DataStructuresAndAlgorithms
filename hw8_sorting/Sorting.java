import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class Sorting {
    /**
     * Helper function used to swap
     * @param arr The input array you are trying to modify
     * @param pos1 The 1st location of array position you are trying to swap
     * @param pos2 The 2nd location of array position you are trying to swap
     * @param <T> Must be generic type
     */
    private static <T> void swap(T[] arr, int pos1, int pos2) {
        T temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        if (comparator == null) {
            String error = "You tried passing in a null comparator.";
            throw new IllegalArgumentException(error);
        }
        boolean flag = true;
        T temp;
        int i = 0;
        while (i < arr.length - 1 && flag) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        if (comparator == null) {
            String error = "You tried passing in a null comparator.";
            throw new IllegalArgumentException(error);
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, j - 1);
                j = j - 1;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        if (comparator == null) {
            String error = "You tried passing in a null comparator.";
            throw new IllegalArgumentException(error);
        }
        if (rand == null) {
            String error = "You tried passing in a null rand.";
            throw new IllegalArgumentException(error);
        }
        quickSort(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     *
     * @param arr The array being quickSorted
     * @param left The left bound of the array being modified
     * @param right The right bound of the array being modified
     * @param comparator The comparator used to compare values
     * @param rand The random generator used to create random values
     * @param <T> Must be generic typed.
     */
    private static <T> void quickSort(T[] arr, int left, int right,
        Comparator<T> comparator, Random rand) {
        if (arr.length == 0 || left >= right) {
            return;
        }
        int pivotIndex = rand.nextInt(right + 1 - left) + left;
        T pivotValue = arr[pivotIndex];
        swap(arr, left, pivotIndex);
        int leftIndex = left + 1;
        int rightIndex = right;
        while (leftIndex <= rightIndex) {
            while (leftIndex <= rightIndex
                && comparator.compare(arr[leftIndex], pivotValue) <= 0) {
                leftIndex++;
            }
            while (rightIndex > left
                && comparator.compare(arr[rightIndex], pivotValue) >= 0) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        swap(arr, left, rightIndex);
        quickSort(arr, left, rightIndex - 1, comparator, rand);
        quickSort(arr, rightIndex + 1, right, comparator, rand);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        if (comparator == null) {
            String error = "You tried passing in a null comparator.";
            throw new IllegalArgumentException(error);
        }
        int length = arr.length;
        int midIndex = length / 2;
        T[] leftArr = (T[]) new Object[midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArr[i] = arr[i];
        }
        T[] rightArr = (T[]) new Object[length - midIndex];
        for (int i = midIndex; i < length; i++) {
            rightArr[i - midIndex] = arr[i];
        }
        if (leftArr.length > 1) {
            mergeSort(leftArr, comparator);
        }
        if (rightArr.length > 1) {
            mergeSort(rightArr, comparator);
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while (leftIndex < midIndex && rightIndex < length - midIndex) {
            if (comparator.compare(leftArr[leftIndex],
                rightArr[rightIndex]) <= 0) {
                arr[currentIndex] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }
        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArr[leftIndex];
            leftIndex++;
            currentIndex++;
        }
        while (rightIndex < length - midIndex) {
            arr[currentIndex] = rightArr[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        Queue<Integer>[] buckets = new Queue[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int maxDigits = Integer.MIN_VALUE;
        for (int i : arr) {
            i = Math.abs(i);
            int length = 1;
            while ((i = i / 10) != 0) {
                length++;
            }
            if (length > maxDigits) {
                maxDigits = length;
            }
        }
        int divisor = 1;
        for (int i = 0; i < maxDigits; i++) {
            for (int value : arr) {
                int sortedNumber = ((value / divisor) % 10) + 9;
                buckets[sortedNumber].add(value);
            }
            int index = 0;
            for (Queue<Integer> bucket: buckets) {
                while (!bucket.isEmpty()) {
                    arr[index++] = bucket.remove();
                }
            }
            divisor *= 10;
        }
        return arr;
    }

    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            String error = "You tried passing in a null array.";
            throw new IllegalArgumentException(error);
        }
        int maxDigits = Integer.MIN_VALUE;
        for (int i : arr) {
            i = Math.abs(i);
            int length = 1;
            while ((i = i / 10) != 0) {
                length++;
            }
            if (length > maxDigits) {
                maxDigits = length;
            }
        }
        msdRadixSort(arr, maxDigits);
        return arr;
    }

    /**
     * Helper functions used to recursively call msdRadixSort
     * @param arr The array being MSDsorted
     * @param maxLength The largest sized digit found in the array
     * @param <T> Must be generic type
     */
    private static <T> void msdRadixSort(int[] arr, int maxLength) {
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int divisor = pow(10, maxLength - 1);
        if (divisor == 0) {
            divisor = 1;
        }
        for (int value : arr) {
            int sortedNumber = ((value / divisor) % 10) + 9;
            buckets[sortedNumber].add(value);
        }
        int index = 0;
        for (LinkedList<Integer> bucket: buckets) {
            if (bucket.size() > 1 && maxLength > 1) {
                bucket = msdHelper(bucket, maxLength - 1);
            }
            while (!bucket.isEmpty()) {
                arr[index++] = bucket.remove();
            }
        }
        //divisor /= 10;
    }

    /**
     * msdHelper used to recursively modify buckets with collisions
     * @param arr Array being modified by MSDhelper
     * @param maxLength The largest digit found in the array
     * @return returns the sorted LinkedList that had collisions in it
     */
    private static LinkedList<Integer> msdHelper(LinkedList<Integer> arr,
        int maxLength) {
        LinkedList<Integer> outList = new LinkedList<Integer>();
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int divisor = pow(10, maxLength - 1);
        for (int value : arr) {
            int sortedNumber = ((value / divisor) % 10) + 9;
            buckets[sortedNumber].add(value);
        }
        for (LinkedList<Integer> bucket: buckets) {
            if (bucket.size() > 1 && maxLength > 1) {
                bucket = msdHelper(bucket, maxLength - 1);
            }
            while (!bucket.isEmpty()) {
                outList.add(bucket.remove());
            }
        }
        //divisor /= 10;
        return outList;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * halfPow * base;
        }
    }
}
