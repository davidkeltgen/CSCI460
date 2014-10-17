/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt2;

import java.util.*;

enum sortType {

    QS_NT, QSSS_NT, QS_T, QSSS_T, NONE;
}

/**
 *
 *
 * @author david
 */
public class General_assignment_7_pt2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instance vars
        boolean writeTimes = true;
        int g, i, j;  //counter variables
        int n;  // number of potential different random values;
        int m;  // seed for RNG
        int x;  // list size
        int k;  // number of times experiment is to be ran
        int numsorts;
        long y = 0;
        long start = 0;
        long stop = 0; // in milliseconds, time it took to sort
        int list[]; // array of values to be sorted
        int listSizeArr[]; // array of values of the size of the list for each exp.
        double timeResultsArr[]; // time values for different list sizes
        sortType sortArr[];
        String sFileName;
        sortType type = sortType.NONE;

        /* Maybe enable user input? */
        n = 500;
        m = 314159;
        Random generator = new Random(m);
        k = 5;
        listSizeArr = new int[k];
        timeResultsArr = new double[k];
        sFileName = "sortingspreadsheet.csv";

        numsorts = 4;
        sortArr = new sortType[4];
        sortArr[0] = sortType.QS_NT;
        sortArr[1] = sortType.QSSS_NT;
        sortArr[2] = sortType.QS_T;
        sortArr[3] = sortType.QSSS_T;

        for (g = 0; g < 4; g++) {
            /* Run the experiment k times */
            for (i = 0; i < k; i++) {
                /*start with i = 0 for simplicity */
                x = (int) Math.pow(i, 2);
                listSizeArr[i] = x;
                list = new int[x];
                /*Generate list with random vars */
                for (j = 0; j < x; j++) {
                    list[j] = generator.nextInt(n);
                }

                System.out.println();
                System.out.println();
                //record time
                start = System.nanoTime();

                /**
                 * *******************************************
                 */
                /* Insert favorite sorting algorithm here */
                /* Unsorted list as input */
                /**
                 * *******************************************
                 */
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
                        System.out.println("None of these sorts");

                }

                //record time
                stop = System.nanoTime();

                //elapsed time = end time - start time
                y = stop - start;
                double temp = (double) y / (double) 1000000000;
               // System.out.println("temp is:  " + temp);
                timeResultsArr[i] = temp;
               // System.out.println("stop start y");
               // System.out.println(stop + "  " + start + "  " + y);

                //System.out.println("sorted");
                for (j = 0; j < list.length; j++) {
                //    System.out.format("%d ", list[j]);
                }
               // System.out.println();
               //System.out.println();

            }// end run experiment k times
           // System.out.println("listSizeArr");
           // for (j = 0; j < listSizeArr.length; j++) {
            //    System.out.format("%d ", listSizeArr[j]);
            //}
           // System.out.println();
           // System.out.println();

           // for (j = 0; j < timeResultsArr.length; j++) {
           //     System.out.format("%f ", timeResultsArr[j]);
           // }
            
            if (writeTimes) {
                CsvWriter writer = new CsvWriter(listSizeArr, timeResultsArr, type, sFileName);
                writer.generateCSVFile();
                writeTimes = false;

            } else {
                CsvWriter writer = new CsvWriter(timeResultsArr, type, sFileName);
                writer.generateCSVFile();
            }
        } // end for g
    } // end main

} // end class
