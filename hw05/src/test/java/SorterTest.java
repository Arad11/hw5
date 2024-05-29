import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    final static long FIXED_RANDOMNESS_SEED = 12345L;
    Random randomGenerator;

    @BeforeEach
    void setUp() {
        randomGenerator = new Random(FIXED_RANDOMNESS_SEED);
    }

    // @Test
    // void testQuicksortOnSmallArrayWithNarrowRangeOfValues() {
    //     Long[] array = randomArray(30, 100, 120);
    //     Sorter.quickSort(array);
    //     assertTrue(isSorted(array), "Quicksort did not sort the array");
    // }

    // @Test
    // void testMergeSortOnMediumArrayWithWideRangeOfValues() {
    //     Long[] array = randomArray(1024, 0, 1_000_000);
    //     Sorter.mergeSortNoRecursion(array);
    //     assertTrue(isSorted(array), "Mergesort did not sort the array");
    // }

    @Test
    public void atestRadixSortLargeArray() {
        Long[] array = new Long[100000];
        int bitsPerDigit = 4;

        // Fill the array with random numbers
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * Long.MAX_VALUE);
        }

        Long[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy); // Sort using built-in sort for comparison

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(arrayCopy, array);
    }

    @Test
    public void btestRadixSortPerformance() {
        Long[] array = new Long[1000000];
        int bitsPerDigit = 8;

        // Fill the array with random numbers
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * Long.MAX_VALUE);
        }

        long startTime = System.currentTimeMillis();
        Sorter.radixSort(array, bitsPerDigit);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken for sorting 1,000,000 elements: " + (endTime - startTime) + " milliseconds");

        // Ensure array is sorted
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i - 1] <= array[i]);
        }
    }

    @Test
    public void ctestRadixSortDuplicateValues() {
        Long[] array = {5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L};
        int bitsPerDigit = 4;
        Long[] expected = {5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }

    @Test
    public void gtestRadixSortNegativeValues() {
        Long[] array = {-10L, -5L, -3L, -15L, -20L, -1L, -8L, -12L, -5L, -3L};
        int bitsPerDigit = 4;
        Long[] expected = {-20L, -15L, -12L, -10L, -8L, -5L, -5L, -3L, -3L, -1L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }

    
    @Test
    public void testRadixSort() {
        Long[] array = {170L, 45L, 75L, 90L, 802L, 24L, 2L, 66L};
        int bitsPerDigit = 4;
        Long[] expected = {2L, 24L, 45L, 66L, 75L, 90L, 170L, 802L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }

    @Test
    public void testRadixSortEmptyArray() {
        Long[] array = {};
        int bitsPerDigit = 4;

        Sorter.radixSort(array, bitsPerDigit);

        assertTrue(array.length == 0);
    }

    @Test
    public void testRadixSortSingleElementArray() {
        Long[] array = {5L};
        int bitsPerDigit = 4;

        Sorter.radixSort(array, bitsPerDigit);

        assertTrue(array.length == 1);
        assertEquals(Long.valueOf(5), array[0]);
    }

    @Test
    public void testRadixSortAlreadySortedArray() {
        Long[] array = {1L, 2L, 3L, 4L, 5L};
        int bitsPerDigit = 4;
        Long[] expected = {1L, 2L, 3L, 4L, 5L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }


    @Test
    void atestRadixSort() {
        Long[] array = randomArray(20, 0, 19);
        Sorter.radixSort(array, 2);
        assertTrue(isSorted(array), "Radix sort did not sort the array:\n" + Arrays.toString(array));
    }

    
    @Test
    public void testRadixSortLargeArray() {
        Long[] array = new Long[10000];
        int bitsPerDigit = 4;

        // Fill the array with random numbers
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * Long.MAX_VALUE);
        }

        Long[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy); // Sort using built-in sort for comparison

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(arrayCopy, array);
    }

    @Test
    public void testRadixSortPerformance() {
        Long[] array = new Long[1000000];
        int bitsPerDigit = 8;

        // Fill the array with random numbers
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * Long.MAX_VALUE);
        }

        long startTime = System.currentTimeMillis();
        Sorter.radixSort(array, bitsPerDigit);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken for sorting 1,000,000 elements: " + (endTime - startTime) + " milliseconds");

        // Ensure array is sorted
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i - 1] <= array[i]);
        }
    }

    @Test
    public void testRadixSortDuplicateValues() {
        Long[] array = {5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L};
        int bitsPerDigit = 4;
        Long[] expected = {5L, 5L, 5L, 5L, 5L, 5L, 5L, 5L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }

    @Test
    public void testRadixSortNegativeValues() {
        Long[] array = {-10L, -5L, -3L, -15L, -20L, -1L, -8L, -12L};
        int bitsPerDigit = 4;
        Long[] expected = {-20L, -15L, -12L, -10L, -8L, -5L, -3L, -1L};

        Sorter.radixSort(array, bitsPerDigit);

        assertArrayEquals(expected, array);
    }

    private Long[] randomArray(int length, long lowerBound, long upperBound) {
        if (length < 0) {
            throw new IllegalArgumentException("An array length can't be negative");
        }
        return randomGenerator.longs(length, lowerBound, upperBound).boxed().toArray(Long[]::new);
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

}
