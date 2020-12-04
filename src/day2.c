#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

char **readCharLines(char*);
bool is_correct_v1(int, int, char, char*);
bool is_correct_v2(int, int, char, char*);
extern int LENGTH; //this is jank as hell but it's a quick hack to keep a dynamic array length handy. don't @ me

int main(int argc, char **argv)
{
    bool result1, result2;
    char x;
    char **lines;
    int min, max, total1, total2;
    char *pwd, *t1, *t2;

    lines = readCharLines("../assets/day2.txt");

    for(int i = 0; i < LENGTH; i++) {
        pwd = strchr(lines[i], ':');
        if (pwd != NULL) pwd += 1;

        t1 = strsep(&lines[i], ":");
        min = atoi(strsep(&t1, "-"));

        t2 = t1;
        max = atoi(strsep(&t2, " "));
        x = t2[0];

        result1 = is_correct_v1(min, max, x, pwd);
        if (result1) total1 += 1;

        result2 = is_correct_v2(min, max, x, pwd);
        if (result2) total2 += 1;
    }

    printf("Total1: %d\nTotal2: %d\n", total1, total2);

    free(lines);
    return 0;
}

bool is_correct_v1(int min, int max, char x, char *pwd)
{
    int i;
    for (i = 0; pwd[i]; pwd[i] == x ? i++ : *pwd++); // spooky pointer magic here

    if (i >= min && i <= max) return true;
    else return false;
}

bool is_correct_v2(int pos1, int pos2, char x, char *pwd)
{
    if (pwd[pos1-1] == x && pwd[pos2-1] == x) return false;
    else if (pwd[pos1-1] == x) return true;
    else if (pwd[pos2-1] == x) return true;
    else return false;
}

