/* 
 * File:   main.c
 * Author: david
 *
 * Created on October 5, 2014, 9:08 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include "main.h"

/*
 * 
 */
int main(int argc, char** argv) {
    
    outfile = fopen("/home/david/Desktop/output.txt", "a"); // write to this file

    //close(outfile);
    int num = 20;
    int table[] = {12, 7, 15, 1, 19, 5, 3, 11, 13, 4, 2, 17, 6, 18, 8, 20, 9, 16, 14, 10};
    
    sleep(1);
    quickSort(table, 0, num -1);
    

    return (EXIT_SUCCESS);
}

void quickSort(int table[], int first, int last)
{
    char* msg;
    msg = (char *) calloc(50, sizeof (char*));
    fprintf(outfile,"inside quicksort\n");
    int i;
    for(i = 0; i < 20; i++)
    {
        fprintf(outfile, "%d ", table[i]);
    }
    fprintf(outfile,"\n");
   if (first < last)
   {
       int pivIndex = partition(table, first, last);
       fprintf(outfile, "piv_index :%d \n", pivIndex);
       fprintf(outfile, "***********ENTERING FIRST QUICKSORT************\n");
       quickSort(table, first, pivIndex -1);
       fprintf(outfile, "***********ENTERING SECOND QUICKSORT************\n");
       quickSort(table, pivIndex, last);
       
   }
}

int partition(int table[], int first, int last)
{
    fprintf(outfile,"in partition\n");
    int pivot = table[first];
    int up = first;
    int down = last;
    do {
        while((up < last) && (pivot >= table[up]))
        {
            up++;
        }
        while(pivot < table[down])
        {
            down--;
        }
        if(up < down)
        {
            swap(table, up, down);
        }
        
    }while(up < down);
    swap(table, first, down);
    return down;
}

void swap(int table[], int first, int last)
{
    fprintf(outfile,"inside swap, %d  %d\n", first, last);
    int temp = table[first];
    table[first] = table[last];
    table[last] = temp;
}
