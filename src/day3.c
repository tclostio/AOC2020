#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

char **readCharLines(char*);
void findTrees(char**, int, int);
extern int LENGTH; //this is jank as hell but it's a quick hack to keep a dynamic array length handy. don't @ me

int main(int argc, char **argv)
{
    char **lines;
    lines = readCharLines("../assets/day3.txt");
    findTrees(lines, 1, 1);
    findTrees(lines, 3, 1);
    findTrees(lines, 5, 1);
    findTrees(lines, 7, 1);
    findTrees(lines, 1, 2);

    free(lines);
    return 0;
}

void findTrees(char **lines, int right, int down)
{
    int trees, cursor = 0;
    int width = 31;

    for (int i = 0; i < LENGTH - down; i++) {
        cursor += right;
        if (cursor >= width) cursor = cursor - width;
        if (lines[i+down][cursor] == '#') trees += 1;
    }

    printf("%d\n", trees);
    trees = 0;
}

