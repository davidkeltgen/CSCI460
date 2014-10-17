/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: QS_NT.java                          *
 * Date:  October 17 2014                           *
 * Description:  Nonthreaded implementation of      *
 *               quicksort algorithm.              *
 **************************************************/
package general_assignment_7_pt2;

public class QS_NT {
    
    int[] table;
    int first;
    int last;
    int pivIndex;
    public QS_NT()
    {
    }
    public QS_NT(int[] in_table, int in_first, int in_last) {
        table = in_table;
        first = in_first;
        last = in_last;
    }

    public void quickSort(int[] table, int first, int last) {
        if (first < last) {
            System.out.println("inside QS_NT");
            int pivIndex = partition(table, first, last);

            quickSort(table, first, pivIndex - 1);

            quickSort(table, pivIndex + 1, last);
        }
    }

    private int partition(int[] table, int first, int last) {
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

    private void swap(int[] table, int first, int last) {

        int temp = table[first];
        table[first] = table[last];
        table[last] = temp;
    }
}
