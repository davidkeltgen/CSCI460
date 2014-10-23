/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: QSSS_T.java                          *
 * Date:  October 17 2014                           *
 * Description:  Threaded implementation of      *
 *               quicksort algorithm with selection *
 *               sort used to sort the last 11      *
 *               values in a partition.             *
 **************************************************/
package general_assignment_7_pt2;


/**
 *
 * @author david
 */
public class QSSS_T {

    int[] table;
    int first;
    int last;
    int pivIndex;
    //boolean threadsCreated;

    public QSSS_T(int[] in_table, int in_first, int in_last) {
        table = in_table;
        first = in_first;
        last = in_last;
        //threadsCreated = in_threadsCreated;
        
    }

    public void quickSort(int[] table, int first, int last) {
        if (first < last) {
            //System.out.println("inside QS_NT");
            int pivIndex = partition(table, first, last);
            /* If this is the first time going into quicksort, create two threads, that will
             * each handle one half of the table, or 1 partition each */
            /*if (threadsCreated == false) {
                //System.out.println("inside threadsCreated");
                //threadsCreated = true;
                ThreadDemo T1 = new ThreadDemo("Thread-1", this.table, first, pivIndex - 1, sortType.QSSS_T);
                ThreadDemo T2 = new ThreadDemo("Thread-2", this.table, pivIndex + 1, last, sortType.QSSS_T);
                T2.start();
                T1.start();
                /* Else, runThread is true, the threads have been created, and each thread
                 * will be working on its assigned partition as normal */
            //} else {
                /* If partition size greater than 11 */

                if ((pivIndex - 1) - first > 11) {
                    quickSort(table, first, pivIndex - 1);
                    quickSort(table, pivIndex + 1, last);
                } else /* If smaller, use selection sort */ {
                    //System.out.println("Partition smaller than 11, use Selection Sort");
                    selectionSort(table, first, pivIndex - 1);
                    selectionSort(table, pivIndex + 1, last);
                }
            //}
        }
    }

    public int partition(int[] table, int first, int last) {
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

    public void swap(int[] table, int first, int last) {

        int temp = table[first];
        table[first] = table[last];
        table[last] = temp;
    }

    public void selectionSort(int[] table, int first, int last) {
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
