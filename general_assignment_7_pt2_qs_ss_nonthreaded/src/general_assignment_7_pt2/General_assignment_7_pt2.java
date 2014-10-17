/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: General_assignment_7_pt2.java         *
 * Date:  October 17 2014                           *
 * Description: This file is the main file for      *
 *              running quicksort/selection sort    *
 *              programs in both their threaded and *
 *              nonthreaded forms to determine time *
 *              efficiencies.                       *
 **************************************************/

package general_assignment_7_pt2;

import java.util.*;

enum sortType {
    QS_NT, QSSS_NT, QS_T, QSSS_T, NONE;
}

/**
 *
 *
 * @author David Keltgen
 */
public class General_assignment_7_pt2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instance vars
        boolean writeNumElements = true;
        int g, i, j;  //counter variables
        int n;  // number of potential different random values;
        int m;  // seed for RNG
        int x;  // list size
        int k;  // number of times experiment is to be ran
        int numsorts; //number of different sorting algorithms to be implmented
        long y = 0;
        long start = 0;
        long stop = 0; // in milliseconds, time it took to sort
        int list[]; // array of values to be sorted
        int listSizeArr[]; // array of values of the size of the list for each exp.
        double timeResultsArr[]; // time values for different list sizes
        sortType sortArr[];
        String sFileName;
        

        /* Maybe user input in future? */
        n = 500;
        m = 314159;
        Random generator = new Random(m);
        k = 5;
        listSizeArr = new int[k];
        timeResultsArr = new double[k];
        sFileName = "sortingspreadsheet.csv";
        numsorts = 4;
        sortType type = sortType.NONE;
        
        sortArr = new sortType[numsorts];
        sortArr[0] = sortType.QS_NT;
        sortArr[1] = sortType.QSSS_NT;
        sortArr[2] = sortType.QS_T;
        sortArr[3] = sortType.QSSS_T;

        /* Run the experiment for each sorting algorithm */
        for (g = 0; g < 4; g++) {
            /* Run the experiment k times for each sort*/
            for (i = 0; i < k; i++) {
                /*start with i = 0 for simplicity , increment by input cubed*/
                x = (int) Math.pow(i, 3);
                listSizeArr[i] = x;
                list = new int[x];
                
                /*Generate list with random vars */
                for (j = 0; j < x; j++) {
                    list[j] = generator.nextInt(n);
                }

                //record time
                start = System.nanoTime();

                
                /***********************************************************/
                /* Insert favorite sorting algorithm here                  */
                /* Unsorted list as input, also thread status if threaded  */
                /* *********************************************************/
                type = sortArr[g];
                switch (type) {
                    case QS_NT:
                        QS_NT qs_nt = new QS_NT();
                        qs_nt.quickSort(list, 0, list.length - 1);
                        break;
                    case QSSS_NT:
                        QSSS_NT qsss_nt = new QSSS_NT();
                        qsss_nt.quickSort(list, 0, list.length - 1);
                        break;
                    case QS_T:
                        QS_T qs_t = new QS_T(list, 0, list.length - 1, false);
                        qs_t.quickSort(list, 0, list.length - 1);
                        break;
                    case QSSS_T:
                        QSSS_T qsss_t = new QSSS_T(list, 0, list.length - 1, false);
                        qsss_t.quickSort(list, 0, list.length - 1);
                        break;
                    default:
                        System.out.println("There will be none of these sorts around here.");

                }

                //record time
                stop = System.nanoTime();

                /*Get the time in seconds (ns / 1e9) */
                y = stop - start;
                double temp = (double) y / (double) 1000000000;
                timeResultsArr[i] = temp;

            }// end run experiment k times
            
            /* Finally write time values to a CSV file */
            /* First time write number of elements */
            if (writeNumElements) {
                CsvWriter writer = new CsvWriter(listSizeArr, timeResultsArr, type, sFileName);
                writer.generateCSVFile();
                writeNumElements = false;

              /* After the first sort, number of elements is the same, so dont write again */  
            } else {
                CsvWriter writer = new CsvWriter(timeResultsArr, type, sFileName);
                writer.generateCSVFile();
            }
        } // end for g
    } // end main

} // end class
