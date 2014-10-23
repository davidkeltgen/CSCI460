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
    private static int level = 0;
    int[] table;
    int first;
    int last;
    int pivIndex;
    int pivot;
    int up;
    int down;
    int temp;
    public QS_NT()
    {
    }
    public QS_NT(int[] in_table, int in_first, int in_last) {
        table = in_table;
        first = in_first;
        last = in_last;
    }
    
    public int quickSort(int[] table, int first, int last) {
        if (first < last) {
            level++;
            //System.out.println("inside QS_NT");
            pivIndex = partition(table, first, last);

            quickSort(table, first, pivIndex - 1);

            quickSort(table, pivIndex + 1, last);
            
            return 0;
        }
        else
        {
            return 0;
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

        temp = table[first];
        table[first] = table[last];
        table[last] = temp;
    }
}
