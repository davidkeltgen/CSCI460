/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt2_qs_ss_nonthreaded;

/**
 * This version of Quicksort will simply take an array with random integers in
 * it and sort it.
 *
 * @author David Keltgen Date: 10/5/14
 */
import java.util.*;

public class QS_NT {

    int pivIndex;

    private static void quickSort(int[] table, int first, int last) {
        if (first < last) {
            int pivIndex = partition(table, first, last);

            quickSort(table, first, pivIndex - 1);

            quickSort(table, pivIndex + 1, last);
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
}
