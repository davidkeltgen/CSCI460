/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt1_qs_ss_threaded;

/**
 * This version of Quicksort will sort an array to the point where the size of
 * the partitions will be less than 11. After that, selection sort will be used
 * to finish it.
 *
 * @author David Keltgen Date: 10/5/14
 */
import java.util.*;

public class Main {

    int pivIndex;

    /*Will be used in the task of creating threads to run the quicksort */
    static boolean threadsCreated = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Random generator = new Random(314159);
         int listSize = 100;
         int[] table = new int[listSize];
         int i = 0;
         long start, end, diff;
         while (i < listSize) {
         table[i] = generator.nextInt((1000 - 500) + 1) + 500;  //generates random numbers for each element of the list
         i++;
         }
         System.out.println("Array Before Sorting");

         for (i = 0; i < table.length; i++) {
         System.out.print(table[i] + " ");
         }
         System.out.println();
         
         start = System.currentTimeMillis();
         
         ThreadedQuickSort newTQS = new ThreadedQuickSort(table, 0, table.length -1);
         newTQS.quickSort(table, 0, table.length - 1);
         
         end = System.currentTimeMillis();
         
         diff = end - start;
         System.out.println("Difference in milli seconds: " + diff);
         System.out.println("Array After Sorting");
         for (i = 0; i < table.length; i++) {
         System.out.print(table[i] + " ");
         }
    }
}

    
