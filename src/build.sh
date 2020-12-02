#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <input file>"
else
    echo "[*] Compiling..."
    gcc -c -Wall -ggdb $1 -o ../obj/$1.o
    gcc -c -Wall -ggdb helpers.c -o ../obj/helpers.o

    echo "[*] Linking..."
    gcc -Wall -ggdb ../obj/helpers.o ../obj/$1.o -o ../bin/$1.out

    echo "[*] Done."
fi
