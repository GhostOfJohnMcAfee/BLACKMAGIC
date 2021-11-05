public class SortingEngine<T extends Comparable<? super T>> {
    
	//Sorting Engine can sort arrays of integers, double, an strings using a variety of sorting methods.
	
	public static void main(String[] args)
    {
        // example using Strings
        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};
        SortingEngine<String> stringSorter   = new SortingEngine<>();
        stringSorter.quickSort(arrayOfStrings, 0, arrayOfStrings.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        // example using Doubles
        Double[] arrayOfDoubles = {0.35, 0.02, 0.36, 0.82, 0.27, 0.49, 0.41, 0.17, 0.30, 0.89, 0.37, 0.66, 0.82, 0.17, 0.20, 0.96, 0.18, 0.25, 0.37, 0.52};
        SortingEngine<Double> doubleSorter   = new SortingEngine<>();
        doubleSorter.quickSort(arrayOfDoubles, 0, arrayOfDoubles.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfDoubles));
    }

    
    //Quick Sort Algorithm implementation with Generics.
    public void quickSort(T[] array, int startIndex, int endIndex)
    {
        // verify that the start and end index have not overlapped
        if (startIndex < endIndex)
        {
            // calculate the pivotIndex
            int pivotIndex = partition(array, startIndex, endIndex);
            // sort the left sub-array
            quickSort(array, startIndex, pivotIndex);
            // sort the right sub-array
            quickSort(array, pivotIndex + 1, endIndex);
        }
    }

    //Sub-method called by Quick Sort.
    private int partition(T[] array, int startIndex, int endIndex)
    {
        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true)
        {
            // start at the FIRST index of the sub-array and increment
            // FORWARD until we find a value that is > pivotValue
            do startIndex++; while (array[startIndex].compareTo(pivotValue) < 0) ;

            // start at the LAST index of the sub-array and increment
            // BACKWARD until we find a value that is < pivotValue
            do endIndex--; while (array[endIndex].compareTo(pivotValue) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            // swap values at the startIndex and endIndex
            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }
    
    //Selection Sort method with Generics.
    public void selectionSort(T[] array)
    {
        // step 1: loop from the beginning of the array to the second to last item
        for (int currentIndex = 0; currentIndex < array.length - 1; currentIndex++)
        {
            // step 2: save a copy of the currentIndex
            int minIndex = currentIndex;
            // step 3: loop through all indexes that proceed the currentIndex
            for (int i = currentIndex + 1; i < array.length; i++)
            {
                // step 4:  if the value of the index of the current loop is less
                //          than the value of the item at minIndex, update minIndex
                //          with the new lowest value index */
                if (array[i].compareTo(array[minIndex]) < 0)
                {
                    // update minIndex with the new lowest value index
                    minIndex = i;
                }
            }
            // step 5: if minIndex has been updated, swap the values at minIndex and currentIndex
            if (minIndex != currentIndex)
            {
                T temp = array[currentIndex];
                array[currentIndex] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    
    
    //Bubble Sort method with Generics.
    public void bubbleSort(T[] array)
    {
        int n = array.length;
        while (n > 0)
        {
            int lastModifiedIndex = 0;
            for (int currentIndex = 1; currentIndex < n; currentIndex++)
            {
                // if the item at the previous index is greater than the item at the `currentIndex`, swap them
                if (array[currentIndex - 1].compareTo(array[currentIndex]) > 0)
                {
                    // swap
                    T temp = array[currentIndex - 1];
                    array[currentIndex - 1] = array[currentIndex];
                    array[currentIndex] = temp;
                    // save the index that was modified
                    lastModifiedIndex = currentIndex;
                }
            }
            // save the last modified index so we know not to iterate past it since all proceeding values are sorted
            n = lastModifiedIndex;
        }
    }
    
    //Insertion Sort method with Generics.
    public void insertionSort(T[] array)
    {
        // start at the first index and iterate through to the end
        for (int i = 1; i < array.length; i++)
        {
            int currentIndex = i;
            /*
             * Check:
             *      1. that currentIndex is at least 1
             *      2. that the item directly before the currentIndex is greater than the item at currentIndex
             *
             * If both conditions are met, swap the indexes
             */
            while (currentIndex > 0 && array[currentIndex - 1].compareTo(array[currentIndex]) > 0)
            {
                T temp = array[currentIndex];
                array[currentIndex] = array[currentIndex - 1];
                array[currentIndex - 1] = temp;
                currentIndex--;
            }
        }
    }
    
    //Heap Sort method with Generics.
    public void heapSort(T[] array)
    {
        int size = array.length;

        // build heapSort (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(array, size, i);

        // one by one extract an element from heapSort
        for (int i = size - 1; i >= 0; i--)
        {
            // move current root to end
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // call max heapify on the reduced heapSort
            heapify(array, i, 0);
        }
    }
    
    //Sub-method for Heap Sort.
    // to heapify a subtree rooted with node i which is an index in array[]
    private void heapify(T[] array, int size, int i)
    {
        int max   = i; // initialize max as root
        int left  = 2 * i + 1;
        int right = 2 * i + 2;

        // if left child is larger than root
        if (left < size && array[left].compareTo(array[max]) > 0)
            max = left;

        // if right child is larger than max
        if (right < size && array[right].compareTo(array[max]) > 0)
            max = right;

        // if max is not root
        if (max != i)
        {
            // swap
            T temp = array[i];
            array[i] = array[max];
            array[max] = temp;

            // recursively heapify the affected sub-tree
            heapify(array, size, max);
        }
    }
    
    //Cycle Sort method with Generics.
    public void cycleSort(T[] array)
    {
        // loop from the beginning of the array to the second to last item
        for (int currentIndex = 0; currentIndex < array.length - 1; currentIndex++)
        {
            // save the value of the item at the currentIndex
            T   item             = array[currentIndex];
            int currentIndexCopy = currentIndex;

            // loop through all indexes that proceed the currentIndex
            for (int i = currentIndex + 1; i < array.length; i++)
                if (array[i].compareTo(item) < 0)
                    currentIndexCopy++;

            // if currentIndexCopy has not changed, the item at the currentIndex is already in the correct currentIndexCopy
            if (currentIndexCopy == currentIndex)
                continue;

            // skip duplicates
            while (item == array[currentIndexCopy])
                currentIndexCopy++;

            // swap
            T temp = array[currentIndexCopy];
            array[currentIndexCopy] = item;
            item = temp;

            // repeat above steps as long as we can find values to swap
            while (currentIndexCopy != currentIndex)
            {
                currentIndexCopy = currentIndex;
                // loop through all indexes that proceed the currentIndex
                for (int i = currentIndex + 1; i < array.length; i++)
                    if (array[i].compareTo(item) < 0)
                        currentIndexCopy++;

                // skip duplicates
                while (item == array[currentIndexCopy])
                    currentIndexCopy++;

                // swap
                temp = array[currentIndexCopy];
                array[currentIndexCopy] = item;
                item = temp;
            }
        }
    }
    
    //Merge Sort method using Generics.
    public void mergeSort(T[] array, int start, int end)
    {
        // base case
        if (start < end)
        {
            // find the middle point
            int middle = (start + end) / 2;

            mergeSort(array, start, middle); // sort first half
            mergeSort(array, middle + 1, end);  // sort second half

            // merge the sorted halves
            merge(array, start, middle, end);
        }
    }

    //Sub-method called by Merge Sort.
    // merges two subarrays of array[].
    private void merge(T[] array, int start, int middle, int end)
    {
        T[] leftArray  = (T[]) new Comparable[middle - start + 1];
        T[] rightArray = (T[]) new Comparable[end - middle];

        // fill in left array
        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = array[start + i];

        // fill in right array
        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = array[middle + 1 + i];

        /* Merge the temp arrays */

        // initial indexes of first and second subarrays
        int leftIndex = 0, rightIndex = 0;

        // the index we will start at when adding the subarrays back into the main array
        int currentIndex = start;

        // compare each index of the subarrays adding the lowest value to the currentIndex
        while (leftIndex < leftArray.length && rightIndex < rightArray.length)
        {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0)
            {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else
            {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        // copy remaining elements of leftArray[] if any
        while (leftIndex < leftArray.length) array[currentIndex++] = leftArray[leftIndex++];

        // copy remaining elements of rightArray[] if any
        while (rightIndex < rightArray.length) array[currentIndex++] = rightArray[rightIndex++];
    }
    
    //Shell Sort method with Generics.
    public void shellSort(T[] array)
    {
        /*
         * for-loop setup:
         *      1. set the gapSize to the length of the array / 2
         *      2. run the loop as long as gapSize > 0
         */
        for (int gapSize = array.length / 2; gapSize > 0; gapSize /= 2)
        {
            for (int currentIndex = gapSize; currentIndex < array.length; currentIndex++)
            {
                // save the currentIndex
                int currentIndexCopy = currentIndex;
                // save the value of the currentIndex
                T item = array[currentIndex];
                while (currentIndexCopy >= gapSize && array[currentIndexCopy - gapSize].compareTo(item) > 0)
                {
                    array[currentIndexCopy] = array[currentIndexCopy - gapSize];
                    currentIndexCopy -= gapSize;
                }
                array[currentIndexCopy] = item;
            }
        }
    }
    
}