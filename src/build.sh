#!/bin/bash

echo "[*] Compiling binary..."
gcc -Wall -g $1 -o ../bin/$1.out

echo "[*] Generating obj file..."
gcc -c $1 -o ../obj/$1.o

echo "[*] Done."
