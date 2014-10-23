/**
 * ************************************************
 * Author: David Keltgen * Class: Montana State University: CSCI 460 * File
 * Name: General_assignment_7_pt2.java * Date: October 17 2014 * Description:
 * This file is the main file for * running quicksort/selection sort * programs
 * in both their threaded and * nonthreaded forms to determine time *
 * efficiencies. *
 *************************************************
 */
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
        k = 241;
        listSizeArr = new int[k];
        timeResultsArr = new double[k];
        sFileName = "sortingspreadsheet.csv";
        numsorts = 4;
        sortType type = sortType.NONE;

        sortArr = new sortType[numsorts];
        sortArr[0] = sortType.QS_NT;
        sortArr[1] = sortType.QSSS_T;
        sortArr[2] = sortType.QS_T;
        sortArr[3] = sortType.QSSS_NT;

        /* Run the experiment for each sorting algorithm */
        for (g = 0; g < 4; g++) {
            type = sortArr[g];
            System.out.println("Starting " + type);
            /* Run the experiment k times for each sort*/
            for (i = 1; i < k; i+=5) {
                /*start with i = 0 for simplicity , increment by input cubed*/
                x = (int) Math.pow(i, 3);
                listSizeArr[i] = x;
                list = new int[x];

                /*Generate list with random vars */
                for (j = 0; j < x; j++) {
                    list[j] = generator.nextInt(n);
                }

                //System.out.println("Array before Sorting");
                //for (j = 0; j < list.length; j++) {
                //    System.out.print(list[j] + " ");
                //}
                //
                //System.out.println();
                //System.out.println();
                
                //record time
                start = System.nanoTime();

                /**
                 * ********************************************************
                 */
                /* Insert favorite sorting algorithm here                  */
                /* Unsorted list as input, also thread status if threaded  */
                /* *********************************************************/
                switch (type) {
                    case QS_NT:
                        QS_NT qs_nt = new QS_NT();
                        int var = qs_nt.quickSort(list, 0, list.length - 1);
                        break;
                    case QSSS_NT:
                        QSSS_NT qsss_nt = new QSSS_NT();
                        qsss_nt.quickSort(list, 0, list.length - 1);
                        break;
                    case QS_T:

                        QS_T qs_t = new QS_T(list, 0, list.length - 1);
                        qs_t.partition(list, 0, list.length - 1);
                        ThreadDemo T1_qs = new ThreadDemo("Thread-1, qs", qs_t.table, qs_t.first, qs_t.pivIndex - 1, sortType.QS_T);
                        ThreadDemo T2_qs = new ThreadDemo("Thread-2, qs", qs_t.table, qs_t.pivIndex + 1, qs_t.last, sortType.QS_T);
                        T1_qs.start();
                        T2_qs.start();
                        

                        //qs_t.quickSort(list, 0, list.length - 1);
                        break;
                    case QSSS_T:
                        
                        QSSS_T qsss_t = new QSSS_T(list, 0, list.length - 1);
                        qsss_t.partition(list, 0, list.length - 1);
                        ThreadDemo T1_qsss = new ThreadDemo("Thread-1, qsss", qsss_t.table, qsss_t.first, qsss_t.pivIndex - 1, sortType.QSSS_T);
                        ThreadDemo T2_qsss = new ThreadDemo("Thread-2, qsss", qsss_t.table, qsss_t.pivIndex + 1, qsss_t.last, sortType.QSSS_T);
                        T1_qsss.start();
                        T2_qsss.start();
                        break;
                    default:
                        System.out.println("There will be none of these sorts around here.");

                }

                //record time
                stop = System.nanoTime();

                //System.out.println("Array After Sorting");
                //for (j = 0; j < list.length; j++) {
                //    System.out.print(list[j] + " ");
                //}
                //
                //System.out.println();
                //System.out.println();                

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
            System.out.println("Ending   " + type + "\n");
        } // end for g
    } // end main

} // end class
