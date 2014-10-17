/**************************************************
 * Author: David Keltgen                            *
 * Class: Montana State University: CSCI 460        *
 * File Name: CSVWriter.java                        *
 * Date:  October 17 2014                           *
 * Description:  CSVWriter takes in a sorting       *
 *               list size,the time it took of each *
 *               sorting of that respective size,   *
 *               and the filename that it is writing*
 *               to and the type of sorting used    *
 *               It then compiles those values into *
 *               a comma separated value file that  *
 *               then can be read by a spreadsheet  *
 *               program to get a visual            *
 *               representation of timing of        *
 *               different sorting methods          *
 *                                                  *
 **************************************************/


package general_assignment_7_pt2;

import java.io.*;
import java.io.IOException;

/**
 *
 * @author David Keltgen
 */
public class CsvWriter {

    //instance vars
    boolean writeTimes;
    double timesList[]; //list of times 
    int sizeList[];  //list of sizes of the arrays
    sortType algorithm;
    String filename;
    /*number of examples ran will not change with each sorting type */

    public CsvWriter(int in_sizeList[], double in_timesList[], sortType in_algorithm, String in_filename) {
        sizeList = in_sizeList;
        timesList = in_timesList;
        algorithm = in_algorithm;
        writeTimes = true;
        filename = in_filename;
    }

    public CsvWriter(double in_timesList[], sortType in_algorithm, String in_filename) {
        timesList = in_timesList;
        algorithm = in_algorithm;
        filename = in_filename;
    }

    public void generateCSVFile() {
        
        int i;
        try {
            FileWriter writer = new FileWriter(filename, true);
            PrintWriter out = new PrintWriter(writer);
            
            if (writeTimes) {
                out.format("# of values,");
                for(i = 0; i < sizeList.length; i++)
                {
                    if(i == sizeList.length - 1)
                        out.format("%d", sizeList[i]);
                    else
                    out.format("%d,", sizeList[i]);
                }
                out.format("\n");
                writeTimes = false;
            }
            out.format("%s,", algorithm);
            for(i = 0; i < timesList.length; i++)
                {
                    if(i == timesList.length -1)
                        out.format("%f", timesList[i]);
                    else
                    out.format("%f,", timesList[i]);
                }
            out.format("\n");
            
            
            
            out.flush();
           out.close();
            
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        }
    }
