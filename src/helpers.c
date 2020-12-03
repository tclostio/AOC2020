/*
 * Helper function to dynamically read lines from a file into an array.
 * I made this because file I/O in C is a bitch.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getlength(int);
int LENGTH; // i know i know

/*
 * WARNING: You must still free the 'lines' allocated memory after use in your main routine.
 */
int *readlines(char *filename)
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

/*
 * Christ what a mess.
 */
char **readCharLines(char *filename)
{
    FILE *fp = fopen(filename, "rb+");
    char *lineBuf = NULL;
    size_t i, n = 0;
    ssize_t lineLength = 0;
    size_t increment = 10;

    char **lines = malloc(increment * sizeof(char**));

    while ((lineLength = getline(&lineBuf, &n, fp)) != -1) {
        if (i >= increment) {
            increment += increment;

            char **tmp = realloc(lines, increment * sizeof(char**));
            if(!tmp) {
                perror("realloc");
                exit(1);
            }

            lines = tmp;
        }
        // remove \n
        lineBuf[strcspn(lineBuf, "\n")] = 0;

        *(lines + i) = malloc((lineLength + 1) * sizeof(char));

        strcpy(*(lines + i), lineBuf);

        i++;
        LENGTH = i;
    }

    return lines;
}

