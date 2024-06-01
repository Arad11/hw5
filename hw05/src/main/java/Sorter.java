import java.util.Arrays;
import java.util.Random;

public class Sorter {

// optimzized quickSort code

public static <T extends Comparable<T>> void quickSort(T[] array) {
    quickSort(array, 0, array.length - 1);
}

private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
    if(low > high) {
        return;
    }

    if (low < high - 2) {
        int pivotIndex = partition(array, low, high);
        quickSort(array, low, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, high);
    } else {
        bubbleSort(array, low, high);
    }
}

private static <T extends Comparable<T>> int partition(T[] array, int left, int right) { 
    Random rand =  new Random();
    int randomIndex = rand.nextInt(left, right);
    T pivot = array[randomIndex];
    swap(array, randomIndex, right);
    int rtlIndex = right - 1;
    int ltrIndex = left;
    
    while (ltrIndex <= rtlIndex) {
        while (ltrIndex <= rtlIndex && array[ltrIndex].compareTo(pivot) < 0) {
            ltrIndex++;
        }
        while (ltrIndex <= rtlIndex && array[rtlIndex].compareTo(pivot) > 0) {
            rtlIndex--;
        }
        if (ltrIndex <= rtlIndex) {
            swap(array, rtlIndex, ltrIndex);
            ltrIndex++;
            rtlIndex--;
        }
    }
    swap(array, ltrIndex, right);  // Final pivot placement
    return ltrIndex;
    }
    

    private static <T extends Comparable<T>> void bubbleSort(T[] array, int left, int right) {
        if (left < 0 || right >= array.length || left >= right) {
            return;
        }
    
        boolean swapped;
        for (int i = left; i < right; i++) {
            swapped = false;
            for (int j = left; j < right - (i - left); j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) {
                break;
            }
        }
    }

    private static <T> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }







    //optimzed merge sort not recursion

    public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] arr) {
        int n = arr.length;
        for (int currSize = 1; currSize < n; currSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                merge(arr, leftStart, mid, rightEnd);
            }
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        // this implementation would work if and only if the left and the right arrays are already sorted.
        int leftArrayLength = mid - left + 1;
        int rightArrayLength = right - mid;
        T[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int leftIndex = 0;
        int rightIndex = 0;

        for(int total = left; total <= right; total++) {
           if(leftIndex < leftArrayLength && rightIndex < rightArrayLength) {
                if(leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
                    arr[total] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    arr[total] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex >= leftArrayLength && rightIndex < rightArrayLength) {
                arr[total] = rightArray[rightIndex];
                rightIndex++;
            } else if (leftIndex < leftArrayLength && rightIndex >= rightArrayLength) {
                arr[total] = leftArray[leftIndex];
                leftIndex++;
            }
        }
    }   
    
    
    
    
    
    // optimzed radixSort

    private static int extractDigit(Long key, int bitsPerDigit, int digitIndex) {
        int mask = (1 << bitsPerDigit) - 1; // Create a mask with bitsPerDigit 1s
        return (int) ((key >> (bitsPerDigit * digitIndex)) & mask); // Shift and mask to get the desired digit
    }
        

    private static void countingSort(Long[] arr, int bitsPerDigit, int digitIndex) {
        int n = arr.length;
        Long[] output = new Long[n];
        int base = 1 << bitsPerDigit; // Calculate the base, which is 2^bitsPerDigit
        int[] count = new int[base];
    
        // Initialize count array
        for (int i = 0; i < base; i++) {
            count[i] = 0;
        }
    
        // Store count of occurrences of digits
        for (int i = 0; i < n; i++) {
            int digit = extractDigit(arr[i], bitsPerDigit, digitIndex);
            count[digit]++;
        }
    
        // Change count[i] so that it contains the actual position of this digit in output[]
        for (int i = 1; i < base; i++) {
            count[i] += count[i - 1];
        }
    
        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = extractDigit(arr[i], bitsPerDigit, digitIndex);
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
    
        // Copy the output array to arr[], so that arr[] now contains sorted numbers
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    


    public static void radixSort(Long[] array, int bitsPerDigit) {
        // Find the maximum number to know the number of digits
        long max = array[0];
        for (Long num : array) {
            if (num > max) {
                max = num;
            }
        }
    
        // Calculate the number of digits needed
        int numDigits = 0;
        while (max > 0) {
            numDigits++;
            max >>= bitsPerDigit;
        }
    
        // Apply counting sort to sort based on each digit
        for (int digitIndex = 0; digitIndex < numDigits; digitIndex++) {
            countingSort(array, bitsPerDigit, digitIndex);
        }
    }    
}
