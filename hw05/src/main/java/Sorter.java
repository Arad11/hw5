import java.util.Random;
import java.util.Arrays;

public class Sorter {

    // public static <T extends Comparable<T>> void quickSort(T[] array) {
    //     quickSort(array, -1, array.length);
    // }

    // // quickSort

    // public static <T extends Comparable<T>> void quickSort(T[] array, int startIndex, int endIndex) {
    //     if (startIndex < endIndex - 2) {
    //         int currentPivotIndex = partition(array, startIndex, endIndex - 1);
    //         quickSort(array, startIndex, currentPivotIndex - 1);
    //         quickSort(array, currentPivotIndex + 1, endIndex);
    //     } else {
    //         simpleSort(array, startIndex, endIndex);
    //     }
    // } 

    // private static <T extends Comparable<T>> void simpleSort(T[] array, int startIndex, int endIndex) {
    //     if (array == null || startIndex >= endIndex) {
    //         return;
    //     }
    //     if (startIndex == -1) {
    //         startIndex++;
    //     }
    //     for (int i = startIndex; i < endIndex; i++) {
    //         for (int j = startIndex; j < endIndex - (i - startIndex); j++) {
    //             if(j + 1 < array.length) {
    //                 if (isGreaterThanOrEqual(array[j], array[j + 1])) {
    //                     swap(array, j, j + 1);
    //                 }
    //             }
    //         }
    //     }
    // }
    
    // private static <T extends Comparable<T>> int partition (T[] array, int startIndex, int endIndex) {
    //     Random rand = new Random();
    //     int randomIndex = rand.nextInt(0, endIndex);
    //     T pivot = array[randomIndex];
    //     int currentLastIndex = endIndex;
    //     int currentStartIndex = startIndex;

    //     swap(array, randomIndex, endIndex);

    //     while (true) {
    //         currentLastIndex--;
    //         while (isLessThan(array[currentLastIndex], pivot) || currentLastIndex < startIndex) {
    //             currentLastIndex--;
    //             if(currentLastIndex < 0) {
    //                 break;
    //             }
    //         }

    //         currentStartIndex++;
    //         while(!isLessThan(array[currentStartIndex], pivot) || currentStartIndex > endIndex) {
    //             currentStartIndex++;
    //             if(currentStartIndex >= array.length) {
    //                 break;
    //             }
    //         }

    //         if(currentStartIndex < currentLastIndex) {
    //             T temp = array[currentLastIndex];
    //             array[currentLastIndex] = array[currentStartIndex];
    //             array[currentStartIndex] = temp;
    //         } else {
    //             T temp = array[currentLastIndex + 1];
    //             array[currentLastIndex + 1] = pivot;
    //             array[endIndex] = temp;
    //             return currentLastIndex + 1;
    //         }

    //     }
    // }

    // private static <T> void swap(T[] array, int i, int j) {
    //     T temp = array[i];
    //     array[i] = array[j];
    //     array[j] = temp;
    // }




// optimzized code


    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        while (low < high) {
            if (high - low < 10) {
                insertionSort(array, low, high);
                break;
            } else {
                int pi = partition(array, low, high);
                if (pi - low < high - pi) {
                    quickSort(array, low, pi - 1);
                    low = pi + 1;
                } else {
                    quickSort(array, pi + 1, high);
                    high = pi - 1;
                }
            }
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = medianOfThree(array, low, high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static <T extends Comparable<T>> T medianOfThree(T[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        if (array[low].compareTo(array[mid]) > 0) swap(array, low, mid);
        if (array[low].compareTo(array[high]) > 0) swap(array, low, high);
        if (array[mid].compareTo(array[high]) > 0) swap(array, mid, high);
        swap(array, mid, high);
        return array[high];
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T extends Comparable<T>> void insertionSort(T[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= low && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }







// first cide


    
    // public static <T extends Comparable<T>> void quickSort(T[] array) {
    //     quickSort(array, 0, array.length - 1);
    // }
    
    // private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
    //     if (low < high) {
    //         int pi = partition(array, low, high);
    //         quickSort(array, low, pi - 1);
    //         quickSort(array, pi + 1, high);
    //     }
    // }
    
    // private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
    //     Random rand = new Random();
    //     // Select a random pivot index
    //     int pivotIndex = low + rand.nextInt(high - low + 1);
    //     T pivot = array[pivotIndex];

    //     // Swap pivot with the last element
    //     swap(array, pivotIndex, high);

    //     int i = low - 1;
    //     for (int j = low; j < high; j++) {
    //         if (array[j].compareTo(pivot) <= 0) {
    //             i++;
    //             swap(array, i, j);
    //         }
    //     }

    //     // Place the pivot element in the correct position
    //     swap(array, i + 1, high);
    //     return i + 1;
    // }

    // private static <T> void swap(T[] array, int i, int j) {
    //     T temp = array[i];
    //     array[i] = array[j];
    //     array[j] = temp;
    // }







    // public static void main(String[] args) {
    //     Integer[] arr = {12, 11, 13, 5, 6, 7};
    //     System.out.println("Given Array");
    //     System.out.println(Arrays.toString(arr));

    //     quickSort(arr);

    //     System.out.println("\nSorted array");
    //     System.out.println(Arrays.toString(arr));
    // }
    










    //merge sort not recursion

// optimzed code

    public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] arr) {
        int n = arr.length;
        @SuppressWarnings("unchecked")
        T[] tempArr = (T[]) new Comparable[n];
        for (int currSize = 1; currSize < n; currSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                merge(arr, tempArr, leftStart, mid, rightEnd);
            }
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] tempArr, int left, int mid, int right) {
        System.arraycopy(arr, left, tempArr, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (tempArr[i].compareTo(tempArr[j]) <= 0) {
                arr[k] = tempArr[i];
                i++;
            } else {
                arr[k] = tempArr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr[k] = tempArr[i];
            i++;
            k++;
        }

        while (j <= right) {
            arr[k] = tempArr[j];
            j++;
            k++;
        }
    }



    //first code:
   
    // public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] arr) {
    //     int n = arr.length;
    //     int curr_size;

    //     int left_start;

    //     for (curr_size = 1; curr_size <= n - 1; curr_size = 2 * curr_size) {
    //         for (left_start = 0; left_start < n - 1; left_start += 2 * curr_size) {
    //             int mid = Math.min(left_start + curr_size - 1, n - 1);
    //             int right_end = Math.min(left_start + 2 * curr_size - 1, n - 1);
    //             merge(arr, left_start, mid, right_end);
    //         }
    //     }
    // }

    // private static <T extends Comparable<T>> void merge(T[] arr, int l, int m, int r) {
    //     int n1 = m - l + 1;
    //     int n2 = r - m;

    //     T[] L = Arrays.copyOfRange(arr, l, m + 1);
    //     T[] R = Arrays.copyOfRange(arr, m + 1, r + 1);

    //     int i = 0, j = 0;
    //     int k = l;

    //     while (i < n1 && j < n2) {
    //         if (L[i].compareTo(R[j]) <= 0) {
    //             arr[k] = L[i];
    //             i++;
    //         } else {
    //             arr[k] = R[j];
    //             j++;
    //         }
    //         k++;
    //     }

    //     while (i < n1) {
    //         arr[k] = L[i];
    //         i++;
    //         k++;
    //     }

    //     while (j < n2) {
    //         arr[k] = R[j];
    //         j++;
    //         k++;
    //     }
    // }

    
    
    
    
    
    
    
    
    
    // radixSort
        //optimzed code:


    public static void radixSort(Long[] array, int bitsPerDigit) {
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
    
    
    public static void main(String args[]) {
        // Long[] first = {(long) 1,(long) 3,(long) 4,(long) 5,(long) 6};
        // Long[] second = {(long) 4, (long) 2, (long) 3, (long) 1, (long) 8};
        // Long[] third = {(long) 4, (long)2, (long) 2, (long) 9, (long) 6};
        
        // quickSort(first);
        // quickSort(second);
        // quickSort(third);
        
        
        Long[] data = { (long) 121, (long) 432, (long) 564, (long) 23, (long) 1, (long) 45, (long) 788 };
        int size = data.length;
        radixSort(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}
