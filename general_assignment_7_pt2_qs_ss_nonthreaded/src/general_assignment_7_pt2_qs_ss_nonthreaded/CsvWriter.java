/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_assignment_7_pt2_qs_ss_nonthreaded;

import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author david
 */
public class CsvWriter {

    //instance vars
    boolean writeTimes;
    int column;
    int timesList[]; //list of times 
    int sizeList[];  //list of sizes of the arrays
    sortType algorithm;
    String filename;
    /*number of examples ran will not change with each sorting type */

    public CsvWriter(int in_sizeList[], int in_timesList[], int in_column, sortType in_algorithm, String in_filename) {
        sizeList = in_sizeList;
        timesList = in_timesList;
        column = in_column;
        algorithm = in_algorithm;
        writeTimes = true;
        filename = in_filename;
    }

    public CsvWriter(int in_timesList[], int in_column, sortType in_algorithm) {
        timesList = in_timesList;
        column = in_column;
        algorithm = in_algorithm;
    }

    public void generateCSVFile() {
        try {

            if (writeTimes) {
                
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        }
    }
