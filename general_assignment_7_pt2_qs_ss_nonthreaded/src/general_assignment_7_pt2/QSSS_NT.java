/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: QSSS_NT.java                          *
 * Date:  October 17 2014                           *
 * Description:  Nonthreaded implementation of      *
 *               quicksort algorithm with selection *
 *               sort used to sort the last 11      *
 *               values in a partition.             *
 **************************************************/

package general_assignment_7_pt2;

/**
 * This version of Quicksort will sort an array to the point where the size of
 * the partitions will be less than 11. After that, selection sort will be used
 * to finish it.
 *
 * @author David Keltgen Date: 10/5/14
 */
import java.util.*;

public class QSSS_NT {

    private int pivIndex;
    private int pivot;
    int up;
    int down;
    int statementCount;
    int i, j, temp1, temp2;
            
    public void quickSort(int[] table, int first, int last) {
        if (first < last) {
            //System.out.println("inside QSSS_NT");
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

    private int partition(int[] table, int first, int last) {
        pivot = table[first];
        up = first;
        down = last;
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

    private void swap(int[] table, int first, int last) {

        temp1 = table[first];
        table[first] = table[last];
        table[last] = temp1;
    }

    private void selectionSort(int[] table, int first, int last) {
        statementCount = 0;
        for (i = first; i <= last - 2; i++) {
            statementCount++;
            for (j = i + 1; j <= last - 1; j++) {
                statementCount++;
                if (table[j] < table[i]) {
                    temp2 = table[i];
                    table[i] = table[j];
                    table[j] = temp2;
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
