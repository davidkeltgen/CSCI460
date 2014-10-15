/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package general_assignment_7_pt2_qs_ss_nonthreaded;

/**
 * This version of Quicksort will sort an array to the point where the size of
 * the partitions will be less than 11. After that, selection sort will be used
 * to finish it.
 *
 * @author David Keltgen Date: 10/5/14
 */
import java.util.*;

public class QSSS_NT {

    int pivIndex;

    private static void quickSort(int[] table, int first, int last) {
        if (first < last) {
            int pivIndex = partition(table, first, last);
            /*If partition size greater than 11 */
            if ((pivIndex - 1) - first > 11) {
                quickSort(table, first, pivIndex - 1);
                quickSort(table, pivIndex + 1, last);
            } else /* If smaller, use selection sort */ {
                selectionSort(table, first, pivIndex - 1);
                selectionSort(table, pivIndex + 1, last);
            }
        }
    }

    private static int partition(int[] table, int first, int last) {
        int pivot = table[first];
        int up = first;
        int down = last;
        do {
            while ((up < last) && (pivot >= table[up])) {
                up++;
            }
            while (pivot < table[down]) {
                down--;
            }
            if (up < down) {
                swap(table, up, down);
            }
        } while (up < down);
        swap(table, first, down);
        return down;
    }

    private static void swap(int[] table, int first, int last) {

        int temp = table[first];
        table[first] = table[last];
        table[last] = temp;
    }

    private static void selectionSort(int[] table, int first, int last) {
        int statementCount = 0;
        int i, j, temp;
        for (i = first; i <= last - 2; i++) {
            statementCount++;
            for (j = i + 1; j <= last - 1; j++) {
                statementCount++;
                if (table[j] < table[i]) {
                    temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                    statementCount = statementCount + 3;
                }
                statementCount++; // add one for execution of the if statement
                // itself
            }
            statementCount++; // add one for the last execution of the inner
            // loop statement that kicks execution out of the loop
        }
        statementCount++; // add one for the last execution of the outer
        // loop statement that kicks execution out of the loop
    }
}
