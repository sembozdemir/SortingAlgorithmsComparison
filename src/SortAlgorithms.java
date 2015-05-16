/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Semih Bozdemir
 */
public class SortAlgorithms {
    // Bubble Sort Algorihm
    public static int bubbleSort(int a[], int n) {
        int i, j;
        int temp = 0; // for swapping
        int keyCompCount = 0;
        for (i = 0; i < n; i++) {
            for (j = 1; j < (n - i); j++) {
                if (a[j - 1] > a[j]) { // basic operation
                    keyCompCount++;// increment key comparision
                    // swap elements
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return keyCompCount;
    }

    // Quick Sort Algorithm
    public static int quickSort(int arr[], int left, int right, int keyCompCount) {

        int[] returnedValues = partition(arr, left, right, keyCompCount);
        int index = returnedValues[0];
        int count = returnedValues[1];
        if (left < index - 1) {
            quickSort(arr, left, index - 1, count);
        }

        if (index < right) {
            quickSort(arr, index, right, count);
        }
        return count;

    }

    public static int[] partition(int arr[], int left, int right, int keyCompCount) {
        int i = left, j = right, count = keyCompCount;
        int[] returnValues = new int[2];
        int tmp;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot) { // basic operation
                i++;
                count++;
            }
            while (arr[j] > pivot) { // basic operation
                j--;
                count++;
            }
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };
        returnValues[0] = i;
        returnValues[1] = count;
        return returnValues;

    }
}
