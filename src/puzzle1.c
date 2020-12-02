#include <stdio.h>
#include <stdlib.h>

int* readlines(char* filename);
extern int LENGTH; //this is jank as hell but it's a quick hack to keep a dynamic array length handy. don't @ me

int main(int argc, char **argv)
{
    int* lines;

    lines = readlines("../assets/input1.txt");

    printf("Array length: %d\n", LENGTH);

    free(lines);

    return 0;
}

