#!/bin/bash

echo "[*] Compiling..."
gcc -c -ggdb $1 -o ../obj/$1.o
gcc -c -ggdb helpers.c -o ../obj/helpers.o

echo "[*] Linking..."
gcc -Wall -ggdb ../obj/helpers.o ../obj/$1.o -o ../bin/$1.out

echo "[*] Done."
