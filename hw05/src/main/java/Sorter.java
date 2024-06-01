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
    























////////////////////////////////////////////////////////
    public static void radixSortb(Long[] array, int bitsPerDigit) {
        int n = array.length;
        if (n == 0) return;

        // Determine the number of digits needed
        int numDigits = (64 + bitsPerDigit - 1) / bitsPerDigit;
        int numBuckets = 1 << bitsPerDigit;

        // Initialize the temporary array
        Long[] output = new Long[n];

        // Iterate through each digit
        for (int d = 0; d < numDigits; d++) {
            // Initialize the count array
            int[] count = new int[numBuckets];

            // Count occurrences of each digit
            for (Long num : array) {
                int digit = (int) ((num >>> (d * bitsPerDigit)) & (numBuckets - 1));
                count[digit]++;
            }

            // Compute positions
            for (int i = 1; i < numBuckets; i++) {
                count[i] += count[i - 1];
            }

            // Place elements in output array according to count
            for (int i = n - 1; i >= 0; i--) {
                int digit = (int) ((array[i] >>> (d * bitsPerDigit)) & (numBuckets - 1));
                output[--count[digit]] = array[i];
            }

            // Swap array and output for next iteration
            Long[] temp = array;
            array = output;
            output = temp;
        }

        // Ensure the sorted array is in the input array
        if (numDigits % 2 == 1) {
            System.arraycopy(array, 0, output, 0, n);
        }
    }

////////////////////////////////////////////////////////


















































    //first code
    // public static void radixSort(Long[] array, int bitsPerDigit) {
    //     // Determine the number of digits needed
    //     int numDigits = (64 + bitsPerDigit - 1) / bitsPerDigit;
        
    //     // Initialize the count array
    //     int[] count = new int[1 << bitsPerDigit];
        
    //     // Initialize the temporary array
    //     Long[] temp = new Long[array.length];
        
    //     // Iterate through each digit
    //     for (int d = 0; d < numDigits; d++) {
    //         // Reset count array
    //         Arrays.fill(count, 0);
            
    //         // Count occurrences of each digit
    //         for (Long num : array) {
    //             int digit = (int) ((num >>> (d * bitsPerDigit)) & ((1 << bitsPerDigit) - 1));
    //             count[digit]++;
    //         }
            
    //         // Compute cumulative count
    //         for (int i = 1; i < count.length; i++) {
    //             count[i] += count[i - 1];
    //         }
            
    //         // Place elements in temporary array according to count
    //         for (int i = array.length - 1; i >= 0; i--) {
    //             int digit = (int) ((array[i] >>> (d * bitsPerDigit)) & ((1 << bitsPerDigit) - 1));
    //             temp[--count[digit]] = array[i];
    //         }
            
    //         // Copy elements from temporary array back to original array
    //         System.arraycopy(temp, 0, array, 0, array.length);
    //     }
    // }
    
    









//     // Using counting sort to sort the elements in the basis of significant places
//   private static void countingSort(int[] array, int size, int place) {
//       int[] output = new int[size + 1];
//       int max = array[0];
//       for (int i = 1; i < size; i++) {
//           if (array[i] > max)
//           max = array[i];
//         }
//         int[] count = new int[max + 1];
        
//         for (int i = 0; i < max; ++i)
//         count[i] = 0;
        
//         // Calculate count of elements
//         for (int i = 0; i < size; i++)
//         count[(array[i] / place) % 10]++;
        
//         // Calculate cumulative count
//         for (int i = 1; i < 10; i++)
//         count[i] += count[i - 1];
        
//         // Place the elements in sorted order
//         for (int i = size - 1; i >= 0; i--) {
//             output[count[(array[i] / place) % 10] - 1] = array[i];
//             count[(array[i] / place) % 10]--;
//         }
        
//         for (int i = 0; i < size; i++)
//         array[i] = output[i];
//     }
    
//     // Function to get the largest element from an array
//     private static Long getMax(Long array[], int n) {
//         Long max = array[0];
//         for (int i = 1; i < n; i++)
//         if (array[i] > max)
//         max = array[i];
//         return max;
//     }
    
//     // Main function to implement radix sort
//     public static void radixSort(Long array[], int size) {
//         // Get maximum element
//         Long max = getMax(array, size);
        
//         // Apply counting sort to sort elements based on place value.
//         for (int place = 1; max / place > 0; place *= 10)
//         countingSort(array, size, place);
//     }


    
    public static <T extends Comparable<T>> boolean isLessThan(T a, T b) {
        return a.compareTo(b) < 0;
    }
    
    public static <T extends Comparable<T>> boolean isGreaterThanOrEqual(T a, T b) {
        return (a.compareTo(b) > 0 || a.compareTo(b) == 0 );
    }
    
    
}
