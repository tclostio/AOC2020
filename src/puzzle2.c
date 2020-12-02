#include <stdio.h>
#include <stdlib.h>

int* readlines(char* filename);
extern int LENGTH; //this is jank as hell but it's a quick hack to keep a dynamic array length handy. don't @ me

int main(int argc, char **argv)
{
    int* lines;
    int sum;

    lines = readlines("../assets/input1.txt");

    for(int i = 0; i < LENGTH; i++) {
        for (int j = i+1; j < LENGTH; j++) {
            for (int k = j+1; k < LENGTH; k++) {
                sum = lines[i] + lines[j] + lines[k];

                if (sum == 2020) printf("Result:  %d\n", lines[i] * lines[j] * lines[k]);
            }
        }
    }

    free(lines);

    return 0;
}

