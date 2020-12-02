/*
 * Helper function to dynamically read lines from a file into an array.
 * I made this because file I/O in C is a bitch.
 */

#include <stdio.h>
#include <stdlib.h>

int getlength(int);
int LENGTH; // i know i know

/*
 * WARNING: You must still free the 'lines' allocated memory after use in your main routine.
 */
int* readlines(char* filename)
{
    FILE *fp;
    int temp;
    int *lines = NULL;
    int count = 1;
    int index;

    fp = fopen(filename,"rb+");

    while( fscanf(fp,"%d",&temp) != EOF )
    {
        if( lines == NULL )
        {

           lines = malloc(sizeof(temp));
           *lines = temp;
        }
        else
        {
           count++;
           lines = realloc(lines,sizeof(lines)*count);
           index = count -1;
           *(lines+index) = temp;
        }

    }

    LENGTH = count; // I KNOW
    return lines;
}

