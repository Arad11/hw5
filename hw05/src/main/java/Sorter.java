import java.util.Random;
import java.util.Arrays;

public class Sorter {

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, -1, array.length);
    }

    // quickSort

    public static <T extends Comparable<T>> void quickSort(T[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex - 2) {
            int currentPivotIndex = partition(array, startIndex, endIndex - 1);
            quickSort(array, startIndex, currentPivotIndex - 1);
            quickSort(array, currentPivotIndex + 1, endIndex);
        } else {
            simpleSort(array, startIndex, endIndex);
        }
    } 

    private static <T extends Comparable<T>> void simpleSort(T[] array, int startIndex, int endIndex) {
        if (array == null || startIndex >= endIndex) {
            return;
        }
        if (startIndex == -1) {
            startIndex++;
        }
        for (int i = startIndex; i < endIndex; i++) {
            for (int j = startIndex; j < endIndex - (i - startIndex); j++) {
                if(j + 1 < array.length) {
                    if (isGreaterThanOrEqual(array[j], array[j + 1])) {
                        swap(array, j, j + 1);
                    }
                }
            }
        }
    }
    
    private static <T extends Comparable<T>> int partition (T[] array, int startIndex, int endIndex) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(0, endIndex);
        T pivot = array[randomIndex];
        int currentLastIndex = endIndex;
        int currentStartIndex = startIndex;

        swap(array, randomIndex, endIndex);

        while (true) {
            currentLastIndex--;
            while (isLessThan(array[currentLastIndex], pivot) || currentLastIndex < startIndex) {
                currentLastIndex--;
                if(currentLastIndex < 0) {
                    break;
                }
            }

            currentStartIndex++;
            while(!isLessThan(array[currentStartIndex], pivot) || currentStartIndex > endIndex) {
                currentStartIndex++;
                if(currentStartIndex >= array.length) {
                    break;
                }
            }

            if(currentStartIndex < currentLastIndex) {
                T temp = array[currentLastIndex];
                array[currentLastIndex] = array[currentStartIndex];
                array[currentStartIndex] = temp;
            } else {
                T temp = array[currentLastIndex + 1];
                array[currentLastIndex + 1] = pivot;
                array[endIndex] = temp;
                return currentLastIndex + 1;
            }

        }
    }


    // public static <T extends Comparable<T>> void mergeSortNoRecursion(T[] array) {
    //     if (array == null || array.length <= 1) {
    //         return;
    //     }

    //     int n = array.length;
    //     T[] tempArray = Arrays.copyOf(array, n);
    //     int size, start
    // }



    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    
    //
    
    
    
    
    
    
    
    
    
    // radixSort
    public static void radixSort(Long[] array, int bitsPerDigit) {
        // Determine the number of digits needed
        int numDigits = (64 + bitsPerDigit - 1) / bitsPerDigit;
        
        // Initialize the count array
        int[] count = new int[1 << bitsPerDigit];
        
        // Initialize the temporary array
        Long[] temp = new Long[array.length];
        
        // Iterate through each digit
        for (int d = 0; d < numDigits; d++) {
            // Reset count array
            Arrays.fill(count, 0);
            
            // Count occurrences of each digit
            for (Long num : array) {
                int digit = (int) ((num >>> (d * bitsPerDigit)) & ((1 << bitsPerDigit) - 1));
                count[digit]++;
            }
            
            // Compute cumulative count
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            
            // Place elements in temporary array according to count
            for (int i = array.length - 1; i >= 0; i--) {
                int digit = (int) ((array[i] >>> (d * bitsPerDigit)) & ((1 << bitsPerDigit) - 1));
                temp[--count[digit]] = array[i];
            }
            
            // Copy elements from temporary array back to original array
            System.arraycopy(temp, 0, array, 0, array.length);
        }
    }
    
    
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