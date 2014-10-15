/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package general_assignment_7_pt2_qs_ss_nonthreaded;

import java.util.*;

enum sortType {
    QS_NT, QSSS_NT, QS_T, QSSS_T;  
}


/**
 * 
 *
 * @author david
 */
public class General_assignment_7_pt2_qs_ss_nonthreaded {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instance vars
        int i,j;  //counter variables
        int n;  // number of potential different random values;
        int m;  // seed for RNG
        int x;  // list size
        int k;  // number of times experiment is to be ran
        long y, start, stop; // in milliseconds, time it took to sort
        int list[]; // array of values to be sorted
        int listSizeArr[]; // array of values of the size of the list for each exp.
        long timeResultsArr[]; // time values for different list sizes
        String sFileName = "";
        int 
        
        /* Maybe enable user input? */
        n = 500;
        m = 314159;
        Random generator = new Random(m);
        k = 50;
        listSizeArr = new int[k];
        timeResultsArr = new long[k];
        
        
        /* Run the experiment k times */
        for(i = 0; i < k; i++)
        {
        /*start with i = 0 for simplicity */
            x = (i^2) + 1;
            listSizeArr[i] = x;
            list = new int[x];
        /*Generate list with random vars */
        for(j = 0; j < x; j++)
        {
            list[j] = generator.nextInt(n);
        }
        //record time
        start = System.currentTimeMillis();
        
        /**********************************************/
        /* Insert favorite sorting algorithm here */
        /* Unsorted list as input */
        /**********************************************/
        
        //record time
        stop = System.currentTimeMillis();
        
        //elapsed time = end time - start time
        y = stop - start;
        timeResultsArr[i] = y;
        
        
        } // end run experiment k times
        if()
        CsvWriter(listSizeArr, timeResultsArr);
    }
    
}
